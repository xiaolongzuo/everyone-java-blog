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
import com.zuoxiaolong.blog.common.utils.ValidateUtils;
import com.zuoxiaolong.blog.mapper.*;
import com.zuoxiaolong.blog.model.dto.ArticleCommentAndReplyDTO;
import com.zuoxiaolong.blog.model.dto.ArticleCommentDTO;
import com.zuoxiaolong.blog.model.dto.ArticleInfoDTO;
import com.zuoxiaolong.blog.model.persistent.*;
import com.zuoxiaolong.blog.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author DeserveL
 * @since 1.0.0
 */

@Service
public class ArticleServiceImpl implements ArticleService {

    protected Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);

    //默认评论路径分隔符
    public static final String COMMENT_CUT = ",";

    @Autowired
    private UserArticleMapper userArticleMapper;

    @Autowired
    private ArticleCommentMapper articleCommentMapper;

    @Autowired
    private ArticleCategoryMapper articleCategoryMapper;

    @Autowired
    private BlogConfigMapper blogConfigMapper;

    @Autowired
    private WebUserMapper webUserMapper;

    @Autowired
    private ArticleThumbupMapper articleThumbupMapper;

    /**
     * 查询文章详细信息
     *
     * @param articleId
     * @return
     */
    @Override
    public ArticleInfoDTO updateThumbupTimesAndGetArticleInfo(Integer articleId) {
        ValidateUtils.required(articleId);

        //根据文章id获取文章的基本信息
        UserArticle userArticle = userArticleMapper.selectByPrimaryKey(articleId);
        if(ObjectUtils.isEmpty(userArticle)){
            logger.error("文章：{} 不存在！", articleId);
            throw new BusinessException(ExceptionType.DATA_NOT_FOUND);
        }else {
            //获取文章的用户信息
            WebUser webUser = webUserMapper.selectByPrimaryKey(userArticle.getWebUserId());
            //获取文章用户的博客信息
            BlogConfig blogConfig = blogConfigMapper.selectByWebUserId(userArticle.getWebUserId());
            //获取文章的分类信息
            ArticleCategory articleCategory= articleCategoryMapper.selectByPrimaryKey(userArticle.getCategoryId());

            //用来设置返回页面文章详细信息
            ArticleInfoDTO articleInfoDTO = new ArticleInfoDTO();

            //文章用户信息
            WebUser dtoUser = new WebUser();
            dtoUser.setId(webUser.getId()); //用户id
            dtoUser.setNickname(webUser.getNickname()); //用户昵称
            dtoUser.setUsername(webUser.getUsername()); //用户名称
            articleInfoDTO.setWebUser(dtoUser);

            //文章用户的博客信息
            BlogConfig dtoBlogConfig = new BlogConfig();
            dtoBlogConfig.setId(blogConfig.getId());  //用户博客id
            dtoBlogConfig.setIntroduction(blogConfig.getIntroduction()); //用户个人介绍
            dtoBlogConfig.setAddress(blogConfig.getAddress()); //用户博客地址
            dtoBlogConfig.setBlogTitle(blogConfig.getBlogTitle()); //用户博客标题
            dtoBlogConfig.setBlogSubTitle(blogConfig.getBlogSubTitle()); //用户博客小标题
            articleInfoDTO.setBlogConfig(dtoBlogConfig);

            //文章的分类信息
            ArticleCategory dtoArticleCategory = new ArticleCategory();
            dtoArticleCategory.setId(articleCategory.getId()); //文章分类id
            dtoArticleCategory.setCategoryName(articleCategory.getCategoryName()); //文章分类名称
            articleInfoDTO.setArticleCategory(dtoArticleCategory);

            //文章基本信息
            articleInfoDTO.setUserArticle(userArticle);

            //增加一次阅读量
            userArticleMapper.updateReadTimes(articleId);

            return articleInfoDTO;
        }
    }

    /**
     * 查看评论和每条评论前三条回复列表
     *
     * @param articleId 文章id
     * @param offset 分页开始头评论id
     * @param size 每次加载数
     * @return
     */
    @Override
    public List<ArticleCommentAndReplyDTO> getCommentInfo(Integer articleId,Integer offset, Integer size) {
        ValidateUtils.required(articleId);

        //分页数据设置
        DropDownPage page = new DropDownPage();
        if(!ObjectUtils.isEmpty(offset)){
            page.setOffset(offset);
        }else{
            page.setOffset(0);
        }
        if(!ObjectUtils.isEmpty(size)){
            page.setSize(size);
        }
        page.setOrderType("ASC"); //升序排列

        //获取主评论
        List<ArticleComment> articleCommentList = articleCommentMapper.getCommentByArticleID(page,articleId);

        //用于返回数据
        List<ArticleCommentAndReplyDTO> articleCommentAndReplyList = new ArrayList<>();

        //获取每条评论对应的详细信息
        for(ArticleComment articleComment :articleCommentList){
            ArticleCommentAndReplyDTO articleCommentAndReplyDTO = new ArticleCommentAndReplyDTO();

            //获取该评论的用户信息
            WebUser webUser = webUserMapper.selectByPrimaryKey(articleComment.getWebUserId());
            //评论的用户信息
            WebUser dtoWebUser = new WebUser();
            dtoWebUser.setId(webUser.getId());
            dtoWebUser.setNickname(webUser.getNickname()); //用户昵称
            dtoWebUser.setUsername(webUser.getUsername()); //用户名称
            articleCommentAndReplyDTO.setWebUser(dtoWebUser);

            //评论信息
            articleCommentAndReplyDTO.setArticleComment(articleComment);

            //查看每条评论的回复数
            int recount = articleCommentMapper.getReCommentCount(articleComment.getId());
            articleCommentAndReplyDTO.setReCommentCount(recount); //回复条数
            //获取评论的前三条回复信息
            if(recount>0){
                articleCommentAndReplyDTO.setArticleCommentDTOList(getReCommentInfo(articleComment.getId(),0,3));
            }

            articleCommentAndReplyList.add(articleCommentAndReplyDTO);
        }
        return articleCommentAndReplyList;
    }

    /**
     * 加载评论的回复
     *
     * @param commentId
     * @param offset 分页开始头评论id
     * @param size 每次加载数
     * @return
     */
    @Override
    public List<ArticleCommentDTO> getReCommentInfo(Integer commentId,Integer offset, Integer size) {
        ValidateUtils.required(commentId);

        //分页数据设置
        DropDownPage page = new DropDownPage();
        if(!ObjectUtils.isEmpty(offset)){
            page.setOffset(offset);
        }else{
            page.setOffset(0);
        }
        if(!ObjectUtils.isEmpty(size)){
            page.setSize(size);
        }
        page.setOrderType("ASC"); //升序排列

        //获取评论的回复
        List<ArticleComment> articleCommentList = articleCommentMapper.getReCommentByCommentId(page,commentId);

        //用于返回数据
        List<ArticleCommentDTO> articleCommentDTOList = new ArrayList<>();

        //获取每条回复的详细信息
        for(ArticleComment articleComment :articleCommentList){
            ArticleCommentDTO articleCommentDTO = new ArticleCommentDTO();

            //获取该评论的用户信息
            WebUser webUser = webUserMapper.selectByPrimaryKey(articleComment.getWebUserId());
            //评论的用户信息
            WebUser dtoWebUser = new WebUser();
            dtoWebUser.setId(webUser.getId());
            dtoWebUser.setNickname(webUser.getNickname()); //用户昵称
            dtoWebUser.setUsername(webUser.getUsername()); //用户名称
            articleCommentDTO.setWebUser(dtoWebUser);

            //获取该回复评论的父用户信息
            ArticleComment parentArticleComment = articleCommentMapper.selectByPrimaryKey(articleComment.getReplyCommentId());
            WebUser parentUser = webUserMapper.selectByPrimaryKey(parentArticleComment.getWebUserId());
            //回复评论的父用户信息
            WebUser dtoParentUser = new WebUser();
            dtoParentUser.setId(parentUser.getId());
            dtoParentUser.setNickname(parentUser.getNickname()); //用户昵称
            dtoParentUser.setUsername(parentUser.getUsername()); //用户名称
            articleCommentDTO.setParentUser(dtoParentUser);

            //评论信息
            articleCommentDTO.setArticleComment(articleComment);
            articleCommentDTOList.add(articleCommentDTO);
        }
        return articleCommentDTOList;
    }


    /**
     * 添加评论/回复
     *
     * @param articleComment
     * @param webUserId
     * @return
     */@Override
    public Integer insertArticleComment(ArticleComment articleComment,Integer webUserId) {
        ValidateUtils.required(articleComment);
        ValidateUtils.required(webUserId);
        ValidateUtils.required(articleComment.getComment());

        //脏词判断
        //TODO 脏词

        ArticleComment articleCommentAdd = new ArticleComment();

        //父评论的id不为空的情况(添加回复的情况)
        if(!ObjectUtils.isEmpty(articleComment.getReplyCommentId())){
            //从数据库获取父评论的信息
            ArticleComment articleCommentParent = articleCommentMapper.selectByPrimaryKey(articleComment.getReplyCommentId());
            articleCommentAdd.setReplyCommentId(articleCommentParent.getId());
            articleCommentAdd.setArticleId(articleCommentParent.getArticleId());
            articleCommentAdd.setParentsCommentId(articleCommentParent.getParentsCommentId() + articleCommentParent.getId() + COMMENT_CUT);
        //添加评论的情况
        }else if(!ObjectUtils.isEmpty(articleComment.getArticleId())){
            //设置评论信息
            articleCommentAdd.setArticleId(articleComment.getArticleId());
        }else{
            throw new BusinessException(ExceptionType.PARAMETER_ILLEGAL);
        }

        articleCommentAdd.setComment(articleComment.getComment());
        articleCommentAdd.setWebUserId(webUserId);

        int record = articleCommentMapper.insertSelective(articleCommentAdd);

        //更新文章的评论条数(+1)
        if(record>0){
            userArticleMapper.updateCommentTimes(articleCommentAdd.getArticleId());
            return articleCommentAdd.getId();
        }else{
            throw new BusinessException(ExceptionType.DATA_NOT_FOUND);
        }
    }

    /**
     * 添加一次点赞
     *
     * @param articleId
     * @param ipAddress
     * @return
     */
    @Override
    public boolean updateThumbupTimes(Integer articleId,String ipAddress) {
        ValidateUtils.required(articleId);
        ValidateUtils.required(ipAddress);

        //查询点赞信息表
        ArticleThumbup articleThumbup = articleThumbupMapper.selectByArticleIdAndKey(articleId, ipAddress);
        if(ObjectUtils.isEmpty(articleThumbup)){
            articleThumbup = new ArticleThumbup();
            articleThumbup.setArticleId(articleId);
            articleThumbup.setIpAddress(ipAddress);
            articleThumbupMapper.insertSelective(articleThumbup);
            int record = userArticleMapper.updateThumbupTimes(articleId);
            return record>0?true:false;
        }else{
            throw new BusinessException(ExceptionType.ARTICLE_THUMBUP_ERROR);
        }
    }

    @Override
    public int insertUserArticle(UserArticle userArticle) {
        ValidateUtils.required(userArticle.getWebUserId());
        ValidateUtils.required(userArticle.getCategoryId());
        ValidateUtils.required(userArticle.getTitle());
        ValidateUtils.required(userArticle.getIsMainPage());
        userArticleMapper.insertSelective(userArticle);
        return userArticle.getId();
    }

    @Override
    public void updateUserArticle(UserArticle userArticle) {
        ValidateUtils.required(userArticle.getId());
        if (userArticle.getPublishTime() != null) {
            throw new BusinessException(ExceptionType.PARAMETER_ILLEGAL);
        }
        if (!ObjectUtils.isEmpty(userArticle.getStatus()) && userArticle.getStatus() == UserArticle.STATUS_PUBLISH) {
            userArticle.setPublishTime(new Date());
        }
        userArticleMapper.updateByPrimaryKeySelective(userArticle);
    }

    @Override
    public List<UserArticle> getArticlesByWebUserId(Integer userId) {
        return userArticleMapper.getArticlesByWebUserId(userId);
    }

}
