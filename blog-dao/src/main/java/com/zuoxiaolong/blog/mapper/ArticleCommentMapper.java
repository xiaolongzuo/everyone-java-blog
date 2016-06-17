package com.zuoxiaolong.blog.mapper;

import com.zuoxiaolong.blog.common.orm.DropDownPage;
import com.zuoxiaolong.blog.model.persistent.ArticleComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(ArticleComment record);

    ArticleComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleComment record);

    /**
     * 查看评论回复条数
     *
     * @param commentId
     * @return
     */
    int getReCommentCount(Integer commentId);

    /**
     * 查看文章评论
     *
     * @param page
     * @param articleId
     * @return
     */
    List<ArticleComment> getCommentByArticleID(@Param("page") DropDownPage page,@Param("articleId") Integer articleId);

    /**
     * 查看评论的回复
     *
     * @param page
     * @param commentId
     * @return
     */
    List<ArticleComment> getReCommentByCommentId(@Param("page") DropDownPage page,@Param("commentId") Integer commentId);

}