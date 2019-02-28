package com.jaagro.cbs.api.service;


import com.github.pagehelper.PageInfo;
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
     * @param plantId
     * @return
     */
    ReturnChickenSignDetailsDto chickenSignDetails(Integer plantId);

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
}
