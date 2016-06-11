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
package com.zuoxiaolong.blog.web.controller;

import com.zuoxiaolong.blog.common.spring.BaseController;
import com.zuoxiaolong.blog.common.utils.JsonUtils;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Boren You
 * @dateTime 2016/6/11 0:23
 * @since 1.0.0
 */
public abstract class WebBaseController extends BaseController {

    /**
     * 存放当前线程的HttpServletResponse对象
     */
    private static ThreadLocal<HttpServletResponse> httpServletResponseThreadLocal = new ThreadLocal<>();

    protected static final String TOKEN_ATTRIBUTE_NAME = "token";

    /**
     * 绑定response对象
     * @param response
     */
    @ModelAttribute
    protected void setThreadLocal(HttpServletResponse response) {
        httpServletResponseThreadLocal.set(response);
    }

    /**
     * 成功登录后处理session
     *
     * @param token
     */
    protected void loginSuccess(String token) {
        setSessionAttribute(TOKEN_ATTRIBUTE_NAME, token);
    }

    /**
     * 获取用户的token
     *
     * @return
     */
    protected String getToken() {
        return (String) getSessionAttribute(TOKEN_ATTRIBUTE_NAME);
    }

    /**
     * 获取当前线程的HttpServletResponse对象
     * @return 当前线程的HttpServletResponse对象
     */
    protected HttpServletResponse getResponse() {
        return httpServletResponseThreadLocal.get();
    }

    /**
     * 客户端返回JSON字符串
     * @param object
     * @return
     */
    protected void renderJson(Object object) {
        renderText(JsonUtils.toJson(object), "application/json");
    }

    /**
     * 客户端返回字符串
     * @param string
     * @return
     */
    protected void renderText(String string, String type) {
        try {
            HttpServletResponse response = getResponse();
            response.reset();
            response.setContentType(type);
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
