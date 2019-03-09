package com.jaagro.cbs.api.dto.farmer;

import com.jaagro.cbs.api.dto.progress.BreedingBatchParamTrackingDto;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 养殖计划下所有的养殖参数
 * @author yj
 * @date 2019/3/7 15:10
 */
@Data
@Accessors(chain = true)
public class BreedingBatchParamListDto implements Serializable{
    private static final long serialVersionUID = 6404097107053888770L;
    /**
     * 日龄列表
     */
    private List<DayAgeDto> dayAgeDtoList;
    /**
     * 参数列表
     */
    private List<BreedingBatchParamTrackingDto> breedingBatchParamTrackingDtoList;
}
