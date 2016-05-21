package com.zuoxiaolong.blog.model.dto;

import lombok.Data;

/**
 * 文件上传基本信息
 *
 * @author Bing Pei
 * @since 1.0.0
 */
@Data
public class UploadInfo {

    /** 原文件名称 */
    private String originalFileName;

    /** 上传成功后资源服务器路径 */
    private String fileServerPath;

    /** 错误码用户编号为空 */
    public static final ErrorCode ERROR_CODE_UPLOAD_UID_NULL = new ErrorCode(1000, "用户编号不能为空");

    /** 错误码上传资源为空 */
    public static final ErrorCode ERROR_CODE_UPLOAD_RESOURCE_NULL = new ErrorCode(1001, "上传资源不能为空");

    /** 错误码上传资源类型错误 */
    public static final ErrorCode ERROR_CODE_UPLOAD_RESOURCE_TYPE_ERROR = new ErrorCode(1002, "上传资源类型不正确");

    /** 错误码上传资源失败 */
    public static final ErrorCode ERROR_CODE_UPLOAD_FAILURE = new ErrorCode(1003, "上传资源失败");

}
