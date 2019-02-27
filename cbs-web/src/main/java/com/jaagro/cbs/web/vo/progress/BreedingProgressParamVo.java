package com.jaagro.cbs.web.vo.progress;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @description: 养殖过程参数
 * @author: gavin
 * @create: 2019-02-27
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BreedingProgressParamVo {
    /**
     * 养殖计划id
     */
    private Integer planId;
    /**
     * 养殖厂id
     */
    private Integer plantId;
    /**
     * 鸡舍id
     */
    private Integer coopId;
    /**
     * 日龄
     */
    private Integer dayAge;
    /**
     * 日龄对应的日期,格式:2019-02-28
     */
    private String strDate;
}
