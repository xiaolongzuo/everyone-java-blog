/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zuoxiaolong.blog.service.impl;

import com.zuoxiaolong.blog.common.bean.ExceptionType;
import com.zuoxiaolong.blog.common.exception.BusinessException;
import com.zuoxiaolong.blog.common.orm.DigitalPage;
import com.zuoxiaolong.blog.common.utils.ObjectUtils;
import com.zuoxiaolong.blog.common.utils.StringUtils;
import com.zuoxiaolong.blog.common.utils.ValidateUtils;
import com.zuoxiaolong.blog.mapper.MessageBoxMapper;
import com.zuoxiaolong.blog.mapper.WebUserMapper;
import com.zuoxiaolong.blog.model.dto.MessageBoxDto;
import com.zuoxiaolong.blog.model.persistent.MessageBox;
import com.zuoxiaolong.blog.model.persistent.WebUser;
import com.zuoxiaolong.blog.service.MessageBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author iCodingStar
 * @version 1.0
 * @date 2016/6/24 16:44
 */
@Service
public class MessageBoxServiceImpl implements MessageBoxService {
    private static final int RECEIVE_MESSAGE = 0;//收信
    private static final int SEND_MESSAGE = 1;//发信
    private static final int MESSAGE_STATUS_READ = 0;//已读
    private static final int MESSAGE_STATUS_UNREAD = 1;//未读
    private static final int MESSAGE_STATUS_DELETE = 0;//已删除

    @Autowired
    private MessageBoxMapper messageBoxMapper;

    @Autowired
    private WebUserMapper webUserMapper;

    /***
     * 发送短消息
     *
     * @param username
     * @param messageBox
     * @return
     */
    @Override
    public Integer insertMessage(String username, MessageBox messageBox) {
        ValidateUtils.required(username);
        ValidateUtils.required(messageBox.getTitle());
        ValidateUtils.required(messageBox.getContent());
        ValidateUtils.sensitiveWord(messageBox.getTitle());
        ValidateUtils.sensitiveWord(messageBox.getContent());

        messageBox.setTitle(StringUtils.escapeHtml(messageBox.getTitle()));
        messageBox.setContent(StringUtils.escapeHtml(messageBox.getContent()));
        WebUser receiver = webUserMapper.selectByUsername(username);
        if (ObjectUtils.isEmpty(receiver)) {
            throw new BusinessException(ExceptionType.USER_NOT_FOUND);
        }
        messageBox.setReceiver(receiver.getId());
        messageBox.setStatus(1);
        return messageBoxMapper.insertSelective(messageBox);
    }

    /***
     * 根据id查短消息的详细信息
     *
     * @param id
     * @return
     */
    @Override
    public MessageBoxDto updateStatusAndGetContent(Integer id) {
        MessageBox message = new MessageBox();
        if (!ObjectUtils.isEmpty(id)) {
            message.setId(id);
            message.setStatus(MESSAGE_STATUS_READ);
            updateMessageStatus(message);
            message = messageBoxMapper.selectByPrimaryKey(id);
        }
        return getMessageBoxDto(message);
    }

    /***
     * @param currentPageNumber 当前页的页号
     * @param pageSize          页面大小
     * @param type              消息类型
     * @param status            消息状态
     * @param webUserId         用户id
     * @return 返回满足相应查询条件的消息列表
     */
    @Override
    public DigitalPage getMessagesByPage(Integer currentPageNumber, Integer pageSize, Integer type, Integer webUserId, Integer status) {
        DigitalPage page = new DigitalPage();
        MessageBox messageBox = new MessageBox();
        if (!ObjectUtils.isEmpty(currentPageNumber)) {
            page.setCurrentPageNumber(currentPageNumber);
        }
        if (!ObjectUtils.isEmpty(pageSize)) {
            page.setPageSize(pageSize);
        }
        page.setOrderColumn("create_time");
        if (!ObjectUtils.isEmpty(type) && type == RECEIVE_MESSAGE) {
            messageBox.setReceiver(webUserId);
        } else if (!ObjectUtils.isEmpty(type) && type == SEND_MESSAGE) {
            messageBox.setSender(webUserId);
        }
        if (!ObjectUtils.isEmpty(status)) {
            messageBox.setStatus(status);
        }
        List<MessageBoxDto> messageBoxDtos = new ArrayList<>();
        List<MessageBox> messageBoxes = messageBoxMapper.getMessagesByPage(page, messageBox);
        if (ObjectUtils.isEmpty(messageBoxes))
            return page;
        for (MessageBox message : messageBoxes) {
            messageBoxDtos.add(getMessageBoxDto(message));
        }
        page.setData(messageBoxDtos);
        return page;
    }

    @Override
    public Integer updateMessageStatus(MessageBox messageBox) {
        if (ObjectUtils.isEmpty(messageBox.getId())) {
            throw new BusinessException(ExceptionType.PARAMETER_ILLEGAL);
        }
        return messageBoxMapper.updateByPrimaryKeySelective(messageBox);
    }

    /***
     * 获取短消息的包装信息
     *
     * @param messageBox 基本消息
     * @return 返回消息的包装信息
     */
    private MessageBoxDto getMessageBoxDto(MessageBox messageBox) {
        MessageBoxDto messageBoxDto = new MessageBoxDto();
        if (!ObjectUtils.isEmpty(messageBox)) {
            messageBoxDto.setMessage(messageBox);
            WebUser sender = webUserMapper.selectByPrimaryKey(messageBox.getSender());
            if (!ObjectUtils.isEmpty(sender)) {
                messageBoxDto.setSender(getWebUserBasicInfo(sender));
            }
            WebUser receiver = webUserMapper.selectByPrimaryKey(messageBox.getReceiver());
            if (!ObjectUtils.isEmpty(receiver)) {
                messageBoxDto.setReceiver(getWebUserBasicInfo(receiver));
            }
        }
        return messageBoxDto;
    }

    /***
     * 获取用户个人的基本信息
     *
     * @param webUser 用户
     * @return 返回用户的非保密性基本信息
     */
    private WebUser getWebUserBasicInfo(WebUser webUser) {
        WebUser webUserDto = new WebUser();
        webUserDto.setNickname(webUser.getNickname());
        webUserDto.setUsername(webUser.getUsername());
        return webUserDto;
    }

}
