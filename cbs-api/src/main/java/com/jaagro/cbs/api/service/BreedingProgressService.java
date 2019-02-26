package com.jaagro.cbs.api.service;

import com.jaagro.cbs.api.dto.progress.BreedingBatchParamTrackingDto;
import com.jaagro.cbs.api.dto.progress.BreedingProgressDto;
import com.jaagro.cbs.api.dto.progress.BreedingRecordDto;

import java.util.List;

/**
 * @Author gavin
 *
 * @Date 20190225
 */
public interface BreedingProgressService {
    /**
     * 根据养殖计划Id获取养殖过程喂养头信息
     * @param planId  养殖计划id
     * @param plantId 养殖厂id
     * @return
     */
  BreedingProgressDto getBreedingProgressById(Integer planId, Integer plantId);

    /**
     * 获取每天养殖参数详情
     * @param planId
     * @param coopId
     * @param dayAge
     * @param strDate
     * @return
     */
  List<BreedingBatchParamTrackingDto> getBreedingBatchParamById(Integer planId, Integer coopId, Integer dayAge,String strDate);

    /**
     * 获取每天养殖喂养情况
     * @param planId
     * @param coopId
     * @param dayAge
     * @return
     */
  BreedingRecordDto getBreedingRecordsById(Integer planId, Integer coopId, Integer dayAge);


}
