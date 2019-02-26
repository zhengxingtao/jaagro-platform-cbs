package com.jaagro.cbs.biz.service.impl;

import com.jaagro.cbs.api.dto.base.CustomerContactsReturnDto;
import com.jaagro.cbs.api.dto.plan.ReturnBreedingPlanDto;
import com.jaagro.cbs.api.dto.progress.BreedingBatchParamTrackingDto;
import com.jaagro.cbs.api.dto.progress.BreedingProgressDto;
import com.jaagro.cbs.api.dto.progress.BreedingRecordDto;
import com.jaagro.cbs.api.dto.progress.ListBatchCriteriaDto;
import com.jaagro.cbs.api.model.*;
import com.jaagro.cbs.api.service.BreedingProgressService;
import com.jaagro.cbs.biz.mapper.*;
import com.jaagro.cbs.biz.service.CustomerClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
    private BreedingBatchParameterMapperExt breedingBatchParameterMapper;
    @Autowired
    private CoopDeviceMapperExt coopDeviceMapper;
    @Autowired
    private DeviceValueMapperExt deviceValueMapper;
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


        BreedingProgressDto breedingProgressDto = new BreedingProgressDto();
        breedingProgressDto.setBatchNo(breedingPlan.getBatchNo());
        breedingProgressDto.setPlanChickenQuantity(breedingPlan.getPlanChickenQuantity());
        breedingProgressDto.setPlanTime(breedingPlan.getPlanTime());

        //该计划上报死掉的鸡
        BreedingRecordExample breedingRecordExample = new BreedingRecordExample();
        breedingRecordExample.createCriteria().andPlanIdEqualTo(planId).andRecordTypeEqualTo(4).andEnableEqualTo(true);
        ;
        List<BreedingRecord> breedingRecordDos = breedingRecordMapper.selectByExample(breedingRecordExample);
        BigDecimal deadChickenCount = new BigDecimal(0.00);
        for (BreedingRecord breedingRecordDo : breedingRecordDos) {
            deadChickenCount = deadChickenCount.add(breedingRecordDo.getBreedingValue());
        }

        //存栏量：=breedingPlan.getPlanChickenQuantity()减去该养殖场死去的鸡数量
        BigDecimal planQuantity = new BigDecimal(breedingPlan.getPlanChickenQuantity());
        BigDecimal livingQuantity = planQuantity.subtract(deadChickenCount);
        breedingProgressDto.setLivingChickenQuantity(livingQuantity);

        CustomerContactsReturnDto customerDto = customerClientService.getCustomerContactByCustomerId(breedingPlan.getCustomerId());
        if (null != customerDto) {
            breedingProgressDto.setContactPhone(customerDto.getPhone());
            breedingProgressDto.setCustomerName(customerDto.getCustomerName());
            breedingProgressDto.setCustomerAddress(customerDto.getAddress());
        }

        //养殖计划的鸡舍信息
        BatchPlantCoopExample example = new BatchPlantCoopExample();
        example.createCriteria().andPlanIdEqualTo(planId).andEnableEqualTo(true);
        ;
        List<BatchPlantCoop> batchPlantCoopDos = batchPlantCoopMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(batchPlantCoopDos)) {
            Set<Integer> plantIds = new HashSet<>();
            List<Coop> coopDos = new ArrayList<>();
            for (BatchPlantCoop batchPlantCoop : batchPlantCoopDos) {
                plantIds.add(batchPlantCoop.getPlantId());
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
    public List<BreedingBatchParamTrackingDto> getBreedingBatchParamById(Integer planId, Integer coopId, Integer dayAge) {

        List<BreedingBatchParamTrackingDto> returnDtos = new ArrayList<>();
        //养殖计划所用的参数
        BreedingBatchParameterExample batchParameterExample = new BreedingBatchParameterExample();
        batchParameterExample.createCriteria().andPlanIdEqualTo(planId).andDayAgeEqualTo(dayAge).andEnableEqualTo(true);
        List<BreedingBatchParameter> breedingBatchParameterDos = breedingBatchParameterMapper.selectByExample(batchParameterExample);

        if (!CollectionUtils.isEmpty(breedingBatchParameterDos)) {
            //鸡舍绑定的设备
            CoopDeviceExample coopDeviceExample = new CoopDeviceExample();
            coopDeviceExample.createCriteria().andCoopIdEqualTo(coopId).andEnableEqualTo(true);
            List<CoopDevice> coopDeviceDos = coopDeviceMapper.selectByExample(coopDeviceExample);
            Set<Integer> deviceIds = new HashSet<>();
            if (!CollectionUtils.isEmpty(coopDeviceDos)) {
                for (CoopDevice coopDeviceDo : coopDeviceDos) {
                    deviceIds.add(coopDeviceDo.getId());
                }
            }
            //鸡舍绑定的设备采集上来的最新值
            DeviceValueExample deviceValueExample = new DeviceValueExample();
            deviceValueExample.createCriteria().andDeviceIdIn(new ArrayList<>(deviceIds));
            List<DeviceValue> deviceValueDos = deviceValueMapper.selectByExample(deviceValueExample);

            for (BreedingBatchParameter breedingBatchParameterDo : breedingBatchParameterDos) {
                BreedingBatchParamTrackingDto returnDto = new BreedingBatchParamTrackingDto();
                BeanUtils.copyProperties(breedingBatchParameterDo, returnDto);
                //养殖参数对应的设备列表
                if (!CollectionUtils.isEmpty(coopDeviceDos)) {
                    List<CoopDevice> deviceDos = coopDeviceDos.stream().filter(c -> c.getDeviceType().equals(breedingBatchParameterDo.getParamType())).collect(Collectors.toList());
                    if (!CollectionUtils.isEmpty(deviceDos)) {
                        List<DeviceValue> actualResult = new ArrayList<>();
                        for (CoopDevice deviceDo : deviceDos) {
                            if (!CollectionUtils.isEmpty(deviceValueDos)) {
                                List<DeviceValue> deviceValues = deviceValueDos.stream().filter(c -> deviceDo.getId().equals(c.getDeviceId())).collect(Collectors.toList());
                                if (!CollectionUtils.isEmpty(deviceValues)) {
                                    actualResult.add(deviceValues.get(0));
                                }
                            }
                        }
                        returnDto.setActualResult(actualResult);
                        //如果养殖参数的值类型是"区间值"
                        if(breedingBatchParameterDo.getValueType().equals(1)){
                            if(!CollectionUtils.isEmpty(actualResult)){
                                for (DeviceValue deviceValue : actualResult) {
                                    //如果检测值不在标准区间里面，那个报警
                                    if(breedingBatchParameterDo.getLowerLimit().compareTo(deviceValue.getCurrentValue())>0 || deviceValue.getCurrentValue().compareTo(breedingBatchParameterDo.getUpperLimit())>0)
                                    {
                                        returnDto.setAlarmMessage("检测值异常，其及时处理！");

                                    }
                                }
                            }

                        }
                    }
                }
                returnDtos.add(returnDto);
            }
        }
        return returnDtos;
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

        BreedingRecordDto breedingRecordDto = new BreedingRecordDto();
        //养殖计划的鸡舍在某日龄上的喂料记录
        BreedingRecordExample breedingRecordExample = new BreedingRecordExample();
        breedingRecordExample.createCriteria().andPlanIdEqualTo(planId).andCoopIdEqualTo(coopId).andDayAgeEqualTo(dayAge).andRecordTypeEqualTo(1).andEnableEqualTo(true);
        ;
        List<BreedingRecord> feedFoodList = breedingRecordMapper.selectByExample(breedingRecordExample);
        int feedFoodTimes = feedFoodList.size();
        BigDecimal feedFoodWeight = new BigDecimal(0.00);
        for (BreedingRecord breedingRecordDo : feedFoodList) {
            feedFoodWeight = feedFoodWeight.add(breedingRecordDo.getBreedingValue());
        }
        breedingRecordDto.setFeedFoodList(feedFoodList);
        breedingRecordDto.setFeedFoodTimes(feedFoodTimes);
        breedingRecordDto.setFeedFoodWeight(feedFoodWeight);

        //养殖计划的鸡舍在某日龄上的喂水记录
        breedingRecordExample = new BreedingRecordExample();
        breedingRecordExample.createCriteria().andPlanIdEqualTo(planId).andCoopIdEqualTo(coopId).andDayAgeEqualTo(dayAge).andRecordTypeEqualTo(5).andEnableEqualTo(true);
        ;
        List<BreedingRecord> feedWaterList = breedingRecordMapper.selectByExample(breedingRecordExample);
        int feedWaterTimes = feedWaterList.size();
        breedingRecordDto.setFeedWaterList(feedWaterList);
        breedingRecordDto.setFeedWaterTimes(feedWaterTimes);

        //养殖计划的鸡舍在某日龄上的喂水记录
        breedingRecordExample = new BreedingRecordExample();
        breedingRecordExample.createCriteria().andPlanIdEqualTo(planId).andCoopIdEqualTo(coopId).andDayAgeEqualTo(dayAge).andRecordTypeEqualTo(5).andEnableEqualTo(true);
        ;
        List<BreedingRecord> feedMedicineList = breedingRecordMapper.selectByExample(breedingRecordExample);
        int feedMedicineTimes = feedMedicineList.size();
        BigDecimal feedMedicineWeight = new BigDecimal(0.00);
        for (BreedingRecord breedingRecordDo : feedFoodList) {
            feedMedicineWeight = feedMedicineWeight.add(breedingRecordDo.getBreedingValue());
        }
        breedingRecordDto.setFeedMedicineWeight(feedMedicineWeight);
        breedingRecordDto.setFeedMedicineList(feedMedicineList);
        breedingRecordDto.setFeedMedicineTimes(feedMedicineTimes);

        return breedingRecordDto;
    }

    /**
     * 批次管理
     *
     * @param criteriaDto
     * @return
     */
    @Override
    public List<ReturnBreedingPlanDto> listPlanByCriteria(ListBatchCriteriaDto criteriaDto) {
        return null;
    }

}
