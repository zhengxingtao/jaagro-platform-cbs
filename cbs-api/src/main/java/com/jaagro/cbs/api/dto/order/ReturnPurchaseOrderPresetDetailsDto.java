package com.jaagro.cbs.api.dto.order;

import com.jaagro.cbs.api.dto.farmer.ReturnProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @description: 采购预置订单详情
 * @author: @Gao.
 * @create: 2019-03-15 09:51
 **/
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ReturnPurchaseOrderPresetDetailsDto implements Serializable {

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 商品采购单编号
     */
    private String purchaseNo;

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

    /**
     * 采购订单商品
     */
    private List<ReturnProductDto> returnProductDtos;


}
