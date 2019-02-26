package com.jaagro.cbs.api.model;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author :gavinwang
 * @date :2019/02/26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Product implements Serializable {
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
     * 创建时间
     */
    private Date createTime;

    /**
     * 是否有效（1-有效 0 -无效）
     */
    private Boolean enable;

    private static final long serialVersionUID = 1L;
}