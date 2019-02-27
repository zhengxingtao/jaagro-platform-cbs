package com.jaagro.cbs.api.dto.plan;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @description: 待上鸡签收详情
 * @author: @Gao.
 * @create: 2019-02-27 13:17
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ReturnChickenSignDetailsDto implements Serializable {

    /**
     * 商品采购信息
     */
    private List<ReturnPurchaseOrderDto> returnPurchaseOrderDtos;
    /**
     * 养殖计划详情信息
     */
    private ReturnBreedingPlanDetailsDto returnBreedingPlanDetails;

}
