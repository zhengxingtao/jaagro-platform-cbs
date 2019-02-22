package com.jaagro.cbs.api.service;


import com.jaagro.cbs.api.dto.plan.CreateBreedingPlanDto;

/**
 * 养殖计划管理
 *
 * @author @Gao.
 */
public interface BreedingStardandService {
    /**
     * 创建养殖计划
     *
     * @param dto
     */
    void createBreedingPlan(CreateBreedingPlanDto dto);
}
