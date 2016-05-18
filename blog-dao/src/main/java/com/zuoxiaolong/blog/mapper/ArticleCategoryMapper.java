package com.zuoxiaolong.blog.mapper;

import com.zuoxiaolong.blog.model.persistent.ArticleCategory;

public interface ArticleCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(ArticleCategory record);

    ArticleCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleCategory record);

}