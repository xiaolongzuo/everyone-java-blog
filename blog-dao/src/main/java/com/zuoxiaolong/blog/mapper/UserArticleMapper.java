package com.zuoxiaolong.blog.mapper;

import com.zuoxiaolong.blog.model.UserArticle;

public interface UserArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserArticle record);

    int insertSelective(UserArticle record);

    UserArticle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserArticle record);

    int updateByPrimaryKey(UserArticle record);
}