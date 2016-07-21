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

import com.zuoxiaolong.blog.common.web.AbstractController;

/**
 * @author Xiaolong Zuo
 * @since 1.0.0
 */
public abstract class AbstractApiController extends AbstractController {

    protected static final String USERNAME_ATTRIBUTE_KEY = "username";

    protected static final String WEB_USER_ID_ATTRIBUTE_KEY = "webUserId";

    protected String getUsername() {
        return (String) getSessionAttribute(USERNAME_ATTRIBUTE_KEY);
    }

    protected Integer getWebUserId() {
        return (Integer) getSessionAttribute(WEB_USER_ID_ATTRIBUTE_KEY);
    }

    protected String getIpAddr() {
        String ipAddress;
        ipAddress = this.getRequest().getHeader("x-forwarded-for");
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = this.getRequest().getHeader("Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = this.getRequest().getHeader("WL-Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = this.getRequest().getHeader("HTTP_CLIENT_IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = this.getRequest().getHeader("HTTP_X_FORWARDED-FOR");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = this.getRequest().getRemoteAddr();
        }

        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15
            if(ipAddress.indexOf(",")>0){
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

}
