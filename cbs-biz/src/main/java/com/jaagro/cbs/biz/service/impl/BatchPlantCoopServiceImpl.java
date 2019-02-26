package com.jaagro.cbs.biz.service.impl;

import com.jaagro.cbs.api.model.BatchPlantCoop;
import com.jaagro.cbs.api.model.BatchPlantCoopExample;
import com.jaagro.cbs.api.service.BatchPlantCoopService;
import com.jaagro.cbs.biz.mapper.BatchPlantCoopMapperExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baiyiran
 * @Date 2019/2/25
 */
@Service
public class BatchPlantCoopServiceImpl implements BatchPlantCoopService {

    @Autowired
    private BatchPlantCoopMapperExt batchPlantCoopMapper;

    /**
     * 根据批次id查询鸡舍
     *
     * @param PlanId
     * @return
     */
    @Override
    public List<Integer> listCoopIdByPlanId(Integer PlanId) {
        if (StringUtils.isEmpty(PlanId)) {
            return null;
        }
        BatchPlantCoopExample batchPlantCoopExample = new BatchPlantCoopExample();
        batchPlantCoopExample.createCriteria()
                .andPlanIdEqualTo(PlanId)
                .andEnableEqualTo(true);
        List<BatchPlantCoop> batchPlantCoops = batchPlantCoopMapper.selectByExample(batchPlantCoopExample);
        List<Integer> coopId = new ArrayList<>();
        if (!CollectionUtils.isEmpty(batchPlantCoops)) {
            for (BatchPlantCoop batchPlantCoop : batchPlantCoops) {
                coopId.add(batchPlantCoop.getCoopId());
            }
        }
        return coopId;
    }
}
