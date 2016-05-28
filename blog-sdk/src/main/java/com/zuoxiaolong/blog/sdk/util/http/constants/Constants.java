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
package com.zuoxiaolong.blog.sdk.util.http.constants;

/**
 * @author Boren You
 * @dateTime 2016/5/25 22:44
 * @since 1.0.0
 */
public interface Constants {
    String DEFAULT_CHARSET = "UTF-8";

    String PARAMETER_SEPARATOR = "&";

    String EQUAL_SIGN = "=";

    String QUESTION_MARK = "?";

    Integer DEFAULT_CONNECT_TIMEOUT = 5000;

    Integer DEFAULT_READ_TIMEOUT = 5000;

    String DEFAULT_CONTENT_TYPE = "application/x-www-form-urlencoded";

    String METHOD_POST = "POST";

    String METHOD_GET = "GET";

    Integer HTTP_CODE_200 = 200;
}
