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

package com.zuoxiaolong.blog.cache.controller;

import com.google.common.collect.Maps;
import com.zuoxiaolong.blog.cache.service.UserArticleServiceManager;
import com.zuoxiaolong.blog.common.web.BaseController;
import com.zuoxiaolong.blog.model.dto.cache.ArticleRankResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 文章排名Controller
 *
 * @author goozi
 * @create 2016-05-17 20:15
 * @since 1.0.0
 */
@RestController
@RequestMapping("/cache")
public class ArticleRankController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserArticleServiceManager userArticleServiceManager;

    /**
     * 文章排名查询接口
     *
     * @return
     */
    @RequestMapping(value = "/article/rank")
    public ResponseEntity articleRank() {
        logger.info("Starting get article rank...");
        List<ArticleRankResponseDto> articleRankResponseDtoList;
        try {
            articleRankResponseDtoList = userArticleServiceManager.getArticlesRank();
        } catch (Exception e) {
            logger.error("Getting article rank occur an error.", e);
            Map<String, String> map = Maps.newHashMap();
            map.put("error", e.getMessage());
            return new ResponseEntity(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(articleRankResponseDtoList, HttpStatus.OK);
    }
}
