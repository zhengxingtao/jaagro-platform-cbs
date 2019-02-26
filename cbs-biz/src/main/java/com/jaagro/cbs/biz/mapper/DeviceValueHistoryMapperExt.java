package com.jaagro.cbs.biz.mapper;

import javax.annotation.Resource;
import com.jaagro.cbs.api.model.DeviceValueHistory;
import com.jaagro.cbs.api.model.DeviceValueHistoryExample;
import com.jaagro.cbs.biz.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Param;


/**
 * DeviceValueHistoryMapperExt接口
 * @author :generator
 * @date :2019/2/21
 */
@Resource
public interface DeviceValueHistoryMapperExt extends BaseMapper<DeviceValueHistory,DeviceValueHistoryExample> {

    /**
     * 根据设备ID和日龄获取设备历史值数据
     * @param deviceId
     * @param strDate
     * @return
     */
    DeviceValueHistory getLimitOneRecordByDeviceId(@Param("deviceId") Integer deviceId,@Param("strDate") String strDate);

}