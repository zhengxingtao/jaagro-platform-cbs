package com.jaagro.cbs.api.dto.standard;

import com.jaagro.cbs.api.model.BreedingStandardParameter;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 养殖参数在每个日龄上的值
 * @author yj
 * @date 2019/3/13 10:54
 */
@Data
@Accessors(chain = true)
public class BreedingParameterListDto implements Serializable{
    /**
     * 养殖模板id
     */
    private Integer breedingStandardId;
    /**
     * 参数名称
     */
    private String paramName;
    /**
     * 参数类型（10-温度,11-湿度,12-光照强度,13-光照时间,14-氮气,15-二氧化碳,16-通风,17-负压值,20-喂料(次/日),21-喂水(次/日),22-饲喂(克/日),23-体重(克/只/日),24-死淘(只),25-药品/疫苗)
     */
    private Integer paramType;
    /**
     * 数值类型(1-区间值,2-标准值,3-临界值)
     */
    private Integer valueType;
    /**
     * 单位(摄氏度℃｜百分比%｜百万分比ppm｜立方英尺每分钟cfm｜小时H｜勒克斯lux｜只,｜次/日｜克/只/日|只/M/平方)
     */
    private String unit;
    /**
     * 养殖参数状态（1-启用 0 -未启用）
     */
    private Integer status;

    /**
     * 是否必要(0-不必要,1-必要)
     */
    private Boolean necessary;
    /**
     /**
     * 养殖参数列表
     */
    private List<BreedingStandardParameterItemDto> breedingStandardParameterList;
}
