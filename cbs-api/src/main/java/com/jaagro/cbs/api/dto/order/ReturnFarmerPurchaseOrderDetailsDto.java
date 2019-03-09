package com.jaagro.cbs.api.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 农户端采购订单详情
 * @author: @Gao.
 * @create: 2019-03-07 10:56
 **/
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ReturnFarmerPurchaseOrderDetailsDto implements Serializable {
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
     * 商品品名
     */
    private String productName;

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
     * 状态(0-待审核,1-审核通过,2－已完成送货 ,3-审核拒绝)
     */
    private String purchaseOrderStatus;

    /**
     * 开单时间
     */
    private Date createTime;

    /**
     * 待送达时间
     */
    private Date planDeliveryTime;

    /**
     * 到货时间
     */
    private Date deliveryTime;

    /**
     *
     */
    private String orderPhase;


}
