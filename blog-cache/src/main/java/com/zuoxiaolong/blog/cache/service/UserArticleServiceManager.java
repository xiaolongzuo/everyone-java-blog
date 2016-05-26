/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zuoxiaolong.blog.cache.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zuoxiaolong.blog.cache.config.GuavaCacheConfig;
import com.zuoxiaolong.blog.cache.constants.CacheConstants;
import com.zuoxiaolong.blog.model.dto.cache.ArticleRankResponseDataResult;
import com.zuoxiaolong.blog.model.dto.cache.ArticleRankResponseDto;
import com.zuoxiaolong.blog.model.persistent.ArticleCategory;
import com.zuoxiaolong.blog.model.persistent.UserArticle;
import com.zuoxiaolong.blog.service.UserArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author goozi
 * @create 2016-05-15 16:14
 * @since 1.0.0
 */
@Service
public class UserArticleServiceManager {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //默认1
    public static final Integer TOP_NUM = 1;
    //默认推前的天数
    public static final Integer DEFAULT_DAYS_BEFORE = 1;
    //没有查询到排名结果是往前推的天数
    public static final Integer DEFAULT_DAYS_BEFORE_PLUS = 3;

    public static final String QUERY_PARAMETER_TIME = "time";

    public static final String QUERY_PARAMETER_CATEGORY_ID = "categoryId";

    @Autowired
    CacheManager cacheManager;

    @Autowired
    UserArticleService userArticleService;

    @Autowired
    ArticleCategoryServiceManager articleCategoryServiceManager;

    Cache blogCache;

    @PostConstruct
    public void init() {
        blogCache = cacheManager.getCache(GuavaCacheConfig.BLOG_CACHE);
    }


    /**
     * 按照类别和文章发布时间取出评论文章列表
     *
     * @param map
     * @return
     */
    public List<UserArticle> getTopCommendArticles(Map<String, Object> map) {
        List<UserArticle> userArticles = userArticleService.getTopCommendArticles(map);
        List<UserArticle> recommendUserArticle =
                userArticleService.getArticleCommentByCategoryId((Integer) map.get(QUERY_PARAMETER_CATEGORY_ID));
        if (CollectionUtils.isEmpty(userArticles) && !CollectionUtils.isEmpty(recommendUserArticle)) {
            //每次往前加DEFAULT_DAYS_BEFORE_PLUS天
            map.put(QUERY_PARAMETER_TIME, Timestamp.valueOf(((Timestamp) map.get(QUERY_PARAMETER_TIME))
                    .toLocalDateTime()
                    .minus(DEFAULT_DAYS_BEFORE_PLUS, ChronoUnit.DAYS)));
            userArticles = this.getTopReadArticlesByCategoryIdAndTime(map);
        }
        return userArticles;
    }

    /**
     * 按照类别和文章发布时间取出阅读文章列表
     *
     * @param map
     * @return
     */
    public List<UserArticle> getTopReadArticlesByCategoryIdAndTime(Map<String, Object> map) {
        List<UserArticle> userArticles = userArticleService.getTopReadArticles(map);
        List<UserArticle> articles =
                userArticleService.getArticlesByCategoryId((Integer) map.get(QUERY_PARAMETER_CATEGORY_ID));
        if (CollectionUtils.isEmpty(userArticles) && !CollectionUtils.isEmpty(articles)) {
            //每次往前加DEFAULT_DAYS_BEFORE_PLUS天
            map.put(QUERY_PARAMETER_TIME, Timestamp.valueOf(((Timestamp) map.get(QUERY_PARAMETER_TIME))
                    .toLocalDateTime()
                    .minus(DEFAULT_DAYS_BEFORE_PLUS, ChronoUnit.DAYS)));
            userArticles = this.getTopReadArticlesByCategoryIdAndTime(map);
        }
        return userArticles;
    }

    /**
     * 按照类别和文章发布时间取出推荐文章列表
     *
     * @param map
     * @return
     */
    public List<UserArticle> getTopRecommendArticlesByCategoryIdAndTime(Map<String, Object> map) {
        List<UserArticle> userArticles = userArticleService.getTopRecommendArticles(map);
        List<UserArticle> articles =
                userArticleService.getArticlesByCategoryId((Integer) map.get(QUERY_PARAMETER_CATEGORY_ID));
        if (CollectionUtils.isEmpty(userArticles) && !CollectionUtils.isEmpty(articles)) {
            //每次往前加DEFAULT_DAYS_BEFORE_PLUS天
            map.put(QUERY_PARAMETER_TIME, Timestamp.valueOf(((Timestamp) map.get(QUERY_PARAMETER_TIME))
                    .toLocalDateTime()
                    .minus(DEFAULT_DAYS_BEFORE_PLUS, ChronoUnit.DAYS)));
            userArticles = this.getTopRecommendArticlesByCategoryIdAndTime(map);
        }
        return userArticles;
    }

