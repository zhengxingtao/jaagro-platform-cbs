package com.jaagro.cbs.api.dto.farmer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yj
 * @date 2019/3/5 17:29
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class BatchCoopDto implements Serializable {
    /**
     * 客户鸡舍信息表id
     */
    private Integer id;

    /**
     * 鸡舍编号
     */
    private String coopNo;

    /**
     * 鸡舍名称
     */
    private String coopName;

    /**
     * 存栏量
     */
    private Integer breedingStock;

    /**
     * 异常提示
     */
    private boolean alarm;

}
