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

    int updateArticleStatusByArticleId(UserArticle record);

    List<UserArticle> getArticlesByCategoryIdAndPage(Map<String, Object> mapInfo);
    /**
     * 获取文章分页列表，根据个人文章创建时间倒序排序
     * @param webUserId
     * @param offset
     * @param pageSize
     * @return
     */
    List<UserArticle> getPageByWebUserId(@Param("webUserId") int webUserId, @Param("offset") int offset, @Param("pageSize") int pageSize);

    /**
     * 根据用户id，获取推荐的文章列表，按推荐次数倒序排序
     * @param webUserId
     * @param pageSize
     * @return
     */
    List<UserArticle> getTopThumbupArticlesByWebUserId(@Param("webUserId") int webUserId, @Param("pageSize") int pageSize);

    /**
     * 增加一个阅读次数
     *
     * @param articleId
     * @return
     */
    int updateReadTimes(Integer articleId);

    /**
     * 增加一个评论次数
     *
     * @param articleId
     * @return
     */
    int updateCommentTimes(Integer articleId);

    /**
     * 增加一个点赞次数
     *
     * @param articleId
     * @return
     */
    int updateThumbupTimes(Integer articleId);
}