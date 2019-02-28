package com.jaagro.cbs.api.dto.plan;

import com.jaagro.cbs.api.dto.standard.BreedingParameterDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.List;

/**
 * 养殖计划参数配置参数
 * @author yj
 * @date 2019/2/27 18:03
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class BreedingPlanParamConfigurationDto implements Serializable{
    /**
     * 养殖参数列表
     */
    @NotEmpty(message = "{breedingParameterDtoList.NotEmpty}")
    private List<BreedingParameterDto> breedingParameterDtoList;
    /**
     * 鸡舍配置列表
     */
    @NotEmpty(message = "{breedingPlanCoopDtoList.NotEmpty}")
    private List<BreedingPlanCoopDto> breedingPlanCoopDtoList;
}
