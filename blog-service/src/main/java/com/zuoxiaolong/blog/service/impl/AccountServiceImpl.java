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
package com.zuoxiaolong.blog.service.impl;

import com.zuoxiaolong.blog.common.utils.Md5Utils;
import com.zuoxiaolong.blog.mapper.WebUserMapper;
import com.zuoxiaolong.blog.model.persistent.WebUser;
import com.zuoxiaolong.blog.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 郭松涛
 * @date 2016/5/14 20:36
 * @since 1.0.0
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private WebUserMapper webUserMapper;

    @Override
    public WebUser findUser(WebUser webUser) {
        return webUserMapper.selectByWebUser(webUser);
    }

    @Override
    public boolean insertUser(WebUser webUser) {
        int record = webUserMapper.insertSelective(webUser);
        return record>0?true:false;
    }

    @Override
    public boolean modifyPassword(WebUser webUser) {
        int record = webUserMapper.updateByPrimaryKeySelective(webUser);
        return record>0?true:false;
    }
}
