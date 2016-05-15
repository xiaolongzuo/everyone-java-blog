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

import java.util.*;

/**
 * 敏感词检索
 * <p>
 * 翻译于:http://www.cnblogs.com/xingd/archive/2008/02/01/1061800.html
 *
 * @author Boren You
 * @dateTime 2016/5/16 0:04
 * @since 1.0.0
 */
public class BadWordFilter {

    private HashSet<String> hash = new HashSet<String>();
    private byte[] fastCheck = new byte[65536];
    private byte[] fastLength = new byte[65536];
    private boolean[] charCheck = new boolean[65536];
    private boolean[] endCheck = new boolean[65536];
    private int maxWordLength = 0;
    private int minWordLength = Integer.MAX_VALUE;


    /**
     * 批量添加敏感词
     *
     * @param badwords
     */
    public void addBadWords(String[] badwords) {
        for (String word : badwords) {
            addBadWord(word);
        }
    }

    /**
     * 添加单个敏感词
     *
     * @param word
     */
    public void addBadWord(String word) {
        maxWordLength = Math.max(maxWordLength, word.length());
        minWordLength = Math.min(minWordLength, word.length());

        for (int i = 0; i < 7 && i < word.length(); i++) {
            fastCheck[word.charAt(i)] |= (byte) (1 << i);
        }
        for (int i = 7; i < word.length(); i++) {
            fastCheck[word.charAt(i)] |= 0x80;
        }

        if (word.length() == 1) {
            charCheck[word.charAt(0)] = true;
        } else {
            fastLength[word.charAt(0)] |= (byte) (1 << (Math.min(7, word.length() - 2)));
            endCheck[word.charAt(word.length() - 1)] = true;
            hash.add(word);
        }
    }

    /**
     * 判断内容是否存在脏词
     *
     * @param text
     * @return
     */
    public boolean hasBadWord(String text) {
        int index = 0;
        while (index < text.length()) {
            int count = 1;
            if (index > 0 || (fastCheck[text.charAt(index)] & 1) == 0) {
                while (index < text.length() - 1 && (fastCheck[text.charAt(++index)] & 1) == 0) ;
            }
            char begin = text.charAt(index);
            if (minWordLength == 1 && charCheck[begin]) {
                return true;
            }
            for (int j = 1; j <= Math.min(maxWordLength, text.length() - index - 1); j++) {
                char current = text.charAt(index + j);
                if ((fastCheck[current] & 1) == 0) {
                    ++count;
                }
                if ((fastCheck[current] & (1 << Math.min(j, 7))) == 0) {
                    break;
                }
                if (j + 1 >= minWordLength) {
                    if ((fastLength[begin] & (1 << Math.min(j - 1, 7))) > 0 && endCheck[current]) {
                        if (hash.contains(text.substring(index, index + j + 1))) {
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
     * @param text
     * @param mark
     * @return
     */
    public String replaceWith(String text, char mark) {
        int index = 0;
        char[] ca = text.toCharArray();
        while (index < text.length()) {
            int count = 1;
            if (index > 0 || (fastCheck[text.charAt(index)] & 1) == 0) {
                while (index < text.length() - 1 && (fastCheck[text.charAt(++index)] & 1) == 0) ;
            }
            char begin = text.charAt(index);
            if (minWordLength == 1 && charCheck[begin]) {
                ca[index] = mark;
                index++;
                continue;
            }
            for (int j = 1; j <= Math.min(maxWordLength, text.length() - index - 1); j++) {
                char current = text.charAt(index + j);
                if ((fastCheck[current] & 1) == 0) {
                    ++count;
                }
                if ((fastCheck[current] & (1 << Math.min(j, 7))) == 0) {
                    break;
                }
                if (j + 1 >= minWordLength) {
                    if ((fastLength[begin] & (1 << Math.min(j - 1, 7))) > 0 && endCheck[current]) {
                        if (hash.contains(text.substring(index, index + j + 1))) {
                            for (int m = index; m < (index + j + 1); m++) {
                                ca[m] = mark;
                            }
                            break;
                        }
                    }
                }
            }
            index += count;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : ca) {
            sb.append(c);
        }
        return sb.toString();
    }

}
