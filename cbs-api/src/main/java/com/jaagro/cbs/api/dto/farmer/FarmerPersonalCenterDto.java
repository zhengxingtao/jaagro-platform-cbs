package com.jaagro.cbs.api.dto.farmer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 农户端个人中心
 * @author: @Gao.
 * @create: 2019-03-06 09:44
 **/
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class FarmerPersonalCenterDto implements Serializable {
    /**
     * 登录用户名
     */
    private String loginName;

    /**
     * 登录手机号
     */
    private String phoneNumber;

    /**
     * 客户id
     */
    private Integer customerId;

    /**
     * 客户名称
     */
    private String customerName;
}
