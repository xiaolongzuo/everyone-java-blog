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

package com.zuoxiaolong.blog.cache.config;

import com.zuoxiaolong.blog.cache.service.SchedulingJobManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author goozi
 * @create 2016-05-15 0:05
 * @since 1.0.0
 */
@Configuration
@EnableScheduling
public class ScheduledConfig {

    @Bean
    public SchedulingJobManager schedulingJobManager() {
        return new SchedulingJobManager();
    }
}
