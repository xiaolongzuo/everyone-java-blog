package com.zuoxiaolong.blog.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Bing Pei
 * @since 1.0.0
 */

public interface UploadService {

    Object doUpload(HttpServletRequest request, String uid, MultipartFile[] file);

}
