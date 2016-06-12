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

package com.zuoxiaolong.blog.common.web;

import com.zuoxiaolong.blog.common.bean.JsonResponse;
import com.zuoxiaolong.blog.common.utils.JsonUtils;
import com.zuoxiaolong.blog.common.utils.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;

/**
 * 可以统一JSON返回格式
 *
 * @author Xiaolong Zuo
 * @since 1.0.0
 */
public class JsonHandlerMethodReturnValueHandler implements HandlerMethodReturnValueHandler {

    private static final String CONTENT_TYPE = "application/json;charset=utf-8";

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return true;
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        mavContainer.setRequestHandled(true);
        HttpServletResponse httpServletResponse = webRequest.getNativeResponse(HttpServletResponse.class);
        httpServletResponse.setContentType(CONTENT_TYPE);
        ServletServerHttpResponse outputMessage = new ServletServerHttpResponse(httpServletResponse);
        JsonResponse jsonResponse = new JsonResponse(returnValue);
        outputMessage.getBody().write(StringUtils.toBytes(JsonUtils.toJson(jsonResponse)));
        outputMessage.getBody().flush();
    }

}
