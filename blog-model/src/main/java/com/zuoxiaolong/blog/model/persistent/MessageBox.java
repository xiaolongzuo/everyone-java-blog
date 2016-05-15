package com.zuoxiaolong.blog.model.persistent;

import lombok.Data;

@Data
public class MessageBox extends BaseModel {

    private String content;

    private Integer sender;

    private Integer receiver;

    private Byte status;

}