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

import com.google.common.collect.ImmutableMap;
import com.zuoxiaolong.blog.common.spring.BaseController;
import com.zuoxiaolong.blog.sdk.Api;
import com.zuoxiaolong.blog.sdk.BlogSdk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author 郭松涛
 * @date 2016/6/10 8:23
 * @since 1.0.0
 */
@Controller
@RequestMapping("/Homepage")
public class UserArticleController extends BaseController {
    @Autowired
    private BlogSdk blogSdk;

    @RequestMapping("/Articles")
    public String getArticles(@RequestParam("categoryId") int categoryId,
                              @RequestParam(required = false, defaultValue = "1") int pageNum,
                              @RequestParam(required = false, defaultValue = "20") int pageSize) {

        Map<String, String> params = ImmutableMap.of("categoryId",categoryId+"",
                "startRow",(pageNum-1)*pageSize+"",
                "pageSize",pageSize+"");
        setModelAttribute("result", blogSdk.invokeApi(Api.Homepage_Articles, params));
        return "/index/index";
    }
}
