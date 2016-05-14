package com.zuoxiaolong.blog.model;

import lombok.Data;

@Data
public class ArticleComment extends BaseModel {

    private Integer webUserId;

    private Integer articleId;

    private String comment;

    private Integer replyCommentId;

}