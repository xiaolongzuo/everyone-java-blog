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
package com.zuoxiaolong.blog.web.controller.com.zuoxiaolong.blog.web.service.impl;

import com.zuoxiaolong.blog.entity.UserBlogInfo;
import com.zuoxiaolong.blog.mapper.BlogConfigMapper;
import com.zuoxiaolong.blog.web.controller.com.zuoxiaolong.blog.web.service.WebBlogService;

import javax.annotation.Resource;

/**
 * 用户博客个人主页业务实现类
 *
 * @author linjiedeng
 * @date 16/5/14 下午7:50
 * @since 1.0.0
 */
public class WebBlogServiceImpl implements WebBlogService {

    @Resource
    private BlogConfigMapper blogConfigMapper;

    @Override
    public UserBlogInfo selectUserBlogInfoByUsername(String username) {
        return blogConfigMapper.selectUserBlogInfoByUsername(username);
    }
}
