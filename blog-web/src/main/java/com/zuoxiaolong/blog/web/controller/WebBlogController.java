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
import com.zuoxiaolong.blog.common.utils.CollectionUtils;
import com.zuoxiaolong.blog.sdk.Api;
import com.zuoxiaolong.blog.sdk.BlogSdk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 博客主页controller
 *
 * @author linjiedeng
 * @since 1.0.0
 */
@Controller
@RequestMapping("/WebBlog")
public class WebBlogController extends WebBaseController {

    @Autowired
    private BlogSdk blogSdk;

    /**
     * 获取个人博客主页信息
     *
     * @return
     */
    @RequestMapping("/Homepage/{username}")
    public String personalBlogHomePage(@PathVariable String username) {
        JsonResponse response = blogSdk.invokeApi(Api.WebBlog_HomePage, CollectionUtils.newMap("username", username));
        setModelAttribute("result", response);
        return "/blog/blog";
    }
}
