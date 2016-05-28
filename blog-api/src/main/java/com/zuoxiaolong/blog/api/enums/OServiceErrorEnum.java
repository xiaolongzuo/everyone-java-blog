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

package com.zuoxiaolong.blog.api.enums;

/**
 * api异常枚举
 * @author doubi
 * @since 1.0.0
 */
public enum OServiceErrorEnum {

    SYSTEM_ERROR(20001,"system error");

    private int code;

    private String message;

    private OServiceErrorEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
