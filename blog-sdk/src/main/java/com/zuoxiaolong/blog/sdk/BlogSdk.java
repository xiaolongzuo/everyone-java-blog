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

package com.zuoxiaolong.blog.sdk;

import com.zuoxiaolong.blog.common.bean.Attachment;
import com.zuoxiaolong.blog.common.bean.JsonResponse;

import java.util.Map;

/**
 * blog-sdk接口,该接口提供了不同种类API调用的方法
 *
 * @author Xiaolong Zuo
 * @since 1.0.0
 *
 * @see Api
 */
public interface BlogSdk {

    JsonResponse invokeApi(String token, String remoteIp, Api api);

    JsonResponse invokeApi(String token, String remoteIp, Api api, Map<String, String> params);

    JsonResponse invokeApi(String token, String remoteIp, Api api, String attachmentKey, Attachment[] attachments);

    JsonResponse invokeApi(String token, String remoteIp, Api api, Map<String, String> params, String attachmentKey, Attachment[] attachments);

}
