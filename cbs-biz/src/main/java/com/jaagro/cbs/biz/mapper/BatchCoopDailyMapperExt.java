package com.jaagro.cbs.biz.mapper;

import com.jaagro.cbs.api.model.BatchCoopDaily;
import com.jaagro.cbs.api.model.BatchCoopDailyExample;
import com.jaagro.cbs.biz.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;
import java.util.List;


/**
 * BatchCoopDailyMapperExt接口
 *
 * @author :generator
 * @date :2019/2/21
 */
@Resource
public interface BatchCoopDailyMapperExt extends BaseMapper<BatchCoopDaily, BatchCoopDailyExample> {


    /**
     * 查询前一天的剩余鸡数
     *
     * @param batchCoopDaily
     * @return
     */
    Integer getStartAmountByCoopId(BatchCoopDaily batchCoopDaily);

    /**
     * 批量插入
     *
     * @param dailyList
     */
    void batchInsert(List<BatchCoopDaily> dailyList);

    /**
     * 根据日期删除
     *
     * @param todayDate
     */
    void deleteByDate(@Param("todayDate") String todayDate);
}