package com.jaagro.cbs.web.controller;

import com.jaagro.cbs.api.model.BreedingPlan;
import com.jaagro.cbs.api.model.PurchaseOrder;
import com.jaagro.cbs.api.service.BreedingBrainService;
import com.jaagro.utils.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@Api(description = "养殖大脑生产订单", produces = MediaType.APPLICATION_JSON_VALUE)
public class BreedingBrainOrderController {

    @Autowired
    private BreedingBrainService breedingBrainService;


    @ApiOperation("根据养殖计划Id计算并生成药品采购订单")
    @PostMapping("/createDrugPurchaseOrderById/{planId}")
    public BaseResponse createDrugPurchaseOrderById(@PathVariable("planId") Integer planId) {
        List<PurchaseOrder> purchaseOrders;
        Assert.notNull(planId, "养殖计划id不能为空");
        try {
            BreedingPlan breedingPlan = breedingBrainService.getBreedingPlanById(planId);
            purchaseOrders = breedingBrainService.calculateDrugPurchaseOrderById(breedingPlan);
        } catch (Exception ex) {
            return BaseResponse.errorInstance("生成药品采购订单失败：" + ex);
        }
        return BaseResponse.successInstance(purchaseOrders);
    }

    @ApiOperation("根据养殖计划Id计算1->14天饲料订单")
    @PostMapping("/calculatePhaseOneFoodWeightById/{planId}")
    public BaseResponse calculatePhaseOneFoodWeightById(@PathVariable("planId") Integer planId) {
        List<PurchaseOrder> purchaseOrders;
        Assert.notNull(planId, "养殖计划id不能为空");
        try {
            BreedingPlan breedingPlan = breedingBrainService.getBreedingPlanById(planId);
            purchaseOrders = breedingBrainService.calculatePhaseOneFoodWeightById(breedingPlan);
        } catch (Exception ex) {
            return BaseResponse.errorInstance("1->14天饲料、鸡苗订单失败：" + ex);
        }
        return BaseResponse.successInstance(purchaseOrders);
    }
    @ApiOperation("根据养殖计划Id计算15->19天的饲料订单")
    @PostMapping("/calculatePhaseTwoFoodWeightById/{planId}")
    public BaseResponse calculatePhaseTwoFoodWeightById(@PathVariable("planId") Integer planId) {
        List<PurchaseOrder> purchaseOrders;
        Assert.notNull(planId, "养殖计划id不能为空");
        try {
            BreedingPlan breedingPlan = breedingBrainService.getBreedingPlanById(planId);
            purchaseOrders = breedingBrainService.calculatePhaseTwoFoodWeightById(breedingPlan);
        } catch (Exception ex) {
            return BaseResponse.errorInstance("15->19天天饲料订单失败：" + ex);
        }
        return BaseResponse.successInstance(purchaseOrders);
    }
    @ApiOperation("根据养殖计划Id计算20->28天的饲料订单")
    @PostMapping("/calculatePhaseThreeFoodWeightById/{planId}")
    public BaseResponse calculatePhaseThreeFoodWeightById(@PathVariable("planId") Integer planId) {
        List<PurchaseOrder> purchaseOrders;
        Assert.notNull(planId, "养殖计划id不能为空");
        try {
            BreedingPlan breedingPlan = breedingBrainService.getBreedingPlanById(planId);
            purchaseOrders = breedingBrainService.calculatePhaseThreeFoodWeightById(breedingPlan);
        } catch (Exception ex) {
            return BaseResponse.errorInstance("计算20->28天饲料订单失败：" + ex);
        }
        return BaseResponse.successInstance(purchaseOrders);
    }
    @ApiOperation("根据养殖计划Id计算29->计划养殖天数的饲料订单")
    @PostMapping("/calculatePhaseFourFoodWeightById/{planId}")
    public BaseResponse calculatePhaseFourFoodWeightById(@PathVariable("planId") Integer planId) {
        List<PurchaseOrder> purchaseOrders;
        Assert.notNull(planId, "养殖计划id不能为空");
        try {
            BreedingPlan breedingPlan = breedingBrainService.getBreedingPlanById(planId);
            purchaseOrders = breedingBrainService.calculatePhaseFourFoodWeightById(breedingPlan);
        } catch (Exception ex) {
            return BaseResponse.errorInstance("计算29->计划养殖天数之间饲料订单失败：" + ex);
        }
        return BaseResponse.successInstance(purchaseOrders);
    }

}
