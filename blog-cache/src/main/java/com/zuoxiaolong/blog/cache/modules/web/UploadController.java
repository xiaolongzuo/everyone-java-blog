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
package com.zuoxiaolong.blog.cache.modules.web;

import com.zuoxiaolong.blog.common.web.BaseController;
import com.zuoxiaolong.blog.model.persistent.WebUser;
import com.zuoxiaolong.blog.service.WebUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Boren You
 * @date 2016/5/12 19:45
 * @since 1.0.0
 */

@Controller
public class UploadController extends BaseController {

    @Autowired
    private WebUserService webUserService;

    @RequestMapping(value = "/cache/test")
    public void test(){

        WebUser webUser = webUserService.selectByPrimaryKey(1);
        logger.debug("userId:" + webUser.getId());
        logger.debug("username:" + webUser.getUsername());
        logger.debug("password:" + webUser.getPassword());
        renderString(getHttpServletResponse(),webUser);

    }
}
