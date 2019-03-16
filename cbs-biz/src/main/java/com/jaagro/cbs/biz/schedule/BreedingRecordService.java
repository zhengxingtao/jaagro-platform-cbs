package com.jaagro.cbs.biz.schedule;

import com.jaagro.cbs.api.service.BatchCoopDailyService;
import com.jaagro.cbs.api.service.BatchInfoService;
import com.jaagro.cbs.api.service.BreedingRecordDailyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 批次养殖记录
 *
 * @author baiyiran
 * @Date 2019/2/26
 */
@Service
@Slf4j
public class BreedingRecordService {

    @Autowired
    private BatchInfoService batchInfoService;
    @Autowired
    private BatchCoopDailyService batchCoopDailyService;
    @Autowired
    private BreedingRecordDailyService breedingRecordDailyService;

    /**
     * 鸡舍养殖每日汇总
     */
    @Scheduled(cron = "0 0 1 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void batchCoopDaily() {
        batchCoopDailyService.batchCoopDaily();
    }


    /**
     * 批次养殖记录表日汇总
     */
    @Scheduled(cron = "0 0 2 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void breedingRecordDaily() {
        breedingRecordDailyService.breedingRecordDaily();
    }

    /**
     * 批次养殖情况汇总
     */
    @Scheduled(cron = "0 0 2 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void batchInfo() {
        batchInfoService.batchInfo();
    }

}
