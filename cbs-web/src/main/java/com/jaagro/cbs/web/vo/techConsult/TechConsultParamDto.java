package com.jaagro.cbs.web.vo.techConsult;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author gavin
 * @Date 20190228
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TechConsultParamDto implements Serializable {

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
     * 客户id
     */
    private List<Integer> customerIds;

    /**
     * 客户姓名/手机号
     */
    private String keyWord;


    /**
     * 状态
     */
    private Integer status;


}
