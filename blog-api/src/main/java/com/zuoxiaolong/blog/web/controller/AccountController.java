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

import com.zuoxiaolong.blog.common.utils.Md5Utils;
import com.zuoxiaolong.blog.common.web.BaseController;
import com.zuoxiaolong.blog.model.persistent.WebUser;
import com.zuoxiaolong.blog.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 郭松涛
 * @date 2016/5/14 18:03
 * @since 1.0.0
 */
@Controller
@RequestMapping("/account")
public class AccountController extends BaseController{

    @Autowired
    AccountService accountService;

    @RequestMapping("/register}")
    public ModelAndView register(@RequestBody WebUser webUser) {
        ModelAndView modelAndView = new ModelAndView();

        webUser.setPasswordSalt(webUser.getUsername());
        String salt = webUser.getPasswordSalt();
        webUser.setPassword(Md5Utils.EncoderByMd5(webUser.getPassword() + salt));
        boolean result = accountService.insertUser(webUser);

        modelAndView.addObject("reult",result);
        return modelAndView;
    }

    /**
     * 判断账号或者昵称是否可用
     * @param webUser
     * @return
     */
    @RequestMapping("/isused}")
    public ModelAndView isused(@RequestBody WebUser webUser) {
        ModelAndView modelAndView = new ModelAndView();

        WebUser user=accountService.findUser(webUser);
        boolean result = (user==null)?false:true;

        modelAndView.addObject("reult",result);
        return modelAndView;
    }

    @RequestMapping("/login}")
    public ModelAndView login(@RequestBody WebUser webUser) {
        ModelAndView modelAndView = new ModelAndView();

        webUser.setPassword(Md5Utils.EncoderByMd5(webUser.getPassword() + webUser.getUsername()));
        WebUser user=accountService.findUser(webUser);
        //如果用户名密码存在，放到session中
        if(user != null){
            getHttpSession().setAttribute("USER_PK", user.getId());
            getHttpSession().setAttribute("USER_NAME", user.getUsername());
            getHttpSession().setAttribute("USER_NICK", user.getNickname());
        }

        modelAndView.addObject("user",user);
        return modelAndView;
    }

    @RequestMapping("/changepassword}")
    public ModelAndView changePassword(@RequestBody WebUser webUser,@RequestParam("newpassword") String newpassword) {
        ModelAndView modelAndView = new ModelAndView();

        boolean result = true;
        webUser.setPassword(Md5Utils.EncoderByMd5(webUser.getPassword() + webUser.getUsername()));
        WebUser user=accountService.findUser(webUser);
        if(user != null){
            webUser.setPassword(Md5Utils.EncoderByMd5(newpassword+webUser.getUsername()));
            result = accountService.modifyPassword(webUser);
        }

        modelAndView.addObject("reult",result);
        return modelAndView;
    }

    @RequestMapping("/logout}")
    public String logout(@RequestBody WebUser webUser) {
        getHttpSession().invalidate();
        return "index.jsp";
    }
}
