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
public class CreatePlantImageDto implements Serializable {
    /**
     * 养殖场id
     */
    private Integer plantId;

    /**
     * 图片地址
     */
    private String imageUrl;

    private Integer createUserId;

}
