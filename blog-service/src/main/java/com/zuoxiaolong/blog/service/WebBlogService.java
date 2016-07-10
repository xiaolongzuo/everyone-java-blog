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
package com.zuoxiaolong.blog.service;

import com.zuoxiaolong.blog.common.orm.DropDownPage;
import com.zuoxiaolong.blog.model.dto.UserBlogInfo;
import com.zuoxiaolong.blog.model.persistent.BlogConfig;
import com.zuoxiaolong.blog.model.persistent.UserArticle;

import java.util.List;

/**
 * 用户博客个人主页业务接口
 *
 * @author linjiedeng
 * @author youboren
 * @date 16/5/14 下午7:49
 * @since 1.0.0
 */

public interface WebBlogService {

    UserBlogInfo selectUserBlogInfoByUsername(String username, String pageSize, String offset);

    int updateBlogConfig(BlogConfig blogConfig);

    BlogConfig selectBlogConfigByWebUserId(Integer webUserId);

    /**
     * 获取我的个人博客
     * @param userId
     * @return
     */
    List<UserArticle> getMyBlogByUserId(Integer userId, String pageSize, String offset);

    /**
     * 获取用户热门文章
     * @param userId
     * @param num
     * @return
     */
    List<UserArticle> getUserHotBlog(Integer userId, Integer num);
}
