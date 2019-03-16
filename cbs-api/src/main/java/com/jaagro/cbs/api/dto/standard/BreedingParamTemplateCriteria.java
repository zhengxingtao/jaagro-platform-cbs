package com.jaagro.cbs.api.dto.standard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 养殖模板查询条件
 * @author: @Gao.
 * @create: 2019-03-15 16:01
 **/
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class BreedingParamTemplateCriteria implements Serializable {

    /**
     * 起始页
     */
    private Integer pageNum;

    /**
     * 每页条数
     */
    private Integer pageSize;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 客户姓名 手机 查询 条件
     */
    private String customerInfo;

    /**
     * 计划状态(0-待录入合同,1-待参数纠偏,2-待上鸡签收,3-养殖中,4-待出栏确认,5-待出栏计划,6-已完成,7-已取消)
     */
    private Integer planStatus;


    /**
     * 客户id
     */
    private List<Integer> customerIds;

}
