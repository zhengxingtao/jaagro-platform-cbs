package com.jaagro.cbs.biz.mapper;

import javax.annotation.Resource;

import com.jaagro.cbs.api.model.CoopDevice;
import com.jaagro.cbs.api.model.CoopDeviceExample;
import com.jaagro.cbs.biz.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Param;


/**
 * CoopDeviceMapperExt接口
 *
 * @author :generator
 * @date :2019/2/21
 */
@Resource
public interface CoopDeviceMapperExt extends BaseMapper<CoopDevice, CoopDeviceExample> {

    /**
     * 逻辑删除鸡舍设备
     *
     * @param coopId
     */
    void logicDeleteCoopDeviceByCoopId(@Param("coopId") Integer coopId);
}