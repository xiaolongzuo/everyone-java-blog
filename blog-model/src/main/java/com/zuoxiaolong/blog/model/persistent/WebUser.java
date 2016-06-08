package com.zuoxiaolong.blog.model.persistent;

import com.zuoxiaolong.blog.common.utils.DateUtils;
import com.zuoxiaolong.blog.common.utils.EncodeDecodeUtils;
import lombok.Data;

import java.util.Date;

@Data
public class WebUser extends BaseModel {

    private String username;

    private String password;

    private String passwordSalt;

    private String nickname;

    private Boolean enable;

    private String token;

    public void encodePassword() {
        this.password = EncodeDecodeUtils.encodeByMd5(password + passwordSalt);
    }

    public boolean checkPassword(String password) {
        return this.password.equals(EncodeDecodeUtils.encodeByMd5(password + passwordSalt));
    }

    public void generateToken() {
        this.token = EncodeDecodeUtils.encryptDes(DateUtils.format(new Date(), "yyyyMMddHHmmss") + username, password);
    }

}