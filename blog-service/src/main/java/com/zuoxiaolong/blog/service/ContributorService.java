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

import com.zuoxiaolong.blog.model.persistent.Contributor;

/**
 * 贡献者信息操作事务接口
 *
 * @author cnJun
 * @since 1.0.0
 */
public interface ContributorService {

    /**
     * 根据主键删除贡献者信息
     *
     * @param id
     *      贡献者主键
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 根据贡献者实体信息新增新贡献者
     *
     * @param record
     *      贡献者实体
     * @return
     */
    int insertSelective(Contributor record);

    /**
     * 根据主键查询贡献者信息
     *
     * @param id
     *      贡献者主键
     * @return
     */
    Contributor selectByPrimaryKey(Integer id);

    /**
     * 根据贡献者实体更新贡献者信息
     *
     * @param record
     *      贡献者实体
     * @return
     */
    int updateByPrimaryKeySelective(Contributor record);

}
