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

package com.zuoxiaolong.blog.common.mybatis;

import lombok.Data;

/**
 * @author Xiaolong Zuo
 * @since 1.0.0
 */
@Data
public class DigitalPage {

    private int totalCount;

    private int totalPageNumber;

    private int currentPageNumber = 1;

    private int pageSize = 10;

    private Object data;

    public void setTotalPageNumberWithTotalCount() {
        this.totalPageNumber = (this.totalCount % pageSize == 0) ? (this.totalCount / pageSize) : (this.totalCount / pageSize + 1);
    }

}