    /**
     * 取出排行榜的九篇文章的信息并缓存
     *
     * @return
     */
    @Cacheable(value = GuavaCacheConfig.BLOG_CACHE, key = "#root.methodName")
    public List<ArticleRankResponseDto> getArticlesRank() {
        List<ArticleCategory> articleCategories = articleCategoryServiceManager.getAllArticleCategory();
        if (CollectionUtils.isEmpty(articleCategories)) return Collections.emptyList();

        List<ArticleRankResponseDto> articleRankResponseDtos = Lists.newArrayList();

        //推荐排行
        ArticleRankResponseDto recommentArticleRankResponseDto = new ArticleRankResponseDto();
        recommentArticleRankResponseDto.setActionType(CacheConstants.ACTION_TYPE_RECOMMEND);

        Map<String, Object> recommendMap = Maps.newHashMap();
        List<UserArticle> recommendUserArticles;
        List<ArticleRankResponseDataResult> recommendArticleRankResponseDataResultList = Lists.newArrayList();
        ArticleRankResponseDataResult recommendDataResult;
        for (ArticleCategory articleCategory : articleCategories) {
            recommendMap.put(QUERY_PARAMETER_CATEGORY_ID, articleCategory.getId());
            recommendMap.put(QUERY_PARAMETER_TIME, Timestamp.valueOf(LocalDateTime.now().minus(DEFAULT_DAYS_BEFORE,
                    ChronoUnit.DAYS)));
            recommendUserArticles = this.getTopRecommendArticlesByCategoryIdAndTime(recommendMap);
            if (!CollectionUtils.isEmpty(recommendUserArticles)) {
                recommendDataResult = new ArticleRankResponseDataResult();
                recommendDataResult.setCategoryInfo(articleCategory);
                recommendDataResult.setArticleInfo(recommendUserArticles.get(TOP_NUM - 1));
                recommendArticleRankResponseDataResultList.add(recommendDataResult);
            }
        }
        recommentArticleRankResponseDto.setDataResult(recommendArticleRankResponseDataResultList);
        articleRankResponseDtos.add(recommentArticleRankResponseDto);

        //阅读排行
        ArticleRankResponseDto readArticleRankResponseDto = new ArticleRankResponseDto();
        readArticleRankResponseDto.setActionType(CacheConstants.ACTION_TYPE_READ);

        Map<String, Object> readMap = Maps.newHashMap();
        List<UserArticle> readUserArticles;
        ArticleRankResponseDataResult readDataResult;
        List<ArticleRankResponseDataResult> readArticleRankResponseDataResultList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(articleCategories)) return Collections.emptyList();
        for (ArticleCategory articleCategory : articleCategories) {
            readMap.put(QUERY_PARAMETER_CATEGORY_ID, articleCategory.getId());
            readMap.put(QUERY_PARAMETER_TIME, Timestamp.valueOf(LocalDateTime.now().minus(DEFAULT_DAYS_BEFORE,
                    ChronoUnit.DAYS)));
            readUserArticles = this.getTopReadArticlesByCategoryIdAndTime(readMap);
            if (!CollectionUtils.isEmpty(readUserArticles)) {
                readDataResult = new ArticleRankResponseDataResult();
                readDataResult.setCategoryInfo(articleCategory);
                readDataResult.setArticleInfo(readUserArticles.get(TOP_NUM - 1));
                readArticleRankResponseDataResultList.add(readDataResult);
            }
        }
        readArticleRankResponseDto.setDataResult(readArticleRankResponseDataResultList);
        articleRankResponseDtos.add(readArticleRankResponseDto);

        //评论排行
        ArticleRankResponseDto commendArticleRankResponseDto = new ArticleRankResponseDto();
        commendArticleRankResponseDto.setActionType(CacheConstants.ACTION_TYPE_COMMEND);

        Map<String, Object> commendMap = Maps.newHashMap();
        List<UserArticle> commendUserArticles;
        ArticleRankResponseDataResult commendDataResult;
        List<ArticleRankResponseDataResult> commendArticleRankResponseDataResultList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(articleCategories)) return Collections.emptyList();
        for (ArticleCategory articleCategory : articleCategories) {
            commendMap.put(QUERY_PARAMETER_CATEGORY_ID, articleCategory.getId());
            commendMap.put(QUERY_PARAMETER_TIME, Timestamp.valueOf(LocalDateTime.now().minus(DEFAULT_DAYS_BEFORE,
                    ChronoUnit.DAYS)));
            commendUserArticles = this.getTopCommendArticles(commendMap);
            if (!CollectionUtils.isEmpty(commendUserArticles)) {
                commendDataResult = new ArticleRankResponseDataResult();
                commendDataResult.setCategoryInfo(articleCategory);
                commendDataResult.setArticleInfo(commendUserArticles.get(TOP_NUM - 1));
                commendArticleRankResponseDataResultList.add(commendDataResult);
            }
        }
        commendArticleRankResponseDto.setDataResult(commendArticleRankResponseDataResultList);
        articleRankResponseDtos.add(commendArticleRankResponseDto);

        return articleRankResponseDtos;
    }
}
