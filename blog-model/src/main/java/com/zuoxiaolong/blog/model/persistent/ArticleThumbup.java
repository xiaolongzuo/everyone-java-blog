package com.zuoxiaolong.blog.model.persistent;

import lombok.Data;

@Data
public class ArticleThumbup extends BaseModel {

    private Integer articleId;

    private String ipAddress;

}