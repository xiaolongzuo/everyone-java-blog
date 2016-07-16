package com.zuoxiaolong.blog.model.persistent;

import lombok.Data;

@Data
public class BlogConfig extends BaseModel {

    private Integer webUserId;

    private String username;

    private String introduction;

    private String address;

    private String blogTitle; // 博客标题

    private String blogSubTitle; // 博客子标题

}