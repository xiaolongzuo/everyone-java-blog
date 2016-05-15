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
package com.zuoxiaolong.blog.web.service.impl;

import com.zuoxiaolong.blog.mapper.BlogConfigMapper;
import com.zuoxiaolong.blog.mapper.UserArticleMapper;
import com.zuoxiaolong.blog.mapper.WebUserMapper;
import com.zuoxiaolong.blog.model.dto.UserBlogInfo;
import com.zuoxiaolong.blog.model.persistent.BlogConfig;
import com.zuoxiaolong.blog.model.persistent.UserArticle;
import com.zuoxiaolong.blog.model.persistent.WebUser;
import com.zuoxiaolong.blog.web.service.WebBlogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户博客个人主页业务实现类
 *
 * @author linjiedeng
 * @date 16/5/14 下午7:50
 * @since 1.0.0
 */

@Service
public class WebBlogServiceImpl implements WebBlogService {

    @Resource
    private BlogConfigMapper blogConfigMapper;

    @Resource
    private WebUserMapper webUserMapper;

    @Resource
    private UserArticleMapper userArticleMapper;

    /**
     * 根据用户名获取用户博客个人主页的相关信息
     * @param username
     * @return
     */
    @Override
    public UserBlogInfo selectUserBlogInfoByUsername(String username) {
        WebUser webUser = webUserMapper.selectByUsername(username);
        if(webUser == null) {
            return null;
        }

        BlogConfig blogConfig = blogConfigMapper.selectByWebUserId(webUser.getId());
        if(blogConfig == null) {
            return null;
        }

        List<UserArticle> userArticle = userArticleMapper.selectByWebUserId(webUser.getId());

        UserBlogInfo userBlogInfo = new UserBlogInfo();
        userBlogInfo.setIntroduction(blogConfig.getIntroduction());
        userBlogInfo.setNickname(webUser.getNickname());
        userBlogInfo.setUsername(webUser.getUsername());
        userBlogInfo.setWebUserId(webUser.getId());
        userBlogInfo.setUserArticleList(userArticle);

        return userBlogInfo;
    }
}
