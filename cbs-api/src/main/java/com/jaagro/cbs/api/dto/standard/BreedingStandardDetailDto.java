package com.jaagro.cbs.api.dto.standard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 养殖参数模板详情
 * @author yj
 * @date 2019/2/26 14:57
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class BreedingStandardDetailDto implements Serializable{
    private static final long serialVersionUID = -3826572533168281991L;
    /**
     * 日龄列表
     */
    private List<Integer> dayAgeList;
    /**
     * 养殖参数列表
     */
    private List<BreedingParameterDto> breedingParameterDtoList;
}
