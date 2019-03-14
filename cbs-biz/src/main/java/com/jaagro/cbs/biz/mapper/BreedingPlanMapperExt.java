package com.jaagro.cbs.biz.mapper;

import javax.annotation.Resource;

import com.jaagro.cbs.api.dto.farmer.BreedingPlanDetailDto;
import com.jaagro.cbs.api.dto.farmer.ReturnBreedingBatchDetailsDto;
import com.jaagro.cbs.api.dto.order.PurchaseOrderPresetCriteriaDto;
import com.jaagro.cbs.api.dto.order.ReturnPurchaseOrderPresetDto;
import com.jaagro.cbs.api.dto.plan.BreedingPlanParamDto;
import com.jaagro.cbs.api.dto.plan.ReturnBreedingPlanDto;
import com.jaagro.cbs.api.model.BreedingPlan;
import com.jaagro.cbs.api.model.BreedingPlanExample;
import com.jaagro.cbs.biz.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 根据客户id 查询当前客户所有养殖批次详情
     *
     * @param customerId
     * @return
     */
    List<ReturnBreedingBatchDetailsDto> listBreedingPlanByCustomerId(@Param("customerId") Integer customerId);

    /**
     * 根据当前客户id 查询所有养殖计划id 集合
     *
     * @param customerId
     * @return
     */
    List<Integer> listBreedingPlanIdByCustomerId(@Param("customerId") Integer customerId);

    /**
     * 根据客户id查询
     *
     * @param customerId
     * @return
     */
    List<BreedingPlanDetailDto> listByCustomerId(@Param("customerId") Integer customerId);

    /**
     * @param dto
     * @return
     */
    List<ReturnPurchaseOrderPresetDto> listPurchaseOrderPreset(PurchaseOrderPresetCriteriaDto dto);
}