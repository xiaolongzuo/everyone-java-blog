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

import com.fasterxml.jackson.databind.JavaType;
import com.zuoxiaolong.blog.sdk.util.http.common.HttpConfig;
import com.zuoxiaolong.blog.sdk.util.http.exception.HttpProcessException;
import com.zuoxiaolong.blog.sdk.util.http.httpclient.HttpClientUtil;
import com.zuoxiaolong.blog.model.dto.cache.ArticleRankResponseDto;
import com.zuoxiaolong.blog.sdk.util.JsonMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Boren You
 * @date 2016/5/25 14:57
 * @since 1.0.0
 */
public class RequestTest {

    public static void main(String[] args){
        HttpConfig httpConfig = HttpConfig.custom().url("http://localhost:8080/cache/article/rank");
        try {
            String resp = HttpClientUtil.send(httpConfig);

            JsonMapper jsonMapper = new JsonMapper();
            JavaType javaType = jsonMapper.createCollectionType(ArrayList.class, ArticleRankResponseDto.class);
            List<ArticleRankResponseDto> list = jsonMapper.fromJson(resp,javaType);
            for(ArticleRankResponseDto dto : list){
                System.out.println("ActionType:"+dto.getActionType());
            }
        } catch (HttpProcessException e) {
            e.printStackTrace();
        }
    }
}
