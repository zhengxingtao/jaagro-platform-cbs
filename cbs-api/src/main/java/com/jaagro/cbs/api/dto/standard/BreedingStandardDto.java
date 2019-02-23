package com.jaagro.cbs.api.dto.standard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author :Gavin
 * @date :2019/02/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BreedingStandardDto implements Serializable {


    private static final long serialVersionUID = -7384357842932479972L;
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
    /**
     * 模板的标准参数
     */
    private List<BreedingStandardParameterDto> standardParameterDtos;
}
