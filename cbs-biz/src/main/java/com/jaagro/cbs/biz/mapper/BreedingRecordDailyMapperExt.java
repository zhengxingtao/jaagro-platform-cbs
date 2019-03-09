package com.jaagro.cbs.biz.mapper;

import com.jaagro.cbs.api.model.BreedingRecordDaily;
import com.jaagro.cbs.api.model.BreedingRecordDailyExample;
import com.jaagro.cbs.biz.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;
import java.util.List;


/**
 * BreedingRecordDailyMapperExt接口
 *
 * @author :generator
 * @date :2019/2/21
 */
@Resource
public interface BreedingRecordDailyMapperExt extends BaseMapper<BreedingRecordDaily, BreedingRecordDailyExample> {

    /**
     * 根据日期删除
     *
     * @param todayDate
     */
    void deleteByDate(@Param("todayDate") String todayDate);

    /**
     * 批量插入
     *
     * @param dailyList
     */
    void insertBatch(List<BreedingRecordDaily> dailyList);
}