package com.jaagro.cbs.biz.service.impl;

import com.jaagro.cbs.api.enums.*;
import com.jaagro.cbs.api.model.*;
import com.jaagro.cbs.api.service.BreedingBrainService;
import com.jaagro.cbs.api.service.BreedingPlanService;
import com.jaagro.cbs.biz.bo.BatchInfoBo;
import com.jaagro.cbs.biz.mapper.BatchInfoMapperExt;
import com.jaagro.cbs.biz.mapper.BreedingBatchParameterMapperExt;
import com.jaagro.cbs.biz.mapper.BreedingPlanMapperExt;
import com.jaagro.cbs.biz.mapper.PurchaseOrderMapperExt;
import com.jaagro.cbs.biz.utils.JsonUtils;
import com.jaagro.cbs.biz.utils.RedisUtil;
import com.jaagro.cbs.biz.utils.SequenceCodeUtils;
import com.jaagro.constant.UserInfo;
import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.command.BatchHystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 养殖大脑--生成订单
 *
 * @author gavin
 * @date :2019/03/05
 */
@Slf4j
@Service
public class BreedingBrainServiceImpl implements BreedingBrainService {

    @Autowired
    private BreedingPlanMapperExt breedingPlanMapper;
    @Autowired
    private PurchaseOrderMapperExt purchaseOrderMapper;
    @Autowired
    private SequenceCodeUtils sequenceCodeUtils;
    @Autowired
    private CurrentUserService currentUserService;
    @Autowired
    private BreedingBatchParameterMapperExt breedingBatchParameterMapper;
    @Autowired
    private RedisUtil redis;
    @Autowired
    private BatchInfoMapperExt batchInfoMapper;
    @Autowired
    private BreedingPlanService breedingPlanService;
    private String PO_FOOD_PREFIX = "OS";
    private String PO_DRUG_PREFIX = "OM";

