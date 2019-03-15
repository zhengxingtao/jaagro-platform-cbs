package com.jaagro.cbs.biz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jaagro.cbs.api.dto.base.CustomerContactsReturnDto;
import com.jaagro.cbs.api.dto.base.ShowCustomerDto;
import com.jaagro.cbs.api.dto.order.AccumulationPurchaseOrderParamDto;
import com.jaagro.cbs.api.dto.order.PurchaseOrderPresetCriteriaDto;
import com.jaagro.cbs.api.dto.order.ReturnPurchaseOrderPresetDto;
import com.jaagro.cbs.api.enums.PackageUnitEnum;
import com.jaagro.cbs.api.enums.PurchaseOrderStatusEnum;
import com.jaagro.cbs.api.model.PurchaseOrderItems;
import com.jaagro.cbs.api.model.PurchaseOrderItemsExample;
import com.jaagro.cbs.api.service.BreedingPlanService;
import com.jaagro.cbs.api.service.BreedingPlantService;
import com.jaagro.cbs.api.service.BreedingPurchaseOrderService;
import com.jaagro.cbs.biz.mapper.BreedingPlanMapperExt;
import com.jaagro.cbs.biz.mapper.PurchaseOrderItemsMapperExt;
import com.jaagro.cbs.biz.service.CustomerClientService;
import com.jaagro.cbs.biz.service.UserClientService;
import com.jaagro.constant.UserInfo;
import com.jaagro.utils.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
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
    private BreedingPlanService breedingPlanService;
    @Autowired
    private UserClientService userClientService;
    @Autowired
    private CurrentUserService currentUserService;

    /**
     * 采购预置列表
     *
     * @return
     * @author @Gao.
     */
    @Override
    public PageInfo listPurchaseOrderPreset(PurchaseOrderPresetCriteriaDto dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        if (dto.getCustomerInfo() != null) {
            List<Integer> listCustomerIds = breedingPlanService.listCustomerIdsByKeyword(dto.getCustomerInfo());
            dto.setCustomerIds(listCustomerIds);
        }
        List<ReturnPurchaseOrderPresetDto> returnPurchaseOrderPresetDtos = breedingPlanMapper.listPurchaseOrderPreset(dto);
        if (!CollectionUtils.isEmpty(returnPurchaseOrderPresetDtos)) {
            for (ReturnPurchaseOrderPresetDto returnPurchaseOrderPresetDto : returnPurchaseOrderPresetDtos) {
                if (returnPurchaseOrderPresetDto.getCustomerId() != null) {
                    ShowCustomerDto customerInfo = breedingPlanService.getCustomerInfo(returnPurchaseOrderPresetDto.getCustomerId());
                    if (customerInfo.getCustomerName() != null && customerInfo.getCustomerPhone() != null) {
                        returnPurchaseOrderPresetDto
                                .setCustomerPhone(customerInfo.getCustomerPhone())
                                .setCustomerName(customerInfo.getCustomerName());
                    }
                }
                if (returnPurchaseOrderPresetDto.getPurchaseOrderStatus() != null) {
                    returnPurchaseOrderPresetDto
                            .setStrPurchaseOrderStatus(PurchaseOrderStatusEnum.getDescByCode(returnPurchaseOrderPresetDto.getPurchaseOrderStatus()));
                }
                if (returnPurchaseOrderPresetDto.getPurchaseOrderId() != null) {
                    AccumulationPurchaseOrderParamDto paramDto = breedingPlanService.accumulationPurchaseOrderItems(returnPurchaseOrderPresetDto.getPurchaseOrderId());
                    if (paramDto.getUnit() != null) {
                        returnPurchaseOrderPresetDto.setStrUnit(PackageUnitEnum.getDescByCode(paramDto.getUnit()));
                    }
                    if (paramDto.getQuantity() != null) {
                        returnPurchaseOrderPresetDto.setQuantity(paramDto.getQuantity());
                    }
                }
                if (returnPurchaseOrderPresetDto.getSignerId() != null) {
                    BaseResponse<UserInfo> globalUser = userClientService.getGlobalUser(returnPurchaseOrderPresetDto.getSignerId());
                    if (globalUser != null && globalUser.getData() != null) {
                        UserInfo userInfo = globalUser.getData();
                        if (userInfo.getName() != null) {
                            returnPurchaseOrderPresetDto.setSignerName(userInfo.getName());
                        }
                        if (userInfo.getPhoneNumber() != null) {
                            returnPurchaseOrderPresetDto.setSignerPhone(userInfo.getPhoneNumber());
                        }
                    }
                }
            }
        }
        return new PageInfo(returnPurchaseOrderPresetDtos);
    }

    @Override
    public void purchaseOrderPresetDetails(Integer purchaseOrderId) {

    }
}
