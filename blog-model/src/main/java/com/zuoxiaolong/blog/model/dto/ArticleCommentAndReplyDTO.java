package com.zuoxiaolong.blog.model.dto;

import com.zuoxiaolong.blog.model.persistent.ArticleComment;
import com.zuoxiaolong.blog.model.persistent.WebUser;
import lombok.Data;

import java.util.List;

@Data
public class ArticleCommentAndReplyDTO {

    private Integer reCommentCount; //回复条数

    private WebUser webUser; // 用户

    private ArticleComment articleComment; //评论内容

    private List<ArticleCommentDTO> articleCommentDTOList; //回复内容
}