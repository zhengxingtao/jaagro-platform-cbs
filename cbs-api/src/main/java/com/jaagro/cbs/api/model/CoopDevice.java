package com.jaagro.cbs.api.model;

import java.io.Serializable;
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
public class CoopDevice implements Serializable {
    /**
     * 鸡舍设备表id
     */
    private Integer id;

    /**
     * 鸡舍id
     */
    private Integer coopId;

    /**
     * 养殖场id
     */
    private Integer plantId;

    /**
     * 检测类型(1-温度,2-湿度,3-光照强度,4-氮气,5-二氧化碳,6-通风,7-负压值)
     */
    private Integer deviceType;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 生产商
     */
    private String producer;

    /**
     * 设备型号（生产商家的型号）
     */
    private String modelNumber;

    /**
     * 设备状态（0-拆除，1-正常 2-故障）
     */
    private Integer coopDeviceStatus;

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
     * 是否有效（1-有效 0-无效）
     */
    private Boolean enable;

    private static final long serialVersionUID = 1L;
}