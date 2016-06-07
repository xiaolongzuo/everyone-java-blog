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

import com.zuoxiaolong.blog.common.utils.ObjectUtils;
import com.zuoxiaolong.blog.model.persistent.WebUser;
import com.zuoxiaolong.blog.service.WebUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Xiaolong Zuo
 * @date 2016/5/14 18:03
 * @since 1.0.0
 */
@Controller
@RequestMapping("/WebUser")
public class WebUserController extends ApiBaseController {

    @Autowired
    private WebUserService webUserService;

    @RequestMapping("/Register")
    public String register(WebUser webUser) {
        webUserService.register(webUser);
        String token = webUserService.login(webUser.getUsername(), webUser.getPassword());
        setSessionAttribute(USERNAME_ATTRIBUTE_KEY, webUser.getUsername());
        return token;
    }

    @RequestMapping("/CheckUsername")
    public boolean checkUsername(String username) {
        return webUserService.checkUsername(username);
    }

    @RequestMapping("/IsLogin")
    public boolean isLogin() {
        return !ObjectUtils.isEmpty(getSessionAttribute(USERNAME_ATTRIBUTE_KEY));
    }

    @RequestMapping("/Login")
    public String login(String username, String password) {
        String token = webUserService.login(username, password);
        setSessionAttribute(USERNAME_ATTRIBUTE_KEY, username);
        return token;
    }

    @RequestMapping("/LoginWithToken")
    public String loginWithToken(String token) {
        WebUser webUser = webUserService.loginWithToken(token);
        setSessionAttribute(USERNAME_ATTRIBUTE_KEY, webUser.getUsername());
        return webUser.getToken();
    }

    @RequestMapping("/ModifyPassword")
    public void modifyPassword(String username, String oldPassword, String newPassword) {
        webUserService.modifyPassword(username, oldPassword, newPassword);
    }

    @RequestMapping("/Logout")
    public void logout() {
        setSessionAttribute(USERNAME_ATTRIBUTE_KEY, null);
    }

}
