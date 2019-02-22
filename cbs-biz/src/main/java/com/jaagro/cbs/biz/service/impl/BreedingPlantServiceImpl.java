package com.jaagro.cbs.biz.service.impl;

import com.jaagro.cbs.api.dto.breedingPlant.CreatePlantDto;
import com.jaagro.cbs.api.dto.breedingPlant.ReturnPlantDto;
import com.jaagro.cbs.api.dto.breedingPlant.UpdatePlantDto;
import com.jaagro.cbs.api.service.BreedingPlantService;
import com.jaagro.cbs.biz.mapper.PlantMapperExt;
import com.jaagro.cbs.biz.model.Plant;
import com.jaagro.utils.ServiceResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author baiyiran
 * @Date 2019/2/22
 */
@Service
public class BreedingPlantServiceImpl implements BreedingPlantService {

    @Autowired
    private PlantMapperExt plantMapper;


    /**
     * 新增
     *
     * @param plantDto
     * @return
     */
    @Override
    public Map<String, Object> createPlant(CreatePlantDto plantDto) {
        Plant plant = new Plant();
        BeanUtils.copyProperties(plantDto, plant);
        try {
            plantMapper.insertSelective(plant);
        } catch (Exception e) {
            e.printStackTrace();
            return ServiceResult.error(e.getMessage());
        }
        return ServiceResult.toResult("创建成功");
    }

    /**
     * 修改
     *
     * @param plantDto
     * @return
     */
    @Override
    public Map<String, Object> updatePlant(UpdatePlantDto plantDto) {
        return null;
    }

    /**
     * 通过养殖id获取养殖场详情
     *
     * @param id
     * @return
     */
    @Override
    public ReturnPlantDto getPlantDetailsById(Integer id) {
        return null;
    }

    /**
     * 通过养殖户id获取养殖场列表
     *
     * @param customerId
     * @return
     */
    @Override
    public List<ReturnPlantDto> listPlantByCustomerId(Integer customerId) {
        return null;
    }
}
