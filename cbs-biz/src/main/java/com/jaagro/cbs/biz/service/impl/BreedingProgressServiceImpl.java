package com.jaagro.cbs.biz.service.impl;

import com.jaagro.cbs.api.dto.progress.BreedingBatchParamTrackingDto;
import com.jaagro.cbs.api.dto.progress.BreedingProgressDto;
import com.jaagro.cbs.api.dto.progress.BreedingRecordDto;
import com.jaagro.cbs.api.model.BreedingPlan;
import com.jaagro.cbs.api.service.BreedingProgressService;
import com.jaagro.cbs.biz.mapper.BreedingPlanMapperExt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 养殖过程管理
 *
 * @author gavin
 * @date :2019/02/25
 */
@Slf4j
@Service
public class BreedingProgressServiceImpl implements BreedingProgressService {

    @Autowired
    private BreedingPlanMapperExt breedingPlanMapperExt;
    /**
     * 根据养殖计划Id获取养殖过程喂养头信息
     *
     * @param planId
     * @return
     */
    @Override
    public BreedingProgressDto getBreedingProgressById(Integer planId) {
        BreedingPlan breedingPlan = breedingPlanMapperExt.selectByPrimaryKey(planId);
        Assert.notNull(breedingPlan, "养殖计划不存在");

        BreedingProgressDto breedingProgressDto = new BreedingProgressDto();
        breedingProgressDto.setBatchNo(breedingPlan.getBatchNo());
        breedingProgressDto.setPlanChickenQuantity(breedingPlan.getPlanChickenQuantity());


        return null;
    }

    /**
     * 获取每天养殖参数详情
     *
     * @param planId
     * @param coopId
     * @param dayAge
     * @return
     */
    @Override
    public BreedingBatchParamTrackingDto getBreedingBatchParamById(Integer planId, Integer coopId, Integer dayAge) {
        return null;
    }

    /**
     * 获取每天养殖喂养情况
     *
     * @param planId
     * @param coopId
     * @param dayAge
     * @return
     */
    @Override
    public BreedingRecordDto getBreedingRecordsById(Integer planId, Integer coopId, Integer dayAge) {
        return null;
    }
}
