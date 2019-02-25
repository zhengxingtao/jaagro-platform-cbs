package com.jaagro.cbs.api.service;

import com.jaagro.cbs.api.dto.plan.ReturnBreedingPlanDto;
import com.jaagro.cbs.api.dto.progress.ListBatchCriteriaDto;

import java.util.List;

public interface BreedingProgressService {

    /**
     * 批次管理
     *
     * @param criteriaDto
     * @return
     */
    List<ReturnBreedingPlanDto> listPlanByCriteria(ListBatchCriteriaDto criteriaDto);
}
