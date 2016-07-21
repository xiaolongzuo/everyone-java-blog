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

import com.zuoxiaolong.blog.common.authorization.CheckLogin;
import com.zuoxiaolong.blog.model.dto.ArticleCommentAndReplyDTO;
import com.zuoxiaolong.blog.model.dto.ArticleCommentDTO;
import com.zuoxiaolong.blog.model.dto.ArticleInfoDTO;
import com.zuoxiaolong.blog.model.persistent.ArticleComment;
import com.zuoxiaolong.blog.model.persistent.UserArticle;
import com.zuoxiaolong.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author DeserveL
 * @date 2016/5/15 11:38
 * @since 1.0.0
 */
@Controller
@RequestMapping("/Article")
public class ArticleController  extends AbstractApiController {

    @Autowired
    private ArticleService articleService;

    /**
     * 查看文章详细
     *
     * @param articleId 文章id
     * @return
     */
    @RequestMapping(value = "/GetArticleInfo" , method = RequestMethod.GET)
    public ArticleInfoDTO getArticleInfo(int articleId) {
        return articleService.getArticleInfo(articleId);
    }

    /**
     * 查看评论和每条评论前三条回复列表
     *
     * @param articleId 文章id
     * @param offset 分页开始头评论id
     * @param size 每次加载数
     */
    @RequestMapping(value = "/GetCommentInfo" , method = RequestMethod.GET)
    public List<ArticleCommentAndReplyDTO> getCommentInfo(int articleId,int offset, int size) {
        return  articleService.getCommentInfo(articleId, offset, size);
    }

    /**
     *加载该条评论的更多回复
     *
     * @param commentId 评论id
     * @param offset 分页开始头评论id
     * @param size 每次加载数
     */
    @RequestMapping(value = "/GetMoreReComment" , method = RequestMethod.GET)
    public List<ArticleCommentDTO> getMoreReComment(int commentId,int offset, int size){
        return articleService.getReCommentInfo(commentId, offset, size);
    }

    /**
     * 添加评论（回复）
     *
     * @param articleComment
     */
    @CheckLogin
    @RequestMapping(value = "/AddComment" , method = RequestMethod.POST)
    public Integer addComment(ArticleComment articleComment){
        return  articleService.insertArticleComment(articleComment, getWebUserId());
    }

    /**
     * 添加一次点赞
     *
     * @param articleId
     */
    @RequestMapping(value = "/AddThumbupTimes" , method = RequestMethod.POST)
    public boolean addThumbupTimes(int articleId){
        return  articleService.updateThumbupTimes(articleId,getIpAddr());
    }

    /**
     * 增加一篇博文
     * @param userArticle
     */
    @CheckLogin
    @RequestMapping(value = "/Create" , method = RequestMethod.POST)
    public int addUserArticle(UserArticle userArticle){
        userArticle.setWebUserId(getWebUserId());
        return articleService.insertUserArticle(userArticle);
    }

    /**
     *修改博文信息（标题、内容、状态）
     */
    @CheckLogin
    @RequestMapping(value = "/Update" , method = RequestMethod.POST)
    public void updateUserArticle(UserArticle userArticle){
        articleService.updateUserArticle(userArticle);
    }

    /**
     *获取当前用户对应的文章
     */
    @CheckLogin
    @RequestMapping(value = "/GetUserArticle" , method = RequestMethod.GET)
    public List<UserArticle> getUserArticle(){
        return articleService.getArticlesByWebUserId(getWebUserId());
    }
}
