package com.zuoxiaolong.blog.admin;

import com.zuoxiaolong.blog.service.WebUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2016/5/13.
 */

@Controller
public class WebUserController {

    @Autowired
    private WebUserService webUserService;


    @RequestMapping(value = "/login")
    public String login() {

        webUserService.selectByPrimaryKey()

        return "loginSuccess";
    }
}