    /**
     * 根据养殖计划Id生成养殖第一阶段（1->14天）的饲料订单、鸡苗采购订单
     * 养殖计划提交时调用
     *
     * @param planId
     * @return 需要购买饲料重量
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BigDecimal calculatePhaseOneFoodWeightById(Integer planId) {
        BigDecimal PhaseOneWeight = new BigDecimal(0.00);
        UserInfo currentUser = currentUserService.getCurrentUser();
        Integer userId = currentUser.getId();
        try {
            BreedingPlan breedingPlan = breedingPlanMapper.selectByPrimaryKey(planId);
            Assert.notNull(breedingPlan, "养殖计划不存在");
            log.info("O BreedingBrainServiceImpl.getPhaseOneFoodWeightById input planId:{}", planId);
            BigDecimal planChickenQuantity = new BigDecimal(breedingPlan.getPlanChickenQuantity());
            //给定的日龄区间每天每只鸡吃的饲料总和
            BigDecimal totalFeedWeight = getTotalFoodWeightByPlanIdAndDayAgeArea(planId, 1, 14);
            PhaseOneWeight = planChickenQuantity.multiply(totalFeedWeight);
            //计算出来的订单重量如果大于0，则插入生产并插入该订单
            if (PhaseOneWeight.compareTo(new BigDecimal(0)) == 1) {
                //1.删除第一个饲料订单
                PurchaseOrderExample example = new PurchaseOrderExample();
                example.createCriteria().andPlanIdEqualTo(planId)
                        .andProductTypeEqualTo(ProductTypeEnum.FEED.getCode())
                        .andOrderPhaseEqualTo(PurchaseOrderPhaseEnum.PHASE_ONE.getCode());
                purchaseOrderMapper.deleteByExample(example);

                //单位由克化成吨
                PhaseOneWeight = PhaseOneWeight.divide(new BigDecimal(1000 * 1000)).setScale(3, BigDecimal.ROUND_HALF_UP);
                String purchaseNo = sequenceCodeUtils.genSeqCode(PO_FOOD_PREFIX);
                //2.插入第一个饲料订单
                PurchaseOrder purchaseOrder = new PurchaseOrder();
                purchaseOrder.setBatchNo(breedingPlan.getBatchNo())
                        .setPlanId(planId)
                        .setCustomerId(breedingPlan.getCustomerId())
                        .setWeight(PhaseOneWeight)
                        .setPurchaseNo(purchaseNo)
                        .setUnit(UnitEnum.TONS.getCode())
                        .setPurchaseOrderStatus(PurchaseOrderStatusEnum.ORDER_PLACED.getCode())
                        .setCreateUserId(userId)
                        .setPlanDeliveryTime(breedingPlan.getPlanTime())
                        .setCreateTime(new Date())
                        .setEnable(true)
                        .setOrderPhase(PurchaseOrderPhaseEnum.PHASE_ONE.getCode())
                        .setPlanExecuteTime(DateUtils.addDays(breedingPlan.getPlanTime(), -2))
                        .setProductType(ProductTypeEnum.FEED.getCode());

                purchaseOrderMapper.insertSelective(purchaseOrder);
            }
            //3.删除鸡苗订单

            PurchaseOrderExample example = new PurchaseOrderExample();
            example.createCriteria().andPlanIdEqualTo(planId)
                    .andProductTypeEqualTo(ProductTypeEnum.SPROUT.getCode())
                    .andOrderPhaseEqualTo(PurchaseOrderPhaseEnum.PHASE_ONE.getCode());
            purchaseOrderMapper.deleteByExample(example);
            //4.插入鸡苗订单
            PurchaseOrder purchaseOrder = new PurchaseOrder();
            String purchaseNo = sequenceCodeUtils.genSeqCode(PO_DRUG_PREFIX);
            purchaseOrder.setBatchNo(breedingPlan.getBatchNo())
                    .setPlanId(planId)
                    .setCustomerId(breedingPlan.getCustomerId())
                    .setQuantity(breedingPlan.getPlanChickenQuantity())
                    .setPurchaseNo(purchaseNo)
                    .setUnit(UnitEnum.AN.getCode())
                    .setPurchaseOrderStatus(PurchaseOrderStatusEnum.ORDER_PLACED.getCode())
                    .setCreateUserId(userId)
                    .setPlanDeliveryTime(breedingPlan.getPlanTime())
                    .setCreateTime(new Date())
                    .setEnable(true)
                    .setOrderPhase(PurchaseOrderPhaseEnum.PHASE_ONE.getCode())
                    .setPlanExecuteTime(DateUtils.addDays(breedingPlan.getPlanTime(), -2))
                    .setProductType(ProductTypeEnum.SPROUT.getCode());

            purchaseOrderMapper.insertSelective(purchaseOrder);
        } catch (Exception ex) {
            log.error("R BreedingBrainServiceImpl.getPhaseOneFoodWeightById  error:" + ex);
        }
        return PhaseOneWeight;
    }

    /**
     * 根据养殖计划Id生成养殖第二阶段（15->19天）的饲料订单
     *
     * @param planId
     * @return 返回第二阶段需要购买饲料重量
     */
    @Override
    public BigDecimal calculatePhaseTwoFoodWeightById(Integer planId) {
        BigDecimal PhaseTwoWeight = new BigDecimal(0.00);
        UserInfo currentUser = currentUserService.getCurrentUser();
        Integer userId = currentUser.getId();
        try {
            BreedingPlan breedingPlan = breedingPlanMapper.selectByPrimaryKey(planId);
            Assert.notNull(breedingPlan, "养殖计划不存在");
            log.info("O BreedingBrainServiceImpl.calculatePhaseTwoFoodWeightById input planId:{}", planId);
            long currentDayAgeLong = breedingPlanService.getDayAge(planId);
            int currentDayAge = (int)currentDayAgeLong;
            if(currentDayAge==12){


            }
            BatchInfoExample example = new BatchInfoExample();
            example.createCriteria().andPlanIdEqualTo(planId).andDayAgeEqualTo(currentDayAge).andEnableEqualTo(true);
            List<BatchInfo> batchInfoDos =  batchInfoMapper.selectByExample(example);
            /*
            Integer ageDay12LivingQuantity =
            BigDecimal planChickenQuantity = new BigDecimal(breedingPlan.getPlanChickenQuantity());
            //给定的日龄区间每天每只鸡吃的饲料总和
            BigDecimal totalFeedWeight = getTotalFoodWeightPerDayArea(planId, 1, 14);
            PhaseTwoWeight = planChickenQuantity.multiply(totalFeedWeight);
            //计算出来的订单重量如果大于0，则插入生产并插入该订单
            if (PhaseOneWeight.compareTo(new BigDecimal(0)) == 1) {
                //1.删除第一个饲料订单
                PurchaseOrderExample example = new PurchaseOrderExample();
                example.createCriteria().andPlanIdEqualTo(planId)
                        .andProductTypeEqualTo(ProductTypeEnum.FEED.getCode())
                        .andOrderPhaseEqualTo(PurchaseOrderPhaseEnum.PHASE_ONE.getCode());
                purchaseOrderMapper.deleteByExample(example);

                //单位由克化成吨
                PhaseOneWeight = PhaseOneWeight.divide(new BigDecimal(1000 * 1000)).setScale(3, BigDecimal.ROUND_HALF_UP);
                String purchaseNo = sequenceCodeUtils.genSeqCode(PO_FOOD_PREFIX);
                //2.插入第一个饲料订单
                PurchaseOrder purchaseOrder = new PurchaseOrder();
                purchaseOrder.setBatchNo(breedingPlan.getBatchNo())
                        .setPlanId(planId)
                        .setCustomerId(breedingPlan.getCustomerId())
                        .setWeight(PhaseOneWeight)
                        .setPurchaseNo(purchaseNo)
                        .setUnit(UnitEnum.TONS.getCode())
                        .setPurchaseOrderStatus(PurchaseOrderStatusEnum.ORDER_PLACED.getCode())
                        .setCreateUserId(userId)
                        .setPlanDeliveryTime(breedingPlan.getPlanTime())
                        .setCreateTime(new Date())
                        .setEnable(true)
                        .setOrderPhase(PurchaseOrderPhaseEnum.PHASE_ONE.getCode())
                        .setPlanExecuteTime(DateUtils.addDays(breedingPlan.getPlanTime(), -2))
                        .setProductType(ProductTypeEnum.FEED.getCode());

                purchaseOrderMapper.insertSelective(purchaseOrder);
            }
            //3.删除鸡苗订单

            PurchaseOrderExample example = new PurchaseOrderExample();
            example.createCriteria().andPlanIdEqualTo(planId)
                    .andProductTypeEqualTo(ProductTypeEnum.SPROUT.getCode())
                    .andOrderPhaseEqualTo(PurchaseOrderPhaseEnum.PHASE_ONE.getCode());
            purchaseOrderMapper.deleteByExample(example);
            //4.插入鸡苗订单
            PurchaseOrder purchaseOrder = new PurchaseOrder();
            String purchaseNo = sequenceCodeUtils.genSeqCode(PO_DRUG_PREFIX);
            purchaseOrder.setBatchNo(breedingPlan.getBatchNo())
                    .setPlanId(planId)
                    .setCustomerId(breedingPlan.getCustomerId())
                    .setQuantity(breedingPlan.getPlanChickenQuantity())
                    .setPurchaseNo(purchaseNo)
                    .setUnit(UnitEnum.AN.getCode())
                    .setPurchaseOrderStatus(PurchaseOrderStatusEnum.ORDER_PLACED.getCode())
                    .setCreateUserId(userId)
                    .setPlanDeliveryTime(breedingPlan.getPlanTime())
                    .setCreateTime(new Date())
                    .setEnable(true)
                    .setOrderPhase(PurchaseOrderPhaseEnum.PHASE_ONE.getCode())
                    .setPlanExecuteTime(DateUtils.addDays(breedingPlan.getPlanTime(), -2))
                    .setProductType(ProductTypeEnum.SPROUT.getCode());

            purchaseOrderMapper.insertSelective(purchaseOrder);
             */
        } catch (Exception ex) {
            log.error("R BreedingBrainServiceImpl.getPhaseOneFoodWeightById  error:" + ex);
        }

        return PhaseTwoWeight;
    }

