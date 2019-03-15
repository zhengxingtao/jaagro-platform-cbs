package com.jaagro.cbs.web.controller;

import com.jaagro.cbs.api.dto.order.PurchaseOrderPresetCriteriaDto;
import com.jaagro.cbs.api.model.PurchaseOrder;
import com.jaagro.cbs.api.service.BreedingBrainService;
import com.jaagro.cbs.api.service.BreedingPurchaseOrderService;
import com.jaagro.cbs.api.service.BreedingStandardService;
import com.jaagro.utils.BaseResponse;
import com.jaagro.utils.ResponseStatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Api(description = "养殖大脑生产订单", produces = MediaType.APPLICATION_JSON_VALUE)
public class BreedingBrainOrderController {

    @Autowired
    private BreedingBrainService breedingBrainService;
    @Autowired
    private BreedingPurchaseOrderService breedingPurchaseOrderService;


    @ApiOperation("根据养殖计划Id计算并生成药品采购订单")
    @PostMapping("/createDrugPurchaseOrderById/{planId}")
    public BaseResponse createDrugPurchaseOrderById(@PathVariable("planId") Integer planId) {
        List<PurchaseOrder> purchaseOrders;
        Assert.notNull(planId, "养殖计划id不能为空");
        try {
            purchaseOrders = breedingBrainService.calculateDrugPurchaseOrderById(planId);
        } catch (Exception ex) {
            return BaseResponse.errorInstance("生成药品采购订单失败：" + ex);
        }
        return BaseResponse.successInstance(purchaseOrders);
    }

    @PostMapping("/listPurchaseOrderPreset")
    @ApiOperation("养殖大脑采购预置")
    public BaseResponse listPurchaseOrderPreset(@RequestBody PurchaseOrderPresetCriteriaDto dto) {
        if (dto.getPageNum() == null) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "起始页不能为空");
        }
        if (dto.getPageSize() == null) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "每页条数不能为空");
        }
        return BaseResponse.successInstance(breedingPurchaseOrderService.listPurchaseOrderPreset(dto));
    }

    @ApiOperation("根据养殖计划Id计算1->14天饲料订单")
    @PostMapping("/calculatePhaseOneFoodWeightById/{planId}")
    public BaseResponse calculatePhaseOneFoodWeightById(@PathVariable("planId") Integer planId) {
        List<PurchaseOrder> purchaseOrders;
        Assert.notNull(planId, "养殖计划id不能为空");
        try {
            purchaseOrders = breedingBrainService.calculatePhaseOneFoodWeightById(planId);
        } catch (Exception ex) {
            return BaseResponse.errorInstance("1->14天饲料订单失败：" + ex);
        }
        return BaseResponse.successInstance(purchaseOrders);
    }

    @GetMapping("/purchaseOrderPresetDetails/{purchaseOrderId}")
    @ApiOperation("采购订单详情")
    public BaseResponse purchaseOrderPresetDetails(@PathVariable("purchaseOrderId") Integer purchaseOrderId) {
        if (purchaseOrderId == null) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "采购订单id不能为空");
        }
        return BaseResponse.successInstance(breedingPurchaseOrderService.purchaseOrderPresetDetails(purchaseOrderId));
    }
}
