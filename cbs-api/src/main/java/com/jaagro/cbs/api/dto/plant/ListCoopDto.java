package com.jaagro.cbs.api.dto.plant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author baiyiran
 * @Date 2019/2/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ListCoopDto implements Serializable {
    /**
     * 客户鸡舍信息表id
     */
    private Integer id;

    /**
     * 鸡舍编号
     */
    private String coopNo;
}
