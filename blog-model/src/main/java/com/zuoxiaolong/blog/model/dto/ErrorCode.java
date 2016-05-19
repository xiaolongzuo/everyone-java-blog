package com.zuoxiaolong.blog.model.dto;

import lombok.Data;

/**
 * 错误码
 *
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
