package com.jaagro.cbs.api.dto.farmer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 批次养殖场信息
 * @author yj
 * @date 2019/3/5 17:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BatchPlantDto implements Serializable{
    /**
     * 养殖场id
     */
    private Integer id;

    /**
     * 养殖场名称
     */
    private String plantName;

    /**
     * 批次鸡舍列表
     */
    private List<BatchCoopDto> batchCoopDtoList;
}
