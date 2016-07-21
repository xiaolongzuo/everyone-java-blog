/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zuoxiaolong.blog.api.controller;

import com.zuoxiaolong.blog.common.authorization.CheckLogin;
import com.zuoxiaolong.blog.common.web.ConfigurerPropertiesHolder;
import com.zuoxiaolong.blog.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 文件上传接口
 *
 * @author Bing Pei
 * @since 1.0.0
 */
@Controller
@RequestMapping("/File")
public class FileController extends AbstractApiController {

    @Autowired
    private FileService fileService;

    /**
     * 文件上传方法
     *
     * @param file 上传资源名称
     */
    @CheckLogin
    @RequestMapping(value = "/Upload", method = RequestMethod.POST)
    public String upload(@RequestParam MultipartFile file) throws IOException {
        return fileService.upload(ConfigurerPropertiesHolder.getProperty("upload.directory", getContext().getRealPath("/")), getUsername(), file);
    }

}
