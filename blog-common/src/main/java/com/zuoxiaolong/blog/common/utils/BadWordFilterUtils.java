/*
 * Copyright 2002-2016/5/16 the original author or authors.
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

import java.util.HashSet;
import java.util.Set;

/**
 * 脏词检索
 * <p>
 * 翻译于:http://www.cnblogs.com/xingd/archive/2008/02/01/1061800.html
 *
 * @author Boren You
 * @dateTime 2016/5/16 0:04
 * @since 1.0.0
 */
public class BadWordFilterUtils {

    private Set<String> hashSet = new HashSet<String>();
    private byte[] fastCheck = new byte[Character.MAX_VALUE];
    private byte[] fastLength = new byte[Character.MAX_VALUE];
    private boolean[] charCheck = new boolean[Character.MAX_VALUE];
    private boolean[] endCheck = new boolean[Character.MAX_VALUE];
    private int maxWordLength = 0;
    private int minWordLength = Integer.MAX_VALUE;

    /**
     * 批量添加脏词
     *
     * @param badWords 脏词
     */
    public void addBadWords(String[] badWords) {
        for (String badWord : badWords) {
            addBadWord(badWord);
        }
    }

    /**
     * 添加单个脏词
     *
     * @param badWord 脏词
     */
    public void addBadWord(String badWord) {

        int length = badWord.length();

        maxWordLength = Math.max(maxWordLength, length);
        minWordLength = Math.min(minWordLength, length);

        for (int i = 0; i < 7 && i < length; i++) {
            fastCheck[badWord.charAt(i)] |= (byte) (1 << i);
        }
        for (int i = 7; i < length; i++) {
            fastCheck[badWord.charAt(i)] |= 0x80;
        }

        if (length == 1) {
            charCheck[badWord.charAt(0)] = true;
        } else {
            fastLength[badWord.charAt(0)] |= (byte) (1 << (Math.min(7, length - 2)));
            endCheck[badWord.charAt(length - 1)] = true;
            hashSet.add(badWord);
        }
    }

    /**
     * 判断内容是否存在脏词，存在脏词就返回true，否则返回false
     *
     * @param text 需要过滤的文本内容
     * @return
     */
    public boolean hasBadWord(String text) {
        int index = 0;
        int textLength = text.length();
        while (index < textLength) {
            int count = 1;
            if (index > 0 || (fastCheck[text.charAt(index)] & 1) == 0) {
                while (index < textLength - 1 && (fastCheck[text.charAt(++index)] & 1) == 0) ;
            }
            char begin = text.charAt(index);
            if (minWordLength == 1 && charCheck[begin]) {
                return true;
            }
            for (int j = 1; j <= Math.min(maxWordLength, textLength - index - 1); j++) {
                char current = text.charAt(index + j);
                if ((fastCheck[current] & 1) == 0) {
                    ++count;
                }
                if ((fastCheck[current] & (1 << Math.min(j, 7))) == 0) {
                    break;
                }
                if (j + 1 >= minWordLength) {
                    if ((fastLength[begin] & (1 << Math.min(j - 1, 7))) > 0 && endCheck[current]) {
                        if (hashSet.contains(text.substring(index, index + j + 1))) {
                            return true;
                        }
                    }
                }
            }
            index += count;
        }
        return false;
    }

    /**
     * 替换内容出现的脏词
     *
     * @param text   需要过滤的文本内容
     * @param mosaic 脏词马赛克
     * @return 对脏词打上马赛克之后的文本内容
     */
    public String replaceWith(String text, char mosaic) {
        int index = 0;
        char[] chars = text.toCharArray();
        int textLength = text.length();
        while (index < textLength) {
            int count = 1;
            if (index > 0 || (fastCheck[text.charAt(index)] & 1) == 0) {
                while (index < textLength - 1 && (fastCheck[text.charAt(++index)] & 1) == 0) ;
            }
            char begin = text.charAt(index);
            if (minWordLength == 1 && charCheck[begin]) {
                chars[index] = mosaic;
                index++;
                continue;
            }
            for (int j = 1; j <= Math.min(maxWordLength, textLength - index - 1); j++) {
                char current = text.charAt(index + j);
                if ((fastCheck[current] & 1) == 0) {
                    ++count;
                }
                if ((fastCheck[current] & (1 << Math.min(j, 7))) == 0) {
                    break;
                }
                if (j + 1 >= minWordLength) {
                    if ((fastLength[begin] & (1 << Math.min(j - 1, 7))) > 0 && endCheck[current]) {
                        if (hashSet.contains(text.substring(index, index + j + 1))) {
                            for (int m = index; m < (index + j + 1); m++) {
                                chars[m] = mosaic;
                            }
                            break;
                        }
                    }
                }
            }
            index += count;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : chars) {
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

}
