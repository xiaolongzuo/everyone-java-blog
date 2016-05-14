package com.zuoxiaolong.blog.model;

public class UserArticle {
    private Integer id;

    private Integer webUserId;

    private Integer categoryId;

    private String title;

    private String content;

    private Integer readTimes;

    private Integer thumbupTimes;

    private Byte isMainPage;

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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getReadTimes() {
        return readTimes;
    }

    public void setReadTimes(Integer readTimes) {
        this.readTimes = readTimes;
    }

    public Integer getThumbupTimes() {
        return thumbupTimes;
    }

    public void setThumbupTimes(Integer thumbupTimes) {
        this.thumbupTimes = thumbupTimes;
    }

    public Byte getIsMainPage() {
        return isMainPage;
    }

    public void setIsMainPage(Byte isMainPage) {
        this.isMainPage = isMainPage;
    }
}