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


import com.zuoxiaolong.blog.common.utils.JsonMapper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Boren You
 * @date 2016/5/12 21:28
 * @since 1.0.0
 */
public class BaseController {
    /**
     * 存放当前线程的HttpServletRequest对象
     */
    private static ThreadLocal<HttpServletRequest> request = new ThreadLocal<HttpServletRequest>();

    /**
     * 存放当前线程的HttpServletResponse对象
     */
    private static ThreadLocal<HttpServletResponse> response = new ThreadLocal<HttpServletResponse>();

    /**
     * 存放当前线程的Model对象
     */
    private static ThreadLocal<Model> model = new ThreadLocal<Model>();

    /**
     * 使用@ModelAttribute注解标识的方法会在每个控制器中的方法访问之前先调用
     * @param request
     * @param response
     * @param model
     */
    @ModelAttribute
    protected void setReqAndResp(HttpServletRequest request, HttpServletResponse response, Model model) {
        BaseController.request.set(request);
        BaseController.response.set(response);
        BaseController.model.set(model);
    }

    /**
     * 获取当前线程的HttpServletRequest对象
     * @return
     */
    protected HttpServletRequest getHttpRequest() {
        return BaseController.request.get();
    }

    /**
     * 获取当前线程的HttpServletResponse对象
     * @return
     */
    protected HttpServletResponse getHttpResponse() {
        return BaseController.response.get();
    }

    /**
     * 获取当前线程的HttpSession对象
     * @return
     */
    protected HttpSession getHttpSession() {
        return getHttpRequest().getSession();
    }

    /**
     * 获取当前线程的Model对象
     * @return
     */
    protected Model getModel() {
        return BaseController.model.get();
    }

    /**
     * 向 Model 设置属性值
     * @param name
     * @param value
     */
    protected void setModelAttribute(String name, Object value) {
        getModel().addAttribute(name, value);
    }

    /**
     * 向 HttpServletRequest 设置属性值
     * @param name
     * @param value
     */
    protected void setRequestAttribute(String name, Object value) {
        HttpServletRequest request = this.getHttpRequest();
        request.setAttribute(name, value);
    }

    /**
     * 向 HttpSession 设置属性值
     * @param name
     * @param value
     */
    public void setSessionAttribute(String name, Object value) {
        HttpSession session = this.getHttpSession();
        session.setAttribute(name, value);
    }

    /**
     * 从 HttpSession 中获取属性值
     * @param name
     * @return
     */
    protected Object getSessionAttribute(String name) {
        HttpSession session = this.getHttpSession();
        Object value = session.getAttribute(name);
        return value;
    }

    /**
     * 从 HttpServletRequest 中获取属性值
     * @param name
     * @return
     */
    protected Object getRequestAttribute(String name) {
        HttpServletRequest request = this.getHttpRequest();
        Object value = request.getAttribute(name);
        return value;
    }

    /**
     * 客户端返回JSON字符串
     * @param response
     * @param object
     * @return
     */
    protected String renderString(HttpServletResponse response, Object object) {
        return renderString(response, JsonMapper.toJsonString(object), "application/json");
    }

    /**
     * 客户端返回字符串
     * @param response
     * @param string
     * @return
     */
    protected String renderString(HttpServletResponse response, String string, String type) {
        try {
            response.reset();
            response.setContentType(type);
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
            return null;
        } catch (IOException e) {
            return null;
        }
    }
}
