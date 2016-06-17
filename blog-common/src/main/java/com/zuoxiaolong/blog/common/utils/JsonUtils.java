/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zuoxiaolong.blog.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * 使用GSON作为Json转换工具
 * @author ThinkGem
 * @author Xiaolong Zuo
 * @since 1.0.0
 */
public interface JsonUtils {

	Gson GSON = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    static String toJson(Object object) {
        return GSON.toJson(object);
    }

    static <T> T fromJson(String jsonString, Class<T> clazz) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        return GSON.fromJson(jsonString, clazz);
    }

    static <T> T fromJson(String jsonString, Type type) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        return (T) GSON.fromJson(jsonString, type);
    }

}
