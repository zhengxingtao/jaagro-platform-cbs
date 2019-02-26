package com.jaagro.cbs.api.dto.progress;

import com.jaagro.cbs.api.model.BreedingBatchParameter;
import com.jaagro.cbs.api.model.DeviceValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

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
     * 仪表参数实际检查结果
     */
    private List<DeviceValue> actualResult;
    /**
     * 报警信息
     */
    private String alarmMessage;


}
