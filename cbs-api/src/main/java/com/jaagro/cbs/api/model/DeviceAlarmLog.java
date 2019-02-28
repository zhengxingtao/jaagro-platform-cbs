package com.jaagro.cbs.api.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author :gaoxin
 * @date :2019/02/28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class DeviceAlarmLog implements Serializable {
    private Integer id;

    private Integer planId;

    private Integer plantId;

    private Integer coopId;

    private Integer deviceId;

    private Integer dayAge;

    private BigDecimal currentValue;

    private String paramStandardValue;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}