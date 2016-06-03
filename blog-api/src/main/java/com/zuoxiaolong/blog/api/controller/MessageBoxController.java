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

import com.zuoxiaolong.blog.model.persistent.MessageBox;
import com.zuoxiaolong.blog.service.MessageBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author voyagezhang
 * @date 2016/5/16
 * @since 1.0.0
 */
@RequestMapping("/message")
@Controller
public class MessageBoxController {
    @Autowired
    private MessageBoxService messageBoxService;

    /**
     * 查看信息箱消息内容
     * @param
     * @return
     */
    @RequestMapping(value = "/content",method = RequestMethod.GET)
    public ModelAndView getMessageContent(Model model,@RequestParam("id") Integer id){
        MessageBox messageBox=messageBoxService.selectByPrimaryKey(id);
        ModelAndView modelAndView=new ModelAndView("/success");
        String content=messageBox.getContent();
        model.addAttribute("result",content);
        return modelAndView;
    }

    /**
     * 分页参看消息列表
     * @param
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView getMessageList(Model model,@RequestParam("offset") Integer offset,@RequestParam("limit") Integer limit){
        List<MessageBox> messageBoxes=messageBoxService.selectMessageBoxList(offset, limit);
        ModelAndView modelAndView=new ModelAndView("/success");
        model.addAttribute("result",messageBoxes);
        return modelAndView;
    }

    /**
     * 发送短消息
     * @param
     * @return
     */
    @RequestMapping(value = "/send",method = RequestMethod.POST)
    public ModelAndView sendMessage(Model model,@RequestBody MessageBox messageBox) {
        int resultflag=messageBoxService.insertSelective(messageBox);
        ModelAndView modelAndView=new ModelAndView("/success");
        model.addAttribute("result",resultflag);
        return modelAndView;
    }

    /**
     * 删除短消息
     * @param
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ModelAndView deleteMessage(Model model,@RequestParam("id") Integer id) {
        int resultflag = messageBoxService.deleteByPrimaryKey(id);
        ModelAndView modelAndView=new ModelAndView("/success");
        model.addAttribute("result",resultflag);
        return modelAndView;
    }
}
