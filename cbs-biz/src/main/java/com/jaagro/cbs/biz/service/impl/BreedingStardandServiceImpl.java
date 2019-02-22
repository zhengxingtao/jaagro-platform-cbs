package com.jaagro.cbs.biz.service.impl;

import com.jaagro.cbs.api.dto.plan.CreateBreedingPlanDto;
import com.jaagro.cbs.api.service.BreedingPlanService;
import com.jaagro.cbs.biz.utils.SequenceCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 养殖计划管理
 *
 * @author @Gao.
 */
@Slf4j
@Service
public class BreedingStardandServiceImpl implements BreedingPlanService {

    @Autowired
    private SequenceCodeUtils sequenceCodeUtils;
    /**
     * 创建养殖计划
     *
     * @param dto
     */
    @Override
    public void createBreedingPlan(CreateBreedingPlanDto dto) {

    }
}
