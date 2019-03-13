package com.jaagro.cbs.api.dto.farmer;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 创建养殖记录参数
 * @author yj
 * @date 2019/3/9 9:22
 */
@Data
@Accessors(chain = true)
public class CreateBreedingRecordDto implements Serializable{
    private static final long serialVersionUID = 2735988095960604599L;
    /**
     * 养殖计划id
     */
    private Integer planId;

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
     * 喂量
     */
    private BigDecimal breedingValue;

    /**
     * 计量单位
     */
    private String unit;

    /**
     * 备注
     */
    private String notes;

    /**
     * 饲喂时间
     */
    private Date breedingTime;

    /**
     * 喂料明细
     */
    private List<BreedingRecordItemsDto> breedingRecordItemsDtoList;
}
