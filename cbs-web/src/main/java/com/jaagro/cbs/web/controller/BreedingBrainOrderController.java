package com.jaagro.cbs.web.controller;

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
            purchaseOrders = breedingBrainService.calculateDrugPurchaseOrderById(planId);
        } catch (Exception ex) {
            return BaseResponse.errorInstance("生成药品采购订单失败：" + ex);
        }
        return BaseResponse.successInstance(purchaseOrders);
    }


}
