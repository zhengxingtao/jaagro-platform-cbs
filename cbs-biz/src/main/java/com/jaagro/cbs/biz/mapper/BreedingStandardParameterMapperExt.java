package com.jaagro.cbs.biz.mapper;

import javax.annotation.Resource;
import com.jaagro.cbs.biz.model.BreedingStandardParameter;
import com.jaagro.cbs.biz.model.BreedingStandardParameterExample;
import com.jaagro.cbs.biz.mapper.base.BaseMapper;


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