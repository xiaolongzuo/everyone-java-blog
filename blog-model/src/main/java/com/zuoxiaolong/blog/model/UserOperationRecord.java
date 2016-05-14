package com.zuoxiaolong.blog.model;

import lombok.Data;

@Data
public class UserOperationRecord extends BaseModel {

    private Integer webUserId;

    private Byte operationType;

}