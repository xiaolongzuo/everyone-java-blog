package com.zuoxiaolong.blog.model.persistent;

import lombok.Data;

/**
 * @author Bing Pei
 * @since 1.0.0
 */
@Data
public class ErrorCode {

    /** 错误码 */
    private int errorCode;

    /** 错误信息 */
    private String errorMessage;

    public ErrorCode(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
