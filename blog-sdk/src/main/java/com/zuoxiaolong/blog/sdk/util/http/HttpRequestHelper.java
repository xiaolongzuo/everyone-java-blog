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
package com.zuoxiaolong.blog.sdk.util.http;

import com.zuoxiaolong.blog.sdk.util.http.constants.Constants;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author Boren You
 * @dateTime 2016/5/25 22:09
 * @since 1.0.0
 */
public class HttpRequestHelper {
    /**
     * 讲Map集合中的参数拼揍成以&间隔开的字符串
     *
     * @param parameters
     * @return
     */
    public static String map2String(Map<String, String> parameters) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            if (parameters != null) {
                for (Map.Entry<String, String> entry : parameters.entrySet()) {

                    stringBuffer = stringBuffer.length() > 0 ? stringBuffer.append(Constants.PARAMETER_SEPARATOR).append(URLEncoder.encode(entry.getKey(), Constants.DEFAULT_CHARSET)).append(Constants.EQUAL_SIGN).append(URLEncoder.encode(entry.getValue(), Constants.DEFAULT_CHARSET))
                            : stringBuffer.append(URLEncoder.encode(entry.getKey(), Constants.DEFAULT_CHARSET)).append(Constants.EQUAL_SIGN).append(URLEncoder.encode(entry.getValue(), Constants.DEFAULT_CHARSET));
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }

}
