package com.jaagro.cbs.api.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
     * 起始页
     */
    private Integer pageNum;

    /**
     * 每页条数
     */
    private Integer pageSize;

}
