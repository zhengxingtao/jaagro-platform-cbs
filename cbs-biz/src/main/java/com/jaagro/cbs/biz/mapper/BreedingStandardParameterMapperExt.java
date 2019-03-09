package com.jaagro.cbs.biz.mapper;

import javax.annotation.Resource;
import com.jaagro.cbs.api.dto.standard.ListBreedingStandardCriteria;
import com.jaagro.cbs.api.model.BreedingStandard;
import com.jaagro.cbs.api.model.BreedingStandardParameter;
import com.jaagro.cbs.api.model.BreedingStandardParameterExample;
import com.jaagro.cbs.biz.mapper.base.BaseMapper;

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

}