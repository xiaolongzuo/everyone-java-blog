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

package com.zuoxiaolong.blog.admin.controller;

import com.zuoxiaolong.blog.common.web.BaseController;
import com.zuoxiaolong.blog.model.persistent.Contributor;
import com.zuoxiaolong.blog.service.ContributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * 贡献者信息操作
 *
 * @author cnJun
 * @date 2016/5/15
 * @since 1.0.0
 */
@Controller
@RequestMapping("/contributor")
public class ContributorController extends BaseController{

    @Autowired
    private ContributorService contributorService;

    @RequestMapping(value = "/edit")
    public String edit() {

        return "contributor/contributor-edit";
    }

    /**
     *
     *
     * @param contributor
     *      贡献者实体
     * @param model
     *      新增操作结果返回信息
     */
    @RequestMapping(value = "/insert")
    public String insert(@Validated Contributor contributor, Model model) {

        try {

            contributor.setCreateTime(new Date());

            int row = contributorService.insertSelective(contributor);

            if (row < 1) {
                model.addAttribute("result", "新增贡献者失败");
                return "contributor/contributor-error";
            }

            model.addAttribute("result", contributor);
        } catch (Exception e) {
            logger.error("新增贡献者异常", e);
            model.addAttribute("result", "新增贡献者异常");
            return "contributor/contributor-error";
        }

        return "contributor/contributor-success";
    }
}
