package com.jaagro.cbs.biz.service.impl;

import com.jaagro.cbs.api.dto.base.ShowCustomerDto;
import com.jaagro.cbs.api.dto.plant.*;
import com.jaagro.cbs.api.enums.CoopStatusEnum;
import com.jaagro.cbs.api.service.BreedingPlantService;
import com.jaagro.cbs.biz.mapper.CoopDeviceMapperExt;
import com.jaagro.cbs.biz.mapper.CoopMapperExt;
import com.jaagro.cbs.biz.mapper.PlantImageMapperExt;
import com.jaagro.cbs.biz.mapper.PlantMapperExt;
import com.jaagro.cbs.api.model.*;
import com.jaagro.cbs.biz.service.CustomerClientService;
import com.jaagro.cbs.biz.service.OssSignUrlClientService;
import com.jaagro.utils.ServiceResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author baiyiran
 * @Date 2019/2/22
 */
@Slf4j
@Service
public class BreedingPlantServiceImpl implements BreedingPlantService {

    @Autowired
    private CurrentUserService currentUserService;
    @Autowired
    private CustomerClientService customerClientService;
    @Autowired
    private OssSignUrlClientService ossSignUrlClientService;
    @Autowired
    private PlantMapperExt plantMapper;
    @Autowired
    private CoopMapperExt coopMapperExt;
    @Autowired
    private PlantImageMapperExt plantImageMapper;
    @Autowired
    private CoopDeviceMapperExt coopDeviceMapper;


    /**
     * 新增
     *
     * @param plantDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean createPlant(CreatePlantDto plantDto) {
        Plant plant = new Plant();
        BeanUtils.copyProperties(plantDto, plant);
        if (StringUtils.isEmpty(plant.getCustomerId())) {
            throw new NullPointerException("养殖户id不能为空");
        }
        ShowCustomerDto showCustomerById = customerClientService.getShowCustomerById(plant.getCustomerId());
        if (showCustomerById == null) {
            throw new NullPointerException("养殖户不存在");
        }
        Integer tenantId = customerClientService.getTenantByCustomer(showCustomerById.getId());
        if (tenantId != null) {
            plant.setTenantId(tenantId);
        }
        try {
            plant.setCreateUserId(currentUserService.getCurrentUser().getId());
            plantMapper.insertSelective(plant);
            if (plant.getId() != null && !CollectionUtils.isEmpty(plantDto.getImageDtoList())) {
                for (CreatePlantImageDto imageDto : plantDto.getImageDtoList()) {
                    PlantImage plantImage = new PlantImage();
                    BeanUtils.copyProperties(imageDto, plantImage);
                    plantImage
                            .setCreateUserId(currentUserService.getCurrentUser().getId())
                            .setPlantId(plant.getId());
                    plantImageMapper.insertSelective(plantImage);
                }

            }
        } catch (Exception e) {
            log.error("R BreedingPlantServiceImpl.createPlant  error:" + e);
            return false;
        }
        return true;
    }

    /**
     * 修改
     *
     * @param plantDto
     * @return
     */
    @Override
    public Map<String, Object> updatePlant(UpdatePlantDto plantDto) {
        Plant plant = new Plant();
        BeanUtils.copyProperties(plantDto, plant);
        Integer result = plantMapper.updateByPrimaryKeySelective(plant);
        if (result > 0) {
            return ServiceResult.toResult("修改成功");
        } else {
            return ServiceResult.toResult("修改失败");
        }
    }

    /**
     * 通过养殖id获取养殖场详情
     *
     * @param id
     * @return
     */
    @Override
    public ReturnPlantDto getPlantDetailsById(Integer id) {
        Plant plant = plantMapper.selectByPrimaryKey(id);
        if (plant == null) {
            return null;
        }
        ReturnPlantDto returnPlantDto = new ReturnPlantDto();
        BeanUtils.copyProperties(plant, returnPlantDto);
        //填充养殖场图片
        List<ReturnPlantImageDto> imageDtoList = plantImageMapper.listByPlantId(plant.getId());
        if (!CollectionUtils.isEmpty(imageDtoList)) {
            for (ReturnPlantImageDto plantImageDto : imageDtoList) {
                //替换资质证照地址
                String[] strArray = {plantImageDto.getImageUrl()};
                List<URL> urlList = ossSignUrlClientService.listSignedUrl(strArray);
                plantImageDto.setImageUrl(urlList.get(0).toString());
            }
            returnPlantDto.setPlantImageDtoList(imageDtoList);
        }
        //填充养殖场鸡舍数量
        CoopExample coopExample = new CoopExample();
        coopExample.createCriteria()
                .andPlantIdEqualTo(returnPlantDto.getId());
        long coopCount = coopMapperExt.countByExample(coopExample);
        if (coopCount > 0) {
            returnPlantDto.setCoopCount((int) coopCount);
        }
        return returnPlantDto;
    }

