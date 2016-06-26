/*
 * Copyright 2016-2016 the original author or authors.
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
package com.zuoxiaolong.blog.web.controller;

import com.zuoxiaolong.blog.common.bean.JsonResponse;
import com.zuoxiaolong.blog.common.utils.CollectionUtils;
import com.zuoxiaolong.blog.common.utils.ObjectUtils;
import com.zuoxiaolong.blog.model.dto.MessageBoxDto;
import com.zuoxiaolong.blog.model.persistent.MessageBox;
import com.zuoxiaolong.blog.sdk.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @autor iCodingStar
 * @date 2016/6/25 17:10
 * @since 1.0.0
 */
@Controller
@RequestMapping("/MessageBox")
public class MessageBoxController extends AbstractWebController {
    /***
     * 查看信息箱消息内容
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/Content", method = RequestMethod.GET)
    public void getMessageContent(@RequestParam("id") Integer id) {
        JsonResponse jsonResponse = invokeApi(Api.MessageBox_GetMessageContent, CollectionUtils.newMap("id", id));
        renderJson(jsonResponse);
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
    public void getMessageList(@RequestParam(required = false) Integer currentPageNumber,
                               @RequestParam(required = false) Integer pageSize,
                               MessageBox messageBox) {
        Map<String, String> params = ObjectUtils.objectToMap(messageBox);
        if (!ObjectUtils.isEmpty(currentPageNumber)) {
            params.put("currentPageNumber", String.valueOf(currentPageNumber));
        }
        if (!ObjectUtils.isEmpty(pageSize)) {
            params.put("pageSize", String.valueOf(pageSize));
        }
        JsonResponse jsonResponse = invokeApi(Api.MessageBox_GetMessageList, params);
        renderJson(jsonResponse);
    }

    /***
     * 发送短消息
     *
     * @param messageBoxDto
     * @return
     */
    @RequestMapping(value = "/Send", method = {RequestMethod.POST, RequestMethod.GET})
    public void sendMessage(MessageBoxDto messageBoxDto) {
        renderJson(invokeApi(Api.MessageBox_SendMessage, messageBoxDto));
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
    @RequestMapping(value = "/Update", method = {RequestMethod.POST, RequestMethod.GET})
    public void updateMessageStatus(MessageBox messageBox) {
        JsonResponse jsonResponse = (invokeApi(Api.MessageBox_UpdateMessageStatus, messageBox));
        renderJson(jsonResponse);
    }
}
