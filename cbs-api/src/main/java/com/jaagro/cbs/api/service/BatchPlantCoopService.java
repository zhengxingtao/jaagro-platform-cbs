package com.jaagro.cbs.api.service;

import java.util.List;

/**
 * @author baiyiran
 * @Date 2019/2/25
 */
public interface BatchPlantCoopService {

    /**
     * 根据批次id查询鸡舍
     *
     * @param PlanId
     * @return
     */
    List<Integer> listCoopIdByPlanId(Integer PlanId);

}
