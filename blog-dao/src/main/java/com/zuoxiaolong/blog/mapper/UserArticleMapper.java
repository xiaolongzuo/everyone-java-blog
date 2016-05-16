package com.zuoxiaolong.blog.mapper;

import com.zuoxiaolong.blog.model.persistent.UserArticle;

import java.util.List;

public interface UserArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(UserArticle record);

    UserArticle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserArticle record);

    List<UserArticle> selectByWebUserId(Integer webUserId);

}