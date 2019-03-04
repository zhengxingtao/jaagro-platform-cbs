package com.jaagro.cbs.biz.service.impl;

import com.github.pagehelper.PageInfo;
import com.jaagro.cbs.api.dto.farmer.ReturnBreedingBatchDetailsDto;
import com.jaagro.cbs.api.dto.farmer.ReturnBreedingFarmerIndexDto;
import com.jaagro.cbs.api.model.BatchInfo;
import com.jaagro.cbs.api.model.BatchInfoExample;
import com.jaagro.cbs.api.service.BreedingFarmerService;
import com.jaagro.cbs.api.service.BreedingPlanService;
import com.jaagro.cbs.biz.mapper.BatchInfoMapperExt;
import com.jaagro.cbs.biz.mapper.BreedingPlanMapperExt;
import com.jaagro.cbs.biz.mapper.DeviceAlarmLogMapperExt;
import com.jaagro.constant.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * @description: 农户端app 相关api
 * @author: @Gao.
 * @create: 2019-03-04 10:48
 **/
@Slf4j
@Service
public class BreedingFarmerServiceImpl implements BreedingFarmerService {

    @Autowired
    private CurrentUserService currentUserService;
    @Autowired
    private BreedingPlanMapperExt breedingPlanMapper;
    @Autowired
    private BreedingPlanService breedingPlanService;
    @Autowired
    private BatchInfoMapperExt batchInfoMapper;
    @Autowired
    private DeviceAlarmLogMapperExt deviceAlarmLogMapper;

    /**
     * 农户端app 首页数据统计
     *
     * @return
     * @author @Gao.
     */
    @Override
    public ReturnBreedingFarmerIndexDto breedingFarmerIndexStatistical() {
        BigDecimal totalPlanStock = BigDecimal.ZERO;
        HashSet<Integer> planIds = new HashSet<>();
        UserInfo currentUser = currentUserService.getCurrentUser();
        ReturnBreedingFarmerIndexDto returnBreedingFarmerIndexDto = new ReturnBreedingFarmerIndexDto();
        if (currentUser != null) {
            List<ReturnBreedingBatchDetailsDto> returnBreedingBatchDetailsDtos = breedingPlanMapper.listBreedingPlanByCustomerId(currentUser.getId());
            if (!CollectionUtils.isEmpty(returnBreedingBatchDetailsDtos)) {
                for (ReturnBreedingBatchDetailsDto returnBreedingBatchDetailsDto : returnBreedingBatchDetailsDtos) {
                    planIds.add(returnBreedingBatchDetailsDto.getId());
                    //累计所有上鸡计划所有数量
                    totalPlanStock = totalPlanStock.add(new BigDecimal(returnBreedingBatchDetailsDto.getPlanChickenQuantity()));
                }
                if (!CollectionUtils.isEmpty(planIds)) {
                    //1.累计所有死淘数量
                    BigDecimal accumulativeTotalDeadAmount = batchInfoMapper.accumulativeTotalDeadAmount(planIds);
                    //2.累计所有出栏数量
                    BigDecimal accumulativeTotalSaleAmount = batchInfoMapper.accumulativeTotalSaleAmount(planIds);
                    //当前存栏量
                    BigDecimal totalBreedingStock = totalPlanStock.subtract(accumulativeTotalDeadAmount).subtract(accumulativeTotalSaleAmount);
                    //环控异常指数
                    BigDecimal accumulativeTotalAbnormalWarn = deviceAlarmLogMapper.accumulativeTotalAbnormalWarn(planIds);
                    returnBreedingFarmerIndexDto
                            .setTotalBreedingStock(totalBreedingStock)
                            .setTotalAbnormalWarn(accumulativeTotalAbnormalWarn);

                }

                //饲料库存
            }
        }
        return null;
    }

    /**
     * 农户端app 首页
     *
     * @return
     * @author: @Gao.
     */
    @Override
    public PageInfo breedingFarmerIndex() {
        List<ReturnBreedingBatchDetailsDto> returnBreedingBatchDetailsDtos = null;
        UserInfo currentUser = currentUserService.getCurrentUser();
        if (currentUser != null) {
            returnBreedingBatchDetailsDtos = breedingPlanMapper.listBreedingPlanByCustomerId(currentUser.getId());
            if (!CollectionUtils.isEmpty(returnBreedingBatchDetailsDtos)) {
                for (ReturnBreedingBatchDetailsDto returnBreedingBatchDetailsDto : returnBreedingBatchDetailsDtos) {
                    Integer planId = returnBreedingBatchDetailsDto.getId();
                    Integer dayAge = null;
                    try {
                        //获取当前日龄
                        dayAge = getDayAge(returnBreedingBatchDetailsDto.getPlanTime());
                    } catch (Exception e) {
                        log.info("R breedingFarmerIndex getDayAge error", e);
                    }
                    BatchInfoExample batchInfoExample = new BatchInfoExample();
                    if (dayAge != null) {
                        //今日耗料量
                        batchInfoExample
                                .createCriteria()
                                .andPlanIdEqualTo(planId)
                                .andDayAgeEqualTo(dayAge)
                                .andEnableEqualTo(true);
                        List<BatchInfo> batchInfos = batchInfoMapper.selectByExample(batchInfoExample);
                        if (!CollectionUtils.isEmpty(batchInfos)) {
                            BatchInfo batchInfo = batchInfos.get(0);
                            if (batchInfo != null && batchInfo.getFodderAmount() != null) {
                                returnBreedingBatchDetailsDto.setFodderAmount(batchInfo.getFodderAmount());
                            }
                        }
                    }
                    //1.累计所有出栏量
                    BigDecimal saleAmount = batchInfoMapper.accumulativeSaleAmount(planId).abs();
                    //2.累计所有死淘数量
                    BigDecimal deadAmount = batchInfoMapper.accumulativeDeadAmount(planId).abs();
                    //计算存栏量
                    if (returnBreedingBatchDetailsDto.getPlanChickenQuantity() != null) {
                        Integer planChickenQuantity = returnBreedingBatchDetailsDto.getPlanChickenQuantity();
                        BigDecimal breedingStock = new BigDecimal(planChickenQuantity).subtract(saleAmount).subtract(deadAmount);
                        returnBreedingBatchDetailsDto.setBreedingStock(breedingStock);
                    }
                }
            }
        }
        return new PageInfo(returnBreedingBatchDetailsDtos);
    }

    /**
     * 根据上鸡计划时间获取当前日龄
     *
     * @param beginDate
     * @return
     * @author: @Gao.
     */
    private Integer getDayAge(Date beginDate) throws Exception {
        Integer day = 0;
        Date endDate = new Date();
        if (beginDate == null && endDate == null) {
            return day;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        beginDate = sdf.parse(sdf.format(beginDate));
        endDate = sdf.parse(sdf.format(endDate));
        day = (int) (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
        return day + 1;
    }
}
