package com.jaagro.cbs.api.dto.plan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 鸡舍检查参数
 * @author: @Gao.
 * @create: 2019-03-07 18:34
 **/
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CheckCoopParamDto implements Serializable {
    private List<Integer> plantIds;
}
