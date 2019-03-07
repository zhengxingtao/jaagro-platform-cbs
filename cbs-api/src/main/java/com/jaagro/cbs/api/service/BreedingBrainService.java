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
     * @return 返回第一阶段需要购买饲料重量
     */
    BigDecimal calculatePhaseOneFoodWeightById(Integer planId);

    /**
     * 根据养殖计划Id生成养殖第二阶段（15->19天）的饲料订单
     * @param planId
     * @return 返回第二阶段需要购买饲料重量
     */
    BigDecimal calculatePhaseTwoFoodWeightById(Integer planId);

    /**
     * 根据养殖计划Id生成养殖第三阶段（20->28天）的饲料订单
     * @param planId
     * @return 返回第三阶段需要购买饲料重量
     */
    BigDecimal calculatePhaseThreeFoodWeightById(Integer planId);

    /**
     * 根据养殖计划Id生成养殖第四阶段（29->计划养殖天数）的饲料订单
     * @param planId
     * @return 返回第思念阶段需要购买饲料重量
     */
    BigDecimal calculatePhaseFourFoodWeightById(Integer planId);
}
