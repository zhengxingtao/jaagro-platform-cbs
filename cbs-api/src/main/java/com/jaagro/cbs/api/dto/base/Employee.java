package com.jaagro.cbs.api.dto.base;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 员工信息
 * @author: @Gao.
 * @create: 2019-02-26 16:04
 **/
@Data
@Accessors(chain = true)
public class Employee implements Serializable {
    /**
     * 员工表自增
     */
    private Integer id;

    /**
     * 员工姓名
     */
    private String name;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 备注
     */
    private String notes;
}