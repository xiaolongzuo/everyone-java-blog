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
import com.zuoxiaolong.blog.common.utils.CollectionUtils;
import com.zuoxiaolong.blog.common.utils.HttpUtils;
import com.zuoxiaolong.blog.common.utils.JsonUtils;
import com.zuoxiaolong.blog.common.utils.StringUtils;
import com.zuoxiaolong.blog.sdk.Api;
import com.zuoxiaolong.blog.sdk.BlogSdk;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Xiaolong Zuo
 * @since 1.0.0
 */
public class BlogSdkImpl implements BlogSdk {

    private static final Logger logger = LoggerFactory.getLogger(BlogSdkImpl.class);

    private String serverUrl;

    BlogSdkImpl(String serverUrl) {
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
    public JsonResponse invokeApi(String token, String remoteIp, Api api) {
        logger.info("Invoke Blog-api : " + api);
        String response = HttpUtils.sendHttpRequest(api.getMethod(), CollectionUtils.newMap(new String[]{"token", "x-forwarded-for"}, token, remoteIp), api.getUrl(serverUrl));
        return fromJson(response, api);
    }

    @Override
    public JsonResponse invokeApi(String token, String remoteIp, Api api, Map<String, String> params) {
        logger.info("Invoke Blog-api : " + api);
        String response = HttpUtils.sendHttpRequest(api.getMethod(), CollectionUtils.newMap(new String[]{"token", "x-forwarded-for"}, token, remoteIp), api.getUrl(serverUrl), params);
        return fromJson(response, api);
    }

    @Override
    public JsonResponse invokeApi(String token, String remoteIp, Api api, String attachmentKey, Attachment[] attachments) {
        logger.info("Invoke Blog-api : " + api);
        String response;
        try {
            response = HttpUtils.sendSimpleHttpMultipartRequest(CollectionUtils.newMap(new String[]{"token", "x-forwarded-for"}, token, remoteIp), api.getUrl(serverUrl), new HashMap<>(), attachmentKey, attachments);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fromJson(response, api);
    }

    @Override
    public JsonResponse invokeApi(String token, String remoteIp, Api api, Map<String, String> params, String attachmentKey, Attachment[] attachments) {
        logger.info("Invoke Blog-api : " + api);
        String response;
        try {
            response = HttpUtils.sendSimpleHttpMultipartRequest(CollectionUtils.newMap(new String[]{"token", "x-forwarded-for"}, token, remoteIp), api.getUrl(serverUrl), params, attachmentKey, attachments);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fromJson(response, api);
    }

    private JsonResponse fromJson(String response, Api api) {
        JsonResponse jsonResponse = JsonUtils.fromJson(response, JsonResponse.class);
        if (api.getResultType() == null) {
            return jsonResponse;
        }
        String dataJson = JsonUtils.toJson(jsonResponse.getData());
        Object data = JsonUtils.fromJson(dataJson, api.getResultType());
        jsonResponse.setData(data);
        return jsonResponse;
    }

}
