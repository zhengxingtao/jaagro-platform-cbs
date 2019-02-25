package com.jaagro.cbs.api.dto.progress;

import com.jaagro.cbs.api.model.BreedingBatchParameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author :Gavin
 * @date :2019/02/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BreedingBatchParamTrackingDto extends BreedingBatchParameter implements Serializable {

    /**
     * 喂料次数
     */
    private Integer count;
}
