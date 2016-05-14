package com.zuoxiaolong.blog.model;

import lombok.Data;

@Data
public class ArticleComment {

    private Integer id;

    private Integer webUserId;

    private Integer articleId;

    private String comment;

    private Integer replyCommentId;

}