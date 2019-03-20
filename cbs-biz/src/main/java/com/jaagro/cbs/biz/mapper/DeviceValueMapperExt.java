package com.jaagro.cbs.biz.mapper;

import com.jaagro.cbs.api.model.DeviceValue;
import com.jaagro.cbs.api.model.DeviceValueExample;
import com.jaagro.cbs.biz.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;
import java.util.List;


/**
 * DeviceValueMapperExt接口
 *
 * @author :generator
 * @date :2019/2/21
 */
@Resource
public interface DeviceValueMapperExt extends BaseMapper<DeviceValue, DeviceValueExample> {


    /**
     * 删除某设备的记录
     *
     * @param todayDate
     */
    void deleteByDate(@Param("todayDate") Integer todayDate);

    /**
     * 批量插入
     *
     * @param valueList
     */
    void insertBatch(@Param("valueList") List<DeviceValue> valueList);

    /**
     * 删除 根据设备、类型
     *
     * @param history
     */
    void deleteByValue(DeviceValue history);
}