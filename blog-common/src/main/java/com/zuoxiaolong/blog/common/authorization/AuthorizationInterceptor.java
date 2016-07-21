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

package com.zuoxiaolong.blog.common.authorization;

import com.zuoxiaolong.blog.common.bean.ExceptionType;
import com.zuoxiaolong.blog.common.exception.BusinessException;
import com.zuoxiaolong.blog.common.utils.ObjectUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Xiaolong Zuo
 * @since 1.0.0
 */
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        CheckLogin checkLogin = handlerMethod.getMethodAnnotation(CheckLogin.class);
        if (checkLogin != null) {
            checkLogin(request);
        }
        checkLogin = handlerMethod.getBeanType().getDeclaredAnnotation(CheckLogin.class);
        if (checkLogin != null) {
            checkLogin(request);
        }
        return super.preHandle(request, response, handler);
    }

    private void checkLogin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (ObjectUtils.isEmpty(session.getAttribute("username"))) {
            throw new BusinessException(ExceptionType.AUTHORIZATION_ERROR);
        }
    }

}
