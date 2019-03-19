package com.jaagro.cbs.biz.service.impl;

import com.jaagro.cbs.api.model.BreedingRecord;
import com.jaagro.cbs.api.model.BreedingRecordDaily;
import com.jaagro.cbs.api.service.BreedingRecordDailyService;
import com.jaagro.cbs.biz.mapper.BreedingRecordDailyMapperExt;
import com.jaagro.cbs.biz.mapper.BreedingRecordMapperExt;
import com.jaagro.cbs.biz.utils.RedisLock;
import com.jaagro.cbs.biz.utils.RedisUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author baiyiran
 * @Date 2019/3/16
 */
@Service
public class BreedingRecordDailyServiceImpl implements BreedingRecordDailyService {

    @Autowired
    private BreedingRecordMapperExt breedingRecordMapper;
    @Autowired
    private BreedingRecordDailyMapperExt breedingRecordDailyMapper;
    @Autowired
    private RedisUtil redis;
    @Autowired
    private RedisLock redisLock;

    /**
     * 批次养殖记录表日汇总
     */
    @Override
    public void breedingRecordDaily() {
        //加锁
        long time = System.currentTimeMillis() + 10 * 1000;
        boolean success = redisLock.lock("Scheduled:redisLock:breedingRecordDaily", String.valueOf(time),null,null);
        if (!success) {
            throw new RuntimeException("请求正在处理中");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = sdf.format(new Date());
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

            redis.del("Scheduled:redisLock:breedingRecordDaily");
        }
    }
}
