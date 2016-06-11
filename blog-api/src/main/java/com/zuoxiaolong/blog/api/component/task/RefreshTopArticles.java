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

package com.zuoxiaolong.blog.api.component.task;

import com.zuoxiaolong.blog.common.cache.SingletonCache;
import com.zuoxiaolong.blog.model.dto.cache.ArticleRankResponseDto;
import com.zuoxiaolong.blog.service.UserArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 定时任务
 *
 * @author goozi
 * @create 2016-05-15 0:36
 * @since 1.0.0
 */
@Component
@EnableScheduling
public class RefreshTopArticles {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserArticleService userArticleService;

    /**
     * 文章排名JOB，每30秒执行一次
     */
    @Scheduled(cron = "0/30 * * * * *")
    public void articleTopRecommend() {
        logger.info("Starting cron job get article rank!");
        List<ArticleRankResponseDto> articles = userArticleService.getArticlesRank();
        SingletonCache.instance().put("ArticleRankResponseDto", articles);
    }

}
