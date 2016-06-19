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
import com.zuoxiaolong.blog.common.bean.ExceptionType;
import com.zuoxiaolong.blog.common.exception.BusinessException;
import com.zuoxiaolong.blog.model.persistent.ArticleCategory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 该控制器为API的示例控制器
 *
 * example1和example2是普通的返回数据示例
 *
 * example3是返回null的示例
 *
 * example4是void的示例
 *
 * example5是抛出业务异常的示例
 *
 * example6是程序抛出异常的示例
 *
 * @author Xiaolong Zuo
 * @since 1.0.0
 */
@Controller
public class ExampleController extends AbstractApiController {

    @RequestMapping("/example1")
    public List<ArticleCategory> example1() {
        ArticleCategory articleCategory = new ArticleCategory();
        articleCategory.setCategoryName("test");
        articleCategory.setCreateTime(new Date());
        articleCategory.setId(1);
        articleCategory.setUpdateTime(new Date());
        return Arrays.asList(articleCategory);
    }

    @RequestMapping("/example2")
    public ArticleCategory example2(String name) {
        ArticleCategory articleCategory = new ArticleCategory();
        articleCategory.setCategoryName(name);
        articleCategory.setCreateTime(new Date());
        articleCategory.setId(1);
        articleCategory.setUpdateTime(new Date());
        return articleCategory;
    }

    @RequestMapping("/example3")
    public ArticleCategory example3() {
        return null;
    }

    @RequestMapping("/example4")
    public void example4() {
    }

    @RequestMapping("/example5")
    public void example5() {
        throw new BusinessException(ExceptionType.DATA_NOT_FOUND);
    }

    @RequestMapping("/example6")
    public void example6() throws Exception {
        throw new Exception("我也不知道");
    }

    @RequestMapping("/example7")
    @CheckLogin
    public void example7() throws Exception {
        System.out.println(getUsername());
    }

}
