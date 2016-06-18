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

import java.util.Map;

/**
 * 敏感词检查工具类
 *
 * @author linjiedeng
 * @date 16/5/28 下午7:02
 * @since 1.0.0
 */
public class SensitiveWordCheckUtils {

    public static int MIN_MATCH_TYPE = 1;   //最小匹配规则, 目前默认的匹配规则

    public static int MAX_MATCH_TYPE = 2;   //最大匹配规则

    /**
     * 判断是否含敏感词
     * @param sentence
     * @return
     */
    public static boolean isContainSensitiveWord(String sentence) {
        boolean flag = false;
        if(StringUtils.isEmpty(sentence)) {
            return false;
        }

        for (int i = 0; i < sentence.length(); i++) {
            if (hasSensitiveWord(sentence, i)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否包含敏感词
     * @param sentence
     * @param startCheckIndex
     * @return
     */
    private static boolean hasSensitiveWord(String sentence, int startCheckIndex) {
        int matchCount = 0;
        boolean findFlag = false;
        Map currentMap = SensitiveWordMonitor.sensitiveWordMap;
        for (int i = startCheckIndex; i < sentence.length(); i++) {
            char word = sentence.charAt(i);
            currentMap = (Map) currentMap.get(word);

            // 存在，则判断是否为最后一个
            if (currentMap != null) {
                matchCount++; // 找到相应key，匹配个数+1

                // 如果为最后一个匹配规则,结束循环，返回匹配标识数
                if ("1".equals(currentMap.get("isEnd"))) {
                    findFlag = true;
                    break;
                }
            } else {
                break;
            }
        }

        if (findFlag && matchCount > 1) {
            return true;
        } else {
            return false;
        }
    }
}
