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
     *总的存栏量
     */
    private  Integer totalBreedingStock;

    /**
     * 总的异常总数
     */
    private Integer totalAbnormalWarn;

    /**
     * 总的饲料库存
     */
    private BigDecimal totalFeedStock;

    /**
     * 农户端养殖批次详情
     */
    private List<ReturnBreedingBatchDetailsDto> returnBreedingBatchDetailsDtos;





}
