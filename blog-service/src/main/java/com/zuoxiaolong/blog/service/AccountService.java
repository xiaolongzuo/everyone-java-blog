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
package com.zuoxiaolong.blog.service;

import com.zuoxiaolong.blog.model.persistent.WebUser;

/**
 * @author 郭松涛
 * @date 2016/5/14 18:32
 * @since 1.0.0
 */
public interface AccountService {

    /**
     * 根据用户名和密码查找用户
     * @param webUser
     * @return
     */
    WebUser findUser(WebUser webUser);

    /**
     * 新注册用户保存
     * @param webUser
     */
    boolean insertUser(WebUser webUser) ;

    /**
     * 用户密码修改
     * @param webUser
     */
    boolean modifyPassword(WebUser webUser);
}
