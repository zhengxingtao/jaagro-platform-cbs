package com.jaagro.cbs.api.service;


import com.github.pagehelper.PageInfo;
import com.jaagro.cbs.api.dto.plan.BreedingPlanParamDto;
import com.jaagro.cbs.api.dto.plan.CreateBreedingPlanDto;

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

    PageInfo listBreedingPlan(BreedingPlanParamDto dto);
}
