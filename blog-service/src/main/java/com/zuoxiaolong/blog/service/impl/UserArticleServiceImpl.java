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

import com.zuoxiaolong.blog.common.orm.DropDownPage;
import com.zuoxiaolong.blog.common.utils.CollectionUtils;
import com.zuoxiaolong.blog.common.utils.DateUtils;
import com.zuoxiaolong.blog.mapper.UserArticleMapper;
import com.zuoxiaolong.blog.mapper.WebUserMapper;
import com.zuoxiaolong.blog.model.dto.ArticleDTO;
import com.zuoxiaolong.blog.model.dto.HomeArticleDTO;
import com.zuoxiaolong.blog.model.persistent.UserArticle;
import com.zuoxiaolong.blog.model.persistent.WebUser;
import com.zuoxiaolong.blog.service.UserArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private static final String THUMBUP_CHARTS_KEY = "thumbupCharts";

    private static final String COMMENT_CHARTS_KEY = "commentCharts";

    private static final String READ_CHARTS_KEY = "readCharts";

    @Autowired
    private UserArticleMapper userArticleMapper;

    @Autowired
    private WebUserMapper webUserMapper;

    @Override
    public Map<String, UserArticle> getArticleCharts(Integer categoryId, Date beginPublishTime) {
        List<UserArticle> thumbupCharts = userArticleMapper.getThumbupCharts(categoryId, beginPublishTime);
        List<UserArticle> commentCharts = userArticleMapper.getCommentCharts(categoryId, beginPublishTime);
        List<UserArticle> readCharts = userArticleMapper.getReadCharts(categoryId, beginPublishTime);
        if (CollectionUtils.isEmpty(thumbupCharts)) {
            return Collections.EMPTY_MAP;
        }
        Map<String, UserArticle> result = new HashMap<>();
        result.put(THUMBUP_CHARTS_KEY, thumbupCharts.get(0));
        if (!result.get(THUMBUP_CHARTS_KEY).getId().equals(commentCharts.get(0).getId())) {
            result.put(COMMENT_CHARTS_KEY, commentCharts.get(0));
        } else if (commentCharts.size() > 1) {
            result.put(COMMENT_CHARTS_KEY, commentCharts.get(1));
        }
        if (!result.get(THUMBUP_CHARTS_KEY).getId().equals(readCharts.get(0).getId()) && !result.get(COMMENT_CHARTS_KEY).getId().equals(readCharts.get(0).getId())) {
            result.put(READ_CHARTS_KEY, readCharts.get(0));
        } else if (readCharts.size() > 1 && !result.get(THUMBUP_CHARTS_KEY).getId().equals(readCharts.get(1).getId()) && !result.get(COMMENT_CHARTS_KEY).getId().equals(readCharts.get(1).getId())) {
            result.put(READ_CHARTS_KEY, readCharts.get(1));
        } else if (readCharts.size() > 2) {
            result.put(READ_CHARTS_KEY, readCharts.get(2));
        }
        return result;
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
