package com.jaagro.cbs.api.dto.plan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 商品采购信息
 * @author: @Gao.
 * @create: 2019-02-27 13:43
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ReturnPurchaseOrderDto implements Serializable {
    /**
     * 养殖采购订单表id
     */
    private Integer id;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 商品采购单编号
     */
    private String purchaseNo;

    /**
     * 订单货物类型（1: 种苗 2: 饲料 3: 药品）
     */
    private String productType;

    /**
     * 签收人姓名
     */
    private String signerName;

    /**
     * 签收人电话
     */
    private String signerPhone;


    /**
     * 采购数量 采购重量
     */
    private BigDecimal quantity;

    /**
     * 单位(千克｜只｜个｜ 吨等)
     */
    private String unit;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 状态(0-待审核,1-审核通过,2－已完成送货 ,3-审核拒绝)
     */
    private String purchaseOrderStatus;

    /**
     * 到货时间
     */
    private Date deliveryTime;

    /**
     * 采购商品备注
     */
    private String notes;

    /**
     * 创建时间
     */
    private Date createTime;


}
