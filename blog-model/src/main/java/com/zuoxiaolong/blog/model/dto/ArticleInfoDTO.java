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
package com.zuoxiaolong.blog.model.dto;

import com.zuoxiaolong.blog.model.persistent.*;
import lombok.Data;

/**
 * @author DeserveL
 * @date 2016/5/15 13:41
 * @since 1.0.0
 */
@Data
public class ArticleInfoDTO {

    private WebUser webUser; // 用户

    private BlogConfig blogConfig; // 博客配置

    private UserArticle userArticle; //文章基本信息

    private ArticleCategory articleCategory; //文章分类信息

}
