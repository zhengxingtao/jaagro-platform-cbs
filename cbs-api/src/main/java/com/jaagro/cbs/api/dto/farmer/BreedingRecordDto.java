package com.jaagro.cbs.api.dto.farmer;

import com.jaagro.cbs.api.model.BreedingRecordItems;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 养殖记录
 *
 * @author yj
 * @date 2019/3/11 9:36
 */
@Data
@Accessors(chain = true)
public class BreedingRecordDto implements Serializable {
    private static final long serialVersionUID = -5551283497584270018L;
    /**
     * 批次养殖记录表id
     */
    private Integer id;

    /**
     * 养殖计划id
     */
    private Integer planId;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 鸡舍id
     */
    private Integer coopId;

    /**
     * 日龄
     */
    private Integer dayAge;

    /**
     * 喂养类型（1-投料 2-喂药 3-通风 4-死淘 5-喂水。。。。）
     */
    private Integer recordType;

    /**
     * 喂养数量
     */
    private BigDecimal breedingValue;

    /**
     * 计量单位
     */
    private String unit;

    /**
     * 饲喂时间
     */
    private Date breedingTime;

    /**
     * 备注
     */
    private String notes;

    /**
     * 是否有效(0-无效,1-有效)
     */
    private Boolean enable;

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
     * 喂养记录明细
     */
    private List<BreedingRecordItems> breedingRecordItemsList;
}
