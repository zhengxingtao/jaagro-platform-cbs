package com.jaagro.cbs.biz.mapper;

import javax.annotation.Resource;
import com.jaagro.cbs.api.model.ContractPriceSection;
import com.jaagro.cbs.api.model.ContractPriceSectionExample;
import com.jaagro.cbs.biz.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * ContractPriceSectionMapperExt接口
 * @author :generator
 * @date :2019/2/21
 */
@Resource
public interface ContractPriceSectionMapperExt extends BaseMapper<ContractPriceSection,ContractPriceSectionExample> {

    /**
     * 批量插入
     * @author yj
     * @param contractPriceSectionList
     */
    void batchInsert(@Param("contractPriceSectionList") List<ContractPriceSection> contractPriceSectionList);
}