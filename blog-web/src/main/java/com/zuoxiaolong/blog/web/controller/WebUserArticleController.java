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

package com.zuoxiaolong.blog.web.controller;

import com.zuoxiaolong.blog.common.bean.JsonResponse;
import com.zuoxiaolong.blog.common.cache.CacheUtils;
import com.zuoxiaolong.blog.common.utils.CollectionUtils;
import com.zuoxiaolong.blog.common.utils.StringUtils;
import com.zuoxiaolong.blog.model.persistent.ArticleCategory;
import com.zuoxiaolong.blog.model.persistent.UserArticle;
import com.zuoxiaolong.blog.sdk.Api;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * 文章Controller
 *
 * @author goozi
 * @create 2016-06-17 21:29
 * @since 1.0.0
 */
@Controller
@RequestMapping("/webArticle")
public class WebUserArticleController extends AbstractWebController {

    public static final String ARTICLE_CATEGORY_CACHE_NAME = "articleCategory";

    @RequestMapping(value = "/form")
    public String form(@RequestParam(required = false) String id, Model model) {
        UserArticle userArticle = new UserArticle();
        List<ArticleCategory> articleCategories = new ArrayList<>();
        if (!StringUtils.isEmpty(id)) {
            JsonResponse jsonResponse = invokeApi(Api.webUserArticle_index, CollectionUtils.newMap("id", id));
            if (jsonResponse.success()) {
                userArticle = (UserArticle) jsonResponse.getData();
            }
        }
        //从缓存获取
        CacheUtils cache = CacheUtils.get(ARTICLE_CATEGORY_CACHE_NAME);
        if (cache != null) {
            articleCategories = (List<ArticleCategory>) cache.value;
        }
        if (CollectionUtils.isEmpty(articleCategories)) {
            JsonResponse jsonResponse = invokeApi(Api.articleCategory_list);
            if (jsonResponse.success()) {
                articleCategories = (List<ArticleCategory>) jsonResponse.getData();
                //写入缓存，有效时间10分钟
                CacheUtils.set(ARTICLE_CATEGORY_CACHE_NAME, articleCategories, 10 * 60 * 1000);
            }
        }
        model.addAttribute("articleCategories", articleCategories);
        model.addAttribute("userArticle", userArticle);
        return "article/articleForm";
    }

    /**
     * 添加或者更新文章
     *
     * @param userArticle 文章信息
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void addArticle(UserArticle userArticle) {
        JsonResponse jsonResponse = invokeApi(Api.webUserArticle_save, userArticle);
        if (!jsonResponse.success()) {

        }
    }

    /**
     * 删除文章
     *
     * @param id 文章ID
     */
    @RequestMapping(value = "/delete")
    public String deleteArticle(@RequestParam String id) {
        JsonResponse jsonResponse = invokeApi(Api.webUserArticle_delete, CollectionUtils.newMap("id", id));
        String userId = "1";
        return "redirect:/webArticle/list?userId=" + userId;
    }

    /**
     * 获取当前登录用户的文章列表
     *
     * @return
     */
    @RequestMapping(value = "/list")
    public List<UserArticle> getArticles(@RequestParam String userId) {
        return null;
    }

}
