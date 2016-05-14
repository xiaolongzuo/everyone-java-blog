package com.zuoxiaolong.blog.model;

public class UserOpeartionRecord {
    private Integer id;

    private Integer webUserId;

    private Byte operationType;

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

    public Byte getOperationType() {
        return operationType;
    }

    public void setOperationType(Byte operationType) {
        this.operationType = operationType;
    }
}