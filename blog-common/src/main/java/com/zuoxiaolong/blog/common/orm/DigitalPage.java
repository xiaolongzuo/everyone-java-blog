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

package com.zuoxiaolong.blog.common.orm;

import com.zuoxiaolong.blog.common.utils.AssertUtils;
import lombok.Data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private String orderColumn = "id";

    private String orderType = "DESC";

    private Object data;

    public void setTotalCount(int totalCount) {
        if (totalCount < 0) {
            totalCount = 0;
        }
        this.totalCount = totalCount;
        this.totalPageNumber = (this.totalCount % pageSize == 0) ? (this.totalCount / pageSize) : (this.totalCount / pageSize + 1);
    }

    public void setTotalPageNumber(int totalPageNumber) {
        if (totalPageNumber < 0) {
            totalPageNumber = 0;
        }
        this.totalPageNumber = totalPageNumber;
    }

    public void setCurrentPageNumber(int currentPageNumber) {
        if (currentPageNumber < 1) {
            currentPageNumber = 1;
        }
        this.currentPageNumber = currentPageNumber;
    }

    public void setPageSize(int pageSize) {
        if (pageSize < 1) {
            pageSize = 1;
        }
        if (pageSize > 100) {
            pageSize = 100;
        }
        this.pageSize = pageSize;
    }

    public void setOrderColumn(String orderColumn) {
        AssertUtils.isEmpty(orderColumn);
        Pattern pattern = Pattern.compile("\\s+");
        Matcher matcher = pattern.matcher(orderColumn);
        if (matcher.find()) {
            throw new IllegalArgumentException("orderColumn can't contains space.");
        }
        this.orderColumn = orderColumn;
    }

    public void setOrderType(String orderType) {
        AssertUtils.isEmpty(orderType);
        if (!orderType.toUpperCase().equals("DESC") && !orderType.toUpperCase().equals("ASC")) {
            throw new IllegalArgumentException("orderType must be DESC or ASC.");
        }
        this.orderType = orderType;
    }

}
