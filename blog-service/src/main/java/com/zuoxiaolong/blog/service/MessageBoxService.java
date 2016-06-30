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

import com.zuoxiaolong.blog.model.persistent.MessageBox;

import java.util.List;

/**
* @author voyagezhang
        * @date 2016/5/16
        * @since 1.0.0
        */
public interface MessageBoxService {
    MessageBox selectByPrimaryKey(Integer id);

    List<MessageBox> selectMessageBoxList(Integer offset,Integer limit);

    int insertSelective(MessageBox record);

    int deleteByPrimaryKey(Integer deleteByPrimaryKey);
}
