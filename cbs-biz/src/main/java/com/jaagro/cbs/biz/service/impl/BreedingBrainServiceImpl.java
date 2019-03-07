package com.jaagro.cbs.biz.service.impl;

import com.jaagro.cbs.api.enums.ProductTypeEnum;
import com.jaagro.cbs.api.enums.PurchaseOrderStatusEnum;
import com.jaagro.cbs.api.enums.UnitEnum;
import com.jaagro.cbs.api.model.BreedingPlan;
import com.jaagro.cbs.api.model.PurchaseOrder;
import com.jaagro.cbs.api.service.BreedingBrainService;
import com.jaagro.cbs.biz.mapper.BreedingPlanMapperExt;
import com.jaagro.cbs.biz.mapper.PurchaseOrderMapperExt;
import com.jaagro.cbs.biz.utils.SequenceCodeUtils;
import com.jaagro.constant.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Date;

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
    private String PO_FOOD_PREFIX = "OS";

    /**
     * 根据养殖计划Id生成养殖第一阶段（1->14天）的饲料订单
     * 养殖计划提交时调用
     * @param planId
     * @return 需要购买饲料重量
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BigDecimal calculatePhaseOneFoodWeightById(Integer planId) {
        BigDecimal PhaseOneWeight = new BigDecimal(0.00);
        try {
            BreedingPlan breedingPlan = breedingPlanMapper.selectByPrimaryKey(planId);
            Assert.notNull(breedingPlan, "养殖计划不存在");
            log.info("O BreedingBrainServiceImpl.getPhaseOneFoodWeightById input planId:{}", planId);
            BigDecimal planQuantity = new BigDecimal(breedingPlan.getPlanChickenQuantity());
            BigDecimal totalFeedWeightPerDay = getTotalFeedWeightPerDay(planId, 1, 14);
            PhaseOneWeight = planQuantity.multiply(totalFeedWeightPerDay);
            //计算出来的订单重量如果大于0，则插入生产并插入该订单
            if (PhaseOneWeight.compareTo(new BigDecimal(0)) == 1) {
                UserInfo currentUser = currentUserService.getCurrentUser();
                String purchaseNo = sequenceCodeUtils.genSeqCode(PO_FOOD_PREFIX);
                PurchaseOrder purchaseOrder = new PurchaseOrder();
                //1.删除

                //2.插入
                purchaseOrder.setBatchNo(breedingPlan.getBatchNo())
                        .setPlanId(planId)
                        .setCustomerId(breedingPlan.getCustomerId())
                        .setWeight(PhaseOneWeight)
                        .setPurchaseNo(purchaseNo)
                        .setUnit(UnitEnum.TONS.getCode())
                        .setPurchaseOrderStatus(PurchaseOrderStatusEnum.ORDER_PLACED.getCode())
                        .setCreateUserId(currentUser.getId())
                        .setPlanDeliveryTime(breedingPlan.getPlanTime())
                        .setCreateTime(new Date())
                        .setEnable(true)
                        .setProductType(ProductTypeEnum.FEED.getCode());

                purchaseOrderMapper.insertSelective(purchaseOrder);

            }
        } catch (Exception ex) {
            log.error("R BreedingBrainServiceImpl.getPhaseOneFoodWeightById  error:" + ex);
        }
        return PhaseOneWeight;

    }

    /**
     *
     * @param planId
     * @param startDayAge
     * @param endDayAge
     * @return 返回某个养殖计划给定的日龄区间每天每只鸡吃的饲料总和
     */
    private BigDecimal getTotalFeedWeightPerDay(Integer planId, Integer startDayAge, Integer endDayAge) {

        return null;
    }

}
