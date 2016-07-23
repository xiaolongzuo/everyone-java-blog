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

package com.zuoxiaolong.blog.common.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Xiaolong Zuo
 * @since 1.0.0
 */
public interface CollectionUtils {

    /**
     * 判断集合是否为空
     *
     * @param collection
     * @return
     */
    static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.size() == 0;
    }

    /**
     * 判断Map是否为空
     *
     * @param map
     * @return
     */
    static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.size() == 0;
    }

    /**
     * 判断数组是否为空
     *
     * @param array
     * @return
     */
    static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 将map转换为values为数组的map
     *
     * @param map
     * @return
     */
    static Map<String, String[]> mapToArrayMap(Map<String, String> map) {
        if (!isEmpty(map)) {
            Map<String, String[]> result = new HashMap<>();
            for (String key : map.keySet()) {
                result.put(key, new String[]{map.get(key)});
            }
            return result;
        }
        return new HashMap<>();
    }

    /**
     * new一个Map并且装入keys和values
     *
     * @param keys
     * @param values
     * @param <T>
     * @return
     */
    static <T> Map<String, T> newMap(String[] keys, T... values) {
        Map<String, T> map = new HashMap<>();
        if (!isEmpty(keys) && !isEmpty(values) && values.length >= keys.length) {
            for (int i = 0;i < keys.length;i++) {
                map.put(keys[i], values[i]);
            }
        }
        return map;
    }

    /**
     * new一个map并且装入key和value
     *
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    static <T> Map<String, T> newMap(String key, T value) {
        Map<String, T> map = new HashMap<>();
        if (!StringUtils.isEmpty(key) && !ObjectUtils.isEmpty(value)) {
            map.put(key, value);
        }
        return map;
    }

    /**
     * 数组克隆
     * @param array
     * @param <T>
     * @return
     */
    static <T> T[] clone(final T[] array) {
        if (array == null) {
            return null;
        }
        return array.clone();
    }

}
