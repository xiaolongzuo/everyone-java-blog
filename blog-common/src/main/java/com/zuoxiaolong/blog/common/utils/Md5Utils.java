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

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;

/**
 * @author 郭松涛
 * @date 2016/5/14 23:24
 * @since 1.0.0
 */
public interface Md5Utils {

    static String EncoderByMd5(String s) {
        //确定计算方法
        MessageDigest messageDigest;
        String result;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            BASE64Encoder base64Encoder = new BASE64Encoder();
            //加密后的字符串
            result = base64Encoder.encode(messageDigest.digest(s.getBytes("utf-8")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}
