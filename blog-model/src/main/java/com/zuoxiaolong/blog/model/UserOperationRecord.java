package com.zuoxiaolong.blog.model;

import lombok.Data;

@Data
public class UserOperationRecord {

    private Integer id;

    private Integer webUserId;

    private Byte operationType;

}