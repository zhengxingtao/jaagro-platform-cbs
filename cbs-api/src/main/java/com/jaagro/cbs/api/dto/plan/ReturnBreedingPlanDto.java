package com.jaagro.cbs.api.dto.plan;

import com.jaagro.cbs.api.dto.plant.ListCoopDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author @Gao.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ReturnBreedingPlanDto implements Serializable {

    /**
     * 计划id
     */
    private Integer id;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 养殖户id
     */
    private Integer customerId;

    /**
     * 养殖户名称
     */
    private Integer customerName;

    /**
     * 养殖户电话
     */
    private Integer customerPhone;

    /**
     * 计划状态(此列表只展示3-养殖中,4-待出栏确认)
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
     * 鸡舍列表
     */
    private List<ListCoopDto> coopDtoList;
}
