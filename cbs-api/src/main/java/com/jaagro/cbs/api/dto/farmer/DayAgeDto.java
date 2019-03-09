package com.jaagro.cbs.api.dto.farmer;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author yj
 * @date 2019/3/7 15:11
 */
@Data
@Accessors(chain = true)
public class DayAgeDto implements Serializable {
    private static final long serialVersionUID = 1532394163963063478L;
    /**
     * 日龄
     */
    private Integer dayAge;
    /**
     * 日龄对应的日期,格式:02-28
     */
    private String strDate;
}
