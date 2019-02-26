package com.jaagro.cbs.biz.mapper;

import com.jaagro.cbs.api.dto.plant.CreatePlantImageDto;
import com.jaagro.cbs.api.dto.plant.ReturnPlantImageDto;
import com.jaagro.cbs.api.model.PlantImage;
import com.jaagro.cbs.api.model.PlantImageExample;
import com.jaagro.cbs.biz.mapper.base.BaseMapper;

import javax.annotation.Resource;
import java.util.List;


/**
 * PlantImageMapperExt接口
 *
 * @author :generator
 * @date :2019/2/21
 */
@Resource
public interface PlantImageMapperExt extends BaseMapper<PlantImage, PlantImageExample> {

    /**
     * 根据养殖场查询图片列表
     *
     * @param id
     * @return
     */
    List<ReturnPlantImageDto> listByPlantId(Integer id);

    /**
     * 批量新增
     *
     * @param imageDtoList
     */
    int insertBatch(List<CreatePlantImageDto> imageDtoList);
}