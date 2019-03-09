package com.jaagro.cbs.api.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author :gavinwang
 * @date :2019/03/09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class PurchaseOrder implements Serializable {
    /**
     * 养殖采购订单表id
     */
    private Integer id;

    /**
     * 客户id
     */
    private Integer customerId;

    /**
     * 养殖计划id
     */
    private Integer planId;

    /**
     * 签收人id
     */
    private Integer signerId;

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
    private Integer productType;

    /**
     * 采购重量
     */
    private BigDecimal weight;

    /**
     * 采购数量
     */
    private Integer quantity;

    /**
     * 单位(1-千克｜2-只｜3-个｜ 4-吨等)
     */
    private Integer unit;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 状态(1-已下单 ,2－待送达 ,3-已签收)
     */
    private Integer purchaseOrderStatus;

    /**
     * 供货商id
     */
    private Integer supplierId;

    /**
     * 计划送达时间
     */
    private Date planDeliveryTime;

    /**
     * 到货时间
     */
    private Date deliveryTime;

    /**
     * 采购商品备注
     */
    private String notes;

    /**
     * 是否有效(0-无效,1-有效)
     */
    private Boolean enable;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private Integer createUserId;

    /**
     * 更新时间
     */
    private Date modifyTime;

    /**
     * 更新人
     */
    private Integer modifyUserId;

    /**
     * 订单阶段：1-第一阶段；2-第二阶段；3-第三阶段
     */
    private Integer orderPhase;

    /**
     * 采购执行时间
     */
    private Date planExecuteTime;

    /**
     * 采购名称
     */
    private String purchaseName;

    private static final long serialVersionUID = 1L;
}