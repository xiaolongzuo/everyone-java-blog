package com.zuoxiaolong.blog.mapper;

import com.zuoxiaolong.blog.common.orm.DropDownPage;
import com.zuoxiaolong.blog.model.persistent.UserArticle;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserArticleMapper {
    /*    base method    */
    int deleteByPrimaryKey(Integer id);

    int insertSelective(UserArticle record);

    UserArticle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserArticle record);

    /*    top three method   */

    List<UserArticle> getTopRecommendArticles(Map<String, Object> mapInfo);

    List<UserArticle> getTopReadArticles(Map<String, Object> map);

    List<UserArticle> getTopCommendArticles(Map<String, Object> map);

    List<UserArticle> getArticlesByCategoryId(Integer categoryId);

    List<UserArticle> getArticleCommentByCategoryId(Integer categoryId);

    /*      user blog method    */
    /**
     * 获取文章分页列表，根据个人文章创建时间倒序排序
     * @param webUserId
     * @param page
     * @return
     */
    List<UserArticle> getPageByWebUserId(@Param("webUserId") int webUserId, @Param("page") DropDownPage page);

    /**
     * 根据用户id，获取推荐的文章列表，按推荐次数倒序排序
     * @param webUserId
     * @param size
     * @return
     */
    List<UserArticle> getHotArticlesByWebUserId(@Param("webUserId") int webUserId, @Param("size") int size);

    /*        common method    */
    /**
     * 获取用户文章
     * @param webUserId
     * @return
     */
    List<UserArticle> getArticlesByWebUserId(Integer webUserId);

    /**
     * 获取主页下拉分页文章
     * @param page
     * @param categoryId
     * @return
     */
    List<UserArticle> getMainPageArticles(@Param("page") DropDownPage page, @Param("categoryId") Integer categoryId);

    /*     add times method     */
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