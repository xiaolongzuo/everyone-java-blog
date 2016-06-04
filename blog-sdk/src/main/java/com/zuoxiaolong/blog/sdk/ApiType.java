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

import com.google.gson.reflect.TypeToken;
import com.zuoxiaolong.blog.model.persistent.ArticleCategory;

import java.lang.reflect.Type;
import java.util.List;

/**
 * API种类
 * NOTE:每增加一种API的调用支持,就需要在这里添加一个枚举示例
 *
 * @author Xiaolong Zuo
 * @since 1.0.0
 */
public enum ApiType {

    example1("GET", new TypeToken<List<ArticleCategory>>(){}.getType()),
    example2("GET", ArticleCategory.class),
    example4("GET")
    ;

    private String url;

    private String method;

    private Type resultType;

    ApiType(String method) {
        this(method, null);
    }

    ApiType(String method, Type resultType) {
        String[] names = toString().split("_");
        StringBuffer url = new StringBuffer();
        for (int i = 0; i < names.length ; i++) {
            url.append("/").append(names[i]);
        }
        this.url = url.toString();
        this.method = method;
        this.resultType = resultType;
    }

    public String getUrl(String serverUrl) {
        return serverUrl + url;
    }

    public String getMethod() {
        return method;
    }

    public Type getResultType() {
        return resultType;
    }

}