    /**
     * 根据养殖计划Id生成养殖第三阶段（20->28天）的饲料订单
     *
     * @param planId
     * @return 返回第三阶段需要购买饲料重量
     */
    @Override
    public BigDecimal calculatePhaseThreeFoodWeightById(Integer planId) {
        return null;
    }

    /**
     * 根据养殖计划Id生成养殖第四阶段（29->计划养殖天数）的饲料订单
     *
     * @param planId
     * @return 返回第思念阶段需要购买饲料重量
     */
    @Override
    public BigDecimal calculatePhaseFourFoodWeightById(Integer planId) {
        return null;
    }


    /**
     * @param planId
     * @param startDayAge
     * @param endDayAge
     * @return 返回某个养殖计划在给定的日龄区间每天每只鸡吃的饲料总和，出来的单位是 克
     */
    private BigDecimal getTotalFoodWeightByPlanIdAndDayAgeArea(Integer planId, Integer startDayAge, Integer endDayAge) {
        List<BreedingBatchParameter> breedingBatchParameterDos;
        //从redis里去取养殖计划的喂养参数
        String key = planId + BreedingStandardParamEnum.FEEDING_KG.getType();
        String BatchParameterListJson = redis.get(key);
        if (StringUtils.isEmpty(BatchParameterListJson)) {
            //养殖计划所用的参数
            BreedingBatchParameterExample batchParameterExample = new BreedingBatchParameterExample();
            batchParameterExample.createCriteria().andPlanIdEqualTo(planId)
                    .andParamTypeEqualTo(BreedingStandardParamEnum.FEEDING_KG.getCode())
                    .andEnableEqualTo(true);
            breedingBatchParameterDos = breedingBatchParameterMapper.selectByExample(batchParameterExample);
            if (!CollectionUtils.isEmpty(breedingBatchParameterDos)) {
                redis.set(key, JsonUtils.objectToJson(breedingBatchParameterDos), 1800);
            }
        } else {
            breedingBatchParameterDos = JsonUtils.jsonToList(BatchParameterListJson, BreedingBatchParameter.class);
        }

        BigDecimal totalFeedWeight = new BigDecimal(0);
        for (int i = startDayAge; i <= endDayAge; i++) {
            Integer temp = i;
            List<BreedingBatchParameter> parameters = breedingBatchParameterDos.stream().filter(c -> temp.equals(c.getDayAge())).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(parameters)) {
                BreedingBatchParameter parameter = parameters.get(0);
                totalFeedWeight = totalFeedWeight.add(new BigDecimal(parameter.getParamValue())).setScale(2, BigDecimal.ROUND_HALF_UP);
            }
        }

