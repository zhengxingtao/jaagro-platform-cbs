package com.jaagro.cbs.biz.mapper;

import javax.annotation.Resource;

import com.jaagro.cbs.api.dto.plan.BreedingPlanParamDto;
import com.jaagro.cbs.api.dto.plan.ReturnBreedingPlanDto;
import com.jaagro.cbs.api.model.BreedingPlan;
import com.jaagro.cbs.api.model.BreedingPlanExample;
import com.jaagro.cbs.biz.mapper.base.BaseMapper;

import java.util.List;


/**
 * BreedingPlanMapperExt接口
 *
 * @author :generator
 * @date :2019/2/21
 */
@Resource
public interface BreedingPlanMapperExt extends BaseMapper<BreedingPlan, BreedingPlanExample> {

    /**
     * 查询养殖列表信息
     *
     * @param dto
     * @return
     */
    List<ReturnBreedingPlanDto> listBreedingPlan(BreedingPlanParamDto dto);
}