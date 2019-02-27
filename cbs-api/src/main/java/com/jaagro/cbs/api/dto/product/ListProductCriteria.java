package com.jaagro.cbs.api.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 查询产品参数
 * @author yj
 * @date 2019/2/27 11:13
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ListProductCriteria implements Serializable{
    /**
     * 当前页
     */
    @NotNull(message = "{pageNum.NotNull}")
    @Min(value = 1,message = "{pageNum.Min}")
    private int pageNum;

    /**
     * 每页的数量
     */
    @NotNull(message = "{pageSize.NotNull}")
    @Min(value = 1,message = "{pageSize.Min}")
    private int pageSize;
    /**
     * 产品名称
     */
    private String productName;

    /**
     * 订单货物类型（1: 种苗 2: 饲料 3: 药品）
     */
    private Integer productType;

    /**
     * 生产商名称
     */
    private String manufacturer;
}
