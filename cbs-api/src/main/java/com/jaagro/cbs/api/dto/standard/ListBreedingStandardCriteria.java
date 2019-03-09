package com.jaagro.cbs.api.dto.standard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 分页查询养殖模板参数
 * @author yj
 * @date 2019/2/26 11:00
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ListBreedingStandardCriteria implements Serializable {
    private static final long serialVersionUID = 453410666308474891L;
    /**
     * 当前页
     */
    private Integer pageNum;

    /**
     * 每页的数量
     */
    private Integer pageSize;

    /**
     * 标准方案名称(左模糊)
     */
    private String standardName;
}
