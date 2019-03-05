package com.jaagro.cbs.api.dto.farmer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 技术询问创建对象
 * @author: @Gao.
 * @create: 2019-03-05 10:53
 **/
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CreateTechnicalInquiriesDto implements Serializable {

    /**
     * 养殖计划id
     */
    private Integer planId;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 问题描述
     */
    private String problemDesc;

    /**
     * 图片相对路径
     */
    private List<String> imageUrl;
}
