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

import com.zuoxiaolong.blog.common.authorization.AuthorizationException;
import com.zuoxiaolong.blog.common.utils.JsonUtils;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 通用的Json返回异常处理器
 *
 * @author Xiaolong Zuo
 * @since 1.0.0
 */
public class CommonHandlerExceptionResolver implements HandlerExceptionResolver {

    public static final Logger LOGGER = LoggerFactory.getLogger(CommonHandlerExceptionResolver.class);

    @Setter
    private String loginPage;

    @Setter
    private String errorPage;

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        logException(handler, exception, parameterMap);
        if (exception instanceof AuthorizationException) {
            ModelAndView modelAndView = new ModelAndView("redirect:" + loginPage);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("redirect:" + errorPage);
            return modelAndView;
        }
    }

    private void logException(Object handler, Exception exception, Map<String, String[]> parameterMap) {
        if (handler != null && HandlerMethod.class.isAssignableFrom(handler.getClass())) {
            try {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                Class<?> beanType = handlerMethod.getBeanType();
                String methodName = handlerMethod.getMethod().getName();
                RequestMapping controllerRequestMapping = beanType.getDeclaredAnnotation(RequestMapping.class);
                String classMapping = "";
                if (controllerRequestMapping != null) {
                    classMapping = controllerRequestMapping.value()[0];
                }
                RequestMapping methodRequestMapping = handlerMethod.getMethodAnnotation(RequestMapping.class);
                String methodMapping = "";
                if (methodRequestMapping != null) {
                    methodMapping = methodRequestMapping.value()[0];
                }
                if (!methodMapping.startsWith("/")) {
                    methodMapping = "/" + methodMapping;
                }
                Logger logger = LoggerFactory.getLogger(beanType);
                logger.error("RequestMapping is:");
                logger.error(classMapping + methodMapping);
                logger.error("HandlerMethod is:");
                logger.error(beanType.getSimpleName() + "." + methodName + "()");
                logger.error("ParameterMap is:");
                logger.error(JsonUtils.toJson(parameterMap), exception);
            } catch (Exception e) {
                LOGGER.error(handler + " execute failed.", exception);
            }
        } else {
            LOGGER.error(handler + " execute failed.", exception);
        }
    }

}
