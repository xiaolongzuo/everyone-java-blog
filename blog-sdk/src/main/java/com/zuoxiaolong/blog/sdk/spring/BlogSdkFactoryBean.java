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

package com.zuoxiaolong.blog.sdk.spring;

import com.zuoxiaolong.blog.common.utils.StringUtils;
import com.zuoxiaolong.blog.sdk.BlogSdk;
import com.zuoxiaolong.blog.sdk.impl.BlogSdkFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * 提供SDK对spring IOC的支持
 *
 * @author Xiaolong Zuo
 * @since 1.0.0
 */
public class BlogSdkFactoryBean implements FactoryBean<BlogSdk>, InitializingBean {

    private String serverUrl;

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (StringUtils.isEmpty(serverUrl)) {
            throw new IllegalArgumentException("serverUrl can't be empty.");
        }
    }

    @Override
    public BlogSdk getObject() throws Exception {
        return BlogSdkFactory.createBlogSdk(serverUrl);
    }

    @Override
    public Class<?> getObjectType() {
        return BlogSdk.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
