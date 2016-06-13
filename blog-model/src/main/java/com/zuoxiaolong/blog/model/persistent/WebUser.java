package com.zuoxiaolong.blog.model.persistent;

import com.zuoxiaolong.blog.common.authorization.AuthorizationHelper;
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
        this.password = AuthorizationHelper.encodePassword(this.password, this.passwordSalt);
    }

    public boolean checkPassword(String password) {
        return this.password.equals(AuthorizationHelper.encodePassword(password, this.passwordSalt));
    }

    public void generateToken() {
        this.token = AuthorizationHelper.generateToken(this.username, this.password);
    }

}