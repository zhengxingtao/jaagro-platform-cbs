package com.jaagro.cbs.api.dto.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author :baiyiran
 * @date :2019/02/27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BatchInfoCriteriaDto implements Serializable {
    /**
     * 养殖计划id
     */
    private Integer planId;

    /**
     * 日期时间
     */
    private String todayDate;
}