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
package com.zuoxiaolong.blog.sdk.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Boren You
 * @date 2016/5/26 16:03
 * @since 1.0.0
 */
public class BlogSdkPropertiesUtil {

    Logger logger = LoggerFactory.getLogger(BlogSdkPropertiesUtil.class);

    private static final String PROPERTIES_NAME = "blog-sdk.properties";

    /**
     * blog-sdk.properties资源文件对象
     */
    private Properties properties;

    private BlogSdkPropertiesUtil() {
        InputStream inputStream = null;
        try {
            properties = new Properties();
            inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTIES_NAME);
            properties.load(inputStream);
        } catch (IOException e) {
            logger.error("Load properties fail : " + e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private static class BlogSdkPropertiesUtilHolder {
        private static final BlogSdkPropertiesUtil INSTANCE = new BlogSdkPropertiesUtil();
    }

    /**
     * 获取工具类对象
     *
     * @return
     */
    private static final BlogSdkPropertiesUtil getInstance() {
        return BlogSdkPropertiesUtilHolder.INSTANCE;
    }

    /**
     * 获取工具类加载的资源文件对象
     *
     * @return
     */
    public static Properties getProperties() {
        return getInstance().properties;
    }

    /**
     * 获取工具类加载的资源文件中的值，如果抛出空异常，那就是在初始化过程，properties对象加载失败
     *
     * @param key
     * @return
     */
    public static String getProperty(String key) {
        if (key != null) {
            return getInstance().properties.getProperty(key);
        }
        return null;
    }
}
