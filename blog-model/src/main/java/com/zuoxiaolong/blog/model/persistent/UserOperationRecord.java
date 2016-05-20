package com.zuoxiaolong.blog.model.persistent;

import lombok.Data;

@Data
public class UserOperationRecord extends BaseModel {

    private Integer webUserId;

    private Integer operationType;

}