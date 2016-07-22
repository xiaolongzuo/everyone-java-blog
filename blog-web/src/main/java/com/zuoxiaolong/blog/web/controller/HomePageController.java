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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @RequestMapping("/Index")
    public String index(@RequestParam(required = false) Integer categoryId) {
        JsonResponse homeArticleDTO = invokeApi(Api.HomePage_Articles, CollectionUtils.newMap("categoryId", categoryId));
        JsonResponse articleCharts = invokeApi(Api.HomePage_GetArticleCharts, CollectionUtils.newMap("categoryId", categoryId));
        if (homeArticleDTO.success()) {
            setModelAttribute("homeArticleDTO", homeArticleDTO.getData());
        }
        if (articleCharts.success()) {
            setModelAttribute("articleCharts", articleCharts.getData());
        }
        return "/index/index";
    }

    @RequestMapping("/Articles")
    public void getArticles(Integer categoryId, String offset, Integer size) {
        JsonResponse jsonResponse = invokeApi(Api.HomePage_Articles, CollectionUtils.newMap(new String[]{"categoryId", "offset", "size"}, categoryId, offset, size));
        if (jsonResponse.success()) {
            renderJson(jsonResponse.getData());
        } else {
            renderJson("error");
        }
    }

}
