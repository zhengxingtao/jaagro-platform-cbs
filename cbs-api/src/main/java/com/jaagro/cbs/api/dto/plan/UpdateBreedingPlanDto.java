package com.jaagro.cbs.api.dto.plan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 更新养殖计划
 * @author: @Gao.
 * @create: 2019-02-26 10:23
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UpdateBreedingPlanDto implements Serializable {

    /**
     * 养殖计划id
     */
    private Integer id;
    /**
     * 计划上鸡数量
     */
    private Integer planChickenQuantity;

    /**
     * 技术员姓名
     */
    private String technician;

    /**
     * 技术员id
     */
    private Integer technicianId;
}
