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

package com.zuoxiaolong.blog.sdk.impl;

import com.zuoxiaolong.blog.common.bean.Attachment;
import com.zuoxiaolong.blog.common.bean.JsonResponse;
import com.zuoxiaolong.blog.common.utils.HttpUtils;
import com.zuoxiaolong.blog.common.utils.JsonUtils;
import com.zuoxiaolong.blog.common.utils.StringUtils;
import com.zuoxiaolong.blog.sdk.ApiType;
import com.zuoxiaolong.blog.sdk.BlogApiSdk;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Xiaolong Zuo
 * @since 1.0.0
 */
public class BlogApiSdkImpl implements BlogApiSdk {

    private static final Logger logger = LoggerFactory.getLogger(BlogApiSdkImpl.class);

    private String serverUrl;

    BlogApiSdkImpl(String serverUrl) {
        if (StringUtils.isEmpty(serverUrl)) {
            throw new IllegalArgumentException("serverUrl can't be empty.");
        }
        while (serverUrl.endsWith("/")) {
            serverUrl = serverUrl.substring(0, serverUrl.length() - 1);
        }
        this.serverUrl = serverUrl;
        logger.info("Blog-sdk use serverUrl : " + serverUrl);
    }

    @Override
    public JsonResponse invokeApi(ApiType apiType) {
        logger.info("Invoke Blog-api : " + apiType);
        String response = HttpUtils.sendHttpRequest(apiType.getMethod(), apiType.getUrl(serverUrl));
        return fromJson(response, apiType);
    }

    @Override
    public JsonResponse invokeApi(ApiType apiType, Map<String, String> params) {
        logger.info("Invoke Blog-api : " + apiType);
        String response = HttpUtils.sendHttpRequest(apiType.getMethod(), apiType.getUrl(serverUrl), params);
        return fromJson(response, apiType);
    }

    @Override
    public JsonResponse invokeApi(ApiType apiType, String attachmentKey, Attachment[] attachments) {
        logger.info("Invoke Blog-api : " + apiType);
        String response;
        try {
            response = HttpUtils.sendSimpleHttpMultipartRequest(apiType.getUrl(serverUrl), new HashMap<>(), attachmentKey, attachments);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fromJson(response, apiType);
    }

    @Override
    public JsonResponse invokeApi(ApiType apiType, Map<String, String> params, String attachmentKey, Attachment[] attachments) {
        logger.info("Invoke Blog-api : " + apiType);
        String response;
        try {
            response = HttpUtils.sendSimpleHttpMultipartRequest(apiType.getUrl(serverUrl), params, attachmentKey, attachments);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fromJson(response, apiType);
    }

    private JsonResponse fromJson(String response, ApiType apiType) {
        JsonResponse jsonResponse = JsonUtils.fromJson(response, JsonResponse.class);
        if (apiType.getResultType() == null) {
            return jsonResponse;
        }
        String dataJson = JsonUtils.toJson(jsonResponse.getData());
        Object data = JsonUtils.fromJson(dataJson, apiType.getResultType());
        jsonResponse.setData(data);
        return jsonResponse;
    }

}
