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

package com.zuoxiaolong.blog.common.web;

import java.util.Enumeration;
import java.util.Properties;

/**
 * 该类将持有通过Configurer加载的所有属性
 *
 * @author Xiaolong Zuo
 * @since 1.0.0
 *
 * @see HeldPropertyPlaceholderConfigurer
 */
public class ConfigurerPropertiesHolder {

    private static Properties properties;

    /**
     * 设置properties属性.通常情况下,该方法由Configurer调用.
     *
     * @param properties
     *
     * @see HeldPropertyPlaceholderConfigurer
     */
    public static synchronized void setProperties(Properties properties) {
        if (ConfigurerPropertiesHolder.properties == null && properties != null) {
            ConfigurerPropertiesHolder.properties = properties;
        }
    }

    /**
     * 获取所有的属性名称
     *
     * @return 所有的属性名称
     */
    public static Enumeration<String> getPropertyNames() {
        if (properties == null) {
            return null;
        }
        return (Enumeration<String>) properties.propertyNames();
    }

    /**
     * 根据name查询属性值
     *
     * @param name 属性名称
     * @return 如果有值则返回对应的值,否则返回null
     */
    public static String getProperty(String name) {
        if (properties == null) {
            return null;
        }
        return properties.getProperty(name);
    }

    /**
     * 根据name查询属性值
     *
     * @param name 属性名称
     * @param defaultValue 默认值
     * @return 如果有值则返回对应的值,否则返回默认值
     */
    public static String getProperty(String name, String defaultValue) {
        String value = getProperty(name);
        return (value == null) ? defaultValue : value;
    }

}
