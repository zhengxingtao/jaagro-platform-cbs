package com.jaagro.cbs.api.dto.standard;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 药品信息
 * @author yj
 * @date 2019/3/15 21:51
 */
@Data
@Accessors(chain = true)
public class BreedingStandardDrugDto implements Serializable {
    private static final long serialVersionUID = -2990378342745928460L;
    /**
     * 养殖参数模板药品配置表id
     */
    private Integer id;

    /**
     * 养殖模板id
     */
    private Integer breedingStandardId;

    /**
     * 商品id
     */
    private Integer productId;

    /**
     * sku编码
     */
    private String skuNo;

    /**
     * 日龄起(包含)
     */
    private Integer dayAgeStart;

    /**
     * 日龄止(包含)
     */
    private Integer dayAgeEnd;

    /**
     * 停药标识(0-否,1-是)
     */
    private Boolean stopDrugFlag;

    /**
     * 千只日喂量
     */
    private BigDecimal feedVolume;

    /**
     * 该日龄区间天数
     */
    private Integer days;

    /**
     * 是否有效(0-无效,1-有效)
     */
    private Boolean enable;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private Integer createUserId;

    /**
     * 更新时间
     */
    private Date modifyTime;

    /**
     * 更新人
     */
    private Integer modifyUserId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 容量单位(1-ml,2-g)
     */
    private Integer capacityUnit;

}
