package com.jaagro.cbs.api.service;

import com.github.pagehelper.PageInfo;
import com.jaagro.cbs.api.dto.message.MessageCriteriaDto;
import com.jaagro.cbs.api.model.Message;

import java.util.List;

/**
 * @description: 消息服务
 * @author: @Gao.
 * @create: 2019-03-08 15:53
 **/
public interface MessageService {

    /**
     * 分页查询消息
     *
     * @param dto
     * @return
     * @author: @Gao.
     */
    PageInfo listMessageByCriteria(MessageCriteriaDto dto);
}
