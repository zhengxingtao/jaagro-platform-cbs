package com.jaagro.cbs.api.dto.standard;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author yj
 * @date 2019/3/17 17:44
 */
@Data
@Accessors(chain = true)
public class BreedingStandardDrugItemDto implements Serializable {
    /**
     * 养殖参数模板药品配置表id
     */
    private Integer id;

    /**
     * 商品id
     */
    private Integer productId;

    /**
     * 千只日喂量
     */
    private BigDecimal feedVolume;

    /**
     * SKU编码
     */
    private String skuNo;
}
