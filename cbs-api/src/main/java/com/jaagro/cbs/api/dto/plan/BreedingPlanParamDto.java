package com.jaagro.cbs.api.dto.plan;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author @Gao.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BreedingPlanParamDto implements Serializable {

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
     * 客户id
     */
    private List<Integer> customerIds;

    /**
     * 客户姓名
     */
    private String customerInfo;

    /**
     * 录单人
     */
    private String createUserName;

    /**
     * 计划列表状态
     */
    private Integer planStatus;

}
