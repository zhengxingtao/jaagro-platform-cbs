package com.jaagro.cbs.api.dto.farmer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @description: 技术询问创建对象
 * @author: @Gao.
 * @create: 2019-03-05 10:53
 **/
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CreateTechnicalInquiriesDto {

    /**
     * 批次号
     */
    private String batchNo;
}
