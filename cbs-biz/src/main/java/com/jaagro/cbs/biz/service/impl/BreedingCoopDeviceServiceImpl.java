package com.jaagro.cbs.biz.service.impl;

import com.jaagro.cbs.api.dto.plant.CreateCoopDeviceDto;
import com.jaagro.cbs.api.dto.plant.ReturnCoopDeviceDto;
import com.jaagro.cbs.api.service.BreedingCoopDeviceService;
import com.jaagro.cbs.biz.mapper.CoopDeviceMapperExt;
import com.jaagro.cbs.biz.mapper.CoopMapperExt;
import com.jaagro.cbs.biz.model.Coop;
import com.jaagro.cbs.biz.model.CoopDevice;
import com.jaagro.cbs.biz.model.CoopDeviceExample;
import com.jaagro.constant.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BreedingCoopDeviceServiceImpl implements BreedingCoopDeviceService {

    @Autowired
    private CoopDeviceMapperExt coopDeviceMapper;
    @Autowired
    private CoopMapperExt coopMapper;
    @Autowired
    private CurrentUserService currentUserService;

    /**
     * 给鸡舍新增设备进行绑定
     *
     * @return
     */
    @Override
    public void bindDeviceToCoop(CreateCoopDeviceDto dto) {
        UserInfo currentUser = currentUserService.getCurrentUser();
        CoopDevice coopDevice = new CoopDevice();
        coopDevice
                .setCreateUserId(currentUser.getId());
        BeanUtils.copyProperties(dto, coopDevice);
        coopDeviceMapper.insertSelective(coopDevice);
    }

    /**
     * 鸡舍与设备列表页
     *
     * @return
     */
    @Override
    public List<ReturnCoopDeviceDto> listBreedingCoopDevice(Integer coopId) {
        //鸡舍信息
        Coop coop = coopMapper.selectByPrimaryKey(coopId);
        //设备信息
        CoopDeviceExample coopDeviceExample = new CoopDeviceExample();
        coopDeviceExample
                .createCriteria()
                .andCoopIdEqualTo(coopId);
        List<CoopDevice> coopDevices = coopDeviceMapper.selectByExample(coopDeviceExample);
        return null;
    }

    /**
     * 定时把数据从device_value_history表更新最新值到device_value表
     */
    @Override
    public void updateDeviceValueTask() {

    }

    /**
     * 从设备盒子实时收集设备数据到表device_value_history
     */
    @Override
    public void collectionDeviceValue() {

    }
}
