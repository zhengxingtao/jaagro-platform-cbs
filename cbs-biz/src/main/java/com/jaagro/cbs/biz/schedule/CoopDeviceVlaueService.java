package com.jaagro.cbs.biz.schedule;

import com.jaagro.cbs.api.model.*;
import com.jaagro.cbs.biz.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 设备检测值
 *
 * @author baiyiran
 * @Date 2019/2/28
 */
@Service
@Slf4j
public class CoopDeviceVlaueService {

    @Autowired
    private CoopDeviceMapperExt coopDeviceMapper;
    @Autowired
    private DeviceValueMapperExt deviceValueMapper;
    @Autowired
    private BatchPlantCoopMapperExt batchPlantCoopMapper;
    @Autowired
    private BreedingBatchParameterMapperExt batchParameterMapper;
    @Autowired
    private DeviceValueHistoryMapperExt deviceValueHistoryMapper;

    /**
     * 批次养殖情况汇总
     */
    @Scheduled(cron = "0 0/10 * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void batchInfo() {
        List<DeviceValue> valueList = new ArrayList<>();
        //从breedingRecord统计
        List<DeviceValueHistory> historyList = deviceValueHistoryMapper.listHistoryByParams();
        if (!CollectionUtils.isEmpty(historyList)) {
            for (DeviceValueHistory history : historyList) {
                DeviceValueExample valueExample = new DeviceValueExample();
                valueExample.createCriteria()
                        .andDeviceIdEqualTo(history.getDeviceId())
                        .andValueTypeEqualTo(history.getValueType());
                //直接先删掉表的的原纪录
                deviceValueMapper.deleteByExample(valueExample);

                //检测是否需要警报
                Integer coopId = coopDeviceMapper.getCoopIdBydeviceId(history.getDeviceId());
                if (coopId != null) {
                    Integer planId = batchPlantCoopMapper.getPlanIdByCoopId(coopId);
                    if (planId != null) {
                        BreedingBatchParameterExample parameterExample = new BreedingBatchParameterExample();
                        parameterExample.createCriteria()
                                .andEnableEqualTo(true)
                                .andPlanIdEqualTo(planId);
                        List<BreedingBatchParameter> parameterList = batchParameterMapper.selectByExample(parameterExample);

                    }
                }
            }
            //批量插入
            deviceValueMapper.insertBatch(valueList);
        }
    }

}
