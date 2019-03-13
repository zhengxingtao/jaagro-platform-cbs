package com.jaagro.cbs.api.dto.farmer;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 农户应喂药参数
 * @author yj
 * @date 2019/3/9 15:20
 */
@Data
@Accessors(chain = true)
public class BreedingRecordItemsDto implements Serializable{
    private static final long serialVersionUID = -3911667559405368573L;
    /**
     * 产品id
     */
    private Integer productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 喂养数量
     */
    private BigDecimal breedingValue;

    /**
     * 单位(g,ml)
     */
    private String capacityUnit;

    /**
     * 喂养时间
     */
    private Date breedingTime;
}
