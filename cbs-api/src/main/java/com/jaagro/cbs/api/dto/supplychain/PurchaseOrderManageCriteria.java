package com.jaagro.cbs.api.dto.supplychain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 供应链采购订单
 * @author: @Gao.
 * @create: 2019-03-19 10:34
 **/
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderManageCriteria implements Serializable {

    /**
     * 当前页
     */
    private Integer pageNum;

    /**
     * 每页的数量
     */
    private Integer pageSize;

    /**
     * 客户id
     */
    private List<Integer> customerIds;

    /**
     * 客户姓名 手机 查询 条件
     */
    private String customerInfo;

}
