package com.jaagro.cbs.api.dto.standard;

import com.jaagro.cbs.api.model.BreedingStandardParameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 养殖参数模板
 * @author yj
 * @date 2019/2/26 15:04
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class BreedingParameterDto implements Serializable{
    /**
     * 日龄
     */
    private Integer dayAge;
    /**
     * 养殖参数列表
     */
    private List<BreedingStandardParameter> breedingStandardParameterList;
}
