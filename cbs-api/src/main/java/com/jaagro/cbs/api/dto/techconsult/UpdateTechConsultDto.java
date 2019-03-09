package com.jaagro.cbs.api.dto.techconsult;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author gavin
 * @Date 20190301
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UpdateTechConsultDto implements Serializable {

    private static final long serialVersionUID = -6446061339408252417L;
    /**
     * 技术咨询记录表id
     */
    private Integer id;
    /**
     * 处理类型(1-电话询问,2-上门查看,3-已经解决,4-暂时搁置)
     */
    private Integer handleType;
    /**
     * 处理描述
     */
    private String handleDesc;
}
