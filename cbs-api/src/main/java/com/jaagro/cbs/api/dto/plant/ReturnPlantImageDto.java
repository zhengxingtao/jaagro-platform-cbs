package com.jaagro.cbs.api.dto.plant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author baiyiran
 * @Date 2019/2/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ReturnPlantImageDto {
    /**
     * 客户养殖场图片表id
     */
    private Integer id;

    /**
     * 养殖场id
     */
    private Integer plantId;

    /**
     * 图片地址
     */
    private String imageUrl;

    /**
     * 是否有效(0-无效,1-有效)
     */
    private Byte enable;
}
