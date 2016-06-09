package com.zuoxiaolong.blog.mapper;

import com.zuoxiaolong.blog.model.persistent.UserArticle;
import org.apache.ibatis.annotations.Param;

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

    List<UserArticle> selectByWebUserId(Integer webUserId);

    /**
     * 获取文章分页列表，根据个人文章创建时间倒序排序
     * @param webUserId
     * @param start
     * @param end
     * @return
     */
    List<UserArticle> selectPageByWebUserId(@Param("webUserId") Integer webUserId, @Param("start") Integer start, @Param("end") Integer end);

}