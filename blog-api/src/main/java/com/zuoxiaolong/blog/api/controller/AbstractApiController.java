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

package com.zuoxiaolong.blog.api.controller;

import com.zuoxiaolong.blog.common.web.AbstractController;

/**
 * @author Xiaolong Zuo
 * @since 1.0.0
 */
public abstract class AbstractApiController extends AbstractController {

    protected static final String USERNAME_ATTRIBUTE_KEY = "username";

    protected static final String WEB_USER_ID_ATTRIBUTE_KEY = "webUserId";

    protected String getUsername() {
        return (String) getSessionAttribute(USERNAME_ATTRIBUTE_KEY);
    }

    protected Integer getWebUserId() {
        return (Integer) getSessionAttribute(WEB_USER_ID_ATTRIBUTE_KEY);
    }

}
