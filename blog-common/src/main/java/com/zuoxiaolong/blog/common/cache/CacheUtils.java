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

package com.zuoxiaolong.blog.common.cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 缓存工具类
 *
 * @author goozi
 * @create 2016-06-24 15:32
 * @since 1.0.0
 */
public class CacheUtils {

    private static ConcurrentMap<String, CacheUtils> impl = new ConcurrentHashMap<>();

    public Object value;

    private long expr;

    private long createTime = System.currentTimeMillis();

    private CacheUtils(Object value, long expr) {
        this.value = value;
        this.expr = expr;
    }

    public static CacheUtils get(String key) {
        CacheUtils cache = impl.get(key);
        if (cache == null) {
            return null;
        }
        if ((System.currentTimeMillis() - cache.createTime) > cache.expr) {
            impl.remove(key);
            return null;
        }
        return cache;
    }

    public static void set(String key, Object value, long expr) {
        CacheUtils cache = new CacheUtils(value, expr);
        impl.put(key, cache);
    }
}
