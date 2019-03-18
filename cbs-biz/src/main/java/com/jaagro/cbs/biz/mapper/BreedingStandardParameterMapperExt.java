package com.jaagro.cbs.biz.mapper;

import javax.annotation.Resource;
import com.jaagro.cbs.api.dto.standard.DelBreedingStandardParamDto;
import com.jaagro.cbs.api.dto.standard.ListBreedingStandardCriteria;
import com.jaagro.cbs.api.model.BreedingStandard;
import com.jaagro.cbs.api.model.BreedingStandardParameter;
import com.jaagro.cbs.api.model.BreedingStandardParameterExample;
import com.jaagro.cbs.biz.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * BreedingStandardParameterMapperExt接口
 * @author :generator
 * @date :2019/2/21
 */
@Resource
public interface BreedingStandardParameterMapperExt extends BaseMapper<BreedingStandardParameter,BreedingStandardParameterExample> {

    /**
     * 根据养殖模板删除模板参数
     * @param id
     */
    void deleteByStandardId(Integer id);

    /**
     * 批量插入
     * @param parameterList
     */
    void batchInsert(@Param("parameterList") List<BreedingStandardParameter> parameterList);

    /**
     * 批量更新
     * @param parameterList
     */
    void batchUpdateByPrimaryKeySelective(@Param("parameterList") List<BreedingStandardParameter> parameterList);

    /**
     * 根据条件删除参数
     * @param dto
     * @return
     */
    Integer delByCondition(DelBreedingStandardParamDto dto);
}