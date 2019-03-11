package com.jaagro.cbs.biz.service.impl;

import com.jaagro.cbs.api.enums.*;
import com.jaagro.cbs.api.model.*;
import com.jaagro.cbs.api.service.BreedingBrainService;
import com.jaagro.cbs.api.service.BreedingPlanService;
import com.jaagro.cbs.biz.bo.BatchInfoBo;
import com.jaagro.cbs.biz.bo.PurchaseOrderBo;
import com.jaagro.cbs.biz.mapper.*;
import com.jaagro.cbs.biz.utils.DateUtil;
import com.jaagro.cbs.biz.utils.JsonUtils;
import com.jaagro.cbs.biz.utils.RedisUtil;
import com.jaagro.cbs.biz.utils.SequenceCodeUtils;
import com.jaagro.constant.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 养殖大脑--生成采购订单
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
    private ProductMapperExt productMapper;
    @Autowired
    private BreedingPlanService breedingPlanService;
    @Autowired
    private MaterialConfigMapperExt materialConfigMapper;
    private String PO_FOOD_PREFIX = "OS";
    private String PO_SPROUT_PREFIX = "OM";
    private String PO_DRUG_PREFIX = "OY";

    /**
     * 根据养殖计划Id生成养殖第一阶段（1->14天）的相关订单：第一阶段的饲料订单、第一阶段的鸡苗订单
     *
     * @param planId
     * @return 返回第一阶段2采购订单：第一阶段的饲料订单、第一阶段的鸡苗订单
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<PurchaseOrder> calculatePhaseOneFoodWeightById(Integer planId) {
        List<PurchaseOrder> phaseOneOrders = new ArrayList<>();
        try {
            BreedingPlan breedingPlan = breedingPlanMapper.selectByPrimaryKey(planId);
            Assert.notNull(breedingPlan, "养殖计划不存在");
            log.info("O BreedingBrainServiceImpl.getPhaseOneFoodWeightById input planId:{}", planId);
            //第一阶段鸡苗需要采购的数量
            BigDecimal planChickenQuantity = new BigDecimal(breedingPlan.getPlanChickenQuantity());
            //饲料采购第一阶段区间配置
            MaterialConfig feedConfig = getMaterialConfig(PurchaseOrderPhaseEnum.PHASE_ONE.getCode(), ProductTypeEnum.FEED.getCode());
            if (null != feedConfig) {
                Integer dayAgeStart = feedConfig.getDayAgeStart();
                Integer dayAgeEnd = feedConfig.getDayAgeEnd();
                //1-14日龄区间每天每只鸡吃的饲料（小料510）总和
                BigDecimal totalFeedWeight114 = getSumFoodWeightByPlanIdAndDayAgeArea(planId, dayAgeStart, dayAgeEnd);
                //第一阶段小料510需要采购的数量
                BigDecimal PhaseOneWeight = planChickenQuantity.multiply(totalFeedWeight114);
                //计算出来的订单重量如果大于0，则插入生产并插入该订单
                if (PhaseOneWeight.compareTo(BigDecimal.ZERO) == 1) {
                    //单位由克化成吨
                    PhaseOneWeight = PhaseOneWeight.divide(new BigDecimal(1000 * 1000)).setScale(3, BigDecimal.ROUND_HALF_UP);
                    //插入第一个饲料订单（小料510）
                    Integer feedProductId = feedConfig.getProductId();
                    PurchaseOrder purchaseOrder = savePurchaseOrder(breedingPlan, PurchaseOrderPhaseEnum.PHASE_ONE.getCode(), PhaseOneWeight, feedProductId);
                    phaseOneOrders.add(purchaseOrder);
                }
            }
            //种苗采购第一阶段区间配置
            MaterialConfig sproutConfig = getMaterialConfig(PurchaseOrderPhaseEnum.PHASE_ONE.getCode(), ProductTypeEnum.SPROUT.getCode());
            if (null != sproutConfig) {
                if (planChickenQuantity.compareTo(BigDecimal.ZERO) == 1) {
                    //插入鸡苗订单
                    int productId = sproutConfig.getProductId();
                    PurchaseOrder purchaseOrder = savePurchaseOrder(breedingPlan, PurchaseOrderPhaseEnum.PHASE_ONE.getCode(), planChickenQuantity, productId);
                    phaseOneOrders.add(purchaseOrder);
                }
            }

        } catch (Exception ex) {
            log.error("R BreedingBrainServiceImpl.getPhaseOneFoodWeightById  error:" + ex);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return phaseOneOrders;
    }

    /**
     * 根据养殖计划Id生成养殖第二阶段（15->19天）的饲料订单
     *
     * @param planId
     * @return 返回第二阶段需要采购的2个订单：15->19天的饲料订单
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<PurchaseOrder> calculatePhaseTwoFoodWeightById(Integer planId) {
        List<PurchaseOrder> phaseTwoOrders = new ArrayList<>();
        try {
            BreedingPlan breedingPlan = breedingPlanMapper.selectByPrimaryKey(planId);
            Assert.notNull(breedingPlan, "养殖计划不存在");
            log.info("O BreedingBrainServiceImpl.calculatePhaseTwoFoodWeightById input planId:{}", planId);
            //饲料采购第二阶段区间配置
            MaterialConfig feedConfig = getMaterialConfig(PurchaseOrderPhaseEnum.PHASE_TWO.getCode(), ProductTypeEnum.FEED.getCode());
            if (null != feedConfig) {
                Integer dayAgeStart = feedConfig.getDayAgeStart();
                Integer dayAgeEnd = feedConfig.getDayAgeEnd();
                Integer feedProductId = feedConfig.getProductId();
                long currentDayAgeLong = breedingPlanService.getDayAge(planId);
                //提前3天下单
                int ageDay12 = dayAgeStart - 3;
                if (currentDayAgeLong == ageDay12) {
                    //获取第12日龄存栏数
                    BatchInfoBo ageDay12BatchInfoBo = getDeadAmountByPlanIdAndDayAge(planId, ageDay12);
                    Integer ageDay12LivingQuantity = ageDay12BatchInfoBo.getCurrentAmount();
                    //15->19日龄区间每天每只鸡吃的饲料总和
                    BigDecimal totalFeedWeight1519 = getSumFoodWeightByPlanIdAndDayAgeArea(planId, dayAgeStart, dayAgeEnd);
                    BigDecimal a = totalFeedWeight1519.multiply(new BigDecimal(ageDay12LivingQuantity));
                    //计算 1->12日龄区间剩余饲料
                    BigDecimal b = getSumLeftFoodWeightByPlanIdAndDayAgeArea(planId, 1, ageDay12);
                    //第二阶段小料510需要采购的数量
                    BigDecimal PhaseTwo1Weight = a.subtract(b);
                    if (PhaseTwo1Weight.compareTo(BigDecimal.ZERO) == 1) {
                        //克转换成吨
                        PhaseTwo1Weight = PhaseTwo1Weight.divide(new BigDecimal(1000000)).setScale(3, BigDecimal.ROUND_HALF_UP);
                        //插入第二次饲料订单(15->19日龄小料510订单)
                        PurchaseOrder purchaseOrder = savePurchaseOrder(breedingPlan, PurchaseOrderPhaseEnum.PHASE_TWO.getCode(), PhaseTwo1Weight, feedProductId);
                        phaseTwoOrders.add(purchaseOrder);
                    }
                }
            }
        } catch (Exception ex) {
            log.error("R BreedingBrainServiceImpl.calculatePhaseTwoFoodWeightById  error:" + ex);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return phaseTwoOrders;
    }

    /**
     * 根据养殖计划Id生成养殖第三阶段20->28天的饲料订单
     *
     * @param planId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<PurchaseOrder> calculatePhaseThreeFoodWeightById(Integer planId) {
        List<PurchaseOrder> phaseTwoOrders = new ArrayList<>();
        try {
            BreedingPlan breedingPlan = breedingPlanMapper.selectByPrimaryKey(planId);
            Assert.notNull(breedingPlan, "养殖计划不存在");
            log.info("O BreedingBrainServiceImpl.calculatePhaseTwoFoodWeightById input planId:{}", planId);
            //饲料采购第二阶段区间配置
            MaterialConfig feedConfig = getMaterialConfig(PurchaseOrderPhaseEnum.PHASE_THREE.getCode(), ProductTypeEnum.FEED.getCode());
            if (null != feedConfig) {
                Integer dayAgeStart = feedConfig.getDayAgeStart();
                Integer dayAgeEnd = feedConfig.getDayAgeEnd();
                Integer feedProductId = feedConfig.getProductId();
                long currentDayAgeLong = breedingPlanService.getDayAge(planId);
                //提前3天下单
                int ageDay12 = 12;
                if (currentDayAgeLong == ageDay12) {
                    //获取第12日龄存栏数
                    BatchInfoBo ageDay12BatchInfoBo = getDeadAmountByPlanIdAndDayAge(planId, ageDay12);
                    Integer ageDay12LivingQuantity = ageDay12BatchInfoBo.getCurrentAmount();

                    //20->28日龄区间每天每只鸡吃的饲料（大料511）总和
                    BigDecimal totalFeedWeight2028 = getSumFoodWeightByPlanIdAndDayAgeArea(planId, dayAgeStart, dayAgeEnd);
                    //第二阶段大料511需要采购的数量
                    BigDecimal PhaseTwo2Weight = totalFeedWeight2028.multiply(new BigDecimal(ageDay12LivingQuantity));
                    if (PhaseTwo2Weight.compareTo(BigDecimal.ZERO) == 1) {
                        //克转换成吨
                        PhaseTwo2Weight = PhaseTwo2Weight.divide(new BigDecimal(1000000)).setScale(3, BigDecimal.ROUND_HALF_UP);
                        //插入第二次饲料订单（20->28日龄大料511饲料订单）
                        PurchaseOrder purchaseOrder = savePurchaseOrder(breedingPlan, PurchaseOrderPhaseEnum.PHASE_THREE.getCode(), PhaseTwo2Weight, feedProductId);
                        phaseTwoOrders.add(purchaseOrder);
                    }
                }
            }
        } catch (Exception ex) {
            log.error("R BreedingBrainServiceImpl.calculatePhaseTwoFoodWeightById  error:" + ex);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return phaseTwoOrders;
    }


    /**
     * 根据养殖计划Id生成29->计划养殖天数的饲料订单
     * 饲料采购 - 第三次饲料配送
     *
     * @param planId
     * @return 返回第三次饲料配送需要采购的饲料订单
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<PurchaseOrder> calculatePhaseFourFoodWeightById(Integer planId) {
        List<PurchaseOrder> phaseTwoOrders = new ArrayList<>();
        try {
            BreedingPlan breedingPlan = breedingPlanMapper.selectByPrimaryKey(planId);
            Assert.notNull(breedingPlan, "养殖计划不存在");
            log.info("O BreedingBrainServiceImpl.calculatePhaseThreeFoodWeightById input planId:{}", planId);

            MaterialConfig feedConfig = getMaterialConfig(PurchaseOrderPhaseEnum.PHASE_FOUR.getCode(), ProductTypeEnum.FEED.getCode());
            if (null != feedConfig) {
                Integer dayAgeStart = feedConfig.getDayAgeStart();
                Integer feedProductId = feedConfig.getProductId();
                int breedingDays = breedingPlan.getBreedingDays();
                long currentDayAgeLong = breedingPlanService.getDayAge(planId);
                //提前3天下单
                int ageDay26 = dayAgeStart - 3;
                if (currentDayAgeLong == ageDay26) {
                    //获取第12日龄存栏数
                    BatchInfoBo ageDay12BatchInfoBo = getDeadAmountByPlanIdAndDayAge(planId, 12);
                    Integer ageDay12LivingQuantity = ageDay12BatchInfoBo.getCurrentAmount();
                    //获取第19日龄存栏数
                    BatchInfoBo ageDay19BatchInfoBo = getDeadAmountByPlanIdAndDayAge(planId, 19);
                    Integer ageDay19LivingQuantity = ageDay19BatchInfoBo.getCurrentAmount();
                    //获取提前3天即第26日龄存栏数
                    BatchInfoBo ageDay26BatchInfoBo = getDeadAmountByPlanIdAndDayAge(planId, ageDay26);
                    Integer ageDay26LivingQuantity = ageDay26BatchInfoBo.getCurrentAmount();
                    //20->28日龄区间每天每只鸡吃的饲料总和
                    BigDecimal totalFeedWeight2028 = getSumFoodWeightByPlanIdAndDayAgeArea(planId, 20, 28);
                    //29->breedingDays日龄区间每天每只鸡吃的饲料总和
                    BigDecimal totalFeedWeight29BreedingDays = getSumFoodWeightByPlanIdAndDayAgeArea(planId, dayAgeStart, breedingDays);

                    //26日龄存栏数乘以sum(29->breedingDays)
                    BigDecimal a = totalFeedWeight29BreedingDays.multiply(new BigDecimal(ageDay26LivingQuantity));
                    //12->19日龄期间死掉的数量
                    Integer diffLivingQuantity1219 = ageDay12LivingQuantity - ageDay19LivingQuantity;
                    //12->19日龄期间剩余的饲料
                    BigDecimal b = totalFeedWeight2028.multiply(new BigDecimal(diffLivingQuantity1219));
                    //计算 20->26日龄区间剩余饲料
                    BigDecimal c = getSumLeftFoodWeightByPlanIdAndDayAgeArea(planId, 20, 26);
                    //第三次大料511需要采购的数量
                    BigDecimal PhaseThreeWeight = a.subtract(b).subtract(c);

                    if (PhaseThreeWeight.compareTo(BigDecimal.ZERO) == 1) {
                        //克转换成吨
                        PhaseThreeWeight = PhaseThreeWeight.divide(new BigDecimal(1000000)).setScale(3, BigDecimal.ROUND_HALF_UP);
                        //插入第三次饲料订单（29->->计划养殖天数日龄大料511饲料订单）
                        PurchaseOrder purchaseOrder = savePurchaseOrder(breedingPlan, PurchaseOrderPhaseEnum.PHASE_FOUR.getCode(), PhaseThreeWeight, feedProductId);
                        phaseTwoOrders.add(purchaseOrder);
                    }
                }
            }
        } catch (Exception ex) {
            log.error("R BreedingBrainServiceImpl.calculatePhaseThreeFoodWeightById  error:" + ex);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return phaseTwoOrders;
    }

    /**
     * 计算养殖计划某个日龄区间剩余的饲料总数 单位 克
     *
     * @param planId
     * @param startDayAge
     * @param endDayAge
     * @return
     */
    private BigDecimal getSumLeftFoodWeightByPlanIdAndDayAgeArea(Integer planId, Integer startDayAge, Integer endDayAge) {
        BigDecimal a = BigDecimal.ZERO;
        int toDayAge = endDayAge + 2;
        for (int i = startDayAge; i <= endDayAge; i++) {
            //获取某个日龄的死淘数
            BatchInfoBo batchInfoBo = getDeadAmountByPlanIdAndDayAge(planId, i);
            Integer deadAmount = batchInfoBo.getDeadAmount();
            int nextAgeDay = i + 1;
            BigDecimal areaTotalFeedWeight = getSumFoodWeightByPlanIdAndDayAgeArea(planId, nextAgeDay, toDayAge);
            //日龄死淘数乘以接下来的日龄区间每天吃的饲料总和
            BigDecimal b = areaTotalFeedWeight.multiply(new BigDecimal(deadAmount));
            a = a.add(b);
        }
        return a;
    }

    /**
     * 先删除订单，再插入订单
     *
     * @param plan
     * @param phase
     * @param orderQuantity
     * @param productId
     * @return
     */
    private PurchaseOrder savePurchaseOrder(BreedingPlan plan, Integer phase, BigDecimal orderQuantity, Integer productId) {
        Product product = productMapper.selectByPrimaryKey(productId);
        UserInfo currentUser = currentUserService.getCurrentUser();
        Integer userId = currentUser.getId();
        if (null != product) {
            PurchaseOrder purchaseOrder = new PurchaseOrder();
            purchaseOrder.setBatchNo(plan.getBatchNo())
                    .setCustomerId(plan.getCustomerId())
                    .setPlanId(plan.getId())
                    .setOrderPhase(phase)
                    .setPurchaseOrderStatus(PurchaseOrderStatusEnum.ORDER_PLACED.getCode())
                    .setCreateUserId(userId)
                    .setEnable(true)
                    .setCreateTime(new Date());
            Integer productType = product.getProductType();
            purchaseOrder.setProductType(productType);
            if (productType == ProductTypeEnum.SPROUT.getCode()) {
                //鸡苗订单单位
                purchaseOrder.setUnit(OrderUnitEnum.AN.getCode());
                purchaseOrder.setQuantity(orderQuantity.intValue());
                if (phase == PurchaseOrderPhaseEnum.PHASE_ONE.getCode()) {
                    String purchaseNo = sequenceCodeUtils.genSeqCode(PO_SPROUT_PREFIX) + "-01";
                    purchaseOrder.setPurchaseNo(purchaseNo);
                    purchaseOrder.setPlanExecuteTime(DateUtils.addDays(plan.getPlanTime(), -2));
                    purchaseOrder.setPlanDeliveryTime(plan.getPlanTime());
                    purchaseOrder.setPurchaseName("鸡苗采购 - 全部鸡苗配送");
                    purchaseOrder.setNotes("鸡苗采购 - 全部鸡苗配送");
                }
            } else if (productType == ProductTypeEnum.FEED.getCode()) {
                //饲料订单单位
                purchaseOrder.setUnit(OrderUnitEnum.TONS.getCode());
                purchaseOrder.setWeight(orderQuantity);
                if (phase == PurchaseOrderPhaseEnum.PHASE_ONE.getCode()) {
                    String purchaseNo = sequenceCodeUtils.genSeqCode(PO_FOOD_PREFIX) + "-01";
                    purchaseOrder.setPurchaseNo(purchaseNo);
                    purchaseOrder.setPlanExecuteTime(DateUtils.addDays(plan.getPlanTime(), -2));
                    purchaseOrder.setPlanDeliveryTime(plan.getPlanTime());
                    purchaseOrder.setPurchaseName("饲料采购 - 第一次饲料配送");
                    purchaseOrder.setNotes("饲料采购 - 第一次饲料配送");
                } else if (phase == PurchaseOrderPhaseEnum.PHASE_TWO.getCode()) {
                    String purchaseNo = sequenceCodeUtils.genSeqCode(PO_FOOD_PREFIX) + "-02";
                    purchaseOrder.setPurchaseNo(purchaseNo);
                    purchaseOrder.setPlanExecuteTime(DateUtils.addDays(new Date(), 1));
                    purchaseOrder.setPlanDeliveryTime(DateUtils.addDays(new Date(), 3));
                    purchaseOrder.setPurchaseName("饲料采购 - 第二次饲料配送");
                    purchaseOrder.setNotes("饲料采购 - 第二次饲料配送");
                } else if (phase == PurchaseOrderPhaseEnum.PHASE_THREE.getCode()) {
                    String purchaseNo = sequenceCodeUtils.genSeqCode(PO_FOOD_PREFIX) + "-03";
                    purchaseOrder.setPurchaseNo(purchaseNo);
                    purchaseOrder.setPlanExecuteTime(DateUtils.addDays(new Date(), 1));
                    purchaseOrder.setPlanDeliveryTime(DateUtils.addDays(new Date(), 3));
                    purchaseOrder.setPurchaseName("饲料采购 - 第二次饲料配送");
                    purchaseOrder.setNotes("饲料采购 - 第二次饲料配送");
                } else if (phase == PurchaseOrderPhaseEnum.PHASE_FOUR.getCode()) {
                    String purchaseNo = sequenceCodeUtils.genSeqCode(PO_FOOD_PREFIX) + "-04";
                    purchaseOrder.setPurchaseNo(purchaseNo);
                    purchaseOrder.setPlanExecuteTime(DateUtils.addDays(new Date(), 1));
                    purchaseOrder.setPlanDeliveryTime(DateUtils.addDays(new Date(), 3));
                    purchaseOrder.setPurchaseName("饲料采购 - 第三次饲料配送");
                    purchaseOrder.setNotes("饲料采购 - 第三次饲料配送");
                }
            } else {
                //药品订单单位
            }
            //1.删除
            PurchaseOrderBo orderBo = new PurchaseOrderBo();
            orderBo.setPlanId(plan.getId())
                    .setOrderPhase(phase)
                    .setProductType(productType)
                    .setProductId(productId);
            this.deleteByCriteria(orderBo);

            //2.插入
            purchaseOrderMapper.insertSelective(purchaseOrder);
            return purchaseOrder;
        }
        return null;
    }

    /**
     * @param planId
     * @param startDayAge
     * @param endDayAge
     * @return 返回某个养殖计划在给定的日龄区间每天每只鸡吃的饲料总和，出来的单位是 克
     */
    private BigDecimal getSumFoodWeightByPlanIdAndDayAgeArea(Integer planId, Integer startDayAge, Integer endDayAge) {
        List<BreedingBatchParameter> breedingBatchParameterDos;
        //从redis里去取养殖计划的喂养参数
        String key = planId + BreedingStandardParamEnum.FEEDING_WEIGHT.getType() + DateUtil.getStringDateShort();
        String BatchParameterListJson = redis.get(key);
        if (StringUtils.isEmpty(BatchParameterListJson)) {
            //养殖计划所用的参数
            BreedingBatchParameterExample batchParameterExample = new BreedingBatchParameterExample();
            batchParameterExample.createCriteria().andPlanIdEqualTo(planId)
                    .andParamTypeEqualTo(BreedingStandardParamEnum.FEEDING_WEIGHT.getCode())
                    .andEnableEqualTo(true);
            breedingBatchParameterDos = breedingBatchParameterMapper.selectByExample(batchParameterExample);
            if (!CollectionUtils.isEmpty(breedingBatchParameterDos)) {
                redis.set(key, JsonUtils.objectToJson(breedingBatchParameterDos), 1800);
            }
        } else {
            breedingBatchParameterDos = JsonUtils.jsonToList(BatchParameterListJson, BreedingBatchParameter.class);
        }

        BigDecimal totalFeedWeight = BigDecimal.ZERO;
        for (int i = startDayAge; i <= endDayAge; i++) {
            Integer temp = i;
            List<BreedingBatchParameter> parameters = breedingBatchParameterDos.stream().filter(c -> temp.equals(c.getDayAge())).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(parameters)) {
                BreedingBatchParameter parameter = parameters.get(0);
                totalFeedWeight = totalFeedWeight.add(new BigDecimal(parameter.getParamValue()));
            }
        }

        return totalFeedWeight.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 获取养殖计划养殖过程中某个日龄鸡的死淘数量
     *
     * @param planId
     * @param dayAge
     * @return
     */
    private BatchInfoBo getDeadAmountByPlanIdAndDayAge(Integer planId, Integer dayAge) {
        BatchInfoBo batchInfoBo = new BatchInfoBo();
        //从redis里去取养殖计划所有日龄的死淘、存栏记录
        String key = planId + BreedingStandardParamEnum.DIE.getType();
        String BatchInfoListJson = redis.get(key);
        List<BatchInfo> batchInfoDos;
        if (StringUtils.isEmpty(BatchInfoListJson)) {
            //养殖计划过程中的死淘记录
            BatchInfoExample example = new BatchInfoExample();
            example.createCriteria().andPlanIdEqualTo(planId).andEnableEqualTo(true);
            batchInfoDos = batchInfoMapper.selectByExample(example);
            if (!CollectionUtils.isEmpty(batchInfoDos)) {
                redis.set(key, JsonUtils.objectToJson(batchInfoDos), 1800);
            }
        } else {
            batchInfoDos = JsonUtils.jsonToList(BatchInfoListJson, BatchInfo.class);
        }

        List<BatchInfo> batchInfoList = batchInfoDos.stream().filter(c -> c.getDayAge().equals(dayAge)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(batchInfoList)) {
            BatchInfo batchInfo = batchInfoList.get(0);
            batchInfoBo.setDeadAmount(batchInfo.getDeadAmount());
            batchInfoBo.setCurrentAmount(batchInfo.getCurrentAmount());
        }
        return batchInfoBo;
    }

    private MaterialConfig getMaterialConfig(Integer phase, Integer productType) {
        //从redis里去取饲料所有日龄区间（阶段）和对应的产品
        String key = phase + productType + DateUtil.getStringDateShort();
        String materialConfigListJson = redis.get(key);
        List<MaterialConfig> materialConfigDos;
        if (StringUtils.isEmpty(materialConfigListJson)) {
            //查询所有日龄区间（阶段）和对应的产品
            MaterialConfigExample example = new MaterialConfigExample();
            example.createCriteria().andEnableEqualTo(true);
            materialConfigDos = materialConfigMapper.selectByExample(example);
            if (!CollectionUtils.isEmpty(materialConfigDos)) {
                redis.set(key, JsonUtils.objectToJson(materialConfigDos), 1800);
            }
        } else {
            materialConfigDos = JsonUtils.jsonToList(materialConfigListJson, MaterialConfig.class);
        }

        List<MaterialConfig> configs = materialConfigDos.stream().filter(c -> c.getOrderPhase().equals(phase) && c.getProductType().equals(productType)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(configs)) {
            return configs.get(0);
        }
        return null;
    }

    /**
     * 删除订单
     *
     * @param orderBo
     */
    private void deleteByCriteria(PurchaseOrderBo orderBo) {
        List<Integer> orderIds = purchaseOrderMapper.queryPurchaseOrderByCondition(orderBo);
        if (!CollectionUtils.isEmpty(orderIds)) {
            for (Integer orderId : orderIds) {
                purchaseOrderMapper.deleteByPrimaryKey(orderId);
            }

        }
    }
}