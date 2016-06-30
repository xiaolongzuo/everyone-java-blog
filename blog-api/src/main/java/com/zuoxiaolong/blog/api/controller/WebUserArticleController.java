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

import com.zuoxiaolong.blog.model.persistent.UserArticle;
import com.zuoxiaolong.blog.service.WebUserArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 个人文章API
 *
 * @author goozi
 * @create 2016-06-08 21:23
 * @since 1.0.0w
 */
@Controller
@RequestMapping("/WebUserArticle")
public class WebUserArticleController extends AbstractApiController {

    @Autowired
    WebUserArticleService webUserArticleService;

    /**
     * 获取单篇文章信息
     *
     * @param id 文章ID
     * @return 文章信息
     */
    @RequestMapping("/Index")
    public UserArticle getArticle(@RequestParam String id) {
        return webUserArticleService.getArticleById(id);
    }

    /**
     * 添加或者更新文章
     *
     * @param userArticle 文章信息
     */
    @RequestMapping(value = "/Save", method = RequestMethod.POST)
    public Integer addArticle(UserArticle userArticle) {
        Integer userId = getWebUserId();
        userArticle.setWebUserId(userId);
        return webUserArticleService.saveOrUpdateArticle(userArticle);
    }

    /**
     * 删除文章
     *
     * @param id 文章ID
     */
    @RequestMapping(value = "/Delete")
    public void deleteArticle(@RequestParam String id) {
        webUserArticleService.deleteArticle(id);
    }

    /**
     * 获取文章列表
     *
     * @return
     */
    @RequestMapping(value = "/List")
    public List<UserArticle> getArticles(@RequestParam String userId) {
        return webUserArticleService.getArticleByUserId(userId);
    }
}
