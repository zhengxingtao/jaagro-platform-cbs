package com.jaagro.cbs.biz.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author :gavinwang
 * @date :2019/03/09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class PurchaseOrderBo implements Serializable {

    private static final long serialVersionUID = 4858088010866253934L;
    /**
     * 养殖计划id
     */
    private Integer planId;

    /**
     * 订单货物类型（1: 种苗 2: 饲料 3: 药品）
     */
    private Integer productType;
    /**
     * 产品id
     */
    private Integer productId;

    /**
     * 订单阶段：1-第一阶段；2-第二阶段；3-第三阶段
     */
    private Integer orderPhase;


}