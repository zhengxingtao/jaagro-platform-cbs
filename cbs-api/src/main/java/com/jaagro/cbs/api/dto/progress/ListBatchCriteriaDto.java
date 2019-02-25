package com.jaagro.cbs.api.dto.progress;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author baiyiran
 * @Date 2019/2/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ListBatchCriteriaDto implements Serializable {
    /**
     * 批次号
     */
    private Integer batchNo;

    /**
     * 客户名/手机号
     */
    private String keywords;

    /**
     * 状态
     */
    private Integer planStatus;

    /**
     * 页数
     */
    private Integer pageNum;

    /**
     * 每页条数
     */
    private Integer pageSize;

    /**
     * 租户id
     */
    private Integer tenantId;

}
