package com.zuoxiaolong.blog.mapper;

import com.zuoxiaolong.blog.model.persistent.ArticleCategory;

import java.util.List;

public interface ArticleCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(ArticleCategory record);

    ArticleCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleCategory record);

    List<ArticleCategory> getAllArticleCategory();
}