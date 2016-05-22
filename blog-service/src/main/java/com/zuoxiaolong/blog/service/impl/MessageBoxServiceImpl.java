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
package com.zuoxiaolong.blog.service.impl;

import com.zuoxiaolong.blog.mapper.MessageBoxMapper;
import com.zuoxiaolong.blog.model.persistent.MessageBox;
import com.zuoxiaolong.blog.service.MessageBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author voyagezhang
 * @date 2016/5/16
 * @since 1.0.0
 */
@Service
public class MessageBoxServiceImpl implements MessageBoxService {

    @Autowired
    private MessageBoxMapper messageBoxMapper;
    @Override
    public MessageBox selectByPrimaryKey(Integer id) {
        MessageBox messageBox=messageBoxMapper.selectByPrimaryKey(id);
        return messageBox;
    }

    @Override
    public List<MessageBox> selectMessageBoxList(Integer offset,Integer limit) {
        List<MessageBox> messageBoxes=messageBoxMapper.selectMessageBoxList(offset,limit);
        return messageBoxes;
    }

    @Override
    public int insertSelective(MessageBox record) {
        int resultflag= messageBoxMapper.insertSelective(record);
        return resultflag;
    }

    @Override
    public int deleteByPrimaryKey(Integer deleteByPrimaryKey) {
        int resultflag=messageBoxMapper.deleteByPrimaryKey(deleteByPrimaryKey);
        return resultflag;
    }
}
