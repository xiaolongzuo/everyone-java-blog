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

import org.apache.commons.lang3.StringEscapeUtils;

import java.io.UnsupportedEncodingException;

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

    static byte[] toBytes(String s) {
        try {
            return s.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    static String fromBytes(byte[] bytes) {
        try {
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    static boolean isNumeric(final CharSequence cs) {
        if (isEmpty(cs)) {
            return false;
        }
        final int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isDigit(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    static String escapeHtml(String html) {
        return StringEscapeUtils.escapeHtml4(html);
    }

    static String unescapeHtml(String html) {
        return StringEscapeUtils.unescapeHtml4(html);
    }

}
