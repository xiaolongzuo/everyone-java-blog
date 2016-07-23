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

package com.zuoxiaolong.blog.web.controller;

import com.zuoxiaolong.blog.common.bean.JsonResponse;
import com.zuoxiaolong.blog.common.utils.CollectionUtils;
import com.zuoxiaolong.blog.model.dto.WebUserDTO;
import com.zuoxiaolong.blog.sdk.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户登录,注册等功能
 *
 * @author Xiaolong Zuo
 * @since 1.0.0
 */
@Controller
@RequestMapping("/WebUser")
public class WebUserController extends AbstractWebController {

    @RequestMapping(value = "/CheckUsername", method = RequestMethod.GET)
    public void checkUsername(String username) throws IOException {
        JsonResponse jsonResponse = invokeApi(Api.WebUser_CheckUsername, CollectionUtils.newMap("username", username));
        if (jsonResponse.success() && (boolean)jsonResponse.getData()) {
            renderJson("success");
        } else {
            getResponse().sendError(HttpServletResponse.SC_CONFLICT);
        }
    }

    @RequestMapping(value = "/Login", method = RequestMethod.GET)
    public String login() {
        return "/user/login";
    }

    @RequestMapping(value = "/Login", method = RequestMethod.POST)
    public String login(String username, String password) {
        JsonResponse token = invokeApi(Api.WebUser_Login, CollectionUtils.newMap(new String[]{"username", "password"}, username, password));
        if (!token.success()) {
            setRequestAttribute("error", token.getMessage());
            return "/user/login";
        }
        loginSuccess((String) token.getData());
        JsonResponse loginWebUser = invokeApi(Api.WebUser_LoginWebUser);
        if (loginWebUser.success()) {
            afterLoginSuccess((WebUserDTO) loginWebUser.getData());
            return "redirect:/HomePage/Index";
        } else {
            setRequestAttribute("error", loginWebUser.getMessage());
            return "/user/login";
        }
    }

    @RequestMapping(value = "/Logout", method = RequestMethod.GET)
    public String logout() {
        logoutSuccess();
        return "redirect:/HomePage/Index";
    }

    @RequestMapping(value = "/Register", method = RequestMethod.GET)
    public String register() {
        return "/user/register";
    }

    @RequestMapping(value = "/Register", method = RequestMethod.POST)
    public String register(String username, String password) {
        JsonResponse token = invokeApi(Api.WebUser_Register, CollectionUtils.newMap(new String[]{"username", "password"}, username, password));
        if (!token.success()) {
            setRequestAttribute("error", token.getMessage());
            return "/user/register";
        }
        loginSuccess((String) token.getData());
        JsonResponse loginWebUser = invokeApi(Api.WebUser_LoginWebUser);
        if (loginWebUser.success()) {
            afterLoginSuccess((WebUserDTO) loginWebUser.getData());
            return "redirect:/HomePage/Index";
        } else {
            setRequestAttribute("error", loginWebUser.getMessage());
            return "/user/register";
        }
    }

    @RequestMapping(value = "/ModifyPassword", method = RequestMethod.POST)
    public String modifyPassword(String oldPassword, String newPassword) {
        JsonResponse response = invokeApi(Api.WebUser_ModifyPassword, CollectionUtils.newMap(new String[]{"oldPassword", "newPassword"}, oldPassword, newPassword));
        if(!response.success()){
            setRequestAttribute("error", "原密码不正确");
        }else{
            setRequestAttribute("error", "修改成功");
        }
        setRequestAttribute("active","change-password");
        return "/blog/blog-config";
    }

}
