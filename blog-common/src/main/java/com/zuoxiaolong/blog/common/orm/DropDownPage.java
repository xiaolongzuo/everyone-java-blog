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
import com.zuoxiaolong.blog.common.utils.ObjectUtils;
import lombok.Data;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Xiaolong Zuo
 * @since 1.0.0
 */
@Data
public class DropDownPage {

    private Object offset;

    private Integer size = 10;

    private String orderColumn = "id";

    private String orderType = "DESC";

    private Object data;

    public void setOffset(Object offset) {
        if (!ObjectUtils.isNull(offset)) {
            Class<?> clazz = offset.getClass();
            if (clazz != Integer.class && clazz != String.class && clazz != Date.class) {
                throw new RuntimeException("offset must be Integer or String or Date");
            }
        }
        this.offset = offset;
    }

    public void setSize(Integer size) {
        if (ObjectUtils.isEmpty(size)) {
            return;
        }
        if (size < 1) {
            size = 1;
        }
        if (size > 100) {
            size = 100;
        }
        this.size = size;
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
