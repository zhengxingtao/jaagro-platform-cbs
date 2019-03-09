package com.jaagro.cbs.api.dto.farmer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @description: 农户端首页
 * @author: @Gao.
 * @create: 2019-03-04 10:03
 **/
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ReturnBreedingFarmerIndexDto implements Serializable {

    /**
     * 总的存栏量
     */
    private BigDecimal totalBreedingStock;

    /**
     * 总的异常总数
     */
    private BigDecimal totalAbnormalWarn;

    /**
     * 总的饲料库存
     */
    private BigDecimal totalFeedStock;

}
