package com.jaagro.cbs.web.controller.app;

import com.jaagro.cbs.api.dto.farmer.BreedingBatchParamDto;
import com.jaagro.cbs.api.dto.farmer.BreedingPlanDetailDto;
import com.jaagro.cbs.api.dto.farmer.CreateTechnicalInquiriesDto;
import com.jaagro.cbs.api.dto.order.PurchaseOrderListParamDto;
import com.jaagro.cbs.api.dto.order.UpdatePurchaseOrderParamDto;
import com.jaagro.cbs.api.dto.plan.CreateBreedingPlanDto;
import com.jaagro.cbs.api.service.BreedingFarmerService;
import com.jaagro.cbs.api.service.BreedingPlanService;
import com.jaagro.utils.BaseResponse;
import com.jaagro.utils.ResponseStatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author @Gao.
 */
@RestController
@Api(description = "农户端养殖管理", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class BreedingFarmerController {
    @Autowired
    private BreedingFarmerService breedingFarmerService;
    @Autowired
    private BreedingPlanService breedingPlanService;


    @GetMapping("/breedingFarmerIndexStatistical")
    @ApiOperation("农户端首页数据统计")
    public BaseResponse breedingFarmerIndexStatistical() {
        return BaseResponse.successInstance(breedingFarmerService.breedingFarmerIndexStatistical());
    }

    @PostMapping("/breedingFarmerIndex")
    @ApiOperation("农户端首页列表")
    public BaseResponse breedingFarmerIndex(@RequestBody BreedingBatchParamDto dto) {
        if (dto.getPageNum() == null) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "起始页不能为空");
        }
        if (dto.getPageSize() == null) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "每页条数不能为空");
        }
        return BaseResponse.successInstance(breedingFarmerService.breedingFarmerIndex(dto));
    }

    @PostMapping("/publishedChickenPlan")
    @ApiOperation("发布上鸡计划")
    public BaseResponse publishedChickenPlan(@RequestBody CreateBreedingPlanDto dto) {
        if (CollectionUtils.isEmpty(dto.getPlantIds())) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "养殖场不能为空");
        }
        if (dto.getCustomerId() == null) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "客户不能为空");
        }
        breedingPlanService.createBreedingPlan(dto);
        return BaseResponse.successInstance(ResponseStatusCode.OPERATION_SUCCESS);
    }

    @PostMapping("/technicalInquiries")
    @ApiOperation("新增技术询问")
    public BaseResponse technicalInquiries(@RequestBody CreateTechnicalInquiriesDto dto) {
        if (dto.getPlanId() == null) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "养殖计划id不能为空");
        }
        if (dto.getBatchNo() == null) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "养殖计划批次号不能为空");
        }
        if (dto.getPlantId() == null) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "养殖场id不能为空");
        }
        if (dto.getCoopId() == null) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "鸡舍id不能为空");
        }
        breedingFarmerService.technicalInquiries(dto);
        return BaseResponse.successInstance(ResponseStatusCode.OPERATION_SUCCESS);
    }

    @GetMapping("/farmerPersonalCenter")
    @ApiOperation("农户端个人中心")
    public BaseResponse farmerPersonalCenter() {
        return BaseResponse.successInstance(breedingFarmerService.farmerPersonalCenter());
    }

    @PostMapping("/listPurchaseOrder")
    @ApiOperation("商品采购列表")
    public BaseResponse listPurchaseOrder(@RequestBody PurchaseOrderListParamDto dto) {
        return BaseResponse.successInstance(breedingFarmerService.listPurchaseOrder(dto));
    }

    @GetMapping("/purchaseOrderDetails/{purchaseOrderId}")
    @ApiOperation("采购订单详情")
    public BaseResponse purchaseOrderDetails(@PathVariable("purchaseOrderId") Integer purchaseOrderId) {
        if (purchaseOrderId == null) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "采购订单id详情");
        }
        return BaseResponse.successInstance(breedingFarmerService.purchaseOrderDetails(purchaseOrderId));
    }

    @GetMapping("/updatePurchaseOrder")
    @ApiOperation("更新采购订单状态")
    public BaseResponse updatePurchaseOrder(UpdatePurchaseOrderParamDto dto) {
        if (dto.getPurchaseOrderId() == null) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "采购订单id详情");
        }
        if (dto.getPurchaseOrderStatus() == null) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "采购订单状态不能为空");
        }
        breedingFarmerService.updatePurchaseOrder(dto);
        return BaseResponse.successInstance(ResponseStatusCode.OPERATION_SUCCESS);
    }

    /**
     * 批次详情
     *
     * @param planId
     * @return
     * @author yj
     */
    @PostMapping("/getBatchDetail/{planId}")
    @ApiOperation("批次详情")
    public BaseResponse getBatchDetail(@PathVariable("planId") Integer planId) {
        log.info("O getBatchDetail planId={}", planId);
        BreedingPlanDetailDto breedingPlanDetailDto = breedingPlanService.getBatchDetail(planId);
        if (breedingPlanDetailDto != null) {
            return BaseResponse.successInstance(breedingPlanDetailDto);
        }
        return BaseResponse.queryDataEmpty();
    }
}
