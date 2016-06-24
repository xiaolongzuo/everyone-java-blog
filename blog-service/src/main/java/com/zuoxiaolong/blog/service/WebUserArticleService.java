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

import com.zuoxiaolong.blog.model.persistent.UserArticle;

import java.util.List;

/**
 * 用户文章接口
 *
 * @author goozi
 * @create 2016-06-11 17:06
 * @since 1.0.0
 */
public interface WebUserArticleService {

    /**
     * 添加文章
     *
     * @param userArticle 文章信息
     */
    void saveArticle(UserArticle userArticle);

    /**
     * 添加或者更新文章
     *
     * @param userArticle 文章信息
     */
    void saveOrUpdateArticle(UserArticle userArticle);

    /**
     * 删除文章
     *
     * @param articleId 文章Id
     */
    void deleteArticle(String articleId);

    /**
     * 更新文章
     *
     * @param userArticle 文章信息
     */
    void updateArticle(UserArticle userArticle);

    /**
     * 获取当前用户的所有文章信息
     *
     * @param userId 用户ID
     * @return
     */
    List<UserArticle> getArticleByUserId(String userId);

    /**
     * 获取单篇文章信息
     *
     * @param articleId 文章主键
     * @return
     */
    UserArticle getArticleById(String articleId);
}
