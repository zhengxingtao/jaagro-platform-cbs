package com.jaagro.cbs.biz.service.impl;

import com.jaagro.cbs.api.dto.plan.CreateBreedingPlanDto;
import com.jaagro.cbs.api.service.BreedingPlanService;
import com.jaagro.cbs.biz.mapper.BatchPlantCoopMapperExt;
import com.jaagro.cbs.biz.mapper.BreedingPlanMapperExt;
import com.jaagro.cbs.biz.model.BatchPlantCoop;
import com.jaagro.cbs.biz.model.BreedingPlan;
import com.jaagro.cbs.biz.utils.SequenceCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;


/**
 * 养殖计划管理
 *
 * @author @Gao.
 */
@Slf4j
@Service
public class BreedingPlanServiceImpl implements BreedingPlanService {

    @Autowired
    private SequenceCodeUtils sequenceCodeUtils;
    @Autowired
    private BreedingPlanMapperExt breedingPlanMapper;
    @Autowired
    private BatchPlantCoopMapperExt batchPlantCoopMapper;

    /**
     * 创建养殖计划
     *
     * @param dto
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createBreedingPlan(CreateBreedingPlanDto dto) {
        String batchNo = sequenceCodeUtils.genSeqCode("AT");
        BreedingPlan breedingPlan = new BreedingPlan();
        breedingPlan
                .setBatchNo(batchNo)
                .setCreateUserId(null)
                .setCreateUserName(null)
                .setPlanStatus(0);
        BeanUtils.copyProperties(dto, breedingPlan);
        //插入养殖计划
        breedingPlanMapper.insertSelective(breedingPlan);
        //插入养殖关联表
        if (CollectionUtils.isEmpty(dto.getPlantsId())) {
            List<Integer> plantsIds = dto.getPlantsId();
            for (Integer plantsId : plantsIds) {
                BatchPlantCoop batchPlantCoop = new BatchPlantCoop();
                batchPlantCoop
                        .setBatchNo(batchNo)
                        .setCreateUserId(null)
                        .setPlantId(plantsId);
            }
        }
    }
}
