package com.jaagro.cbs.api.dto.standard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author :gain
 * @date :2019/02/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BreedingStandardParameterDto implements Serializable {


    private static final long serialVersionUID = 2983927390461640107L;

    /**
     * 参数id
     */
    private Integer id;
    /**
     * 参数名称
     */
    private String paramName;

    /**
     * 日龄
     */
    private Integer dayAge;

    /**
     * 参数类型（1-喂养参数 2-检测参数）
     */
    private Integer paramType;

    /**
     * 数值类型(1-区间值,2-标准值,3-临界值)
     */
    private Integer valueType;

    /**
     * 参考值下限（标准值时有效）
     */
    private BigDecimal lowerLimit;

    /**
     * 参考值上限(临界值时有效)
     */
    private BigDecimal upperLimit;

    /**
     * 单位(摄氏度℃｜百分比%｜百万分比ppm｜立方英尺每分钟cfm｜小时H｜勒克斯lux｜只,｜次/日｜克/只/日)
     */
    private String unit;

    /**
     * 超限是否报警(0-不报警,1-报警)
     */
    private Byte alarm;

}