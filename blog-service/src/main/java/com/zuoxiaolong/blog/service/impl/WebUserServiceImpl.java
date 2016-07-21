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

import com.zuoxiaolong.blog.common.bean.ExceptionType;
import com.zuoxiaolong.blog.common.exception.BusinessException;
import com.zuoxiaolong.blog.common.utils.ObjectUtils;
import com.zuoxiaolong.blog.common.utils.StringUtils;
import com.zuoxiaolong.blog.common.utils.ValidateUtils;
import com.zuoxiaolong.blog.mapper.BlogConfigMapper;
import com.zuoxiaolong.blog.mapper.WebUserMapper;
import com.zuoxiaolong.blog.model.dto.WebUserDTO;
import com.zuoxiaolong.blog.model.persistent.BlogConfig;
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

    @Autowired
    private BlogConfigMapper blogConfigMapper;

    @Override
    public WebUser register(WebUser webUser) {
        ValidateUtils.required(webUser.getUsername());
        ValidateUtils.required(webUser.getPassword());
        ValidateUtils.text(webUser.getUsername());
        ValidateUtils.text(webUser.getPassword());
        ValidateUtils.check(checkUsername(webUser.getUsername()));

        WebUser originWebUser = new WebUser();
        originWebUser.setUsername(webUser.getUsername());
        originWebUser.setPassword(webUser.getPassword());
        if (StringUtils.isEmpty(webUser.getPasswordSalt())) {
            webUser.setPasswordSalt(webUser.getUsername());
        }
        if (StringUtils.isEmpty(webUser.getNickname())) {
            webUser.setNickname(webUser.getUsername());
        }
        webUser.encodePassword();
        webUser.setEnable(true);
        webUserMapper.insertSelective(webUser);

        BlogConfig blogConfig = new BlogConfig();
        blogConfig.init(webUser);
        blogConfigMapper.insertSelective(blogConfig);
        return originWebUser;
    }

    @Override
    public WebUser login(String username, String password) {
        ValidateUtils.required(username);
        ValidateUtils.required(password);
        ValidateUtils.text(username);
        ValidateUtils.text(password);

        WebUser webUser = webUserMapper.selectByUsername(username);
        if (ObjectUtils.isEmpty(webUser)) {
            throw new BusinessException(ExceptionType.USER_NOT_FOUND);
        }
        if (!webUser.checkPassword(password)) {
            throw new BusinessException(ExceptionType.USERNAME_PASSWORD_ERROR);
        }
        webUser.generateToken();
        webUserMapper.updateByPrimaryKeySelective(webUser);
        return webUser;
    }

    @Override
    public WebUser loginWithToken(String token) {
        ValidateUtils.required(token);

        WebUser param = new WebUser();
        param.setToken(token);
        WebUser webUser = webUserMapper.selectByWebUser(param);
        if (ObjectUtils.isEmpty(webUser)) {
            throw new BusinessException(ExceptionType.USERNAME_PASSWORD_ERROR);
        }
        webUser.generateToken();
        webUserMapper.updateByPrimaryKeySelective(webUser);
        return webUser;
    }

    @Override
    public void modifyPassword(String username, String oldPassword, String newPassword) {
        login(username, oldPassword);
        ValidateUtils.required(newPassword);
        ValidateUtils.text(newPassword);
        WebUser webUser = webUserMapper.selectByUsername(username);
        webUser.setPassword(newPassword);
        webUser.encodePassword();
        webUser.generateToken();
        webUserMapper.updateByPrimaryKeySelective(webUser);
    }

    @Override
    public boolean checkUsername(String username) {
        return ObjectUtils.isEmpty(webUserMapper.selectByUsername(username));
    }

    @Override
    public WebUser checkToken(String token) {
        WebUser param = new WebUser();
        param.setToken(token);
        return webUserMapper.selectByWebUser(param);
    }

    @Override
    public WebUserDTO getLoginWebUser(Integer userId) {
        WebUser webUser = webUserMapper.selectByPrimaryKey(userId);
        WebUserDTO webUserDTO = new WebUserDTO();
        if (webUser == null) {
            throw new BusinessException(ExceptionType.USER_NOT_FOUND);
        }
        webUserDTO.setId(webUser.getId());
        webUserDTO.setCreateTime(webUser.getCreateTime());
        webUserDTO.setNickname(webUser.getNickname());
        webUserDTO.setUpdateTime(webUser.getUpdateTime());
        webUserDTO.setUsername(webUser.getUsername());
        return webUserDTO;
    }

}
