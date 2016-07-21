package com.zuoxiaolong.blog.service.impl;

import com.zuoxiaolong.blog.common.bean.ExceptionType;
import com.zuoxiaolong.blog.common.exception.BusinessException;
import com.zuoxiaolong.blog.common.utils.ValidateUtils;
import com.zuoxiaolong.blog.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author Bing Pei
 * @since 1.0.0
 */

@Service
public class FileServiceImpl implements FileService {

    private static final List<String> FILE_SUFFIX_LIST = Arrays.asList(".JPEG", ".JPG", ".PNG", ".GIF");

    /**
     * 用户图片上传接口
     *
     * @param username 用户编号
     * @param file 图片文件
     * @return 文件上传信息
     */
    @Override
    public String upload(String uploadDirectory, String username, MultipartFile file) throws IOException {
        ValidateUtils.required(username);
        ValidateUtils.required(uploadDirectory);
        ValidateUtils.required(file);

        String originalFileName = file.getOriginalFilename();
        if (!originalFileName.contains(".")) {
            throw new BusinessException(ExceptionType.PARAMETER_ILLEGAL);
        }
        String fileSuffix = originalFileName.substring(originalFileName.lastIndexOf("."));
        if(!FILE_SUFFIX_LIST.contains(fileSuffix.toUpperCase())) {
            throw new BusinessException(ExceptionType.PARAMETER_ILLEGAL);
        }
        StringBuilder stringBuilderFilePath = new StringBuilder("upload" + File.separator);
        stringBuilderFilePath.append(username);
        File dirs = new File(uploadDirectory + stringBuilderFilePath.toString());
        if(!dirs.exists()) {
            dirs.mkdirs();
        }
        stringBuilderFilePath.append(File.separator);
        stringBuilderFilePath.append(System.currentTimeMillis());
        stringBuilderFilePath.append(fileSuffix);
        file.transferTo(new File(uploadDirectory + stringBuilderFilePath.toString()));
        return stringBuilderFilePath.toString().replaceAll("\\\\", "/");
    }

}
