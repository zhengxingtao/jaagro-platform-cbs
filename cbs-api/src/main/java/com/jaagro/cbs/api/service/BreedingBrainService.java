package com.jaagro.cbs.api.service;

import com.jaagro.cbs.api.model.BreedingPlan;
import com.jaagro.cbs.api.model.PurchaseOrder;

import java.util.List;

/**
 * @Author gavin
 *
 * @Date 20190305
 */
public interface BreedingBrainService {

    /**
     * 根据养殖计划Id生成养殖第一阶段（1->14天）的相关订单：第一阶段的饲料订单、第一阶段的鸡苗订单
     * @param breedingPlan
     * @return 返回第一阶段2采购订单：第一阶段的饲料订单、第一阶段的鸡苗订单
     */
    List<PurchaseOrder> calculatePhaseOneFoodWeightById(BreedingPlan breedingPlan);

    /**
     * 根据养殖计划Id生成养殖第二阶段（15->19天）的饲料订单
     * @param breedingPlan
     * @return
     * @throws Exception
     */
    List<PurchaseOrder> calculatePhaseTwoFoodWeightById(BreedingPlan breedingPlan) throws Exception;

    /**
     *  根据养殖计划Id生成养殖第三阶段20->28天的饲料订单
     * @param breedingPlan
     * @return
     * @throws Exception
     */
    List<PurchaseOrder> calculatePhaseThreeFoodWeightById(BreedingPlan breedingPlan) throws Exception;

    /**
     * 根据养殖计划Id生成29->计划养殖天数的饲料订单
     *      * 饲料采购 - 第三次饲料配送
     * @param breedingPlan
     * @return
     * @throws Exception
     */
    List<PurchaseOrder> calculatePhaseFourFoodWeightById(BreedingPlan breedingPlan) throws Exception;

    /**
     * 根据养殖计划Id生成药品采购订单
     * @param breedingPlan
     * @return
     */
    List<PurchaseOrder> calculateDrugPurchaseOrderById(BreedingPlan breedingPlan);

    /**
     * 根据计划ID获取养殖计划
     * @param planId
     * @return
     */
    BreedingPlan getBreedingPlanById(Integer planId);


}
