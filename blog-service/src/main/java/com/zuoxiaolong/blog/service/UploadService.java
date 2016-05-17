package com.zuoxiaolong.blog.service;

import com.zuoxiaolong.blog.model.persistent.UploadInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Bing Pei
 * @since 1.0.0
 */

public interface UploadService {

    Object doUpload(HttpServletRequest request, String uid, MultipartFile[] file);

}
