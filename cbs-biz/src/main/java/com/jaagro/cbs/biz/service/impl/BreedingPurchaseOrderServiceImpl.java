package com.jaagro.cbs.biz.service.impl;

import com.jaagro.cbs.api.dto.order.PurchaseOrderPresetCriteriaDto;
import com.jaagro.cbs.api.dto.order.ReturnPurchaseOrderPresetDto;
import com.jaagro.cbs.api.service.BreedingPurchaseOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 采购订单相关api
 * @author: @Gao.
 * @create: 2019-03-14 13:29
 **/
@Service
@Slf4j
public class BreedingPurchaseOrderServiceImpl implements BreedingPurchaseOrderService {
    /**
     * 采购预置列表
     *
     * @return
     * @author @Gao.
     */
    @Override
    public List<ReturnPurchaseOrderPresetDto> listPurchaseOrderPreset(PurchaseOrderPresetCriteriaDto dto) {



        return null;
    }
}
