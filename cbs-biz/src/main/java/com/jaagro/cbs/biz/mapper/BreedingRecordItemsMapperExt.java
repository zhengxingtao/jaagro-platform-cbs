package com.jaagro.cbs.biz.mapper;

import javax.annotation.Resource;
import com.jaagro.cbs.api.model.BreedingRecordItems;
import com.jaagro.cbs.api.model.BreedingRecordItemsExample;
import com.jaagro.cbs.biz.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * BreedingRecordItemsMapperExt接口
 * @author :generator
 * @date :2019/3/9
 */
@Resource
public interface BreedingRecordItemsMapperExt extends BaseMapper<BreedingRecordItems,BreedingRecordItemsExample> {
    /**
     * 根据养殖记录id查询养殖记录明细
     * @param breedingRecordId
     * @return
     */
    List<BreedingRecordItems> listByBreedingRecordId(@Param("breedingRecordId") Integer breedingRecordId);

    /**
     * 批量插入
     * @param breedingRecordItemsList
     */
    void batchInsert(@Param("breedingRecordItemsList") List<BreedingRecordItems> breedingRecordItemsList);
}