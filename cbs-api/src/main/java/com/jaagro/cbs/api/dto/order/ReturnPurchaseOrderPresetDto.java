package com.jaagro.cbs.api.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 商品采购预置
 * @author: @Gao.
 * @create: 2019-03-14 11:22
 **/
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ReturnPurchaseOrderPresetDto implements Serializable {
    /**
     * 采购单id
     */
    private Integer purchaseOrderId;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 商品采购单编号
     */
    private String purchaseNo;

    /**
     * 采购名称
     */
    private String purchaseName;

    /**
     * 采购数量 采购重量
     */
    private BigDecimal quantity;

    /**
     * 单位(1-千克｜2-只｜3-个｜ 4-吨等)
     */
    private String strUnit;

    /**
     * 养殖户的名称
     */
    private String customerName;
    /**
     *
     */
    private String customerPhone;

    /**
     * 采购执行时间
     */
    private Date planExecuteTime;

    /**
     * 计划送达时间
     */
    private Date planDeliveryTime;

    /**
     * 状态(0-待审核,1-审核通过,2－已完成送货 ,3-审核拒绝)
     */
    private String strPurchaseOrderStatus;

    /**
     * 状态(0-待审核,1-审核通过,2－已完成送货 ,3-审核拒绝)
     */
    private Integer purchaseOrderStatus;
}
