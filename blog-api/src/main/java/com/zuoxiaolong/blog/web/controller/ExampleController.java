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

import com.zuoxiaolong.blog.model.persistent.ArticleCategory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Xiaolong Zuo
 * @since 1.0.0
 */
@Controller
public class ExampleController {

    @RequestMapping("/example1")
    @ResponseBody
    public List<ArticleCategory> example1() {
        ArticleCategory articleCategory = new ArticleCategory();
        articleCategory.setCategoryName("test");
        articleCategory.setCreateTime(new Date());
        articleCategory.setId(1);
        articleCategory.setUpdateTime(new Date());
        return Arrays.asList(articleCategory);
    }

    @RequestMapping("/example2")
    @ResponseBody
    public ArticleCategory example2() {
        ArticleCategory articleCategory = new ArticleCategory();
        articleCategory.setCategoryName("test");
        articleCategory.setCreateTime(new Date());
        articleCategory.setId(1);
        articleCategory.setUpdateTime(new Date());
        return articleCategory;
    }

}
