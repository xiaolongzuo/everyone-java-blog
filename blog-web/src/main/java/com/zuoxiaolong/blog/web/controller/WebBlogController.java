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

import com.zuoxiaolong.blog.model.dto.UserBlogInfo;
import com.zuoxiaolong.blog.model.persistent.BlogConfig;
import com.zuoxiaolong.blog.web.service.WebBlogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * 博客主页controller
 * @author linjiedeng
 * @since 1.0.0
 */
@Controller
@RequestMapping("/blog")
public class WebBlogController {

    @Resource
    private WebBlogService webBlogService;

    /**
     * 根据请求路径中的用户名跳转到个人博客主页
     * @param username
     * @return
     */
    @RequestMapping("/homepage/{username}")
    public ModelAndView blog(@PathVariable String username) {
        UserBlogInfo userBlogInfo = webBlogService.selectUserBlogInfoByUsername(username);
        ModelAndView view = new ModelAndView("homepage");
        view.addObject(userBlogInfo);
        return view;
    }

    /**
     * 更新个人简介
     * @param blogConfig
     * @return
     */
    @RequestMapping("/update/config")
    public boolean updateBlogConfig(@RequestBody BlogConfig blogConfig) {

        return false;
    }
}
