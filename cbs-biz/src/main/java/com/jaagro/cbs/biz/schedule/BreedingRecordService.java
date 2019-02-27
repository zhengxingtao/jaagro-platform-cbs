package com.jaagro.cbs.biz.schedule;

import com.jaagro.cbs.api.dto.base.BatchInfoCriteriaDto;
import com.jaagro.cbs.api.model.*;
import com.jaagro.cbs.biz.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private BatchInfoMapperExt batchInfoMapper;
    @Autowired
    private BreedingPlanMapperExt breedingPlanMapper;
    @Autowired
    private BatchCoopDailyMapperExt batchCoopDailyMapper;
    @Autowired
    private BreedingRecordMapperExt breedingRecordMapper;
    @Autowired
    private BreedingRecordDailyMapperExt breedingRecordDailyMapper;

    /**
     * 获取昨天的日期
     *
     * @return
     */
    private String getYesterday() {
        Date newDate = new Date();
        DateUtils.ceiling(newDate, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(newDate);
    }

    /**
     * 获取前天的日期 yyy-mm-dd
     *
     * @return
     */
    private Date getBeforeYesterdayDate() {
        Date createDate = new Date();
        DateUtils.ceiling(createDate, 2);
        return createDate;
    }

    /**
     * 鸡舍养殖每日汇总
     */
    @Scheduled(cron = "0 0 1 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void batchCoopDaily() {
        String todayDate = getYesterday();
        //鸡舍日汇总列表
        List<BatchCoopDaily> dailyList = new ArrayList<>();
        //从BreedingRecord统计
        BreedingRecordExample breedingRecordExample = new BreedingRecordExample();
        breedingRecordExample.createCriteria()
                .andEnableEqualTo(true);
        List<BreedingRecord> breedingRecordList = breedingRecordMapper.listCoopDailySumByParams(todayDate);
        if (!CollectionUtils.isEmpty(breedingRecordList)) {
            for (BreedingRecord record : breedingRecordList) {
                BatchCoopDaily daily = new BatchCoopDaily();
                BeanUtils.copyProperties(record, daily);
                //死淘数量
                Integer deadAmount = breedingRecordMapper.sumDeadAmountByCoopId(record);
                daily.setDeadAmount(deadAmount);
                //起始喂养数量 && 剩余喂养数量
                Date createDate = new Date();
                DateUtils.ceiling(createDate, 2);
                BatchCoopDaily coopDaily = new BatchCoopDaily();
                coopDaily.setCreateTime(createDate).setCoopId(daily.getCoopId());
                Integer startAmount = batchCoopDailyMapper.getStartAmountByCoopId(coopDaily);
                if (startAmount > 0) {
                    daily.setStartAmount(startAmount);
                    if (deadAmount > 0) {
                        // 剩余喂养数量=起始-死淘
                        daily.setCurrentAmount(daily.getStartAmount() - daily.getDeadAmount());
                    }
                } else {
                    //查询不到记录，就用breeding_plan的计划上鸡数量
                    BreedingPlan breedingPlan = breedingPlanMapper.selectByPrimaryKey(record.getPlanId());
                    if (breedingPlan != null) {
                        daily.setStartAmount(breedingPlan.getPlanChickenQuantity());
                        // 剩余喂养数量=计划上鸡数量
                        daily.setCurrentAmount(breedingPlan.getPlanChickenQuantity());
                    }
                }
                //出栏数量
                //当日累积喂料次数
                Integer countFeed = breedingRecordMapper.countFodderTimesByCoopId(record);
                daily.setFodderTimes(countFeed);
                //当日累积喂料量
                daily.setFodderAmount(record.getBreedingValue());
                daily.setCreateUserId(1);
                //
                dailyList.add(daily);
            }
            //插入前先删除
            batchCoopDailyMapper.deleteByDate(todayDate);
            //插入鸡舍养殖每日汇总
            batchCoopDailyMapper.batchInsert(dailyList);
        }
    }


    /**
     * 批次养殖记录表日汇总
     */
    @Scheduled(cron = "0 0 2 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void breedingRecordDaily() {
        String todayDate = new Date().toString();
        //批次日汇总列表
        List<BreedingRecordDaily> dailyList = new ArrayList<>();

        //统计
        List<BreedingRecord> breedingRecordList = breedingRecordMapper.listSumByParams(todayDate);
        if (!CollectionUtils.isEmpty(breedingRecordList)) {
            for (BreedingRecord record : breedingRecordList) {
                BreedingRecordDaily recordDaily = new BreedingRecordDaily();
                BeanUtils.copyProperties(record, recordDaily);
                recordDaily.setCreateUserId(1);
                dailyList.add(recordDaily);
            }

            //删除
            breedingRecordDailyMapper.deleteByDate(todayDate);
            //批量插入
            breedingRecordDailyMapper.insertBatch(dailyList);
        }
    }

    /**
     * 批次养殖情况汇总
     */
    @Scheduled(cron = "0 0 2 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void batchInfo() {
        String todayDate = new Date().toString();
        //从breedingRecord统计
        List<BatchInfo> batchInfoList = breedingRecordMapper.listBatchInfoByParams(todayDate);
        if (!CollectionUtils.isEmpty(batchInfoList)) {
            BatchInfoCriteriaDto criteriaDto = new BatchInfoCriteriaDto();
            criteriaDto.setTodayDate(todayDate);
            for (BatchInfo info : batchInfoList) {
                //死淘数量
                criteriaDto.setPlanId(info.getPlanId());
                Integer deadAmount = 0;
                deadAmount = breedingRecordMapper.sumDeadAmountByPlanId(criteriaDto);
                info.setDeadAmount(deadAmount);
                //起始喂养数量 && 剩余喂养数量
                BatchInfo batchInfo = new BatchInfo();
                batchInfo.setCreateTime(getBeforeYesterdayDate()).setPlanId(info.getPlanId());
                Integer currentAmount = batchInfoMapper.getStartAmountByPlanId(batchInfo);
                if (currentAmount > 0) {
                    info.setStartAmount(currentAmount);
                    // 剩余喂养数量=起始-死淘
                    info.setCurrentAmount(info.getStartAmount() - info.getDeadAmount());
                } else {
                    //查询不到记录，就用breeding_plan的计划上鸡数量
                    BreedingPlan breedingPlan = breedingPlanMapper.selectByPrimaryKey(info.getPlanId());
                    if (breedingPlan != null) {
//                        daily.setStartAmount(breedingPlan.getPlanChickenQuantity());
                        // 剩余喂养数量=计划上鸡数量
//                        daily.setCurrentAmount(breedingPlan.getPlanChickenQuantity());
                    }
                }

            }
            //删除
            batchInfoMapper.deleteByDate(todayDate);
            //批量插入
            batchInfoMapper.insertBatch(batchInfoList);
        }

    }

}
