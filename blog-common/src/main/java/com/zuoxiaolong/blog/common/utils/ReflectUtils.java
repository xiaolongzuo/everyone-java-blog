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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * @author Xiaolong Zuo
 * @since 1.0.0
 */
public interface ReflectUtils {

    /**
     * 填充object的所有空属性值.
     *
     * @param object 要填充的对象
     */
    static void fillNullFields(Object object) {
        if (object == null) {
            return;
        }
        Class<?> clazz = object.getClass();
        if (ClassUtils.isPrimitive(clazz)) {
            return;
        }
        if (ClassUtils.isList(clazz) || ClassUtils.isSet(clazz)) {
            return;
        }
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            if (field.isSynthetic()) {
                continue;
            }
            if (ClassUtils.isPrimitive(field.getType())) {
                continue;
            }
            Object fieldValue = getFieldValueWithGetterMethod(object, clazz, field);
            if (fieldValue != null) {
                if (ClassUtils.isList(field.getType()) || ClassUtils.isSet(field.getType())) {
                    Collection<?> collection = (Collection<?>) fieldValue;
                    collection.forEach(com.zuoxiaolong.blog.common.utils.ReflectUtils::fillNullFields);
                } else {
                    fillNullFields(fieldValue);
                }
                continue;
            }
            if (ClassUtils.isList(field.getType())) {
                setFieldValueWithSetterMethod(object, new ArrayList<>(), clazz, field);
                continue;
            }
            if (ClassUtils.isSet(field.getType())) {
                setFieldValueWithSetterMethod(object, new HashSet<>(), clazz, field);
                continue;
            }
            try {
                Object instance = field.getType().newInstance();
                fillNullFields(instance);
                setFieldValueWithSetterMethod(object, instance, clazz, field);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 将source对象中的属性值复制到target对象中
     * NOTE:如果source拥有属性A,但target没有属性A,则会被忽略
     *
     * @param source
     * @param target
     */
    static void copyFieldValues(Object source, Object target) {
        if (source == null || target == null) {
            return;
        }
        Class<?> sourceClass = source.getClass();
        Class<?> targetClass = target.getClass();
        Field[] fields = getAllFields(targetClass);
        for (int i = 0; i < fields.length; i++) {
            Field targetField = fields[i];
            if (targetField.isSynthetic()) {
                continue;
            }
            String fieldName = targetField.getName();
            try {
                Object sourceValue = getFieldValueWithGetterMethod(source, sourceClass, fieldName);
                setFieldValueWithSetterMethod(target, sourceValue, targetClass, targetField);
            } catch (Exception e) {
                //ignored
            }
        }
    }

    /**
     * 获取clazz的所有属性,包括继承获得的属性
     *
     * @param clazz
     * @return
     */
    static Field[] getAllFields(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        Class<?> superClass = clazz.getSuperclass();
        while (superClass != null && superClass != Object.class) {
            Field[] copy = fields;
            Field[] superClassFields = superClass.getDeclaredFields();
            Field[] newFields = new Field[copy.length + superClassFields.length];
            System.arraycopy(copy, 0, newFields, 0, copy.length);
            System.arraycopy(superClassFields, 0, newFields, copy.length, superClassFields.length);
            fields = newFields;
            superClass = superClass.getSuperclass();
        }
        return fields;
    }

    /**
     * 获取getter方法
     *
     * @param clazz
     * @param fieldName
     * @return
     */
    static Method getGetterMethod(Class<?> clazz, String fieldName) {
        String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        try {
            return clazz.getDeclaredMethod(methodName, new Class<?>[]{});
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取getter方法
     *
     * @param clazz
     * @param field
     * @return
     */
    static Method getGetterMethod(Class<?> clazz, Field field) {
        String fieldName = field.getName();
        String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        try {
            return getInheritMethod(clazz, methodName, new Class<?>[]{});
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取getter方法
     *
     * @param clazz
     * @param field
     * @return
     */
    static Method getSetterMethod(Class<?> clazz, Field field) {
        String fieldName = field.getName();
        String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        try {
            return getInheritMethod(clazz, methodName, new Class<?>[]{field.getType()});
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取继承的方法
     *
     * @param clazz
     * @param methodName
     * @param parameterTypes
     * @return
     * @throws NoSuchMethodException
     */
    static Method getInheritMethod(Class<?> clazz, String methodName, Class<?>... parameterTypes) throws NoSuchMethodException {
        try {
            return clazz.getDeclaredMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e) {
            Class<?> superClass = clazz.getSuperclass();
            if (superClass != null && superClass != Object.class) {
                return getInheritMethod(superClass, methodName, parameterTypes);
            } else {
                throw e;
            }
        }
    }

    /**
     * 使用getter方法获取属性值
     *
     * @param object
     * @param clazz
     * @param field
     * @return
     */
    static Object getFieldValueWithGetterMethod(Object object, Class<?> clazz, Field field) {
        Method method = getGetterMethod(clazz, field);
        try {
            return method.invoke(object);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 使用getter方法获取属性值
     *
     * @param object
     * @param clazz
     * @param fieldName
     * @return
     */
    static Object getFieldValueWithGetterMethod(Object object, Class<?> clazz, String fieldName) {
        Method method = getGetterMethod(clazz, fieldName);
        try {
            return method.invoke(object);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 使用setter方法设置属性值
     *
     * @param target
     * @param value
     * @param clazz
     * @param field
     * @return
     */
    static Object setFieldValueWithSetterMethod(Object target, Object value, Class<?> clazz, Field field) {
        Method method = getSetterMethod(clazz, field);
        try {
            return method.invoke(target, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 利用反射获取指定对象的指定属性
     *
     * @param target    目标对象
     * @param fieldName 目标属性
     * @return 目标属性的值
     */
    static Object getFieldValue(Object target, String fieldName) {
        Object result = null;
        Field field = getField(target, fieldName);
        if (field != null) {
            field.setAccessible(true);
            try {
                result = field.get(target);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    /**
     * 利用反射获取指定对象里面的指定属性
     *
     * @param target    目标对象
     * @param fieldName 目标属性
     * @return 目标字段
     */
    static Field getField(Object target, String fieldName) {
        Field field = null;
        for (Class<?> clazz = target.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                break;
            } catch (NoSuchFieldException e) {
                //ignored
            }
        }
        return field;
    }

    /**
     * 利用反射设置指定对象的指定属性为指定的值
     *
     * @param target     目标对象
     * @param fieldName  目标属性
     * @param fieldValue 目标值
     */
    static void setFieldValue(Object target, String fieldName, String fieldValue) {
        Field field = getField(target, fieldName);
        if (field != null) {
            field.setAccessible(true);
            try {
                field.set(target, fieldValue);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
