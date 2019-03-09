package com.jaagro.cbs.biz.mapper;

import com.jaagro.cbs.api.dto.base.BatchInfoCriteriaDto;
import com.jaagro.cbs.api.model.BatchInfo;
import com.jaagro.cbs.api.model.BreedingRecord;
import com.jaagro.cbs.api.model.BreedingRecordExample;
import com.jaagro.cbs.biz.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;
import java.util.List;


/**
 * BreedingRecordMapperExt接口
 *
 * @author :generator
 * @date :2019/2/21
 */
@Resource
public interface BreedingRecordMapperExt extends BaseMapper<BreedingRecord, BreedingRecordExample> {

    /**
     * 查询每个批次的日汇总
     *
     * @param todayDate
     * @return
     */
    List<BreedingRecord> listSumByParams(@Param("todayDate") String todayDate);

    /**
     * 鸡舍养殖每日汇总
     *
     * @param todayDate
     * @return
     */
    List<BreedingRecord> listCoopDailySumByParams(@Param("todayDate") String todayDate);

    /**
     * 鸡舍当日累积喂料次数
     *
     * @param record
     * @return
     */
    Integer countFodderTimesByCoopId(BreedingRecord record);

    /**
     * 死淘数量 -- 根据鸡舍
     *
     * @param record
     * @return
     */
    Integer sumDeadAmountByCoopId(BreedingRecord record);

    /**
     * 批次养殖情况汇总
     *
     * @param todayDate
     * @return
     */
    List<BatchInfo> listBatchInfoByParams(@Param("todayDate") String todayDate);

    /**
     * 死淘数量 -- 根据批次
     *
     * @param record
     * @return
     */
    Integer sumDeadAmountByPlanId(BatchInfoCriteriaDto criteriaDto);
}