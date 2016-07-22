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

import com.zuoxiaolong.blog.model.dto.ArticleCommentAndReplyDTO;
import com.zuoxiaolong.blog.model.dto.ArticleCommentDTO;
import com.zuoxiaolong.blog.model.dto.ArticleInfoDTO;
import com.zuoxiaolong.blog.model.persistent.ArticleComment;
import com.zuoxiaolong.blog.model.persistent.UserArticle;

import java.util.List;

/**
 * 文章和文章评论接口
 *
 * @author DeserveL
 * @since 1.0.0
 */
public interface ArticleService {

    /**
     * 查询文章详细信息
     *
     * @param articleId 文章id
     * @return
     */
    ArticleInfoDTO updateThumbupTimesAndGetArticleInfo(Integer articleId);

    /**
     * 查看评论和每条评论前三条回复列表
     *
     * @param articleId 文章id
     * @param offset 分页开始头评论id
     * @param size 每次加载数
     * @return
     */
    List<ArticleCommentAndReplyDTO> getCommentInfo(Integer articleId,Integer offset, Integer size);

    /**
     * 查询评论的回复
     * @param commentId 评论id
     * @param offset 分页开始头评论id
     * @param size 每次加载数
     *
     * @return
     */
    List<ArticleCommentDTO> getReCommentInfo(Integer commentId,Integer offset, Integer size);

    /**
     * 添加评论/回复
     *
     * @param articleComment
     * @param webUserId
     * @return
     */
    Integer insertArticleComment(ArticleComment articleComment,Integer webUserId);

    /**
     * 添加一次点赞
     *
     * @param articleId
     * @param ipAddress
     * @return
     */
    boolean updateThumbupTimes(Integer articleId,String ipAddress);

    /**
     * 添加一篇文章
     * @param userArticle
     * @return
     */
    int insertUserArticle(UserArticle userArticle);

    /**
     * 修改文章信息，包括文章标题、内容、状态(发布、草稿、删除)
     * @param userArticle
     * @return
     */
    void updateUserArticle(UserArticle userArticle);

    /**
     * 根据作者获取对应的文章
     * @param userId
     * @return
     */
    List<UserArticle> getArticlesByWebUserId(Integer userId);
}
