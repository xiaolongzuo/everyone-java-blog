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

import com.zuoxiaolong.blog.common.orm.DropDownPage;
import com.zuoxiaolong.blog.common.utils.ObjectUtils;
import com.zuoxiaolong.blog.model.persistent.UserArticle;
import com.zuoxiaolong.blog.service.UserArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 郭松涛
 * @date 2016/6/10 13:20
 * @since 1.0.0
 */
@Controller
@RequestMapping("/HomePage")
public class HomePageController extends AbstractApiController {

    @Autowired
    private UserArticleService userArticleService;

    @RequestMapping(value = "/Articles", method = RequestMethod.POST)
    public List<UserArticle> getArticles(@RequestParam("categoryId") int categoryId,
                                         @RequestParam("offset") Date offset,
                                         @RequestParam(required = false) int size) {

        DropDownPage page = new DropDownPage();
        if (!ObjectUtils.isEmpty(offset)) {
            page.setOffset(offset);
        }
        if (!ObjectUtils.isEmpty(size)) {
            page.setSize(size);
        }
        page.setOrderColumn("update_time");

        List<UserArticle> s = userArticleService.getArticles(page,categoryId);
        return s;
    }

    @RequestMapping(value = {"/TopThreeUserArticles"}, method = {RequestMethod.GET, RequestMethod.POST})
    public List<Map<String, UserArticle>> topThreeUserArticles(String categoryName) {
        return userArticleService.getTopThreeUserArticles(categoryName);
    }

}
