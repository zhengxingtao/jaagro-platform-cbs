package com.jaagro.cbs.biz.service.impl;

import com.jaagro.cbs.api.dto.base.BatchInfoCriteriaDto;
import com.jaagro.cbs.api.model.BatchInfo;
import com.jaagro.cbs.api.model.BreedingPlan;
import com.jaagro.cbs.api.service.BatchInfoService;
import com.jaagro.cbs.biz.mapper.BatchInfoMapperExt;
import com.jaagro.cbs.biz.mapper.BreedingPlanMapperExt;
import com.jaagro.cbs.biz.mapper.BreedingRecordMapperExt;
import com.jaagro.cbs.biz.utils.RedisLock;
import com.jaagro.cbs.biz.utils.RedisUtil;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author baiyiran
 * @Date 2019/3/16
 */
@Service
public class BatchInfoServiceImpl implements BatchInfoService {

    @Autowired
    private BatchInfoMapperExt batchInfoMapper;
    @Autowired
    private BreedingPlanMapperExt breedingPlanMapper;
    @Autowired
    private BreedingRecordMapperExt breedingRecordMapper;
    @Autowired
    private RedisUtil redis;
    @Autowired
    private RedisLock redisLock;

    /**
     * 获取前天的日期 yyy-mm-dd
     *
     * @return
     */
    private Date getBeforeYesterdayDate() {
        return DateUtils.addDays(new Date(), -2);
    }

    /**
     * 批次养殖情况汇总
     */
    @Override
    public void batchInfo() {
        //加锁
        long time = System.currentTimeMillis() + 10 * 1000;
        boolean success = redisLock.lock("Scheduled:redisLock:batchInfo", String.valueOf(time),null,null);
        if (!success) {
            throw new RuntimeException("请求正在处理中");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = sdf.format(new Date());
        //从breedingRecord统计
        List<BatchInfo> batchInfoList = breedingRecordMapper.listBatchInfoByParams(todayDate);
        if (!CollectionUtils.isEmpty(batchInfoList)) {
            BatchInfoCriteriaDto criteriaDto = new BatchInfoCriteriaDto();
            criteriaDto.setTodayDate(todayDate);
            for (BatchInfo info : batchInfoList) {
                BreedingPlan breedingPlan = breedingPlanMapper.selectByPrimaryKey(info.getPlanId());
                //死淘数量
                criteriaDto.setPlanId(info.getPlanId());
                int deadAmount = 0;
                deadAmount = breedingRecordMapper.sumDeadAmountByPlanId(criteriaDto);
                info.setDeadAmount(deadAmount);
                //起始喂养数量 && 剩余喂养数量
                BatchInfo batchInfo = new BatchInfo();
                batchInfo.setCreateTime(getBeforeYesterdayDate()).setPlanId(info.getPlanId());
                Integer currentAmount = batchInfoMapper.getStartAmountByPlanId(batchInfo);
                if (currentAmount != null && currentAmount > 0) {
                    info.setStartAmount(currentAmount);
                    // 剩余喂养数量=起始-死淘
                    info.setCurrentAmount(info.getStartAmount() - info.getDeadAmount());
                } else {
                    //查询不到记录，就用breeding_plan的计划上鸡数量
                    if (breedingPlan != null) {
                        info.setStartAmount(breedingPlan.getPlanChickenQuantity());
                        // 剩余喂养数量=计划上鸡数量
                        info.setCurrentAmount(breedingPlan.getPlanChickenQuantity());
                    }
                }
                info
                        .setStartDay(breedingPlan.getPlanTime())
                        .setCreateUserId(1);
            }
            //删除
            batchInfoMapper.deleteByDate(todayDate);
            //批量插入
            batchInfoMapper.insertBatch(batchInfoList);

            redis.del("Scheduled:redisLock:batchInfo");
        }

    }
}
