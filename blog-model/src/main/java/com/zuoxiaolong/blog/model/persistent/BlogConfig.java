package com.zuoxiaolong.blog.model.persistent;

import lombok.Data;

@Data
public class BlogConfig extends BaseModel {

    private Integer webUserId;

    private String introduction;

    private String address;

<<<<<<< HEAD
    private String blogTitle;

    private String blogSubTitle;
=======
    private String blogTitle; // 博客标题

    private String blogSubTitle; // 博客子标题
>>>>>>> 66949ccde601e2aa40300f0775ed3b1d5f7b1f8d

}