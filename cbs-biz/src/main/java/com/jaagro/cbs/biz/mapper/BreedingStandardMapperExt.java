package com.jaagro.cbs.biz.mapper;

import javax.annotation.Resource;
import com.jaagro.cbs.api.dto.standard.ListBreedingStandardCriteria;
import com.jaagro.cbs.api.model.BreedingStandard;
import com.jaagro.cbs.api.model.BreedingStandardExample;
import com.jaagro.cbs.biz.mapper.base.BaseMapper;

import java.util.List;


/**
 * BreedingStandardMapperExt接口
 * @author :generator
 * @date :2019/2/21
 */
@Resource
public interface BreedingStandardMapperExt extends BaseMapper<BreedingStandard,BreedingStandardExample> {

    /**
     * 分页查询所有的养殖模板
     * @param criteria
     * @return
     */
    List<BreedingStandard> listBreedingStandardByCriteria(ListBreedingStandardCriteria criteria);
}