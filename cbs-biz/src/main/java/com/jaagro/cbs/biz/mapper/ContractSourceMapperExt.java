package com.jaagro.cbs.biz.mapper;

import javax.annotation.Resource;
import com.jaagro.cbs.api.model.ContractSource;
import com.jaagro.cbs.api.model.ContractSourceExample;
import com.jaagro.cbs.biz.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * ContractSourceMapperExt接口
 * @author :generator
 * @date :2019/2/21
 */
@Resource
public interface ContractSourceMapperExt extends BaseMapper<ContractSource,ContractSourceExample> {

    /**
     * 批量插入
     * @param contractSourceList
     */
    void batchInsert(@Param("contractSourceList") List<ContractSource> contractSourceList);
}