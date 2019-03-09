package com.jaagro.cbs.api.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 更新采购订单状态
 * @author: @Gao.
 * @create: 2019-03-07 16:21
 **/
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePurchaseOrderParamDto implements Serializable {
    /**
     * 采购订单id
     */
    private Integer purchaseOrderId;
    /**
     * 采购订单状态
     */
    private Integer purchaseOrderStatus;
}
