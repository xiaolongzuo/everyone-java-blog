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

package com.zuoxiaolong.blog.api.component.authorization;

import com.zuoxiaolong.blog.common.authorization.AuthorizationHelper;
import com.zuoxiaolong.blog.common.utils.ObjectUtils;
import com.zuoxiaolong.blog.common.utils.StringUtils;
import com.zuoxiaolong.blog.model.persistent.WebUser;
import com.zuoxiaolong.blog.service.WebUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Xiaolong Zuo
 * @since 1.0.0
 */
@Component("authorizationFilter")
public class AuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private WebUserService webUserService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        WebUser webUser = webUserService.checkToken(token);
        if (ObjectUtils.isNull(webUser)) {
            filterChain.doFilter(request, response);
            return;
        }
        if (AuthorizationHelper.isTokenExpired(token, webUser.getPassword())) {
            filterChain.doFilter(request, response);
            return;
        }
        HttpSession session = request.getSession(true);
        session.setAttribute("username", webUser.getUsername());
        session.setAttribute("webUserId", webUser.getId());
        filterChain.doFilter(request, response);
    }

}
