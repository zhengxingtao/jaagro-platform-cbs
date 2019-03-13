package com.jaagro.cbs.web.vo.message;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 农户端消息列表
 * @author: @Gao.
 * @create: 2019-03-08 16:56
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class FarmerMessageVo implements Serializable {
    /**
     * 消息头
     */
    private String header;

    /**
     * 消息体
     */
    private String body;

    /**
     * 关联养殖计划Id 等
     */
    private Integer referId;

    /**
     * 消息状态：0-未读 1-已读
     */
    private Integer msgStatus;

    /**
     * 创建时间
     */
    private Date createTime;

}
