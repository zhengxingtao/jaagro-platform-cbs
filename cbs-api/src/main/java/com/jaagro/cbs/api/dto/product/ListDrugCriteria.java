package com.jaagro.cbs.api.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 药品列表查询对象
 * @author: @Gao.
 * @create: 2019-03-16 13:50
 **/
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ListDrugCriteria implements Serializable {

    /**
     * 当前页
     */
    private Integer pageNum;
    /**
     * 每页的数量
     */
    private Integer pageSize;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 订单货物类型（1: 种苗 2: 饲料 3: 药品）
     */
    private Integer productType;

    /**
     * SKU编码
     */
    private String skuNo;
}
