package com.jaagro.cbs.api.model;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author :gavinwang
 * @date :2019/03/09
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
     * 数量
     */
    private Integer quantity;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 是否有效(0-无效,1-有效)
     */
    private Boolean enable;

    private static final long serialVersionUID = 1L;
}