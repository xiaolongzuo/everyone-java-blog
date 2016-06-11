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

import com.zuoxiaolong.blog.common.utils.StringUtils;
import com.zuoxiaolong.blog.model.persistent.UserArticle;
import com.zuoxiaolong.blog.service.WebUserArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
@RequestMapping("/webUserArticle/article")
public class WebUserArticleController extends ApiBaseController {

    @Autowired
    WebUserArticleService webUserArticleService;

    @ModelAttribute
    public UserArticle get(@RequestParam(required = false) String id) {
        if (!StringUtils.isEmpty(id)) {
            return webUserArticleService.getArticleById(id);
        } else {
            return new UserArticle();
        }
    }

    @RequestMapping(value = "/form")
    public String form(UserArticle userArticle, Model model) {
        model.addAttribute("article", userArticle);
        return "jsp/article/articleForm";
    }

    /**
     * 添加或者更新文章
     *
     * @param userArticle 文章信息
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void addArticle(UserArticle userArticle) {
        webUserArticleService.saveOrUpdateArticle(userArticle);
    }

    /**
     * 删除文章
     *
     * @param userArticle
     */
    @RequestMapping(value = "/delete")
    public void deleteArticle(UserArticle userArticle) {
        webUserArticleService.deleteArticle(userArticle.getId());
    }

    /**
     * 获取文章列表
     *
     * @return
     */
    @RequestMapping(value = "/list")
    public List<UserArticle> getArticles() {
        String userId = "";
        return webUserArticleService.getArticleByUserId(userId);
    }
}
