package com.jaagro.cbs.biz.mapper;

import javax.annotation.Resource;
import com.jaagro.cbs.api.model.BreedingBatchParameter;
import com.jaagro.cbs.api.model.BreedingBatchParameterExample;
import com.jaagro.cbs.biz.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * BreedingBatchParameterMapperExt接口
 * @author :generator
 * @date :2019/2/21
 */
@Resource
public interface BreedingBatchParameterMapperExt extends BaseMapper<BreedingBatchParameter,BreedingBatchParameterExample> {

    /**
     * 批量插入
     * @param batchParameterList
     */
    void batchInsert(@Param("batchParameterList") List<BreedingBatchParameter> batchParameterList);
}