package com.jaagro.cbs.biz.service.impl;

import com.jaagro.cbs.api.dto.base.CustomerContactsReturnDto;
import com.jaagro.cbs.api.dto.plan.ReturnBreedingPlanDto;
import com.jaagro.cbs.api.dto.progress.BreedingBatchParamTrackingDto;
import com.jaagro.cbs.api.dto.progress.BreedingProgressDto;
import com.jaagro.cbs.api.dto.progress.BreedingRecordDto;
import com.jaagro.cbs.api.dto.progress.ListBatchCriteriaDto;
import com.jaagro.cbs.api.model.*;
import com.jaagro.cbs.api.service.BreedingProgressService;
import com.jaagro.cbs.biz.mapper.BatchPlantCoopMapperExt;
import com.jaagro.cbs.biz.mapper.BreedingPlanMapperExt;
import com.jaagro.cbs.biz.mapper.BreedingRecordMapperExt;
import com.jaagro.cbs.biz.mapper.CoopMapperExt;
import com.jaagro.cbs.biz.service.CustomerClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

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
    private BreedingPlanMapperExt breedingPlanMapper;
    @Autowired
    private BatchPlantCoopMapperExt batchPlantCoopMapper;
    @Autowired
    private CoopMapperExt coopMapper;
    @Autowired
    private BreedingRecordMapperExt breedingRecordMapper;
    @Autowired
    private CustomerClientService customerClientService;

    /**
     * 根据养殖计划Id获取养殖过程喂养头信息
     *
     * @param planId
     * @return
     */
    @Override
    public BreedingProgressDto getBreedingProgressById(Integer planId) {
        BreedingPlan breedingPlan = breedingPlanMapper.selectByPrimaryKey(planId);
        Assert.notNull(breedingPlan, "养殖计划不存在");

        CustomerContactsReturnDto customerDto = customerClientService.getCustomerContactByCustomerId(breedingPlan.getCustomerId());
        BreedingProgressDto breedingProgressDto = new BreedingProgressDto();
        breedingProgressDto.setBatchNo(breedingPlan.getBatchNo());
        breedingProgressDto.setPlanChickenQuantity(breedingPlan.getPlanChickenQuantity());
        breedingProgressDto.setPlanTime(breedingPlan.getPlanTime());

        //该计划上报死掉的鸡
        BreedingRecordExample breedingRecordExample = new BreedingRecordExample();
        breedingRecordExample.createCriteria().andPlanIdEqualTo(planId).andRecordTypeEqualTo(4);
        List<BreedingRecord> breedingRecordDos = breedingRecordMapper.selectByExample(breedingRecordExample);
        BigDecimal deadChickenCount = new BigDecimal(0.00);
        for (BreedingRecord breedingRecordDo : breedingRecordDos) {
            deadChickenCount = deadChickenCount.add(breedingRecordDo.getBreedingValue());
        }

        //存栏量：=breedingPlan.getPlanChickenQuantity()减去该养殖场死去的鸡数量
        BigDecimal planQuantity = new BigDecimal(breedingPlan.getPlanChickenQuantity());
        BigDecimal livingQuantity = planQuantity.subtract(deadChickenCount);
        breedingProgressDto.setLivingChickenQuantity(livingQuantity);


        if (null != customerDto) {
            breedingProgressDto.setContactPhone(customerDto.getPhone());
            breedingProgressDto.setCustomerName(customerDto.getCustomerName());
            breedingProgressDto.setCustomerAddress(customerDto.getAddress());
        }

        BatchPlantCoopExample example = new BatchPlantCoopExample();
        example.createCriteria().andPlanIdEqualTo(planId);
        List<BatchPlantCoop> batchPlantCoopDos = batchPlantCoopMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(batchPlantCoopDos)) {
            Set<Integer> plantIds = new HashSet<>();
            List<Coop> coopDos = new ArrayList<>();
            for (BatchPlantCoop batchPlantCoop : batchPlantCoopDos) {
                plantIds.add(batchPlantCoop.getPlanId());
                Coop coopDo = coopMapper.selectByPrimaryKey(batchPlantCoop.getCoopId());
                coopDos.add(coopDo);
            }
            Map<Integer, List<Coop>> coopMap = new HashMap<>();
            for (Integer plantId : plantIds) {
                List<Coop> coops = coopDos.stream().filter(c -> plantId.equals(c.getPlantId())).collect(Collectors.toList());
                coopMap.put(plantId, coops);
            }
            breedingProgressDto.setCoopMap(coopMap);
        }
        return breedingProgressDto;
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