        return totalFeedWeight;
    }

    /**
     * 获取养殖计划养殖过程中某个日龄鸡的死淘数量
     * @param planId
     * @param dayAge
     * @return
     */
    private BatchInfoBo getDeadAmountByPlanIdAndDayAge(Integer planId, Integer dayAge){
        BatchInfoBo batchInfoBo = new BatchInfoBo();
        //从redis里去取养殖计划所有日龄的死淘、存栏记录
        String key = planId +BreedingStandardParamEnum.DEAD_AMOUNT.getType();
        String BatchInfoListJson = redis.get(key);
        List<BatchInfo> batchInfoDos;
        if (StringUtils.isEmpty(BatchInfoListJson)) {
            //养殖计划过程中的死淘记录
            BatchInfoExample example = new BatchInfoExample();
            example.createCriteria().andPlanIdEqualTo(planId).andEnableEqualTo(true);
            batchInfoDos =  batchInfoMapper.selectByExample(example);
            if (!CollectionUtils.isEmpty(batchInfoDos)) {
                redis.set(key, JsonUtils.objectToJson(batchInfoDos), 1800);
            }
        } else {
            batchInfoDos = JsonUtils.jsonToList(BatchInfoListJson, BatchInfo.class);
        }

        List<BatchInfo> batchInfoList = batchInfoDos.stream().filter(c->c.getDayAge().equals(dayAge)).collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(batchInfoList))
        {
            BatchInfo batchInfo = batchInfoList.get(0);
            batchInfoBo.setDeadAmount(batchInfo.getDeadAmount());
            batchInfoBo.setCurrentAmount(batchInfo.getCurrentAmount());
        }
        return batchInfoBo;
    }

    public static void main(String[] args) {
        Integer startDayAge = 6;
        Integer endDayAge = 14;
        for (int i = startDayAge; i <= endDayAge; i++) {

            System.out.println(i);

        }
    }

}
