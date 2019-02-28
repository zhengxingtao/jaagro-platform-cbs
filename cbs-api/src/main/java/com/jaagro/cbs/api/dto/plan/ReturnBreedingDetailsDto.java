package com.jaagro.cbs.api.dto.plan;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 养殖中详情信息
 * @author: @Gao.
 * @create: 2019-02-27 15:29
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ReturnBreedingDetailsDto implements Serializable {
    /**
     * 养殖计划详情信息
     */
    private ReturnBreedingPlanDetailsDto returnBreedingPlanDetails;
}
