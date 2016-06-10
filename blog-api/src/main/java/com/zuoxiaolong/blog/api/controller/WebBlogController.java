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

import com.zuoxiaolong.blog.common.utils.SensitiveWordCheckUtils;
import com.zuoxiaolong.blog.model.dto.UserBlogInfo;
import com.zuoxiaolong.blog.model.persistent.BlogConfig;
import com.zuoxiaolong.blog.service.WebBlogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 博客主页controller
 * @author linjiedeng
 * @since 1.0.0
 */
@Controller
@RequestMapping("/WebBlog")
public class WebBlogController extends ApiBaseController{

    @Resource
    private WebBlogService webBlogService;

    /**
     * 更新个人简介,地址等信息
     * @param blogConfig
     * @return
     */
    @RequestMapping("/update/config")
    public int updateBlogConfig(@RequestBody BlogConfig blogConfig) {
        if(SensitiveWordCheckUtils.isContainSensitiveWord(blogConfig.getIntroduction())) {
            return webBlogService.updateBlogConfig(blogConfig);
        } else {
            return -1;
        }
    }


    /**
     * 获取个人博客主页信息
     * @return
     */
    @RequestMapping("/HomePage")
    public UserBlogInfo personalBlogHomePage(){
        return webBlogService.selectUserBlogInfoByUsername(getRequest());
    }
}
