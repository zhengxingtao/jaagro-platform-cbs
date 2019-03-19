package com.jaagro.cbs.web.vo.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 药品库存列表
 * @author: @Gao.
 * @create: 2019-03-16 14:33
 **/
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class DrugStockVo implements Serializable {
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
    private String strProductType;

    /**
     * 产品图片地址
     */
    private String imageUrl;

    /**
     * 库存数量
     */
    private Integer stockQuantity;

    /**
     * SKU编码
     */
    private String skuNo;

    /**
     * 采购单价
     */
    private BigDecimal purchasePrice;

    /**
     * 销售单价
     */
    private BigDecimal salePrice;

    /**
     * 规格
     */
    private String specification;

    /**
     * 创建时间
     */
    private Date createTime;
}
