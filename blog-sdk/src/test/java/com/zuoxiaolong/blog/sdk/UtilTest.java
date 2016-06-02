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

import com.zuoxiaolong.blog.model.dto.cache.ArticleRankResponseDto;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Boren You
 * @date 2016/5/26 10:31
 * @since 1.0.0
 */
public class UtilTest {

    public static void test1(){
        ArticleRankResponseDto dto = new ArticleRankResponseDto();
        System.out.println("map集合" + dto.toString());
    }

    public static void main(String[] args){
        InputStream resourceAsStream = null;
        Properties p = new Properties();

        try {
            resourceAsStream   = BlogSdk.class.getClassLoader().getResourceAsStream("blog-sdk.properties");
            p.load(resourceAsStream);
            System.out.println(p.getProperty("articleRankUrl"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                resourceAsStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println(p.getProperty(null));
    }

}
