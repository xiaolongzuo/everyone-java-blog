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

package com.zuoxiaolong.blog.dao;

import com.google.gson.Gson;
import com.zuoxiaolong.blog.common.orm.DigitalPage;
import com.zuoxiaolong.blog.common.orm.DropDownPage;
import com.zuoxiaolong.blog.common.utils.JsonUtils;
import com.zuoxiaolong.blog.mapper.WebUserMapper;
import com.zuoxiaolong.blog.model.persistent.WebUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Xiaolong Zuo
 * @since 1.0.0
 */
public class ViewPageQuery extends AbstractSpringContextTest {

    @Autowired
    private WebUserMapper webUserMapper;

    @Test
    public void viewPageQuery() throws ParseException {
        WebUser param = new WebUser();
        param.setUsername("user4");
        param.setPassword("pwd");
        DigitalPage digitalPage = new DigitalPage();
        webUserMapper.testDigitalPage(digitalPage, param);

        DigitalPage digitalPage1 = new DigitalPage();
        webUserMapper.testDigitalPage2(digitalPage1);

        DropDownPage dropDownPage = new DropDownPage();
        dropDownPage.setOffset(4);
        webUserMapper.testDropDownPage(dropDownPage, param);

        DropDownPage dropDownPage1 = new DropDownPage();
        dropDownPage1.setSize(6);
        webUserMapper.testDropDownPage2(dropDownPage1);

        DropDownPage dropDownPage2 = new DropDownPage();
        dropDownPage2.setOffset(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2016-06-11 15:02:17"));
        dropDownPage2.setSize(2);
        dropDownPage2.setOrderType("ASC");
        dropDownPage2.setOrderColumn("create_time");
        webUserMapper.testDropDownPage2(dropDownPage2);

        System.out.println(JsonUtils.toJson(digitalPage));
        System.out.println(JsonUtils.toJson(digitalPage1));
        System.out.println(JsonUtils.toJson(dropDownPage));
        System.out.println(JsonUtils.toJson(dropDownPage1));
        System.out.println(JsonUtils.toJson(dropDownPage2));
    }
}
