/*
 * Copyright 2016-2016 the original author or authors.
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
import com.zuoxiaolong.blog.model.dto.cache.ArticleRankResponseDataResult;
import com.zuoxiaolong.blog.model.dto.cache.ArticleRankResponseDto;
import com.zuoxiaolong.blog.model.persistent.UserArticle;
import com.zuoxiaolong.blog.service.UserArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author iCodingStar
 * @version 1.0
 * @date 2016/6/10 19:28
 */
@RestController
@RequestMapping("/HomePage")
public class HomePageController extends BaseController{

    @Autowired
    private UserArticleService userArticleService;

    @RequestMapping(value = {"/TopThreeUserArticles"},method = {RequestMethod.GET,RequestMethod.POST})
    public List<Map<String, UserArticle>> topThreeUserArticles(String categoryName) {
        return userArticleService.getTopThreeUserArticles(categoryName);
    }

}
