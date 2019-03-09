package com.jaagro.cbs.api.dto.farmer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 养殖计划详情
 * @author yj
 * @date 2019/3/5 16:58
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class BreedingPlanDetailDto implements Serializable{
    private static final long serialVersionUID = -8675493136651098575L;
    /**
     * 批次详情
     */
    private ReturnBreedingBatchDetailsDto returnBreedingBatchDetailsDto;
    /**
     * 批次养殖场列表
     */
    private List<BatchPlantDto> batchPlantDtoList;
}
