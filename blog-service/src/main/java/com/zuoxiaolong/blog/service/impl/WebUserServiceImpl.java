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

import com.zuoxiaolong.blog.mapper.WebUserMapper;
import com.zuoxiaolong.blog.model.persistent.WebUser;
import com.zuoxiaolong.blog.service.WebUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Xiaolong Zuo
 * @since 1.0.0
 */

@Service
public class WebUserServiceImpl implements WebUserService {

    @Autowired
    private WebUserMapper webUserMapper;

    @Override
    public Integer insert(WebUser webUser) {
        return webUserMapper.insertSelective(webUser);
    }

    @Override
    public WebUser selectByPrimaryKey(Integer id) {
        return webUserMapper.selectByPrimaryKey(id);
    }

}
