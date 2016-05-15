package com.zuoxiaolong.blog.model.persistent;

import lombok.Data;

@Data
public class BlogConfig extends BaseModel {

    private Integer webUserId;

    private String introduction;

    private String address;

}