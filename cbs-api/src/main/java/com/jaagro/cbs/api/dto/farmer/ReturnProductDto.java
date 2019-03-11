package com.jaagro.cbs.api.dto.farmer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 采购商品
 * @author: @Gao.
 * @create: 2019-03-09 16:46
 **/
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ReturnProductDto implements Serializable {
    /**
     * 产品id
     */
    private Integer id;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 订单货物类型（1: 种苗 2: 饲料 3: 药品）
     */
    private Integer productType;

    /**
     * 生产商名称
     */
    private String manufacturer;

    /**
     * 产品图片地址
     */
    private String imageUrl;

    /**
     * 采购单价
     */
    private BigDecimal purchasePrice;

    /**
     * 销售单价
     */
    private BigDecimal salePrice;

    /**
     * 商品容量
     */
    private BigDecimal productCapacity;

    /**
     * 容量单位(1-ml,2-g)
     */
    private Integer capacityUnit;

    /**
     * 包装单位(1-瓶,2-袋,3-支,4-桶,5-盒)
     */
    private Integer packageUnit;

}
