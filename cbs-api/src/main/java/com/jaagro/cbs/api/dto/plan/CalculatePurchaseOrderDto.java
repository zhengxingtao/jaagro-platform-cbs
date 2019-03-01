package com.jaagro.cbs.api.dto.plan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: 用于结算采购订单中间对象
 * @author: @Gao.
 * @create: 2019-03-01 10:00
 **/
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CalculatePurchaseOrderDto implements Serializable {

    private Integer productType;

    /**
     * 计划采购数量 重量
     */
    private BigDecimal planPurchaseValue;

    /**
     * 已送达采购数量 重量
     */
    private BigDecimal deliverPurchaseValue;

}
