package com.zuoxiaolong.blog.model.persistent;

import com.zuoxiaolong.blog.common.auth.AuthHelper;
import lombok.Data;

@Data
public class WebUser extends BaseModel {

    private String username;

    private String password;

    private String passwordSalt;

    private String nickname;

    private Boolean enable;

    private String token;

    public void encodePassword() {
        this.password = AuthHelper.encodePassword(this.password, this.passwordSalt);
    }

    public boolean checkPassword(String password) {
        return this.password.equals(AuthHelper.encodePassword(password, this.passwordSalt));
    }

    public void generateToken() {
        this.token = AuthHelper.generateToken(this.username, this.password);
    }

}