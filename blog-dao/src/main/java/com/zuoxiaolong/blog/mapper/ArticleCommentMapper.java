package com.zuoxiaolong.blog.mapper;

import com.zuoxiaolong.blog.model.persistent.ArticleComment;

public interface ArticleCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(ArticleComment record);

    ArticleComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleComment record);

}