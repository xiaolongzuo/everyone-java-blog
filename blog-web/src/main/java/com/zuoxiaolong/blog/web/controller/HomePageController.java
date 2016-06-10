/*
<<<<<<< HEAD
 * Copyright 2016-2016 the original author or authors.
=======
 * Copyright 2002-2016 the original author or authors.
>>>>>>> fast
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
import com.zuoxiaolong.blog.common.spring.BaseController;
import com.zuoxiaolong.blog.common.utils.CollectionUtils;
import com.zuoxiaolong.blog.common.utils.JsonUtils;
import com.zuoxiaolong.blog.model.persistent.UserArticle;
import com.zuoxiaolong.blog.sdk.Api;
import com.zuoxiaolong.blog.sdk.BlogSdk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author iCodingStar
 * @version 1.0
 * @date 2016/6/10 19:28
 */
@Controller
@RequestMapping("/HomePage")
public class HomePageController extends BaseController {
    @Autowired
    private BlogSdk blogSdk;

    @RequestMapping(value = {"/TopThreeUserArticles"}, method = {RequestMethod.GET, RequestMethod.POST})
    public
    @ResponseBody
    Object topThreeUserArticles(String categoryName) {
        JsonResponse jsonResponse = blogSdk.invokeApi(Api.HomePage_TopThreeUserArticles, CollectionUtils.newMap("categoryName", categoryName));
        return JsonUtils.toJson(jsonResponse);
    }

    @RequestMapping("/Articles")
    public String getArticles(@RequestParam("categoryId") int categoryId,
                              @RequestParam(required = false, defaultValue = "1") int pageNum,
                              @RequestParam(required = false, defaultValue = "20") int pageSize) {
        Map<String, String> params = new HashMap<>();
        params.put("categoryId",categoryId+"");
        params.put("startRow",(pageNum-1)*pageSize+"");
        params.put("pageSize",pageSize+"");
        setModelAttribute("result", blogSdk.invokeApi(Api.HomePage_Articles, params));
        return "/index/index";
    }
}
