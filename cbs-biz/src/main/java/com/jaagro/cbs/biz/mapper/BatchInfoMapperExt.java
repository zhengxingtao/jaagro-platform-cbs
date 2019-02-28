package com.jaagro.cbs.biz.mapper;

import com.jaagro.cbs.api.model.BatchInfo;
import com.jaagro.cbs.api.model.BatchInfoExample;
import com.jaagro.cbs.biz.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Param;


import javax.annotation.Resource;
import java.util.List;


/**
 * BatchInfoMapperExt接口
 *
 * @author :generator
 * @date :2019/2/21
 */
@Resource
public interface BatchInfoMapperExt extends BaseMapper<BatchInfo, BatchInfoExample> {

    /**
     * 根据日期删除
     *
     * @param todayDate
     */
    void deleteByDate(@Param("todayDate") String todayDate);

    /**
     * 批量插入
     *
     * @param batchInfoList
     */
    void insertBatch(List<BatchInfo> batchInfoList);

    /**
     * 根据批次、日期 查询 上一次的剩余数
     *
     * @param batchInfo
     * @return
     */
    Integer getStartAmountByPlanId(BatchInfo batchInfo);

    /**
     * 查询当前养殖计划 最新一条记录
     *
     * @param planId
     * @return
     */
    BatchInfo getTheLatestBatchInfo(@Param("planId") Integer planId);


}