/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zuoxiaolong.blog.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;

/**
 * 简单封装Jackson，实现JSON String<->Java Object的Mapper.
 * @author ThinkGem
 * @author Xiaolong Zuo
 * @since 1.0.0
 */
public interface JsonMapper {

    Logger logger = LoggerFactory.getLogger(JsonMapper.class);

	ObjectMapper OBJECT_MAPPER = Jackson2ObjectMapperBuilder.json().build();

    /**
     * Object可以是POJO，也可以是Collection或数组。
     * 如果对象为Null, 返回"null".
     * 如果集合为空集合, 返回"[]".
     */
    static String toJson(Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (IOException e) {
            logger.warn("write to json string error:" + object, e);
            return null;
        }
    }

    /**
     * 反序列化POJO或简单Collection如List<String>.
     *
     * 如果JSON字符串为Null或"null"字符串, 返回Null.
     * 如果JSON字符串为"[]", 返回空集合.
     *
     * 如需反序列化复杂Collection如List<MyBean>, 请使用fromJson(String,JavaType)
     * @see #fromJson(String, JavaType)
     */
    static <T> T fromJson(String jsonString, Class<T> clazz) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(jsonString, clazz);
        } catch (IOException e) {
            logger.warn("parse json string error:" + jsonString, e);
            return null;
        }
    }

    /**
     * 反序列化复杂Collection如List<Bean>, 先使用函數createCollectionType构造类型,然后调用本函数.
     * @see #createCollectionType(Class, Class...)
     */
    @SuppressWarnings("unchecked")
    static <T> T fromJson(String jsonString, JavaType javaType) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        try {
            return (T) OBJECT_MAPPER.readValue(jsonString, javaType);
        } catch (IOException e) {
            logger.warn("parse json string error:" + jsonString, e);
            return null;
        }
    }

    /**
     * 構造泛型的Collection Type如:
     * ArrayList<MyBean>, 则调用constructCollectionType(ArrayList.class,MyBean.class)
     * HashMap<String,MyBean>, 则调用(HashMap.class,String.class, MyBean.class)
     */
    static JavaType createCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return OBJECT_MAPPER.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /**
     * 當JSON裡只含有Bean的部分屬性時，更新一個已存在Bean，只覆蓋該部分的屬性.
     */
    @SuppressWarnings("unchecked")
    static <T> T update(String jsonString, T object) {
        try {
            return (T) OBJECT_MAPPER.readerForUpdating(object).readValue(jsonString);
        } catch (JsonProcessingException e) {
            logger.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
        } catch (IOException e) {
            logger.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
        }
        return null;
    }

    /**
     * 輸出JSONP格式數據.
     */
    static String toJsonP(String functionName, Object object) {
        return toJson(new JSONPObject(functionName, object));
    }


}
