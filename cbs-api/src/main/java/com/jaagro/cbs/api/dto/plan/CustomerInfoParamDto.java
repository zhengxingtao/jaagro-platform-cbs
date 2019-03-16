package com.jaagro.cbs.api.dto.plan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 客户信息
 * @author: @Gao.
 * @create: 2019-03-15 13:29
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CustomerInfoParamDto implements Serializable {

    /**
     * 客户名称(个体客户时，就是自然人姓名)
     */
    private String customerName;

    /**
     * 主手机号
     */
    private String customerPhone;

    /**
     * 地址
     */
    private String customerAddress;
}
