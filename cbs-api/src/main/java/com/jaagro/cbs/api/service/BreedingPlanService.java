package com.jaagro.cbs.api.service;


import com.github.pagehelper.PageInfo;
import com.jaagro.cbs.api.dto.farmer.BreedingBatchParamListDto;
import com.jaagro.cbs.api.dto.farmer.BreedingPlanDetailDto;
import com.jaagro.cbs.api.dto.plan.*;

import java.util.List;


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
     *
     * @param createPlanContractDto
     * @author yj
     */
    void createPlanContract(CreatePlanContractDto createPlanContractDto);

    /**
     * 分页查询养殖计划
     *
     * @param dto
     * @return
     */
    PageInfo listBreedingPlan(BreedingPlanParamDto dto);

    /**
     * 更新养殖计划
     *
     * @param dto
     */
    void upDateBreedingPlanDetails(UpdateBreedingPlanDto dto);

    /**
     * 养殖计划详情
     *
     * @param planId
     * @return
     */
    ReturnBreedingPlanDetailsDto breedingPlanDetails(Integer planId);

    /**
     * 待上鸡签收详情
     *
     * @param planId
     * @return
     */
    ReturnChickenSignDetailsDto chickenSignDetails(Integer planId);

    /**
     * 养殖中详情
     *
     * @param planId
     */
    ReturnBreedingDetailsDto breedingDetails(Integer planId);

    /**
     * 获取养殖计划鸡舍信息
     * @author yj
     * @param planId
     * @return
     */
    List<BreedingPlanCoopDto> listBreedingPlanCoopsForChoose(Integer planId);

    /**
     * 养殖计划参数配置
     * @author yj
     * @param dto
     */
    void breedingPlanParamConfiguration(BreedingPlanParamConfigurationDto dto);

    /**
     * 根据计划id获取当前日龄
     *
     * @param planId
     * @return
     */
     long getDayAge(Integer planId) throws Exception;

    /**
     * 批次详情(农户app)
     * @author yj
     * @param planId
     * @return
     */
    BreedingPlanDetailDto getBatchDetail(Integer planId);

    /**
     * 养殖过程批次参数
     * @param planId
     * @param coopId
     * @param dayAge
     * @param strDate
     * @return
     * @throws Exception if has error(异常说明)
     */
    BreedingBatchParamListDto getBreedingBatchParamForApp(Integer planId, Integer coopId, Integer dayAge, String strDate) throws Exception;



}
