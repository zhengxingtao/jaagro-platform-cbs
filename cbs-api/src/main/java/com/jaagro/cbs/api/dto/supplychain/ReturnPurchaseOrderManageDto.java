package com.jaagro.cbs.api.dto.supplychain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 供应链采购订单列表
 * @author: @Gao.
 * @create: 2019-03-19 10:39
 **/
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ReturnPurchaseOrderManageDto {

    /**
     * 养殖采购订单表id
     */
    private Integer id;

    /**
     * 客户id
     */
    private Integer customerId;

    /**
     * 养殖户的名称
     */
    private String customerName;
    /**
     *
     */
    private String customerPhone;

    /**
     * 商品采购单编号
     */
    private String purchaseNo;

    /**
     * 订单货物类型（1: 种苗 2: 饲料 3: 药品）
     */
    private Integer productType;
    
    /**
     *
     */
    private String strProductType;

    /**
     * 订单总金额
     */
    private BigDecimal totalPrice;

    /**
     * 状态(1-已下单 ,2－待送达 ,3-已签收)
     */
    private Integer purchaseOrderStatus;

    /**
     * 状态
     */
    private String strPurchaseOrderStatus;

    /**
     * 创建时间（下单时间）
     */
    private Date createTime;
}
