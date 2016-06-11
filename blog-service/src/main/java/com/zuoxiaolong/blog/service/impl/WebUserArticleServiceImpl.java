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

import com.zuoxiaolong.blog.common.utils.StringUtils;
import com.zuoxiaolong.blog.mapper.UserArticleMapper;
import com.zuoxiaolong.blog.model.persistent.UserArticle;
import com.zuoxiaolong.blog.service.WebUserArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 用户文章服务类
 *
 * @author goozi
 * @create 2016-06-11 17:08
 * @since 1.0.0
 */
@Service
public class WebUserArticleServiceImpl implements WebUserArticleService {

    @Autowired
    UserArticleMapper userArticleMapper;

    //草稿
    public static final int DRAFT = 0;
    //有效
    public static final int VALID = 1;
    //删除
    public static final int INVALID = 2;

    /**
     * 添加文章
     *
     * @param userArticle 文章信息
     */
    @Override
    public void saveArticle(UserArticle userArticle) {
        userArticle.setStatus(VALID);
        userArticleMapper.insertSelective(userArticle);
    }

    /**
     * 添加或者更新文章
     *
     * @param userArticle 文章信息
     */
    @Override
    public void saveOrUpdateArticle(UserArticle userArticle) {
        if (StringUtils.isEmpty(userArticle.getId().toString())) {
            saveArticle(userArticle);
        } else {
            updateArticle(userArticle);
        }

    }

    /**
     * 删除文章
     *
     * @param articleId 文章Id
     */
    @Override
    public void deleteArticle(int articleId) {
        UserArticle record = new UserArticle();
        record.setId(articleId);
        record.setUpdateTime(new Date());
        record.setStatus(INVALID);  //只做逻辑删除
        userArticleMapper.updateArticleStatusByArticleId(record);
    }

    /**
     * 更新文章
     *
     * @param userArticle 文章信息
     */
    @Override
    public void updateArticle(UserArticle userArticle) {
        if (userArticle.getUpdateTime() == null)
            userArticle.setUpdateTime(new Date());
        userArticleMapper.updateByPrimaryKeySelective(userArticle);
    }

    /**
     * 获取当前用户的所有文章信息
     *
     * @param userId 用户ID
     * @return
     */
    @Override
    public List<UserArticle> getArticleByUserId(String userId) {
        if (StringUtils.isEmpty(userId)) return Collections.emptyList();
        return userArticleMapper.selectByWebUserId(Integer.valueOf(userId));
    }

    /**
     * 获取单篇文章信息
     *
     * @param articleId 文章主键
     * @return
     */
    @Override
    public UserArticle getArticleById(String articleId) {
        if (StringUtils.isEmpty(articleId)) return new UserArticle();
        return userArticleMapper.selectByPrimaryKey(Integer.valueOf(articleId));
    }
}
