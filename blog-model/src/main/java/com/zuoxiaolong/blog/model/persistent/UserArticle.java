package com.zuoxiaolong.blog.model.persistent;

import lombok.Data;

import java.util.Date;

@Data
public class UserArticle extends BaseModel {

    public static final int STATUS_DRAFT = 0;

    public static final int STATUS_PUBLISH = 1;

    public static final int STATUS_DELETE = 2;

    private Integer webUserId;

    private Integer categoryId;

    private Integer isMainPage;

    private Integer status;

    private String title;

    private String content;

    private Integer readTimes;

    private Integer thumbupTimes;

    private Integer commentTimes;// 评论次数

    private Date publishTime;

}