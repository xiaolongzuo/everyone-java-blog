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
package com.zuoxiaolong.blog.service.impl;

import com.zuoxiaolong.blog.common.bean.ExceptionType;
import com.zuoxiaolong.blog.common.exception.BusinessException;
import com.zuoxiaolong.blog.common.orm.DropDownPage;
import com.zuoxiaolong.blog.common.utils.ObjectUtils;
import com.zuoxiaolong.blog.common.utils.StringUtils;
import com.zuoxiaolong.blog.common.utils.ValidateUtils;
import com.zuoxiaolong.blog.mapper.BlogConfigMapper;
import com.zuoxiaolong.blog.mapper.UserArticleMapper;
import com.zuoxiaolong.blog.mapper.WebUserMapper;
import com.zuoxiaolong.blog.model.dto.UserBlogInfo;
import com.zuoxiaolong.blog.model.persistent.BlogConfig;
import com.zuoxiaolong.blog.model.persistent.UserArticle;
import com.zuoxiaolong.blog.model.persistent.WebUser;
import com.zuoxiaolong.blog.service.WebBlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户博客个人主页业务实现类
 *
 * @author linjiedeng
 * @author youboren
 * @date 16/5/14 下午7:50
 * @since 1.0.0
 */

@Service
public class WebBlogServiceImpl implements WebBlogService {

    protected Logger logger = LoggerFactory.getLogger(WebBlogServiceImpl.class);

    @Resource
    private BlogConfigMapper blogConfigMapper;

    @Resource
    private WebUserMapper webUserMapper;

    @Resource
    private UserArticleMapper userArticleMapper;

    protected static final int DEFUALT_PAGE_SIZE = 10;

    protected static final int USER_HOTEST_ARTICLE_PAGE_SIZE = 10;


    /**
     * 根据用户名获取用户博客个人主页的相关信息(新)
     *
     * @param username
     * @param pageSize
     * @param offset
     * @return
     */
    public UserBlogInfo selectUserBlogInfoByUsername(String username, String pageSize, String offset) {

        // 根据用户名查询用户是否存在
        WebUser webUser = webUserMapper.selectByUsername(username);
        if (webUser == null) {
            logger.error("用户：{} 不存在！", username);
            throw new BusinessException(ExceptionType.USER_NOT_FOUND);
        }

        // 根据用户id查询博客是否开通
        BlogConfig blogConfig = blogConfigMapper.selectByWebUserId(webUser.getId());
        if (blogConfig == null) {
            logger.error("{} 的博客未开通！", username);
            throw new BusinessException(ExceptionType.DATA_NOT_FOUND);
        }

        List<UserArticle> userArticles = getMyBlogByUserId(webUser.getId(), pageSize, offset);

        List<UserArticle> userHotestArticles = userArticleMapper.getHotArticlesByWebUserId(webUser.getId(), USER_HOTEST_ARTICLE_PAGE_SIZE);

        UserBlogInfo userBlogInfo = new UserBlogInfo();

        WebUser dtoUser = new WebUser();
        dtoUser.setId(webUser.getId());
        dtoUser.setUsername(webUser.getUsername());
        dtoUser.setNickname(webUser.getNickname());

        BlogConfig dtoBlogConfig = new BlogConfig();
        dtoBlogConfig.setId(blogConfig.getId());
        dtoBlogConfig.setIntroduction(blogConfig.getIntroduction());
        dtoBlogConfig.setAddress(blogConfig.getAddress());
        dtoBlogConfig.setBlogTitle(blogConfig.getBlogTitle());
        dtoBlogConfig.setBlogSubTitle(blogConfig.getBlogSubTitle());

        userBlogInfo.setWebUser(dtoUser);
        userBlogInfo.setBlogConfig(dtoBlogConfig);
        userBlogInfo.setUserArticleList(userArticles);
        userBlogInfo.setUserHotestArticleList(userHotestArticles);

        return userBlogInfo;
    }

    @Override
    public int updateBlogConfig(BlogConfig blogConfig) {
        ValidateUtils.required(blogConfig);
        ValidateUtils.required(blogConfig.getWebUserId());
        ValidateUtils.required(blogConfig.getBlogTitle());
        ValidateUtils.required(blogConfig.getBlogSubTitle());
        ValidateUtils.required(blogConfig.getIntroduction());
        ValidateUtils.sensitiveWord(blogConfig.getBlogTitle());
        ValidateUtils.sensitiveWord(blogConfig.getBlogSubTitle());
        ValidateUtils.sensitiveWord(blogConfig.getIntroduction());
        ValidateUtils.numberMin(blogConfig.getWebUserId(), 0);
        return blogConfigMapper.updateByWebUserId(blogConfig);
    }

    @Override
    public BlogConfig selectBlogConfigByWebUserId(Integer webUserId) {
        if(webUserId == null || webUserId < 0) {
            logger.info("webUserId: {} invalid", webUserId);
            throw new BusinessException(ExceptionType.PARAMETER_ILLEGAL);
        }

        return blogConfigMapper.selectByWebUserId(webUserId);
    }

    @Override
    public List<UserArticle> getMyBlogByUserId(Integer userId, String pageSize, String offset) {
        DropDownPage userArticlePage = new DropDownPage();
        if(!ObjectUtils.isEmpty(offset)){
            userArticlePage.setOffset(offset);
        }
        if (StringUtils.isNumeric(pageSize)) {
            userArticlePage.setSize(Integer.valueOf(pageSize));
        }
        userArticlePage.setOrderColumn("publish_time");
        List<UserArticle> userArticles = userArticleMapper.getPageByWebUserId(userId, userArticlePage);
        return userArticles;
    }

    @Override
    public List<UserArticle> getUserHotBlog(Integer userId, Integer num) {
        if (num == null || num <= 0) {
            num = USER_HOTEST_ARTICLE_PAGE_SIZE;
        }
        List<UserArticle> userHotestArticles = userArticleMapper.getHotArticlesByWebUserId(userId, num);
        return userHotestArticles;
    }
}
