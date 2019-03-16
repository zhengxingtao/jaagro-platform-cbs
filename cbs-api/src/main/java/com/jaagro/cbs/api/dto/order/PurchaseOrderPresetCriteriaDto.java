package com.jaagro.cbs.api.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 采购预置查询条件
 * @author: @Gao.
 * @create: 2019-03-14 13:38
 **/
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderPresetCriteriaDto implements Serializable {
    /**
     * 起始页
     */
    private Integer pageNum;

    /**
     * 每页条数
     */
    private Integer pageSize;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 客户姓名 手机 查询 条件
     */
    private String customerInfo;

    /**
     * 录单人
     */
    private String createUserName;

    /**
     * 采购订单状态
     */
    private Integer purchaseOrderStatus;

    /**
     * 客户id
     */
    private List<Integer> customerIds;
}
