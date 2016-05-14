package com.zuoxiaolong.blog.model;

public class BlogConfig {
    private Integer id;

    private Integer webUserId;

    private String introduction;

    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWebUserId() {
        return webUserId;
    }

    public void setWebUserId(Integer webUserId) {
        this.webUserId = webUserId;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
}