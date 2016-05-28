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

package com.zuoxiaolong.blog.service;

import com.zuoxiaolong.blog.common.utils.SensitiveWordCheckUtils;
import com.zuoxiaolong.blog.common.utils.SensitiveWordMonitor;
import com.zuoxiaolong.blog.model.persistent.WebUser;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Xiaolong Zuo
 * @since 1.0.0
 */
public class WebUserServiceTest extends AbstractSpringContextTest {

    @Autowired
    private WebUserService webUserService;

    @Test
    public void insert() {
        Assert.assertNotNull(webUserService);
        WebUser user = new WebUser();
        user.setUsername("zuoxiaolong");
        user.setPassword("123456");
        webUserService.insert(user);
        Assert.assertNotNull(webUserService.selectByPrimaryKey(1));
    }

    @Test
    public void testMonitorFileChange() throws Exception {
        Thread.sleep(1000);
        while (true) {
            if(SensitiveWordCheckUtils.isContaintSensitiveWord("狗日的", 1)) {
                System.out.println(true);
                break;
            }
        }
    }
}
