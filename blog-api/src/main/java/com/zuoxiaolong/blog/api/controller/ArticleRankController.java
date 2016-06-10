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

package com.zuoxiaolong.blog.api.controller;

import com.zuoxiaolong.blog.common.cache.SingletonCache;
import com.zuoxiaolong.blog.common.spring.BaseController;
import com.zuoxiaolong.blog.model.dto.cache.ArticleRankResponseDto;
import com.zuoxiaolong.blog.model.persistent.UserArticle;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 文章排名Controller
 *
 * @author goozi
 * @create 2016-05-17 20:15
 * @since 1.0.0
 */
@RestController
@RequestMapping("/article")
public class ArticleRankController extends BaseController {

    /**
     * 文章排名查询接口
     */
    @RequestMapping(value = "/rank")
    public List<ArticleRankResponseDto> articleRank() {
        return (List<ArticleRankResponseDto>) SingletonCache.instance().get("ArticleRankResponseDto");
    }

}
