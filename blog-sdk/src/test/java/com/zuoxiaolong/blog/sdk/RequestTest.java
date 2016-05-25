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
import com.zuoxiaolong.blog.sdk.util.http.HttpRequest;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

/**
 * @author Boren You
 * @date 2016/5/25 14:57
 * @since 1.0.0
 */
public class RequestTest {

    public static void main(String[] args) throws Exception {
        HttpRequest request = new HttpRequest();

        String resp = request.doGet("http://localhost:8080/cache/article/rank");

        System.out.println(resp);

        // Creates the json object which will manage the information received
        GsonBuilder builder = new GsonBuilder();
        // Register an adapter to manage the date types as long values
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = builder.create();
        Type personListType = new TypeToken<List<ArticleRankResponseDto>>(){}.getType();
        List<ArticleRankResponseDto> jacks = gson.fromJson(resp, personListType);

        for(ArticleRankResponseDto dto : jacks){
            System.out.println(dto.getActionType());
        }

    }
}
