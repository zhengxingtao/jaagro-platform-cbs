package com.jaagro.cbs.api.dto.plan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author @Gao.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CreateBreedingPlanDto implements Serializable {

    /**
     * 养殖场id
     */
    private Integer plantsId;


    /**
     * 租户id
     */
    private Integer tenantId;

    /**
     * 养殖户id
     */
    private Integer customerId;

    /**
     * 计划状态(0-待录入合同,1-待参数纠偏,2-待上鸡签收,3-养殖中,4-待出栏确认,5-已完成,6-已取消)
     */
    private Integer planStatus;

    /**
     * 计划上鸡数量
     */
    private Integer planChickenQuantity;

    /**
     * 上鸡时间
     */
    private Date planTime;

    /**
     * 技术员姓名
     */
    private String technician;

    /**
     * 技术员id
     */
    private Integer technicianId;

    /**
     * 备注
     */
    private String notes;
}
