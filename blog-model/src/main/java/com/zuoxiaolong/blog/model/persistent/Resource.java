package com.zuoxiaolong.blog.model.persistent;

import lombok.Data;

@Data
public class Resource extends BaseModel {

    private String resourcePath;

    private Integer resourceType;

}