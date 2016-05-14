package com.zuoxiaolong.blog.model.persistent;

import lombok.Data;

@Data
public class WebUser extends BaseModel {

    private String username;

    private String password;

    private String passwordSalt;

    private String nickname;

    private Boolean enable;

}