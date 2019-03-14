package com.jaagro.cbs.api.service;

import com.jaagro.cbs.api.dto.order.PurchaseOrderPresetCriteriaDto;
import com.jaagro.cbs.api.dto.order.ReturnPurchaseOrderPresetDto;

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
    List<ReturnPurchaseOrderPresetDto> listPurchaseOrderPreset(PurchaseOrderPresetCriteriaDto dto);
}
