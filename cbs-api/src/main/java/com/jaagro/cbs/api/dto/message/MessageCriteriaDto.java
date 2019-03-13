package com.jaagro.cbs.api.dto.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 消息查询条件
 * @author: @Gao.
 * @create: 2019-03-08 15:59
 **/
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class MessageCriteriaDto implements Serializable {

    /**
     * 起始页
     */
    private Integer pageNum;

    /**
     * 每页条数
     */
    private Integer pageSize;


    private Integer id;

    /**
     * 消息发送者id：0-系统
     */
    private Integer fromUserId;

    /**
     * 消息发送者类型：0-系统 1-客户 2-司机 3-员工
     */
    private Integer fromUserType;

    /**
     * 消息接受者id：0-系统
     */
    private Integer toUserId;

    /**
     * 消息接受者类型：0-系统 1-客户 2-司机 3-员工
     */
    private Integer toUserType;

    /**
     * 分类 1-通知,2-提醒
     */
    private Integer category;

    /**
     * 消息类型：1-系统通知(公告)
     */
    private Integer msgType;

    /**
     * 消息来源:1-APP,2-小程序,3-站内
     */
    private Integer msgSource;

    /**
     * 关联养殖计划Id 等
     */
    private Integer referId;

    /**
     * 关联id类型
     */
    private Integer referType;

    /**
     * 消息状态：0-未读 1-已读
     */
    private Integer msgStatus;
}
