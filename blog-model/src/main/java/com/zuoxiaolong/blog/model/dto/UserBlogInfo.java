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

import com.zuoxiaolong.blog.model.persistent.UserArticle;
import lombok.Data;

import java.util.List;

/**
 * 用户博客信息
 * @author linjiedeng
 * @date 16/5/14 下午6:38
 * @since 1.0.0
 */

@Data
public class UserBlogInfo {

    private int webUserId;          //用户id

    private String username;        //用户名

    private String nickname;        //用户昵称

    private String introduction;    //个人简介

    private List<UserArticle> userArticleList;  //用户文章列表

}
