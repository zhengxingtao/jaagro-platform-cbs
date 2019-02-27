package com.jaagro.cbs.api.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author :gaoxin
 * @date :2019/02/27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BreedingStandardParameter implements Serializable {
    /**
     * 参数id
     */
    private Integer id;

    /**
     * 标准方案id
     */
    private Integer standardId;

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
    private Boolean alarm;

    /**
     * 动保药品
     */
    private String dynamicMedicine;

    /**
     * 养殖参数状态（1-启用 0 -未启用）
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private Integer createUserId;

    /**
     * 更新时间
     */
    private Date modifyTime;

    /**
     * 更新人
     */
    private Integer modifyUserId;

    /**
     * 是否有效（1- 有效 0 -无效）
     */
    private Boolean enable;

    private static final long serialVersionUID = 1L;
}