package com.jaagro.cbs.biz.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 批次养殖场鸡舍信息
 * @author yj
 * @date 2019/3/6 14:10
 */
@Data
@Accessors(chain = true)
public class BatchPlantCoopBo implements Serializable{
    /**
     * 计划id
     */
    private Integer planId;
    /**
     * 养殖场id
     */
    private Integer plantId;
    /**
     * 养殖场名称
     */
    private String plantName;
    /**
     * 鸡舍id
     */
    private Integer coopId;
    /**
     * 鸡舍编号
     */
    private String coopNo;
    /**
     * 鸡舍名称
     */
    private String coopName;
}
