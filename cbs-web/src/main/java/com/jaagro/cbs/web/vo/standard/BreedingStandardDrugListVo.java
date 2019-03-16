package com.jaagro.cbs.web.vo.standard;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 养殖模板药品配置信息
 * @author yj
 * @date 2019/3/15 22:08
 */
@Data
@Accessors(chain = true)
public class BreedingStandardDrugListVo implements Serializable {
    private static final long serialVersionUID = -1577874593401935360L;
    /**
     * 日龄起(包含)
     */
    private Integer dayAgeStart;

    /**
     * 日龄止(包含)
     */
    private Integer dayAgeEnd;

    /**
     * 停药标识(0-否,1-是)
     */
    private Boolean stopDrugFlag;

    /**
     * 药品配置明细列表
     */
    private List<BreedingStandardDrugItemVo> breedingStandardDrugItemVoList;
}
