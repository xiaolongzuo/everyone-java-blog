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

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Xiaolong Zuo
 * @since 1.0.0
 */
public interface ObjectUtils {

    static boolean isEmpty(Object o) {
        return o == null || o.toString().length() == 0;
    }

    static boolean isNull(Object o) {
        return o == null;
    }

    /**
     * 将对象转换成Map
     *
     * @param o
     * @return
     */
    static Map<String, String> objectToMap(Object o) {
        AssertUtils.isNull(o);
        if (o instanceof Map) {
            Map<String, String> map = new HashMap<>();
            for (Object key : ((Map) o).keySet()) {
                map.put(key.toString(), ((Map) o).get(key).toString());
            }
            return map;
        } else {
            Class<?> clazz = o.getClass();
            Field[] fields = ReflectUtils.getAllFields(clazz);
            Map<String, String> map = new HashMap<>();
            if (CollectionUtils.isEmpty(fields)) {
                return map;
            }
            for (Field field : fields) {
                int modifiers = field.getModifiers();
                if (Modifier.isStatic(modifiers) || Modifier.isFinal(modifiers) || !Modifier.isPrivate(modifiers)) {
                    continue;
                }
                Object value = ReflectUtils.getFieldValueWithGetterMethod(o, clazz, field);
                if (isNull(value)) {
                    continue;
                }
                Class<?> fieldClass = value.getClass();
                if (!ClassUtils.isPrimitive(fieldClass)) {
                    continue;
                }
                if (fieldClass == Date.class) {
                    map.put(field.getName(), DateUtils.format((Date) value));
                } else {
                    map.put(field.getName(), value.toString());
                }
            }
            return map;
        }
    }

}
