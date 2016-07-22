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

package com.zuoxiaolong.blog.sdk;

import com.google.gson.reflect.TypeToken;
import com.zuoxiaolong.blog.common.orm.DigitalPage;
import com.zuoxiaolong.blog.model.dto.*;
import com.zuoxiaolong.blog.model.persistent.ArticleCategory;
import com.zuoxiaolong.blog.model.persistent.BlogConfig;
import com.zuoxiaolong.blog.model.persistent.UserArticle;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * API种类
 * NOTE:每增加一种API的调用支持,就需要在这里添加一个枚举示例
 *
 * @author Xiaolong Zuo
 * @since 1.0.0
 */
public enum Api {

    example1("GET", new TypeToken<List<ArticleCategory>>(){}.getType()),
    example2("GET", ArticleCategory.class),
    example4("GET"),
    example7("GET"),

    HomePage_Articles("GET", HomeArticleDTO.class),
    HomePage_GetArticleCharts("GET",new TypeToken<Map<String,UserArticle>>(){}.getType()),
    WebUser_Register("POST", String.class),
    WebUser_CheckUsername("POST", boolean.class),
    WebUser_IsLogin("POST", boolean.class),
    WebUser_Login("POST", String.class),
    WebUser_LoginWithToken("POST", String.class),
    WebUser_ModifyPassword("POST"),
    WebUser_Logout("POST"),
    WebUser_LoginWebUser("GET", WebUserDTO.class),
    WebBlog_Select_Config("GET", new TypeToken<BlogConfig>(){}.getType()),
    WebBlog_Update_Config("POST", new TypeToken<Integer>(){}.getType()),
    WebBlog_HomePage("POST",UserBlogInfo.class),
    Article_GetArticleInfo("GET", ArticleInfoDTO.class),
    Article_GetCommentInfo("GET", new TypeToken<List<ArticleCommentAndReplyDTO>>(){}.getType()),
    Article_GetMoreReComment("GET", new TypeToken<List<ArticleCommentDTO>>(){}.getType()),
    Article_AddComment("POST", Integer.class),
    Article_AddThumbupTimes("POST", boolean.class),
    Article_Create("POST",Integer.class),
    Article_Update("POST"),
    Article_GetUserArticle("GET",new TypeToken<List<UserArticle>>(){}.getType()),
    MessageBox_Content("GET",MessageBoxDto.class),
    MessageBox_List("GET", DigitalPage.class),
    MessageBox_Send("POST",new TypeToken<Integer>(){}.getType()),
    MessageBox_Update("POST",new TypeToken<Integer>(){}.getType()),
    File_Upload("POST", String.class)
    ;

    private String url;

    private String method;

    private Type resultType;

    Api(String method) {
        this(method, null);
    }

    Api(String method, Type resultType) {
        String[] names = toString().split("_");
        StringBuffer url = new StringBuffer();
        for (int i = 0; i < names.length ; i++) {
            url.append("/").append(names[i]);
        }
        this.url = url.toString();
        this.method = method;
        this.resultType = resultType;
    }

    public String getUrl(String serverUrl) {
        return serverUrl + url;
    }

    public String getMethod() {
        return method;
    }

    public Type getResultType() {
        return resultType;
    }

}
