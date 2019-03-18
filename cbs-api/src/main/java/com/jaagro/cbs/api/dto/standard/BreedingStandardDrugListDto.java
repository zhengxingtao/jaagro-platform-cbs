package com.jaagro.cbs.api.dto.standard;

import lombok.Data;
import lombok.Value;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 养殖计划药品参数
 * @author yj
 * @date 2019/3/17 17:42
 */
@Data
@Accessors(chain = true)
public class BreedingStandardDrugListDto implements Serializable{
    /**
     * 日龄起(包含)
     */
    @NotNull(message = "{dayAgeStart.NotNull}")
    private Integer dayAgeStart;

    /**
     * 日龄止(包含)
     */
    @NotNull(message = "{dayAgeEnd.NotNull}")
    @Min(value = 1,message = "{dayAgeEnd.Min}")
    private Integer dayAgeEnd;

    /**
     * 停药标识(0-否,1-是)
     */
    private Boolean stopDrugFlag;

    /**
     * 养殖模板id
     */
    @NotNull(message = "{standardId.NotNull}")
    @Min(value = 1,message = "{standardId.Min}")
    private Integer standardId;

    /**
     * 药品配置明细列表
     */
    private List<BreedingStandardDrugItemDto> breedingStandardDrugItemVoList;
}
