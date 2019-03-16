package com.jaagro.cbs.web.vo.standard;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 养殖参数模板药品配置明细
 * @author yj
 * @date 2019/3/15 22:10
 */
@Data
@Accessors(chain = true)
public class BreedingStandardDrugItemVo implements Serializable {
    private static final long serialVersionUID = 6396788359270279158L;
    /**
     * 养殖参数模板药品配置表id
     */
    private Integer id;

    /**
     * 商品id
     */
    private Integer productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 容量单位(ml,g)
     */
    private String capacityUnit;

    /**
     * 千只日喂量
     */
    private BigDecimal feedVolume;
}
