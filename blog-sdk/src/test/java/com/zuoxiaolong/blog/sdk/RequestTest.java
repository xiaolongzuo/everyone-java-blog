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
import com.zuoxiaolong.blog.common.utils.HttpUtils;
import com.zuoxiaolong.blog.common.utils.JsonUtils;
import com.zuoxiaolong.blog.model.dto.cache.ArticleRankResponseDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Boren You
 * @date 2016/5/25 14:57
 * @since 1.0.0
 */
public class RequestTest {

    public static void main(String[] args) throws Exception {
        String resp = HttpUtils.sendHttpRequest("GET", "http://localhost:8083/cache/article/rank");
        System.out.println(resp);


        ArrayList<ArticleRankResponseDto> list = JsonUtils.fromJson(resp, new TypeToken<List<ArticleRankResponseDto>>(){}.getType());

        for (ArticleRankResponseDto dto : list) {
            System.out.println(dto.getActionType());
        }

    }
}
