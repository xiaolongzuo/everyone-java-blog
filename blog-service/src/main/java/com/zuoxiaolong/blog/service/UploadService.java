package com.zuoxiaolong.blog.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Bing Pei
 * @since 1.0.0
 */

public interface UploadService {

    Object doUpload(String uploadDirectory, String uid, MultipartFile[] file);

}
