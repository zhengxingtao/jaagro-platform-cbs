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
    private Integer dayAge;

    /**
     * 养殖天数
     */
    private Integer breedingDays;

    /**
     * 出栏时间
     */
    private String expectSuchTime;

    /**
     * 养殖场
     */
    private List<Plant> plants;

    /**
     * 存栏量
     */
    private Integer breedingStock;

    /**
     * 成活率
     */
    private String survivalRate;

    /**
     * 异常预警
     */
    private int abnormalWarn;

    /**
     * 质询询问次数
     */
    private Integer askQuestions;

    /**
     * 质询询问解决次数
     */
    private Integer solveQuestions;

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
    private String theoryWeight;

    /**
     * 料肉比
     */
    private BigDecimal feedMeatRate;

    /**
     * 送料情况
     */
    private List<CalculatePurchaseOrderDto> calculatePurchaseOrderDtos;

    /**
     * 养殖计划详情信息
     */
    private ReturnBreedingPlanDetailsDto returnBreedingPlanDetails;
}
