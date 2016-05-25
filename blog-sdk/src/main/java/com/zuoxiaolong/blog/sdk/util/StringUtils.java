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

/**
 * 处理string相关的工具方法
 *
 * @author Xiaolong Zuo
 * @since 1.0.0
 */
public interface StringUtils {

    /**
     * 判断一个字符串是否为空
     *
     * @param s 需要检查的字符串
     * @return 如果s为null或空串则返回true,否则返回false
     */
    static boolean isEmpty(String s) {
        return s == null || s.trim().length() == 0;
    }

}
