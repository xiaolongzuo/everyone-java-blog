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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Xiaolong Zuo
 * @since 1.0.0
 */
public interface DateUtils {

    static long nd = 1000*24*60*60;//一天的毫秒数
    static long nh = 1000*60*60;//一小时的毫秒数
    static long nm = 1000*60;//一分钟的毫秒数

    static String format(Date date) {
        return format(date, "yyyy-MM-dd HH:mm:ss");
    }

    static String format(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    static Date parse(String source, String format) {
        try {
            return new SimpleDateFormat(format).parse(source);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    static String toFriendlyTime(Date date){
        long diff = Calendar.getInstance().getTime().getTime() - date.getTime();
        long day = diff/nd;//计算差多少天
        long hour = diff%nd/nh;//计算差多少小时
        long min = diff%nd%nh/nm;//计算差多少分钟
        if(day>0){
            return "大约发表于"+day+"天前";
        }
        if(hour>0){
            return "大约发表于"+hour+"小时前";
        }
        if(min>1){
            return "大约发表于"+min+"分钟前";
        }
        return "发表于1分钟以内";
    }
}
