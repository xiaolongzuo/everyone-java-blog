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

import com.zuoxiaolong.blog.model.dto.cache.ArticleRankResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * 定时任务
 *
 * @author goozi
 * @create 2016-05-15 0:36
 * @since 1.0.0
 */
public class SchedulingJobManager {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserArticleServiceManager userArticleServiceManager;

    /**
     * 文章排名JOB，每五分钟执行一次
     */
    @Scheduled(cron = "0 */5 * * * *")
    public void articleTopRecommend() {
        logger.info("Starting cron job get article rank!");
        List<ArticleRankResponseDto> articles = userArticleServiceManager.getArticlesRank();
    }

}
