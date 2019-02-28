package com.jaagro.cbs.biz.schedule;

import com.jaagro.cbs.api.dto.base.BatchInfoCriteriaDto;
import com.jaagro.cbs.api.model.DeviceValue;
import com.jaagro.cbs.api.model.DeviceValueHistory;
import com.jaagro.cbs.biz.mapper.DeviceValueHistoryMapperExt;
import com.jaagro.cbs.biz.mapper.DeviceValueMapperExt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 批次养殖记录
 *
 * @author baiyiran
 * @Date 2019/2/28
 */
@Service
@Slf4j
public class CoopDeviceVlaueService {

    @Autowired
    private DeviceValueMapperExt deviceValueMapper;
    @Autowired
    private DeviceValueHistoryMapperExt deviceValueHistoryMapper;

    /**
     * 批次养殖情况汇总
     */
    @Scheduled(cron = "0 0 2 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void batchInfo() {
        String todayDate = new Date().toString();
        List<DeviceValue> valueList = new ArrayList<>();
        //从breedingRecord统计
        List<DeviceValueHistory> historyList = deviceValueHistoryMapper.listHistoryByParams();
        if (!CollectionUtils.isEmpty(historyList)) {
            BatchInfoCriteriaDto criteriaDto = new BatchInfoCriteriaDto();
            criteriaDto.setTodayDate(todayDate);
            for (DeviceValueHistory history : historyList) {

            }
            //批量插入
            deviceValueMapper.insertBatch(valueList);
        }

    }

}
