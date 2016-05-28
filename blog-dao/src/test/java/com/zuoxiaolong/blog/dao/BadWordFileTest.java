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
package com.zuoxiaolong.blog.dao;

import org.junit.Test;

import java.io.InputStream;
import java.util.Properties;

/**
 * TODO
 *
 * @author linjiedeng
 * @date 16/5/28 下午4:01
 * @since TODO
 */
public class BadWordFileTest {

    @Test
    public void test() throws Exception{
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("cache.properties");
//        BadWordConfigureFileChangeMonitor.getBadWordConfigureFileChangeMonitor().monitor("/Users/denglinjie", 500);
    }
}
