package com.zuoxiaolong.blog.service;

import com.zuoxiaolong.blog.model.persistent.ArticleCategory;

import java.util.List;

/**
 * Created by goozi on 2016/5/17.
 */
public interface ArticleCategoryService {

    /**
     * 查询所有的文章类别
     *
     * @return 所有的文章类别信息
     */
    List<ArticleCategory> getAllArticleCategory();

}
