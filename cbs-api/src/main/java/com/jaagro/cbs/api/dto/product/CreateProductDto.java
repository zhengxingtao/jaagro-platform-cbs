package com.jaagro.cbs.api.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: 新增商品
 * @author: @Gao.
 * @create: 2019-03-16 10:38
 **/
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductDto implements Serializable {

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
     * 包装单位(1-瓶,2-袋,3-只,4-桶,5-盒)
     */
    private Integer packageUnit;

    /**
     * 商品备注
     */
    private String notes;
}
