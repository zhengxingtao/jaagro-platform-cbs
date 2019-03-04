package com.jaagro.cbs.api.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: 根据不同商品类型 统计不同类型值
 * @author: @Gao.
 * @create: 2019-03-04 16:42
 **/
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ReturnPurchaseOrderStatisticalDto implements Serializable {
    /**
     * 统计所有种苗数量
     */
    private Integer totalSproutQuantity;
    /**
     * 统计所有饲料的重量
     */
    private BigDecimal totalFeedWeight;
    /**
     * 统计所有药品的数量
     */
    private Integer totalDrugQuantity;

}
