package com.zuoxiaolong.blog.model;

public class Contributor {
    private Integer id;

    private String contributorName;

    private String personalUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContributorName() {
        return contributorName;
    }

    public void setContributorName(String contributorName) {
        this.contributorName = contributorName == null ? null : contributorName.trim();
    }

    public String getPersonalUrl() {
        return personalUrl;
    }

    public void setPersonalUrl(String personalUrl) {
        this.personalUrl = personalUrl == null ? null : personalUrl.trim();
    }
}