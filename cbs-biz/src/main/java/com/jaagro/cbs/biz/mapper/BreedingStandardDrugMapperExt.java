package com.jaagro.cbs.biz.mapper;

import javax.annotation.Resource;

import com.jaagro.cbs.api.dto.standard.BreedingStandardDrugDto;
import com.jaagro.cbs.api.model.BreedingStandardDrug;
import com.jaagro.cbs.api.model.BreedingStandardDrugExample;
import com.jaagro.cbs.biz.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * BreedingStandardDrugMapperExt接口
 * @author :generator
 * @date :2019/3/8
 */
@Resource
public interface BreedingStandardDrugMapperExt extends BaseMapper<BreedingStandardDrug,BreedingStandardDrugExample> {

    /**
     * 根据养殖模板id获取养殖模板药品配置
     * @param standardId
     * @return
     */
    List<BreedingStandardDrugDto> listBreedingStandardDrugs(@Param("standardId") Integer standardId);

    /**
     * 根据养殖模板id删除
     * @param standardId
     */
    void delByStandardId(@Param("standardId") Integer standardId);

    /**
     * 批量插入
     * @param standardDrugList
     */
    void batchInsert(@Param("standardDrugList") List<BreedingStandardDrug> standardDrugList);
}