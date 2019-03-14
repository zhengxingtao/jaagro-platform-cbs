package com.jaagro.cbs.biz.service.impl;

import com.jaagro.cbs.api.enums.BreedingStandardParamEnum;
import com.jaagro.cbs.api.enums.ProductTypeEnum;
import com.jaagro.cbs.api.enums.PurchaseOrderPhaseEnum;
import com.jaagro.cbs.api.enums.PurchaseOrderStatusEnum;
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
import java.util.*;
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
    private PurchaseOrderItemsMapperExt purchaseOrderItemsMapper;
    @Autowired
    private SequenceCodeUtils sequenceCodeUtils;
    @Autowired
    private CurrentUserService currentUserService;
    @Autowired
    private BreedingBatchParameterMapperExt breedingBatchParameterMapper;
    @Autowired
    private BreedingBatchDrugMapperExt breedingBatchDrugMapper;
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
    private final String PO_FOOD_PREFIX = "OS";
    private final String PO_SPROUT_PREFIX = "OM";
    private final String PO_DRUG_PREFIX = "OY";

    /**
     * 根据养殖计划Id生成药品采购订单
     *
     * @param planId
     * @return
     */
    @Override
    public List<PurchaseOrder> calculateDrugPurchaseOrderById(Integer planId) {
        List<PurchaseOrder> phaseOneOrders = new ArrayList<>();
        try {
            BreedingPlan breedingPlan = breedingPlanMapper.selectByPrimaryKey(planId);
            Assert.notNull(breedingPlan, "养殖计划不存在");
            log.info("O BreedingBrainServiceImpl.calculateDrugPurchaseOrderById input planId:{}", planId);

            //养殖计划所用的参数
            BreedingBatchDrugExample batchDrugExample = new BreedingBatchDrugExample();
            batchDrugExample.createCriteria().andPlanIdEqualTo(planId).andEnableEqualTo(true);
            batchDrugExample.setOrderByClause("day_age_end asc");

            List<BreedingBatchDrug> batchDrugDos = breedingBatchDrugMapper.selectByExample(batchDrugExample);
            if (!CollectionUtils.isEmpty(batchDrugDos)) {
                Set<Integer> phaseOneProductIds = new HashSet<>();
                Set<Integer> phaseTwoProductIds = new HashSet<>();
                List<BreedingBatchDrug> phaseOneBatchDrugDos = new ArrayList<>();
                List<BreedingBatchDrug> phaseTwoBatchDrugDos = new ArrayList<>();
                int i = 0;
                for (BreedingBatchDrug batchDrugDo : batchDrugDos) {
                    if (batchDrugDo.getStopDrugFlag()) {
                        i = i + 1;
                    }
                    if (i < 2) {
                        if (!batchDrugDo.getStopDrugFlag()) {
                            phaseOneProductIds.add(batchDrugDo.getProductId());
                            phaseOneBatchDrugDos.add(batchDrugDo);
                        }
                    }
                    if (i >= 2) {
                        if (!batchDrugDo.getStopDrugFlag()) {
                            phaseTwoProductIds.add(batchDrugDo.getProductId());
                            phaseTwoBatchDrugDos.add(batchDrugDo);
                        }
                    }
                }
                //1.1: 删除第一个药品订单
                PurchaseOrderBo orderBo = new PurchaseOrderBo();
                orderBo.setPlanId(planId)
                        .setOrderPhase(PurchaseOrderPhaseEnum.PHASE_ONE.getCode())
                        .setProductType(ProductTypeEnum.DRUG.getCode());
                this.deleteByCriteria(orderBo);
                //1.2: 保存第一个药品订单和订单明细
                PurchaseOrder firstOrder = this.savePurchaseOrder(breedingPlan, PurchaseOrderPhaseEnum.PHASE_ONE.getCode(), ProductTypeEnum.DRUG.getCode(), breedingPlan.getPlanTime());
                Integer orderId = firstOrder.getId();
                List<PurchaseOrderItems> phaseOneItems = this.getDrugPurchaseOrderItems(breedingPlan, phaseOneProductIds, phaseOneBatchDrugDos);
                this.savePurchaseOrderItems(orderId, phaseOneItems);
                phaseOneOrders.add(firstOrder);

                //2.1删除第二个药品订单
                orderBo = new PurchaseOrderBo();
                orderBo.setPlanId(planId)
                        .setOrderPhase(PurchaseOrderPhaseEnum.PHASE_TWO.getCode())
                        .setProductType(ProductTypeEnum.DRUG.getCode());
                this.deleteByCriteria(orderBo);
                //2.2: 保存第二个药品订单和订单明细
                //获取第二次停药日期
                Map<Integer, String> progressDayAgesMap = new HashMap<>();
                for (int j = 0; j < breedingPlan.getBreedingDays(); j++) {
                    progressDayAgesMap.put(j + 1, DateUtil.accumulateDateByDay(breedingPlan.getPlanTime(), j));
                }
                Date secondStopDrugDate = new Date();
                int j = 0;
                for (BreedingBatchDrug batchDrugDo : batchDrugDos) {
                    if (batchDrugDo.getStopDrugFlag()) {
                        j = j + 1;
                    }
                    if (j == 2) {
                        String strSecondStopDrugDate = progressDayAgesMap.get(batchDrugDo.getDayAgeStart());
                        if (!StringUtils.isEmpty(strSecondStopDrugDate)) {
                            secondStopDrugDate = DateUtil.strToDate(strSecondStopDrugDate);
                        }
                        break;
                    }
                }
                PurchaseOrder secondOrder = this.savePurchaseOrder(breedingPlan, PurchaseOrderPhaseEnum.PHASE_TWO.getCode(), ProductTypeEnum.DRUG.getCode(), secondStopDrugDate);
                orderId = secondOrder.getId();
                List<PurchaseOrderItems> phaseTwoItems = this.getDrugPurchaseOrderItems(breedingPlan, phaseTwoProductIds, phaseTwoBatchDrugDos);
                this.savePurchaseOrderItems(orderId, phaseTwoItems);
                phaseOneOrders.add(secondOrder);
            }

        } catch (Exception ex) {
            log.error("R BreedingBrainServiceImpl.calculateDrugPurchaseOrderById  error:" + ex);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return phaseOneOrders;
    }

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
                    //1.删除第一个饲料订单
                    PurchaseOrderBo orderBo = new PurchaseOrderBo();
                    orderBo.setPlanId(planId)
                            .setOrderPhase(PurchaseOrderPhaseEnum.PHASE_ONE.getCode())
                            .setProductType(ProductTypeEnum.FEED.getCode());
                    this.deleteByCriteria(orderBo);
                    //2.保存第一个饲料订单（小料510）
                    PurchaseOrder purchaseOrder = this.savePurchaseOrder(breedingPlan, PurchaseOrderPhaseEnum.PHASE_ONE.getCode(), ProductTypeEnum.FEED.getCode(), breedingPlan.getPlanTime());
                    //3. 保存订单明细
                    Integer orderId = purchaseOrder.getId();
                    List<PurchaseOrderItems> orderItems = new ArrayList<>();
                    PurchaseOrderItems purchaseOrderItemsDo = new PurchaseOrderItems();
                    purchaseOrderItemsDo.setProductId(feedConfig.getProductId());
                    purchaseOrderItemsDo.setQuantity(PhaseOneWeight);
                    orderItems.add(purchaseOrderItemsDo);
                    this.savePurchaseOrderItems(orderId, orderItems);

                    phaseOneOrders.add(purchaseOrder);
                }
            }
            //种苗采购第一阶段区间配置
            MaterialConfig sproutConfig = getMaterialConfig(PurchaseOrderPhaseEnum.PHASE_ONE.getCode(), ProductTypeEnum.SPROUT.getCode());
            if (null != sproutConfig) {
                if (planChickenQuantity.compareTo(BigDecimal.ZERO) == 1) {
                    //1.删除鸡苗订单和明细
                    PurchaseOrderBo orderBo = new PurchaseOrderBo();
                    orderBo.setPlanId(planId)
                            .setOrderPhase(PurchaseOrderPhaseEnum.PHASE_ONE.getCode())
                            .setProductType(ProductTypeEnum.SPROUT.getCode());
                    this.deleteByCriteria(orderBo);
                    //2.插入鸡苗订单
                    PurchaseOrder purchaseOrder = savePurchaseOrder(breedingPlan, PurchaseOrderPhaseEnum.PHASE_ONE.getCode(), ProductTypeEnum.SPROUT.getCode(), breedingPlan.getPlanTime());
                    //3. 保存订单明细
                    Integer orderId = purchaseOrder.getId();
                    List<PurchaseOrderItems> orderItems = new ArrayList<>();
                    PurchaseOrderItems purchaseOrderItemsDo = new PurchaseOrderItems();
                    purchaseOrderItemsDo.setProductId(sproutConfig.getProductId());
                    purchaseOrderItemsDo.setQuantity(planChickenQuantity);
                    orderItems.add(purchaseOrderItemsDo);
                    savePurchaseOrderItems(orderId, orderItems);

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
                        //1.删除第二次饲料订单和订单明细
                        PurchaseOrderBo orderBo = new PurchaseOrderBo();
                        orderBo.setPlanId(planId)
                                .setOrderPhase(PurchaseOrderPhaseEnum.PHASE_TWO.getCode())
                                .setProductType(ProductTypeEnum.FEED.getCode());
                        this.deleteByCriteria(orderBo);

                        //2.插入第二次饲料订单(15->19日龄小料510订单)
                        PurchaseOrder purchaseOrder = this.savePurchaseOrder(breedingPlan, PurchaseOrderPhaseEnum.PHASE_TWO.getCode(), ProductTypeEnum.FEED.getCode(), new Date());
                        //3. 保存订单明细
                        Integer orderId = purchaseOrder.getId();
                        List<PurchaseOrderItems> orderItems = new ArrayList<>();
                        PurchaseOrderItems purchaseOrderItemsDo = new PurchaseOrderItems();
                        purchaseOrderItemsDo.setProductId(feedConfig.getProductId());
                        purchaseOrderItemsDo.setQuantity(PhaseTwo1Weight);
                        orderItems.add(purchaseOrderItemsDo);
                        this.savePurchaseOrderItems(orderId, orderItems);

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
    public List<PurchaseOrder> calculatePhaseThreeFoodWeightById(Integer planId) throws Exception {
        List<PurchaseOrder> phaseTwoOrders = new ArrayList<>();
        BreedingPlan breedingPlan = breedingPlanMapper.selectByPrimaryKey(planId);
        Assert.notNull(breedingPlan, "养殖计划不存在");
        log.info("O BreedingBrainServiceImpl.calculatePhaseTwoFoodWeightById input planId:{}", planId);
        //饲料采购第二阶段区间配置
        MaterialConfig feedConfigPhaseTwo = getMaterialConfig(PurchaseOrderPhaseEnum.PHASE_TWO.getCode(), ProductTypeEnum.FEED.getCode());
        MaterialConfig feedConfigPhaseThree = getMaterialConfig(PurchaseOrderPhaseEnum.PHASE_THREE.getCode(), ProductTypeEnum.FEED.getCode());
        if (null == feedConfigPhaseTwo) {
            throw new NullPointerException("请先配置第二阶段日龄区间与需要采购的产品");
        }
        if (null == feedConfigPhaseThree) {
            throw new NullPointerException("请先配置第三阶段日龄区间与需要采购的产品");
        }
        if (null != feedConfigPhaseThree && null != feedConfigPhaseTwo) {
            Integer dayAgeStart = feedConfigPhaseThree.getDayAgeStart() == null ? 0 : feedConfigPhaseThree.getDayAgeStart();
            Integer dayAgeEnd = feedConfigPhaseThree.getDayAgeEnd() == null ? 0 : feedConfigPhaseThree.getDayAgeEnd();
            long currentDayAgeLong = breedingPlanService.getDayAge(planId);
            //跟第二阶段小料510饲料采购订单同一天生成即第二阶段起始日龄提前3天下单
            int ageDay12 = feedConfigPhaseTwo.getDayAgeStart() - 3;
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

                    //1.删除第二次饲料订单和明细
                    PurchaseOrderBo orderBo = new PurchaseOrderBo();
                    orderBo.setPlanId(planId)
                            .setOrderPhase(PurchaseOrderPhaseEnum.PHASE_THREE.getCode())
                            .setProductType(ProductTypeEnum.FEED.getCode());
                    this.deleteByCriteria(orderBo);

                    //2.插入第二次饲料订单（20->28日龄大料511饲料订单）
                    PurchaseOrder purchaseOrder = this.savePurchaseOrder(breedingPlan, PurchaseOrderPhaseEnum.PHASE_THREE.getCode(), ProductTypeEnum.FEED.getCode(), new Date());
                    //3. 保存订单明细
                    Integer orderId = purchaseOrder.getId();
                    List<PurchaseOrderItems> orderItems = new ArrayList<>();
                    PurchaseOrderItems purchaseOrderItemsDo = new PurchaseOrderItems();
                    purchaseOrderItemsDo.setProductId(feedConfigPhaseThree.getProductId());
                    purchaseOrderItemsDo.setQuantity(PhaseTwo2Weight);
                    orderItems.add(purchaseOrderItemsDo);
                    this.savePurchaseOrderItems(orderId, orderItems);

                    phaseTwoOrders.add(purchaseOrder);
                }
            }
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

            MaterialConfig feedConfigPhaseTwo = getMaterialConfig(PurchaseOrderPhaseEnum.PHASE_TWO.getCode(), ProductTypeEnum.FEED.getCode());
            MaterialConfig feedConfigPhaseThree = getMaterialConfig(PurchaseOrderPhaseEnum.PHASE_THREE.getCode(), ProductTypeEnum.FEED.getCode());
            MaterialConfig feedConfigPhaseFour = getMaterialConfig(PurchaseOrderPhaseEnum.PHASE_FOUR.getCode(), ProductTypeEnum.FEED.getCode());
            if (null == feedConfigPhaseTwo) {
                throw new NullPointerException("请先配置第二阶段日龄区间与需要采购的产品");
            }
            if (null == feedConfigPhaseThree) {
                throw new NullPointerException("请先配置第三阶段日龄区间与需要采购的产品");
            }
            if (null == feedConfigPhaseFour) {
                throw new NullPointerException("请先配置第四阶段日龄区间与需要采购的产品");
            }

            if (null != feedConfigPhaseFour) {
                Integer dayAgeStart = feedConfigPhaseFour.getDayAgeStart();
                int breedingDays = breedingPlan.getBreedingDays();
                long currentDayAgeLong = breedingPlanService.getDayAge(planId);
                //提前3天下单
                int ageDay26 = dayAgeStart - 3;
                if (currentDayAgeLong == ageDay26) {
                    //获取第12日龄存栏数
                    BatchInfoBo ageDay12BatchInfoBo = getDeadAmountByPlanIdAndDayAge(planId, feedConfigPhaseTwo.getDayAgeStart() - 3);
                    Integer ageDay12LivingQuantity = ageDay12BatchInfoBo.getCurrentAmount();
                    //获取第19日龄存栏数
                    BatchInfoBo ageDay19BatchInfoBo = getDeadAmountByPlanIdAndDayAge(planId, feedConfigPhaseThree.getDayAgeStart() - 1);
                    Integer ageDay19LivingQuantity = ageDay19BatchInfoBo.getCurrentAmount();
                    //获取提前3天即第26日龄存栏数
                    BatchInfoBo ageDay26BatchInfoBo = getDeadAmountByPlanIdAndDayAge(planId, ageDay26);
                    Integer ageDay26LivingQuantity = ageDay26BatchInfoBo.getCurrentAmount();
                    //20->28日龄区间每天每只鸡吃的饲料总和
                    BigDecimal totalFeedWeight2028 = getSumFoodWeightByPlanIdAndDayAgeArea(planId, feedConfigPhaseThree.getDayAgeStart(), feedConfigPhaseThree.getDayAgeEnd());
                    //29->breedingDays日龄区间每天每只鸡吃的饲料总和
                    BigDecimal totalFeedWeight29BreedingDays = getSumFoodWeightByPlanIdAndDayAgeArea(planId, dayAgeStart, breedingDays);

                    //26日龄存栏数乘以sum(29->breedingDays)
                    BigDecimal a = totalFeedWeight29BreedingDays.multiply(new BigDecimal(ageDay26LivingQuantity));
                    //12->19日龄期间死掉的数量
                    Integer diffLivingQuantity1219 = ageDay12LivingQuantity - ageDay19LivingQuantity;
                    //12->19日龄期间剩余的饲料
                    BigDecimal b = totalFeedWeight2028.multiply(new BigDecimal(diffLivingQuantity1219));
                    //计算 20->26日龄区间剩余饲料
                    BigDecimal c = getSumLeftFoodWeightByPlanIdAndDayAgeArea(planId, feedConfigPhaseThree.getDayAgeStart(), ageDay26);
                    //第三次大料511需要采购的数量
                    BigDecimal PhaseFourWeight = a.subtract(b).subtract(c);

                    if (PhaseFourWeight.compareTo(BigDecimal.ZERO) == 1) {
                        //克转换成吨
                        PhaseFourWeight = PhaseFourWeight.divide(new BigDecimal(1000000)).setScale(3, BigDecimal.ROUND_HALF_UP);
                        //1.删除第三次饲料订单和明细
                        PurchaseOrderBo orderBo = new PurchaseOrderBo();
                        orderBo.setPlanId(planId)
                                .setOrderPhase(PurchaseOrderPhaseEnum.PHASE_FOUR.getCode())
                                .setProductType(ProductTypeEnum.FEED.getCode());
                        this.deleteByCriteria(orderBo);

                        //2.插入第三次饲料订单（29->->计划养殖天数日龄大料511饲料订单）
                        PurchaseOrder purchaseOrder = this.savePurchaseOrder(breedingPlan, PurchaseOrderPhaseEnum.PHASE_FOUR.getCode(), ProductTypeEnum.FEED.getCode(), new Date());
                        //3. 保存订单明细
                        Integer orderId = purchaseOrder.getId();
                        List<PurchaseOrderItems> orderItems = new ArrayList<>();
                        PurchaseOrderItems purchaseOrderItemsDo = new PurchaseOrderItems();
                        purchaseOrderItemsDo.setProductId(feedConfigPhaseFour.getProductId());
                        purchaseOrderItemsDo.setQuantity(PhaseFourWeight);
                        orderItems.add(purchaseOrderItemsDo);
                        savePurchaseOrderItems(orderId, orderItems);

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
     * 插入订单
     *
     * @param plan
     * @param phase
     * @param productType
     * @return
     */
    private PurchaseOrder savePurchaseOrder(BreedingPlan plan, Integer phase, Integer productType, Date calculateDate) {
        UserInfo currentUser = currentUserService.getCurrentUser();
        Integer userId = currentUser.getId();
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setBatchNo(plan.getBatchNo())
                .setCustomerId(plan.getCustomerId())
                .setPlanId(plan.getId())
                .setOrderPhase(phase)
                .setPurchaseOrderStatus(PurchaseOrderStatusEnum.ORDER_PLACED.getCode())
                .setCreateUserId(userId)
                .setEnable(true)
                .setProductType(productType)
                .setCreateTime(new Date());

        if (productType == ProductTypeEnum.SPROUT.getCode()) {
            //鸡苗订单单位
            if (phase == PurchaseOrderPhaseEnum.PHASE_ONE.getCode()) {
                String purchaseNo = sequenceCodeUtils.genSeqCode(PO_SPROUT_PREFIX) + "-01";
                purchaseOrder.setPurchaseNo(purchaseNo);
                purchaseOrder.setPlanExecuteTime(DateUtils.addDays(plan.getPlanTime(), -2));
                purchaseOrder.setPlanDeliveryTime(plan.getPlanTime());
                purchaseOrder.setPurchaseName("鸡苗采购 - 全部鸡苗配送");
                purchaseOrder.setNotes("鸡苗采购 - 全部鸡苗配送");
            }
        } else if (productType == ProductTypeEnum.FEED.getCode()) {
            if (phase == PurchaseOrderPhaseEnum.PHASE_ONE.getCode()) {
                String purchaseNo = sequenceCodeUtils.genSeqCode(PO_FOOD_PREFIX) + "-01";
                purchaseOrder.setPurchaseNo(purchaseNo);
                purchaseOrder.setPlanExecuteTime(DateUtils.addDays(calculateDate, -2));
                purchaseOrder.setPlanDeliveryTime(calculateDate);
                purchaseOrder.setPurchaseName("饲料采购 - 第一次饲料配送");
                purchaseOrder.setNotes("饲料采购 - 第一次饲料配送");
            } else if (phase == PurchaseOrderPhaseEnum.PHASE_TWO.getCode()) {
                String purchaseNo = sequenceCodeUtils.genSeqCode(PO_FOOD_PREFIX) + "-02";
                purchaseOrder.setPurchaseNo(purchaseNo);
                purchaseOrder.setPlanExecuteTime(DateUtils.addDays(calculateDate, 1));
                purchaseOrder.setPlanDeliveryTime(DateUtils.addDays(calculateDate, 3));
                purchaseOrder.setPurchaseName("饲料采购 - 第二次饲料配送");
                purchaseOrder.setNotes("饲料采购 - 第二次饲料配送");
            } else if (phase == PurchaseOrderPhaseEnum.PHASE_THREE.getCode()) {
                String purchaseNo = sequenceCodeUtils.genSeqCode(PO_FOOD_PREFIX) + "-03";
                purchaseOrder.setPurchaseNo(purchaseNo);
                purchaseOrder.setPlanExecuteTime(DateUtils.addDays(calculateDate, 1));
                purchaseOrder.setPlanDeliveryTime(DateUtils.addDays(calculateDate, 3));
                purchaseOrder.setPurchaseName("饲料采购 - 第二次饲料配送");
                purchaseOrder.setNotes("饲料采购 - 第二次饲料配送");
            } else if (phase == PurchaseOrderPhaseEnum.PHASE_FOUR.getCode()) {
                String purchaseNo = sequenceCodeUtils.genSeqCode(PO_FOOD_PREFIX) + "-04";
                purchaseOrder.setPurchaseNo(purchaseNo);
                purchaseOrder.setPlanExecuteTime(DateUtils.addDays(calculateDate, 1));
                purchaseOrder.setPlanDeliveryTime(DateUtils.addDays(calculateDate, 3));
                purchaseOrder.setPurchaseName("饲料采购 - 第三次饲料配送");
                purchaseOrder.setNotes("饲料采购 - 第三次饲料配送");
            }
        } else if (productType == ProductTypeEnum.DRUG.getCode()) {
            if (phase == PurchaseOrderPhaseEnum.PHASE_ONE.getCode()) {
                String purchaseNo = sequenceCodeUtils.genSeqCode(PO_DRUG_PREFIX) + "-01";
                purchaseOrder.setPurchaseNo(purchaseNo);
                purchaseOrder.setPlanExecuteTime(DateUtils.addDays(calculateDate, -2));
                purchaseOrder.setPlanDeliveryTime(calculateDate);
                purchaseOrder.setPurchaseName("药品采购 - 第一次药品配送");
                purchaseOrder.setNotes("药品采购 - 第一次药品配送");
            } else if (phase == PurchaseOrderPhaseEnum.PHASE_TWO.getCode()) {
                String purchaseNo = sequenceCodeUtils.genSeqCode(PO_DRUG_PREFIX) + "-02";
                purchaseOrder.setPurchaseNo(purchaseNo);
                purchaseOrder.setPlanExecuteTime(DateUtils.addDays(calculateDate, -2));
                purchaseOrder.setPlanDeliveryTime(calculateDate);
                purchaseOrder.setPurchaseName("药品采购 - 第二次药品配送");
                purchaseOrder.setNotes("药品采购 - 第二次药品配送");

            }
        }
        //1.插入采购订单
        purchaseOrderMapper.insertSelective(purchaseOrder);
        return purchaseOrder;

    }

    private void savePurchaseOrderItems(Integer orderId, List<PurchaseOrderItems> orderItems) {
        BigDecimal orderAmount = BigDecimal.ZERO;
        for (PurchaseOrderItems orderItem : orderItems) {
            Integer productId = orderItem.getProductId();
            Product product = productMapper.selectByPrimaryKey(productId);
            if (null != product) {
                orderItem.setPurchaseOrderId(orderId)
                        .setUnit(product.getPackageUnit())
                        .setCreateTime(new Date())
                        .setEnable(true);

                BigDecimal purchasePrice = product.getPurchasePrice() == null ? BigDecimal.ZERO : product.getPurchasePrice();
                if (product.getProductType() == ProductTypeEnum.DRUG.getCode()) {
                    BigDecimal productCapacity = product.getProductCapacity() == null ? new BigDecimal("1") : product.getProductCapacity();
                    BigDecimal packageQuantity = orderItem.getQuantity().divide(productCapacity, 0, BigDecimal.ROUND_UP);
                    BigDecimal price = packageQuantity.multiply(purchasePrice);
                    orderItem.setPrice(price);
                    orderAmount = orderAmount.add(price);
                } else {
                    orderItem.setPrice(orderItem.getQuantity().multiply(purchasePrice));
                }

                purchaseOrderItemsMapper.insert(orderItem);
            }
        }
        PurchaseOrder order = new PurchaseOrder();
        order.setAmount(orderAmount);
        order.setId(orderId);
        order.setModifyTime(new Date());
        purchaseOrderMapper.updateByPrimaryKeySelective(order);

    }

    /**
     * 计算获取要保存的药品订单明细
     *
     * @param breedingPlan
     * @param productIds
     * @param batchDrugDos
     * @return
     */
    private List<PurchaseOrderItems> getDrugPurchaseOrderItems(BreedingPlan breedingPlan, Set<Integer> productIds, List<BreedingBatchDrug> batchDrugDos) {
        List<PurchaseOrderItems> items = new ArrayList<>();
        for (Integer productId : productIds) {
            BigDecimal totalFeedDrug = BigDecimal.ZERO;
            for (BreedingBatchDrug batchDrugDo : batchDrugDos) {
                if (productId.equals(batchDrugDo.getProductId())) {
                    //按照每天1000只鸡的喂养量计算
                    BigDecimal a = new BigDecimal((float) breedingPlan.getPlanChickenQuantity() / 1000).setScale(3, BigDecimal.ROUND_HALF_UP);
                    BigDecimal b = a.multiply(batchDrugDo.getFeedVolume());
                    BigDecimal c = b.multiply(new BigDecimal(batchDrugDo.getDays()));
                    totalFeedDrug = totalFeedDrug.add(c);
                }
            }
            PurchaseOrderItems purchaseOrderItemsDo = new PurchaseOrderItems();
            purchaseOrderItemsDo.setProductId(productId);
            purchaseOrderItemsDo.setQuantity(totalFeedDrug);

            items.add(purchaseOrderItemsDo);
        }
        return items;
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
        String batchParameterListJson = redis.get(key);
        if (StringUtils.isEmpty(batchParameterListJson)) {
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
            breedingBatchParameterDos = JsonUtils.jsonToList(batchParameterListJson, BreedingBatchParameter.class);
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
        String batchInfoListJson = redis.get(key);
        List<BatchInfo> batchInfoDos;
        if (StringUtils.isEmpty(batchInfoListJson)) {
            //养殖计划过程中的死淘记录
            BatchInfoExample example = new BatchInfoExample();
            example.createCriteria().andPlanIdEqualTo(planId).andEnableEqualTo(true);
            batchInfoDos = batchInfoMapper.selectByExample(example);
            if (!CollectionUtils.isEmpty(batchInfoDos)) {
                redis.set(key, JsonUtils.objectToJson(batchInfoDos), 1800);
            }
        } else {
            batchInfoDos = JsonUtils.jsonToList(batchInfoListJson, BatchInfo.class);
        }
        if (!CollectionUtils.isEmpty(batchInfoDos)) {
            List<BatchInfo> batchInfoList = batchInfoDos.stream().filter(c -> c.getDayAge().equals(dayAge)).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(batchInfoList)) {
                BatchInfo batchInfo = batchInfoList.get(0);
                batchInfoBo.setDeadAmount(batchInfo.getDeadAmount());
                batchInfoBo.setCurrentAmount(batchInfo.getCurrentAmount());
            }
        }
        return batchInfoBo;
    }

    /**
     * 获取日龄区间（阶段）和对应的饲料产品
     *
     * @param phase
     * @param productType
     * @return
     */
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
        if (!CollectionUtils.isEmpty(materialConfigDos)) {
            List<MaterialConfig> configs = materialConfigDos.stream().filter(c -> c.getOrderPhase().equals(phase) && c.getProductType().equals(productType)).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(configs)) {
                return configs.get(0);
            }
        }
        return null;
    }

    /**
     * 删除订单和订单明细
     *
     * @param orderBo
     */
    private void deleteByCriteria(PurchaseOrderBo orderBo) {

        purchaseOrderMapper.deleteByCriteria(orderBo);
    }

}