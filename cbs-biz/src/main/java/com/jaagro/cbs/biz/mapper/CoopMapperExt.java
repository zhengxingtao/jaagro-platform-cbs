package com.jaagro.cbs.biz.mapper;

import javax.annotation.Resource;

import com.jaagro.cbs.api.dto.plant.ReturnCoopDeviceDto;
import com.jaagro.cbs.api.model.Coop;
import com.jaagro.cbs.api.model.CoopExample;
import com.jaagro.cbs.biz.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * CoopMapperExt接口
 *
 * @author :generator
 * @date :2019/2/21
 */
@Resource
public interface CoopMapperExt extends BaseMapper<Coop, CoopExample> {

    /**
     * 根据养殖场的id 查询鸡舍和设备信息
     * @param coop
     * @author @Gao.
     * @return
     */
    List<ReturnCoopDeviceDto> listCoopDeviceByCoopExample(Coop coop);

    /**
     * 批量更新
     * @param coopList
     */
    void batchUpdateByPrimaryKeySelective(@Param("coopList") List<Coop> coopList);
}