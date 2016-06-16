package com.zuoxiaolong.blog.model.dto;

import com.zuoxiaolong.blog.model.persistent.ArticleComment;
import com.zuoxiaolong.blog.model.persistent.WebUser;
import lombok.Data;

@Data
public class ArticleCommentDTO{

    private WebUser webUser; // 用户

    private WebUser parentUser; // 用户

    private ArticleComment articleComment; //评论内容

}