package com.jaagro.cbs.api.service;

import com.github.pagehelper.PageInfo;
import com.jaagro.cbs.api.dto.order.PurchaseOrderPresetCriteriaDto;
import com.jaagro.cbs.api.dto.order.ReturnPurchaseOrderPresetDetailsDto;
import com.jaagro.cbs.api.dto.order.ReturnPurchaseOrderPresetDto;
import com.jaagro.cbs.api.dto.supplychain.PurchaseOrderManageCriteria;
import com.jaagro.cbs.api.dto.supplychain.ReturnPurchaseOrderManageDto;

import java.util.List;

/**
 * 采购订单 相关api
 *
 * @author @Gao.
 */
public interface BreedingPurchaseOrderService {

    /**
     * 采购预置列表
     *
     * @return
     * @author @Gao.
     */
    PageInfo listPurchaseOrderPreset(PurchaseOrderPresetCriteriaDto dto);

    /**
     * 采购预置详情
     *
     * @param purchaseOrderId
     * @return
     * @author @Gao.
     */
    ReturnPurchaseOrderPresetDetailsDto purchaseOrderPresetDetails(Integer purchaseOrderId);

    /**
     * 供应链采购订单列表
     *
     * @param criteria
     * @return
     * @author @Gao.
     */
    List<ReturnPurchaseOrderManageDto> purchaseOrderManage(PurchaseOrderManageCriteria criteria);
}
