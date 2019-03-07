package com.jaagro.cbs.biz.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author gavin
 * @date 2019-03-07 10:44
 */
@Data
@Accessors(chain = true)
public class BatchInfoBo implements Serializable {

    private static final long serialVersionUID = -1496212177080161176L;
    /**
     * 剩余喂养数量
     */
    private Integer currentAmount;

    /**
     * 死淘数量
     */
    private Integer deadAmount;
}
