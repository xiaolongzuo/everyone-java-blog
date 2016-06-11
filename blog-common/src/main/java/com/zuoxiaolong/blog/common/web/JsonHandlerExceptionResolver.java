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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 通用的Json返回异常处理器
 *
 * @author Xiaolong Zuo
 * @since 1.0.0
 */
public class JsonHandlerExceptionResolver implements HandlerExceptionResolver {

    public static final Logger LOGGER = LoggerFactory.getLogger(JsonHandlerExceptionResolver.class);

    private static final String KEY = "exception";

    private View errorView = new ErrorView();

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        logException(handler, exception, parameterMap);
        JsonResponse result = new JsonResponse(exception);
        ModelAndView view = new ModelAndView(errorView);
        view.addObject(KEY, result);
        return view;
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

    private static class ErrorView implements View {

        private static final String CONTENT_TYPE = "application/json;charset=utf-8";

        @Override
        public String getContentType() {
            return CONTENT_TYPE;
        }

        @Override
        public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
            JsonResponse errorResponse = (JsonResponse) model.get(KEY);
            response.setContentType(CONTENT_TYPE);
            response.getOutputStream().write(StringUtils.toBytes(JsonUtils.toJson(errorResponse)));
            response.getOutputStream().flush();
        }

    }

}
