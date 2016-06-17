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
import com.zuoxiaolong.blog.model.persistent.UserArticle;
import com.zuoxiaolong.blog.sdk.Api;
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
 * @date 2016/6/10 8:23
 * @since 1.0.0
 */
@Controller
@RequestMapping("/HomePage")
public class HomePageController extends AbstractWebController {

    /**
     * 当访问错误的时候，跳转到默认的主页
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        Map<String, String> params = new HashMap<>();
        params.put("categoryId", "1");
        params.put("startRow", "0");
        params.put("pageSize", "20");
        setModelAttribute("result", invokeApi(Api.HomePage_Articles, params));
        return "/index/index";
    }

    @RequestMapping(value = {"/TopThreeUserArticles"}, method = {RequestMethod.GET, RequestMethod.POST})
    public void topThreeUserArticles(String categoryName) {
        JsonResponse jsonResponse = invokeApi(Api.HomePage_TopThreeUserArticles, CollectionUtils.newMap("categoryName", categoryName));
        renderJson(jsonResponse);
    }


    @RequestMapping("/Articles")
    public void getArticles(@RequestParam("categoryId") int categoryId,
                                         @RequestParam("offset") String offset,
                                         @RequestParam(required = false) int size) {
        Map<String, String> params = new HashMap<>();
        params.put("categoryId", categoryId + "");
        params.put("offset", offset);
        params.put("size", size+"");
        JsonResponse jsonResponse = invokeApi(Api.HomePage_Articles, params);
        renderJson(jsonResponse);
    }

}
