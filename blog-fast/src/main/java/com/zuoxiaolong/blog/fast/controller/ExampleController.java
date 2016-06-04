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

package com.zuoxiaolong.blog.fast.controller;

import com.zuoxiaolong.blog.common.spring.BaseController;
import com.zuoxiaolong.blog.common.utils.CollectionUtils;
import com.zuoxiaolong.blog.sdk.ApiType;
import com.zuoxiaolong.blog.sdk.BlogApiSdk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Xiaolong Zuo
 * @since 1.0.0
 */
@Controller
public class ExampleController extends BaseController{

    @Autowired
    private BlogApiSdk blogApiSdk;

    @RequestMapping("/example1")
    public String example1() {
        setModelAttribute("result", blogApiSdk.invokeApi(ApiType.example1));
        return "example";
    }

    @RequestMapping("/example2")
    public String example2() {
        setModelAttribute("result", blogApiSdk.invokeApi(ApiType.example2, CollectionUtils.newMap("name", "zuoxiaolong")));
        return "example";
    }

    @RequestMapping("/example4")
    public String example4() {
        setModelAttribute("result", blogApiSdk.invokeApi(ApiType.example4));
        return "example";
    }
}
