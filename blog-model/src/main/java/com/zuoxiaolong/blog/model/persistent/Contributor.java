package com.zuoxiaolong.blog.model.persistent;

import lombok.Data;

@Data
public class Contributor extends BaseModel {

    private String contributorName;

    private String personalUrl;

}