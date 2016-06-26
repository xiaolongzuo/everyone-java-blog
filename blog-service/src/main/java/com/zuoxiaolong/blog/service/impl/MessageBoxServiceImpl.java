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

import com.zuoxiaolong.blog.common.orm.DigitalPage;
import com.zuoxiaolong.blog.common.utils.ObjectUtils;
import com.zuoxiaolong.blog.mapper.MessageBoxMapper;
import com.zuoxiaolong.blog.mapper.WebUserMapper;
import com.zuoxiaolong.blog.model.dto.MessageBoxDto;
import com.zuoxiaolong.blog.model.persistent.MessageBox;
import com.zuoxiaolong.blog.model.persistent.WebUser;
import com.zuoxiaolong.blog.service.MessageBoxService;
import org.omg.CORBA.Object;
import org.springframework.beans.factory.ObjectFactory;
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

    @Autowired
    private MessageBoxMapper messageBoxMapper;

    @Autowired
    private WebUserMapper webUserMapper;

    /***
     * 发送短消息
     *
     * @param messageBoxDto
     * @return
     */
    @Override
    public Integer sendMessage(MessageBoxDto messageBoxDto) {
        WebUser receiver = messageBoxDto.getReceiver();
        MessageBox messageBox = messageBoxDto.getMessage();
        if (!ObjectUtils.isEmpty(receiver)) {
            WebUser receiverDto = webUserMapper.selectByWebUser(receiver);
            if (!ObjectUtils.isEmpty(receiverDto)) {
                messageBox.setSender(receiverDto.getId());
            }
        }
        return messageBoxMapper.insertSelective(messageBox);
    }

    /***
     * 根据id查短消息的详细信息
     *
     * @param id
     * @return
     */
    @Override
    public MessageBoxDto getMessageContentById(Integer id) {
        MessageBox message = messageBoxMapper.selectByPrimaryKey(id);
        return getMessageBoxDto(message);
    }

    /**
     * 根据综合条件分页查询消息
     *
     * @param currentPageNumber 当前页的页号
     * @param pageSize          页面大小
     * @param messageBox        查询条件载体
     * @return 返回满足相应查询条件的消息列表
     */
    @Override
    public List<MessageBoxDto> getMessagesByPage(Integer currentPageNumber, Integer pageSize, MessageBox messageBox) {
        DigitalPage page = new DigitalPage();
        if (!ObjectUtils.isEmpty(currentPageNumber)) {
            page.setCurrentPageNumber(currentPageNumber);
        }
        if (!ObjectUtils.isEmpty(pageSize)) {
            page.setPageSize(pageSize);
        }
        page.setOrderColumn("create_time");
        List<MessageBoxDto> messageBoxDtos = new ArrayList<>();
        List<MessageBox> messageBoxes = messageBoxMapper.getMessagesByPage(page, messageBox);
        if (ObjectUtils.isEmpty(messageBoxes))
            return messageBoxDtos;
        for (MessageBox message : messageBoxes) {
            messageBoxDtos.add(getMessageBoxDto(message));
        }
        return messageBoxDtos;
    }

    @Override
    public Integer updateMessageStatus(MessageBox messageBox) {
        if (ObjectUtils.isEmpty(messageBox.getId())) {
            return -1;
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
