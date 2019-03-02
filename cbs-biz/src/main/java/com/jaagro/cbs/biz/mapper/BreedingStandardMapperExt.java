package com.jaagro.cbs.biz.mapper;

import com.jaagro.cbs.api.model.BreedingStandard;
import com.jaagro.cbs.api.model.BreedingStandardExample;
import com.jaagro.cbs.biz.mapper.base.BaseMapper;

import javax.annotation.Resource;
import java.util.List;


/**
 * BreedingStandardMapperExt接口
 *
 * @author :generator
 * @date :2019/2/21
 */
@Resource
public interface BreedingStandardMapperExt extends BaseMapper<BreedingStandard, BreedingStandardExample> {

    /**
     * 查询所有的养殖模板
     *
     * @return
     */
    List<BreedingStandard> listAllBreedingStandard();

}