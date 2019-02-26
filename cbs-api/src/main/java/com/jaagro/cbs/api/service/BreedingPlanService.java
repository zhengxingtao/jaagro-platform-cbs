package com.jaagro.cbs.api.service;


import com.jaagro.cbs.api.dto.plan.CreateBreedingPlanDto;
import com.jaagro.cbs.api.dto.plan.CreatePlanContractDto;

/**
 * 养殖计划管理
 *
 * @author @Gao.
 */
public interface BreedingPlanService {
    /**
     * 创建养殖计划
     *
     * @param dto
     */
    void createBreedingPlan(CreateBreedingPlanDto dto);

    /**
     * 录入合同
     * @author yj
     * @param createPlanContractDto
     */
    void createPlanContract(CreatePlanContractDto createPlanContractDto);
}
