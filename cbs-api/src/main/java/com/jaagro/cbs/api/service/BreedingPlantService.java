package com.jaagro.cbs.api.service;

import com.jaagro.cbs.api.dto.breedingPlant.CreatePlantDto;
import com.jaagro.cbs.api.dto.breedingPlant.ReturnPlantDto;
import com.jaagro.cbs.api.dto.breedingPlant.UpdatePlantDto;

import java.util.List;
import java.util.Map;

/**
 * 养殖基本资料server，包含养殖场、图片、鸡舍等api都在这个里
 *
 * @author tonyZheng
 * @date 2019-02-21 14:28
 */
public interface BreedingPlantService {

    /**
     * 新增
     *
     * @param plantDto
     * @return
     */
    Map<String, Object> createPlant(CreatePlantDto plantDto);

    /**
     * 修改
     *
     * @param plantDto
     * @return
     */
    Map<String, Object> updatePlant(UpdatePlantDto plantDto);

    /**
     * 通过养殖id获取养殖场详情
     *
     * @param id
     * @return
     */
    ReturnPlantDto getPlantDetailsById(Integer id);

    /**
     * 通过养殖户id获取养殖场列表
     *
     * @param customerId
     * @return
     */
    List<ReturnPlantDto> listPlantByCustomerId(Integer customerId);
}
