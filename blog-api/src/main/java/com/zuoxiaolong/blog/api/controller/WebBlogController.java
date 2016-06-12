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

import com.zuoxiaolong.blog.common.bean.ExceptionType;
import com.zuoxiaolong.blog.common.exception.BusinessException;
import com.zuoxiaolong.blog.common.utils.SensitiveWordCheckUtils;
import com.zuoxiaolong.blog.model.dto.UserBlogInfo;
import com.zuoxiaolong.blog.model.persistent.BlogConfig;
import com.zuoxiaolong.blog.service.WebBlogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * 博客主页controller
 *
 * @author linjiedeng
 * @since 1.0.0
 */
@Controller
@RequestMapping("/WebBlog")
public class WebBlogController extends AbstractApiController {

    @Resource
    private WebBlogService webBlogService;

    /**
     * 更新个人简介,地址,博客名称等信息
     * @param blogConfig
     * @return
     */
    @RequestMapping(value = "/update/config", method = RequestMethod.POST)
    public Integer updateBlogConfig(@RequestBody BlogConfig blogConfig) {
        if(blogConfig == null
                || SensitiveWordCheckUtils.isContainSensitiveWord(blogConfig.getIntroduction())
                || SensitiveWordCheckUtils.isContainSensitiveWord(blogConfig.getBlogTitle())
                || SensitiveWordCheckUtils.isContainSensitiveWord(blogConfig.getBlogSubTitle())) {
            logger.info("blogConfig param: {} is invalid", blogConfig);
            throw new BusinessException(ExceptionType.PARAMETER_ILLEGAL);
        }

        return webBlogService.updateBlogConfig(blogConfig);
    }



    @RequestMapping(value = "/select/config" , method = RequestMethod.POST)
    public BlogConfig selectUserBlogConfig(Integer webUserId) {
        if(webUserId == null || webUserId < 0) {
            logger.info("webUserId: {} invalid", webUserId);
            throw new BusinessException(ExceptionType.PARAMETER_ILLEGAL);
        }

        return webBlogService.selectBlogConfigByWebUserId(webUserId);
    }


    /**
     * 获取个人博客主页信息
     * @param username
     * @param pageSize
     * @param pageNo
     * @return
     */
    @RequestMapping("/HomePage")
    public UserBlogInfo personalBlogHomePage(String username, String pageSize, String pageNo) {
        return webBlogService.selectUserBlogInfoByUsername(username, pageSize, pageNo);
    }
}
