package com.zuoxiaolong.blog.model;

import lombok.Data;

@Data
public class Resource extends BaseModel {

    private String resourcePath;

    private Byte resourceType;

}