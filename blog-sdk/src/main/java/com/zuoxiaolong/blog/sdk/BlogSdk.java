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
import com.zuoxiaolong.blog.common.utils.JsonMapper;
import com.zuoxiaolong.blog.model.dto.cache.ArticleRankResponseDto;
import com.zuoxiaolong.blog.sdk.util.BlogSdkPropertiesUtil;
import com.zuoxiaolong.blog.sdk.util.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Boren You
 * @date 2016/5/26 10:52
 * @since 1.0.0
 */
public interface BlogSdk {
    Logger logger = LoggerFactory.getLogger(BlogSdk.class);

    /**
     * 从文章排名接口中获取文章数据
     *
     * @return
     */
    static List<ArticleRankResponseDto> getArticleRank() throws Exception {
        HttpRequest request = new HttpRequest();
        String responseString = request.doGet(BlogSdkPropertiesUtil.getProperty("articleRankUrl"));
        logger.debug("文章排名列表接口返回：" + responseString);
        System.out.println(responseString);
        JavaType javaType = JsonMapper.createCollectionType(ArrayList.class, ArticleRankResponseDto.class);
        ArrayList<ArticleRankResponseDto> list = JsonMapper.fromJson(responseString, javaType);
        return list;
    }

}
