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
import com.zuoxiaolong.blog.model.persistent.BlogConfig;
import com.zuoxiaolong.blog.sdk.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/WebBlog")
@Controller
public class WebBlogController extends AbstractWebController {

    /**
     * 获取个人博客主页信息
     *
     * @return
     */
    @RequestMapping("/HomePage/{username}")
    public String personalBlogHomePage(@PathVariable String username) {
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        JsonResponse response = invokeApi(Api.WebBlog_HomePage, params);
        if (response.getCode() == 200) {
            setModelAttribute("result", response);
            return "/blog/blog";
        }
        return "/user/login";
    }

    /**
     * 获取我的文章，加载更多
     * @param offset
     * @param size
     */
    @RequestMapping("/getMyBlogArticle")
    public void getMyBlogArticle(String offset, String size, String username) {
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("offset", offset);
        JsonResponse response = invokeApi(Api.WebBlog_HomePage, params);
        renderJson(response);
    }

    /**
     * 更新个人简介,地址,博客名称等信息
     * @param blogConfig
     * @return
     */
    @RequestMapping(value = "/Update/Config", method = RequestMethod.POST)
    public String updateBlogConfig(BlogConfig blogConfig) {
        JsonResponse response = invokeApi(Api.WebBlog_Update_Config, blogConfig);
        setModelAttribute("result", response);
        return "redirect:/WebBlog/HomePage/" + getUsername();
    }

    /**
     * 查询用户博客的配置信息
     * @return
     */
    @RequestMapping(value = "/Select/Config", method = RequestMethod.GET)
    public String selectUserBlogConfig() {
        JsonResponse response = invokeApi(Api.WebBlog_Select_Config);
        setModelAttribute("result", response);
        setModelAttribute("active", "self-config");
        return "/blog/blog-config";
    }

}
