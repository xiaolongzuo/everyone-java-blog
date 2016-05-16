package com.zuoxiaolong.blog.model.persistent;

import lombok.Data;

@Data
public class UserArticle extends BaseModel {

    public static final int STATUS_DRAFT = 0;

    public static final int STATUS_PUBLISH = 1;

    public static final int STATUS_DELETE = 2;

    private Integer webUserId;

    private Integer categoryId;

    private String title;

    private String content;

    private Integer readTimes;

    private Integer thumbupTimes;

    private Byte isMainPage;

    private Integer status;

}