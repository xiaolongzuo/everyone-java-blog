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

import com.zuoxiaolong.blog.common.bean.ExceptionType;
import com.zuoxiaolong.blog.common.exception.BusinessException;
import com.zuoxiaolong.blog.mapper.ContributorMapper;
import com.zuoxiaolong.blog.model.persistent.Contributor;
import com.zuoxiaolong.blog.service.ContributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 贡献者信息操作事务实现
 *
 * @author cnJun
 * @date 2016/5/15
 * @since 1.0.0
 */
@Service
public class ContributorServiceImpl implements ContributorService {

    @Autowired
    private ContributorMapper contributorMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return contributorMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(Contributor record) {
        return contributorMapper.insertSelective(record);
    }

    @Override
    public Contributor selectByPrimaryKey(Integer id) {
        return contributorMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Contributor record) {
        if (1 ==1) {
            throw new BusinessException(ExceptionType.USERNAME_PASSWORD_ERROR);
        }
        return contributorMapper.updateByPrimaryKeySelective(record);
    }
}
