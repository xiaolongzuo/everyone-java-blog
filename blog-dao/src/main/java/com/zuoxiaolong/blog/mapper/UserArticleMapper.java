package com.zuoxiaolong.blog.mapper;

import com.zuoxiaolong.blog.model.persistent.UserArticle;

import java.util.List;
import java.util.Map;

public interface UserArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(UserArticle record);

    UserArticle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserArticle record);

    List<UserArticle> getTopRecommendArticles(Map<String, Object> mapInfo);

    List<UserArticle> getTopReadArticles(Map<String, Object> map);

    List<UserArticle> getTopCommendArticles(Map<String, Object> map);

    List<UserArticle> getArticlesByCategoryId(Integer categoryId);
    
    List<UserArticle> getArticleCommentByCategoryId(Integer categoryId);

}