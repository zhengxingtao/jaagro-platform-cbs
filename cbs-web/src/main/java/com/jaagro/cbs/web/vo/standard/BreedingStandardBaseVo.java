package com.jaagro.cbs.web.vo.standard;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 养殖模板基础信息
 * @author yj
 * @date 2019/3/16 17:53
 */
@Data
@Accessors(chain = true)
public class BreedingStandardBaseVo implements Serializable{
    private static final long serialVersionUID = -121153036291384599L;
    /**
     * 养殖标准方案表id
     */
    private Integer id;

    /**
     * 标准方案名称
     */
    private String standardName;

    /**
     * 养殖类型(1-平养,2-棚养,3-笼养)
     */
    private Integer breedingType;

    /**
     * 养殖天数
     */
    private Integer breedingDays;
}
