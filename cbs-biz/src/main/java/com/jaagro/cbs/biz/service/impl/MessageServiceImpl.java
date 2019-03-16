package com.jaagro.cbs.biz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jaagro.cbs.api.dto.message.MessageCriteriaDto;
import com.jaagro.cbs.api.model.Message;
import com.jaagro.cbs.api.model.MessageExample;
import com.jaagro.cbs.api.service.MessageService;
import com.jaagro.cbs.biz.mapper.MessageMapperExt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 消息服务
 * @author: @Gao.
 * @create: 2019-03-08 15:54
 **/
@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapperExt messageMapper;

    /**
     * 分页查询消息
     *
     * @param dto
     * @return
     * @author: @Gao.
     */
    @Override
    public PageInfo listMessageByCriteria(MessageCriteriaDto dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        MessageExample messageExample = new MessageExample();
        MessageExample.Criteria criteria = messageExample.createCriteria();
        List<Message> messages = null;
        if (dto.getToUserId() != null && dto.getToUserType() != null) {
            criteria
                    .andToUserTypeEqualTo(dto.getToUserType())
                    .andToUserIdEqualTo(dto.getToUserId());
            messages = messageMapper.selectByExample(messageExample);
        }
        return new PageInfo(messages);
    }
}
