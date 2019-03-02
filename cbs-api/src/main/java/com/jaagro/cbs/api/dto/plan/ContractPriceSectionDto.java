package com.jaagro.cbs.api.dto.plan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author yj
 * @date 2019/2/25 15:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ContractPriceSectionDto implements Serializable {
    /**
     * 回收价格
     */
    private BigDecimal recyclingPrice;

    /**
     * 体重区间下限
     */
    private BigDecimal weightLower;

    /**
     * 体重区间上限
     */
    private BigDecimal weightUpper;

    /**
     * 是否是市场价(0-不是,1-是)
     */
    private Boolean marketPriceFlag;
}
