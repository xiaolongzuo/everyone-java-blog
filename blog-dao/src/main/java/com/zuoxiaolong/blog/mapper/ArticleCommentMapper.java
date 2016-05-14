package com.zuoxiaolong.blog.mapper;

import com.zuoxiaolong.blog.model.ArticleComment;

public interface ArticleCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleComment record);

    int insertSelective(ArticleComment record);

    ArticleComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleComment record);

    int updateByPrimaryKey(ArticleComment record);
}