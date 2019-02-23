package com.jaagro.cbs.api.service;

/**
 *  养殖鸡舍设备服务相关api
 */
public interface BreedingCoopDeviceService {

    /**
     * 给鸡舍新增设备进行绑定
     * @return
     */
    Boolean bindDeviceToCoop();


    /**
     * 定时把数据从device_value_history表更新最新值到device_value表
     */
    void updateDeviceValueTask();

    /**
     * 从设备盒子实时收集设备数据到表device_value_history
     */
    void collectionDeviceValue();
}
