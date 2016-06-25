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
package com.zuoxiaolong.blog.api.controller;

import com.zuoxiaolong.blog.model.dto.MessageBoxDto;
import com.zuoxiaolong.blog.model.persistent.MessageBox;
import com.zuoxiaolong.blog.model.persistent.WebUser;
import com.zuoxiaolong.blog.service.MessageBoxService;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author iCodingStar
 * @date 2016/6/25 13:20
 * @since 1.0.0
 */
@RequestMapping("/Message")
@Controller
public class MessageBoxController {
    @Autowired
    private MessageBoxService messageBoxService;

    /***
     * 查看信息箱消息内容
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/Content", method = RequestMethod.GET)
    public MessageBoxDto getMessageContent(Model model, @RequestParam("id") Integer id) {
        return messageBoxService.getMessageContentById(id);
    }

    /***
     * 分页参看消息列表
     *
     * @param currentPageNumber
     * @param pageSize
     * @param messageBox
     * @return
     */
    @RequestMapping(value = "/List", method = RequestMethod.GET)
    public List<MessageBoxDto> getMessageList(@RequestParam(required = false) Integer currentPageNumber,
                                              @RequestParam(required = false) Integer pageSize,
                                              MessageBox messageBox) {
        return messageBoxService.getMessagesByPage(currentPageNumber, pageSize, messageBox);
    }

    /***
     * 发送短消息
     *
     * @param messageBoxDto
     * @return
     */
    @RequestMapping(value = "/Send", method = RequestMethod.POST)
    public Integer sendMessage(MessageBoxDto messageBoxDto) {
        return messageBoxService.sendMessage(messageBoxDto);
    }

    /***
     * 修改短消息状态
     * 1:已读
     * 2:未读
     * 3:接收者已删除
     * 4:发送者已删除
     * 5:已删除
     *
     * @param messageBox
     * @return
     */
    @RequestMapping(value = "/Update", method = RequestMethod.DELETE)
    public Integer updateMessageStatus(MessageBox messageBox) {
        return messageBoxService.updateMessageStatus(messageBox);
    }
}
