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

package com.zuoxiaolong.blog.service.impl;

import com.zuoxiaolong.blog.common.cache.SingletonCache;
import com.zuoxiaolong.blog.common.orm.DropDownPage;
import com.zuoxiaolong.blog.common.utils.DateUtils;
import com.zuoxiaolong.blog.common.utils.ObjectUtils;
import com.zuoxiaolong.blog.mapper.UserArticleMapper;
import com.zuoxiaolong.blog.mapper.WebUserMapper;
import com.zuoxiaolong.blog.model.dto.ArticleDTO;
import com.zuoxiaolong.blog.model.dto.HomeArticleDTO;
import com.zuoxiaolong.blog.model.dto.cache.ArticleRankResponseDataResult;
import com.zuoxiaolong.blog.model.dto.cache.ArticleRankResponseDto;
import com.zuoxiaolong.blog.model.persistent.ArticleCategory;
import com.zuoxiaolong.blog.model.persistent.UserArticle;
import com.zuoxiaolong.blog.model.persistent.WebUser;
import com.zuoxiaolong.blog.service.ArticleCategoryService;
import com.zuoxiaolong.blog.service.UserArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * 用户文章服务类
 *
 * @author goozi
 * @create 2016-05-15 16:40
 * @since 1.0.0
 */
@Service
public class UserArticleServiceImpl implements UserArticleService {

    //默认1
    public static final Integer TOP_NUM = 1;
    //默认推前的天数
    public static final Integer DEFAULT_DAYS_BEFORE = 1;
    //没有查询到排名结果是往前推的天数
    public static final Integer DEFAULT_DAYS_BEFORE_PLUS = 3;

    public static final String QUERY_PARAMETER_TIME = "time";

    public static final String QUERY_PARAMETER_CATEGORY_ID = "categoryId";
    //推荐
    public static final String ACTION_TYPE_RECOMMEND = "mostRecommendArticle";
    //阅读
    public static final String ACTION_TYPE_READ = "mostReadArticle";
    //评论
    public static final String ACTION_TYPE_COMMENT = "mostCommentArticle";

    @Autowired
    private UserArticleMapper userArticleMapper;

    @Autowired
    private WebUserMapper webUserMapper;

    @Autowired
    private ArticleCategoryService articleCategoryServiceManager;

    /**
     * 按照类别和文章发布时间取出评论文章列表
     *
     * @param map
     * @return
     */
    private List<UserArticle> getTopCommendArticles(Map<String, Object> map) {
        List<UserArticle> userArticles = userArticleMapper.getTopCommendArticles(map);
        List<UserArticle> recommendUserArticle = userArticleMapper.getArticleCommentByCategoryId((Integer) map.get(QUERY_PARAMETER_CATEGORY_ID));
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
   private List<UserArticle> getTopReadArticlesByCategoryIdAndTime(Map<String, Object> map) {
        List<UserArticle> userArticles = userArticleMapper.getTopReadArticles(map);
        List<UserArticle> articles = userArticleMapper.getArticlesByCategoryId((Integer) map.get(QUERY_PARAMETER_CATEGORY_ID));
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
    private List<UserArticle> getTopRecommendArticlesByCategoryIdAndTime(Map<String, Object> map) {
        List<UserArticle> userArticles = userArticleMapper.getTopRecommendArticles(map);
        List<UserArticle> articles = userArticleMapper.getArticlesByCategoryId((Integer) map.get(QUERY_PARAMETER_CATEGORY_ID));
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
    public List<ArticleRankResponseDto> getArticlesRank() {
        List<ArticleCategory> articleCategories = articleCategoryServiceManager.getAllArticleCategory();
        if (CollectionUtils.isEmpty(articleCategories)) return Collections.emptyList();

        List<ArticleRankResponseDto> articleRankResponseDtos = new ArrayList<>();

        //推荐排行
        ArticleRankResponseDto recommentArticleRankResponseDto = new ArticleRankResponseDto();
        recommentArticleRankResponseDto.setActionType(ACTION_TYPE_RECOMMEND);

        Map<String, Object> recommendMap = new HashMap<>();
        List<UserArticle> recommendUserArticles;
        List<ArticleRankResponseDataResult> recommendArticleRankResponseDataResultList = new ArrayList<>();
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
        readArticleRankResponseDto.setActionType(ACTION_TYPE_READ);

        Map<String, Object> readMap = new HashMap<>();
        List<UserArticle> readUserArticles;
        ArticleRankResponseDataResult readDataResult;
        List<ArticleRankResponseDataResult> readArticleRankResponseDataResultList = new ArrayList<>();
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
        commendArticleRankResponseDto.setActionType(ACTION_TYPE_COMMENT);

        Map<String, Object> commendMap = new HashMap<>();
        List<UserArticle> commendUserArticles;
        ArticleRankResponseDataResult commendDataResult;
        List<ArticleRankResponseDataResult> commendArticleRankResponseDataResultList = new ArrayList<>();
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

    /**
     * @author iCodingStar
     * @date 2016/6/11 0:49
     * @version 1.0
     * @description:根据文章类别名称获取最多评论、最多推荐、最多阅读的三篇文章
     */
    @Override
    public Map<String, UserArticle> getTopThreeUserArticles(Integer categoryId) {
        if (categoryId == null) {
            categoryId = 1;
        }
        Map<String, UserArticle> articleMap = new HashMap<>();
        List<ArticleRankResponseDto> articleRankResponseDtos = (List<ArticleRankResponseDto>) SingletonCache.instance().get("ArticleRankResponseDto");
        if (ObjectUtils.isNull(articleRankResponseDtos)) {
            return articleMap;
        }
        for (ArticleRankResponseDto articleRankResponseDto : articleRankResponseDtos) {
            for (ArticleRankResponseDataResult articleRankResponseDataResult : articleRankResponseDto.getDataResult()) {
                Integer cacheCategoryId = articleRankResponseDataResult.getCategoryInfo().getId();
                if (categoryId.intValue() == cacheCategoryId) {
                    articleMap.put(articleRankResponseDto.getActionType(), articleRankResponseDataResult.getArticleInfo());
                }
            }
        }
        return articleMap;
    }


    @Override
    public HomeArticleDTO getMainPageArticles(String offset, Integer size, Integer categoryId) {
        //构建分页对象
        DropDownPage page = new DropDownPage();
        page.setOffset(DateUtils.parse(offset, new Date()));
        page.setSize(size);
        page.setOrderColumn("publish_time");
        List<UserArticle> list = userArticleMapper.getMainPageArticles(page, categoryId);

        List<ArticleDTO> resultList = new ArrayList<ArticleDTO>();
        for (UserArticle userArticle : list) {
            ArticleDTO articleDTO = new ArticleDTO();
            articleDTO.setUserArticle(userArticle);
            WebUser webUser = webUserMapper.selectByPrimaryKey(userArticle.getWebUserId());
            WebUser webUserDto = new WebUser();
            webUserDto.setNickname(webUser.getNickname()); //用户昵称
            articleDTO.setWebUser(webUserDto);
            articleDTO.setFriendlyTime(DateUtils.toFriendlyTime(userArticle.getPublishTime()));
            resultList.add(articleDTO);
        }

        HomeArticleDTO homeArticleDTO = new HomeArticleDTO();
        homeArticleDTO.setSize(page.getSize());
        homeArticleDTO.setOffset(DateUtils.format((Date) page.getOffset()));
        homeArticleDTO.setCategoryId(categoryId);
        homeArticleDTO.setArticles(resultList);
        return homeArticleDTO;
    }


}
