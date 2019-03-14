package com.jaagro.cbs.biz.service.impl;

import com.jaagro.cbs.api.dto.order.PurchaseOrderPresetCriteriaDto;
import com.jaagro.cbs.api.dto.order.ReturnPurchaseOrderPresetDto;
import com.jaagro.cbs.api.service.BreedingPurchaseOrderService;
import com.jaagro.cbs.biz.mapper.BreedingPlanMapperExt;
import com.jaagro.cbs.biz.service.CustomerClientService;
import com.jaagro.utils.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @description: 采购订单相关api
 * @author: @Gao.
 * @create: 2019-03-14 13:29
 **/
@Service
@Slf4j
public class BreedingPurchaseOrderServiceImpl implements BreedingPurchaseOrderService {

    @Autowired
    private BreedingPlanMapperExt breedingPlanMapper;
    @Autowired
    private CustomerClientService customerClientService;

    /**
     * 采购预置列表
     *
     * @return
     * @author @Gao.
     */
    @Override
    public List<ReturnPurchaseOrderPresetDto> listPurchaseOrderPreset(PurchaseOrderPresetCriteriaDto dto) {
        if (dto.getCustomerInfo() != null) {
            BaseResponse<List<Integer>> response = customerClientService.listCustomerIdByKeyWord(dto.getCustomerInfo());
            if (!CollectionUtils.isEmpty(response.getData())) {
                List<Integer> customerIds = response.getData();
                dto.setCustomerIds(customerIds);
            }
        }
        List<ReturnPurchaseOrderPresetDto> returnPurchaseOrderPresetDtos = breedingPlanMapper.listPurchaseOrderPreset(dto);
        if (!CollectionUtils.isEmpty(returnPurchaseOrderPresetDtos)) {
            for (ReturnPurchaseOrderPresetDto returnPurchaseOrderPresetDto : returnPurchaseOrderPresetDtos) {


            }
        }
        return returnPurchaseOrderPresetDtos;
    }
}
