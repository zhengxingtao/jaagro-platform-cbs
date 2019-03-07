package com.jaagro.cbs.api.service;

import com.github.pagehelper.PageInfo;
import com.jaagro.cbs.api.dto.farmer.*;
import com.jaagro.cbs.api.dto.order.PurchaseOrderDto;
import com.jaagro.cbs.api.dto.order.PurchaseOrderListParamDto;

import java.util.List;


/**
 * 农户端app 相关api
 *
 * @author @Gao.
 */
public interface BreedingFarmerService {

    /**
     * 农户端app 首页数据统计
     *
     * @return
     * @author @Gao.
     */
    ReturnBreedingFarmerIndexDto breedingFarmerIndexStatistical();

    /**
     * 农户端app 首页
     *
     * @return
     * @author @Gao.
     */
    PageInfo breedingFarmerIndex(BreedingBatchParamDto dto);

    /**
     * 技术询问
     *
     * @param dto
     */
    void technicalInquiries(CreateTechnicalInquiriesDto dto);

    /**
     * 农户端个人中心
     *
     * @return
     */
    FarmerPersonalCenterDto farmerPersonalCenter();

    /**
     * 商品采购列表
     *
     * @return
     */
    List<PurchaseOrderDto> listPurchaseOrder(PurchaseOrderListParamDto dto);

    // purchaseOrderDetails(Integer purchaseOrderId);

}
