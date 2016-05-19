package com.zuoxiaolong.blog.service.impl;

import com.zuoxiaolong.blog.common.utils.StringUtils;
import com.zuoxiaolong.blog.model.dto.UploadInfo;
import com.zuoxiaolong.blog.service.UploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author Bing Pei
 * @since 1.0.0
 */

@Service
public class UploadServiceImpl implements UploadService {

    private static final List<String> suffixList = Arrays.asList(".JPG", ".PNG");

    /**
     * 用户图片上传接口
     *
     * @param uid 用户编号
     * @param file 图片文件
     * @return 文件上传信息
     */
    @Override
    public Object doUpload(HttpServletRequest request, String uid, MultipartFile[] file) {
        Map<String, List<Object>> map = new HashMap<String, List<Object>>();
        // 校验用户编号
        if(StringUtils.isEmpty(uid)) {
            map.put("err", Arrays.asList(UploadInfo.ERROR_CODE_UPLOAD_UID_NULL));
            return map;
        }

        // 开使上传
        for(MultipartFile f : file) {
            String originalFileName = f.getOriginalFilename().toUpperCase();
            if(StringUtils.isEmpty(originalFileName)) {
                if(map.get("err") == null || map.get("err").isEmpty())
                map.put("err", Arrays.asList(UploadInfo.ERROR_CODE_UPLOAD_RESOURCE_NULL));
                continue;
            } else {
                if(!suffixList.contains(originalFileName.substring(originalFileName.lastIndexOf(".")))) {
                    if(map.get("err") == null || map.get("err").isEmpty())
                    map.put("err", Arrays.asList(UploadInfo.ERROR_CODE_UPLOAD_RESOURCE_TYPE_ERROR));
                    continue;
                }
                StringBuilder stringBuilderFilePath = new StringBuilder("upload" + File.separator);
                stringBuilderFilePath.append(uid);
                try {
                    File dirs = new File(request.getRealPath("/") + stringBuilderFilePath.toString());
                    if(!dirs.exists()) {
                        dirs.mkdirs();
                    }
                    StringBuilder serverFilePath = new StringBuilder();
                    stringBuilderFilePath.append(File.separator);
                    stringBuilderFilePath.append(System.currentTimeMillis());
                    stringBuilderFilePath.append(".PNG");
                    f.transferTo(new File(request.getRealPath("/") + stringBuilderFilePath.toString()));
                } catch (IOException e) {
                    e.printStackTrace();
                    if(map.get("err") == null || map.get("err").isEmpty())
                    map.put("err", Arrays.asList(UploadInfo.ERROR_CODE_UPLOAD_FAILURE));
                    continue;
                }
                UploadInfo uploadInfo = new UploadInfo();
                uploadInfo.setOriginalFileName(originalFileName.substring(0, originalFileName.lastIndexOf(".")));
                uploadInfo.setFileServerPath(stringBuilderFilePath.toString().replaceAll("\\\\", "/"));
                if(map.get("res") == null || map.get("res").isEmpty()) {
                    map.put("res", new ArrayList<Object>());
                }
                map.get("res").add(uploadInfo);
            }
        }
        return map;
    }
}