    /**
     * 通过养殖户id获取养殖场列表
     *
     * @param customerId
     * @return
     */
    @Override
    public List<ReturnPlantDto> listPlantByCustomerId(Integer customerId) {
        ShowCustomerDto showCustomerById = customerClientService.getShowCustomerById(customerId);
        if (showCustomerById == null) {
            throw new NullPointerException("养殖户不存在");
        }
        PlantExample plantExample = new PlantExample();
        plantExample.createCriteria()
                .andCustomerIdEqualTo(customerId)
                .andEnableEqualTo(true);
        List<Plant> plants = plantMapper.selectByExample(plantExample);
        List<ReturnPlantDto> returnPlantDtoList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(plants)) {
            for (Plant plant : plants) {
                ReturnPlantDto plantDto = new ReturnPlantDto();
                BeanUtils.copyProperties(plant, plantDto);
                //填充养殖场图片
                List<ReturnPlantImageDto> imageDtoList = plantImageMapper.listByPlantId(plant.getId());
                if (!CollectionUtils.isEmpty(imageDtoList)) {
                    for (ReturnPlantImageDto plantImageDto : imageDtoList) {
                        String[] strArray = {plantImageDto.getImageUrl()};
                        plantImageDto.setImageUrl(ossSignUrlClientService.listSignedUrl(strArray).get(0).toString());
                    }
                    plantDto.setPlantImageDtoList(imageDtoList);
                }
                //填充养殖场鸡舍数量
                CoopExample coopExample = new CoopExample();
                coopExample.createCriteria()
                        .andPlantIdEqualTo(plant.getId());
                long coopCount = coopMapperExt.countByExample(coopExample);
                if (coopCount > 0) {
                    plantDto.setCoopCount((int) coopCount);
                }
                returnPlantDtoList.add(plantDto);
            }
        }
        return returnPlantDtoList;
    }

    /**
     * 新增鸡舍
     *
     * @param coopDto
     * @return
     */
    @Override
    public Map<String, Object> createCoop(CreateCoopDto coopDto) {
        Plant plant = plantMapper.selectByPrimaryKey(coopDto.getPlantId());
        if (plant == null) {
            throw new RuntimeException("养殖场不存在");
        }
        Coop coop = new Coop();
        BeanUtils.copyProperties(coopDto, coop);
        coop
                .setCoopStatus(CoopStatusEnum.LEISURE.getCode())
                .setCustomerId(plant.getCustomerId())
                .setCreateUserId(currentUserService.getCurrentUser().getId());
        Integer result = coopMapperExt.insertSelective(coop);
        if (result > 0) {
            return ServiceResult.toResult("创建成功");
        }
        return ServiceResult.error("创建失败");
    }

    /**
     * 逻辑删除鸡舍
     *
     * @param coopId
     */
    @Override
    public void deleteCoop(Integer coopId) {
        //逻辑删除鸡舍
        Coop coop = new Coop();
        coop
                .setId(coopId)
                .setEnable(false);
        coopMapperExt.updateByPrimaryKeySelective(coop);
        //逻辑删除鸡舍下的设备
        coopDeviceMapper.logicDeleteCoopDeviceByCoopId(coopId);
    }


    /**
     * 通过养殖场id获得鸡舍列表
     *
     * @param plantId
     * @return
     */
    @Override
    public List<ReturnCoopDto> listCoopByPlantId(Integer plantId) {
        CoopExample coopExample = new CoopExample();
        coopExample.createCriteria().andPlantIdEqualTo(plantId);
        List<Coop> coops = coopMapperExt.selectByExample(coopExample);
        List<ReturnCoopDto> returnCoopDtoList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(coops)) {
            for (Coop coop : coops) {
                ReturnCoopDto returnCoopDto = new ReturnCoopDto();
                BeanUtils.copyProperties(coop, returnCoopDto);
                returnCoopDtoList.add(returnCoopDto);
            }
        }
        return returnCoopDtoList;
    }

}
