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

import com.zuoxiaolong.blog.common.authorization.CheckLogin;
import com.zuoxiaolong.blog.common.utils.ObjectUtils;
import com.zuoxiaolong.blog.model.dto.WebUserDTO;
import com.zuoxiaolong.blog.model.persistent.WebUser;
import com.zuoxiaolong.blog.service.WebUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Xiaolong Zuo
 * @date 2016/5/14 18:03
 * @since 1.0.0
 */
@Controller
@RequestMapping("/WebUser")
public class WebUserController extends AbstractApiController {

    @Autowired
    private WebUserService webUserService;

    @RequestMapping(value = "/Register" , method = RequestMethod.POST)
    public String register(WebUser webUser) {
        WebUser originWebUser = webUserService.register(webUser);
        WebUser loginWebUser = webUserService.login(originWebUser.getUsername(), originWebUser.getPassword());
        return loginWebUser.getToken();
    }

    @RequestMapping(value = "/CheckUsername" , method = RequestMethod.POST)
    public boolean checkUsername(String username) {
        return webUserService.checkUsername(username);
    }

    @RequestMapping(value = "/IsLogin", method = RequestMethod.POST)
    public boolean isLogin() {
        return !ObjectUtils.isEmpty(getSessionAttribute(USERNAME_ATTRIBUTE_KEY));
    }

    @RequestMapping(value = "/Login", method = RequestMethod.POST)
    public String login(String username, String password) {
        WebUser loginWebUser = webUserService.login(username, password);
        return loginWebUser.getToken();
    }

    @RequestMapping(value = "/LoginWithToken", method = RequestMethod.POST)
    public String loginWithToken(String token) {
        WebUser loginWebUser = webUserService.loginWithToken(token);
        return loginWebUser.getToken();
    }

    @RequestMapping(value = "/ModifyPassword", method = RequestMethod.POST)
    public void modifyPassword(String oldPassword, String newPassword) {
        webUserService.modifyPassword(getUsername(), oldPassword, newPassword);
    }

    @RequestMapping(value = "/Logout", method = RequestMethod.POST)
    public void logout() {
        setSessionAttribute(USERNAME_ATTRIBUTE_KEY, null);
    }


    @RequestMapping(value = "/LoginWebUser", method = RequestMethod.GET)
    @CheckLogin
    public WebUserDTO getLoginWebUser() {
        WebUserDTO webUserDTO = webUserService.getLoginWebUser(getWebUserId());
        return webUserDTO;
    }
}
