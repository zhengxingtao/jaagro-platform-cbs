package com.jaagro.cbs.api.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: 采购单明细累计
 * @author: @Gao.
 * @create: 2019-03-14 17:25
 **/
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class AccumulationPurchaseOrderParamDto implements Serializable {
    /**
     * 单位
     */
    private Integer unit;
    /**
     * 累计 重量 或者数量
     */
    private BigDecimal quantity;
}
