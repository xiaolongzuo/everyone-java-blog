package com.zuoxiaolong.blog.model.persistent;

import com.zuoxiaolong.blog.common.utils.ValidateUtils;
import lombok.Data;

@Data
public class BlogConfig extends BaseModel {

    private Integer webUserId;

    private String introduction;

    private String address;

    private String blogTitle; // 博客标题

    private String blogSubTitle; // 博客子标题

    public void init(WebUser webUser) {
        ValidateUtils.required(webUser);
        ValidateUtils.required(webUser.getId());
        ValidateUtils.required(webUser.getUsername());
        this.webUserId = webUser.getId();
        this.address = "/" + webUser.getUsername();
        this.blogTitle = webUser.getUsername() + "的个人博客";
        this.blogSubTitle = "一直走在编程的路上";
        this.introduction = "我是" + webUser.getUsername();
    }

}