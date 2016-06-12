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

package com.zuoxiaolong.blog.common.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Enumeration;

/**
 * 用于将制定的属性值,从spring中填充到servletContext
 *
 * @author Xiaolong Zuo
 * @since 1.0.0
 *
 * @see ConfigurerPropertiesHolder
 */
public class ServletContextPropertiesLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        Enumeration<String> propertyNames = ConfigurerPropertiesHolder.getPropertyNames();
        if (propertyNames == null) {
            return;
        }
        while (propertyNames.hasMoreElements()) {
            String propertyName = propertyNames.nextElement();
            if (servletContext.getAttribute(propertyName) == null) {
                servletContext.setAttribute(propertyName, ConfigurerPropertiesHolder.getProperty(propertyName));
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {}

}
