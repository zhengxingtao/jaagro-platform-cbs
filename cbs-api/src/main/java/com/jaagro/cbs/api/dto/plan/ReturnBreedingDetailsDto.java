package com.jaagro.cbs.api.dto.plan;


import com.jaagro.cbs.api.model.Plant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @description: 养殖中详情信息
 * @author: @Gao.
 * @create: 2019-02-27 15:29
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ReturnBreedingDetailsDto implements Serializable {
    /**
     * 当前日龄
     */
    private BigDecimal dayAge;

    /**
     * 养殖天数
     */
    private BigDecimal breedingDays;

    /**
     * 出栏时间
     */
    private Date expectSuchTime;

    /**
     * 养殖场
     */
    private List<Plant> plants;

    /**
     * 存栏量
     */
    private BigDecimal breedingStock;

    /**
     * 成活率
     */
    private BigDecimal survivalRate;

    /**
     * 异常预警
     */
    private BigDecimal abnormalWarn;

    /**
     * 质询询问次数
     */
    private BigDecimal askQuestions;

    /**
     * 质询询问解决次数
     */
    private BigDecimal solveQuestions;

    /**
     * 计划用料
     */
    private BigDecimal planFeed;

    /**
     * 剩余饲料
     */
    private BigDecimal remainFeed;

    /**
     * 理论体重
     */
    private BigDecimal theoryWeight;

    /**
     * 养殖计划详情信息
     */
    private ReturnBreedingPlanDetailsDto returnBreedingPlanDetails;
}
