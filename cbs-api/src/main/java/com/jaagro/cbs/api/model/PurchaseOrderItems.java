package com.jaagro.cbs.api.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author :gavinwang
 * @date :2019/03/11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class PurchaseOrderItems implements Serializable {
    private Integer id;

    /**
     * 采购订单id
     */
    private Integer purchaseOrderId;

    /**
     * 产品id
     */
    private Integer productId;

    /**
     * 购买数量
     */
    private BigDecimal quantity;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 是否有效(0-无效,1-有效)
     */
    private Boolean enable;

    /**
     * 1-千克｜2-只｜3-个｜ 4-吨
     */
    private Integer unit;

    /**
     * 价格=quantity * prodcuct.purchase_price
     */
    private BigDecimal price;

    private static final long serialVersionUID = 1L;
}