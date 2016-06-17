package com.zuoxiaolong.blog.model.persistent;

import lombok.Data;

@Data
public class ArticleComment extends BaseModel {

    private Integer webUserId;

    private Integer articleId;

    private String comment;

    private Integer replyCommentId;

    private String parentsCommentId; //父级评论路径

}