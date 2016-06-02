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

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.zuoxiaolong.blog.model.dto.cache.ArticleRankResponseDto;
import com.zuoxiaolong.blog.sdk.util.BlogSdkPropertiesUtil;
import com.zuoxiaolong.blog.sdk.util.http.HttpRequest;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

/**
 * @author Boren You
 * @date 2016/5/26 10:52
 * @since 1.0.0
 */
public interface BlogSdk {

    /**
     * 从文章排名接口中获取文章数据
     *
     * @return
     */
    static List<ArticleRankResponseDto> getArticleRank() throws Exception {
        HttpRequest request = new HttpRequest();
        String responseString = request.doGet(BlogSdkPropertiesUtil.getProperty("articleRankUrl"));
        System.out.println(responseString);

        // Creates the json object which will manage the information received
        GsonBuilder gsonBuilder = new GsonBuilder();
        // Register an adapter to manage the date types as long values
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = gsonBuilder.create();
        Type articleRankResponseDtoListType = new TypeToken<List<ArticleRankResponseDto>>() {
        }.getType();
        List<ArticleRankResponseDto> articleRankResponseDtos = gson.fromJson(responseString, articleRankResponseDtoListType);
        return articleRankResponseDtos;
    }

}
