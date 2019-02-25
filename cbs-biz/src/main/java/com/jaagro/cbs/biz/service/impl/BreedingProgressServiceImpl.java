package com.jaagro.cbs.biz.service.impl;

import com.jaagro.cbs.api.dto.plan.ReturnBreedingPlanDto;
import com.jaagro.cbs.api.dto.progress.ListBatchCriteriaDto;
import com.jaagro.cbs.api.service.BreedingProgressService;
import com.jaagro.cbs.biz.mapper.BreedingPlanMapperExt;
import com.jaagro.cbs.biz.service.CustomerClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author baiyiran
 * @Date 2019/2/25
 */
@Service
public class BreedingProgressServiceImpl implements BreedingProgressService {

    @Autowired
    private BreedingPlanMapperExt breedingPlanMapper;
    @Autowired
    private CustomerClientService customerClientService;

    /**
     * 批次管理
     *
     * @param criteriaDto
     * @return
     */
    @Override
    public List<ReturnBreedingPlanDto> listPlanByCriteria(ListBatchCriteriaDto criteriaDto) {
        return null;
    }
}
