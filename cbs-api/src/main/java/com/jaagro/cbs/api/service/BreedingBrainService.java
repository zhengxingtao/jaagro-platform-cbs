package com.jaagro.cbs.api.service;

import java.math.BigDecimal;

/**
 * @Author gavin
 *
 * @Date 20190305
 */
public interface BreedingBrainService {

    /**
     * 根据养殖计划Id生成养殖第一阶段（1->14天）的饲料订单
     * @param planId
     * @return 需要购买饲料重量
     */
    BigDecimal calculatePhaseOneFoodWeightById(Integer planId);



}
