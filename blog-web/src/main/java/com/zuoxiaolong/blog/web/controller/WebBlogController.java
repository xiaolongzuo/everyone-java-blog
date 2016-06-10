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

import com.zuoxiaolong.blog.common.bean.ExceptionType;
import com.zuoxiaolong.blog.common.exception.BusinessException;
import com.zuoxiaolong.blog.common.spring.BaseController;
import com.zuoxiaolong.blog.common.utils.CollectionUtils;
import com.zuoxiaolong.blog.model.persistent.BlogConfig;
import com.zuoxiaolong.blog.sdk.Api;
import com.zuoxiaolong.blog.sdk.BlogSdk;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 博客主页展示及配置相关
 * @author linjiedeng
 * @since 1.0.0
 */
@Controller
@RequestMapping("/blog")
public class WebBlogController extends BaseController {

    @Resource
    private BlogSdk blogSdk;

    /**
     * 更新个人简介,地址,博客名称等信息
     * @param blogConfig
     * @return
     */
    @RequestMapping(value = "/update/config", method = RequestMethod.POST)
    public String updateBlogConfig(@RequestBody BlogConfig blogConfig) {
        if(blogConfig == null) {
            logger.info("blogConfig param is null");
            throw new BusinessException(ExceptionType.PARAMETER_ILLEGAL);
        }

        Map<String, String> paraMap = CollectionUtils.newMap("webUserId", blogConfig.getWebUserId().toString());
        paraMap.put("introduction", blogConfig.getIntroduction());
        paraMap.put("address", blogConfig.getAddress());
        paraMap.put("blogTitle", blogConfig.getBlogTitle());
        paraMap.put("blogSubTitle", blogConfig.getBlogSubTitle());

        setModelAttribute("result", blogSdk.invokeApi(Api.blog_update_config, paraMap));
        return "/blog/blog-config";
    }

    /**
     * 查询用户博客的配置信息
     * @param webUserId
     * @return
     */
    @RequestMapping(value = "/select/config" , method = RequestMethod.POST)
    public String selectUserBlogConfig(Integer webUserId) {
        if(webUserId == null || webUserId < 0) {
            logger.info("webUserId: {} invalid", webUserId);
            throw new BusinessException(ExceptionType.PARAMETER_ILLEGAL);
        }

        setModelAttribute("result", blogSdk.invokeApi(Api.blog_select_config, CollectionUtils.newMap("webUserId", webUserId.toString())));
        return "/blog/blog-config";
    }
}
