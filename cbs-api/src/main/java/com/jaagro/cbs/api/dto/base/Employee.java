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
     * 所属部门ID
     */
    private Integer departmentId;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 备注
     */
    private String notes;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 帐号状态(0；未审核  1；审核未通过 2－停止合作，3－正常合作)
     */
    private Integer status;
}