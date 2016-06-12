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

import com.zuoxiaolong.blog.common.utils.CollectionUtils;
import com.zuoxiaolong.blog.sdk.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 这是一个示例控制器
 * <p>
 * BlogApiSdk使用spring IOC的方式注入即可
 * <p>
 * 调用接口时,选择适当的API种类传入,并且传入API所需要的参数即可调用
 *
 * @author Xiaolong Zuo
 * @since 1.0.0
 */
@Controller
public class ExampleController extends AbstractWebController {

    @RequestMapping("/example1")
    public String example1() {
        setModelAttribute("result", invokeApi(Api.example1));
        return "/index/index";
    }

    @RequestMapping("/example2")
    public String example2() {
        setModelAttribute("result", invokeApi(Api.example2, CollectionUtils.newMap("name", "zuoxiaolong")));
        return "/blog/blog";
    }

    @RequestMapping("/example4")
    public String example4() {
        setModelAttribute("result", invokeApi(Api.example4));
        return "/article/article";
    }

    @RequestMapping("/example7")
    public String example7() {
        setModelAttribute("result", invokeApi(Api.example7));
        return "/article/article";
    }

    @RequestMapping("/example8")
    public String example8() throws Exception {
        throw new Exception();
    }

}
