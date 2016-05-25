/*
 * Copyright 2002-2016/5/25 the original author or authors.
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
package com.zuoxiaolong.blog.sdk.exception;

/**
 * @author Boren You
 * @dateTime 2016/5/25 23:29
 * @since 1.0.0
 */
public class BlogSdkHttpRequestException extends Exception{

    private static final long serialVersionUID = -9020304012274522297L;


    public BlogSdkHttpRequestException(String message) {
        super(message);
    }

    public BlogSdkHttpRequestException(String message, Exception exception) {
        super(message, exception);
    }

    public BlogSdkHttpRequestException(Exception exception) {
        super(exception);
    }
}
