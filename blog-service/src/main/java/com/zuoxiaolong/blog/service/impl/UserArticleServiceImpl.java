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

import com.zuoxiaolong.blog.mapper.UserArticleMapper;
import com.zuoxiaolong.blog.model.persistent.UserArticle;
import com.zuoxiaolong.blog.service.UserArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 用户文章服务类
 *
 * @author goozi
 * @create 2016-05-15 16:40
 * @since 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class UserArticleServiceImpl implements UserArticleService {

    UserArticleMapper userArticleMapper;

    @Autowired
    public UserArticleServiceImpl(UserArticleMapper userArticleMapper) {
        this.userArticleMapper = userArticleMapper;
    }

    /**
     * 按照类别和文章发布时间取出推荐文章列表
     *
     * @param map
     * @return
     */
    @Override
    public List<UserArticle> getTopRecommendArticles(Map<String, Object> map) {
        return userArticleMapper.getTopRecommendArticles(map);
    }

    /**
     * 按照类别和文章发布时间取出阅读文章列表
     *
     * @param map
     * @return
     */
    @Override
    public List<UserArticle> getTopReadArticles(Map<String, Object> map) {
        return userArticleMapper.getTopReadArticles(map);
    }

    /**
     * 按照类别和文章发布时间取出评论文章列表
     *
     * @param map
     * @return
     */
    @Override
    public List<UserArticle> getTopCommendArticles(Map<String, Object> map) {
        return userArticleMapper.getTopRecommendArticles(map);
    }

    /**
     * 按照文章类别取出所有文章信息
     *
     * @param categoryId 文章类别
     * @return
     */
    @Override
    public List<UserArticle> getArticlesByCategoryId(Integer categoryId) {
        return userArticleMapper.getArticlesByCategoryId(categoryId);
    }

    /**
     * 按照文章类别取出所有评论的文章信息
     *
     * @param categoryId 文章类别
     * @return
     */
    @Override
    public List<UserArticle> getArticleCommentByCategoryId(Integer categoryId) {
        return userArticleMapper.getArticleCommentByCategoryId(categoryId);
    }


}
