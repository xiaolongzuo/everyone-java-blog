package com.zuoxiaolong.blog.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Bing Pei
 * @since 1.0.0
 */

public interface FileService {

    String upload(String uploadDirectory, String uid, MultipartFile file) throws IOException;

}
