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

package com.zuoxiaolong.blog.common.utils;

import com.zuoxiaolong.blog.common.bean.ExceptionType;
import com.zuoxiaolong.blog.common.exception.BusinessException;

import java.math.BigDecimal;

/**
 * @author Xiaolong Zuo
 * @since 1.0.0
 */
public interface ValidateUtils {

    static void sensitiveWord(String parameter) {
        if (SensitiveWordCheckUtils.isContainSensitiveWord(parameter)) {
            throw new BusinessException(ExceptionType.PARAMETER_ILLEGAL);
        }
    }

    static void check(boolean result) {
        if (!result) {
            throw new BusinessException(ExceptionType.PARAMETER_ILLEGAL);
        }
    }

    static void required(Object o) {
        if (ObjectUtils.isEmpty(o)) {
            throw new BusinessException(ExceptionType.PARAMETER_ILLEGAL);
        }
    }

    static void pattern(String parameter, String pattern) {
        if (!parameter.matches(pattern)) {
            throw new BusinessException(ExceptionType.PARAMETER_ILLEGAL);
        }
    }

    static void match(String parameter1, String parameter2) {
        required(parameter1);
        required(parameter2);
        if (!parameter1.equals(parameter2)) {
            throw new BusinessException(ExceptionType.PARAMETER_ILLEGAL);
        }
    }

    static void text(String parameter) {
        pattern(parameter, "^[_A-z0-9]{6,36}$");
    }

    static void email(String parameter) {
        pattern(parameter, "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
    }

    static void url(String parameter) {
        pattern(parameter, "((http|ftp|https)://)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?");
    }

    static void number(Integer number, Integer max, Integer min) {
        if (number > max || number < min) {
            throw new BusinessException(ExceptionType.PARAMETER_ILLEGAL);
        }
    }

    static void number(Double number, Double max, Double min) {
        if (number > max || number < min) {
            throw new BusinessException(ExceptionType.PARAMETER_ILLEGAL);
        }
    }

    static void number(BigDecimal number, BigDecimal max, BigDecimal min) {
        if (number.compareTo(max) > 0 || number.compareTo(min) < 0) {
            throw new BusinessException(ExceptionType.PARAMETER_ILLEGAL);
        }
    }

    static void number(Float number, Float max, Float min) {
        if (number > max || number < min) {
            throw new BusinessException(ExceptionType.PARAMETER_ILLEGAL);
        }
    }

    static void numberMax(Integer number, Integer max) {
        if (number > max) {
            throw new BusinessException(ExceptionType.PARAMETER_ILLEGAL);
        }
    }

    static void numberMax(Double number, Double max) {
        if (number > max) {
            throw new BusinessException(ExceptionType.PARAMETER_ILLEGAL);
        }
    }

    static void numberMax(BigDecimal number, BigDecimal max) {
        if (number.compareTo(max) > 0) {
            throw new BusinessException(ExceptionType.PARAMETER_ILLEGAL);
        }
    }

    static void numberMax(Float number, Float max) {
        if (number > max) {
            throw new BusinessException(ExceptionType.PARAMETER_ILLEGAL);
        }
    }

    static void numberMin(Integer number, Integer min) {
        if (number < min) {
            throw new BusinessException(ExceptionType.PARAMETER_ILLEGAL);
        }
    }

    static void numberMin(Double number, Double min) {
        if (number < min) {
            throw new BusinessException(ExceptionType.PARAMETER_ILLEGAL);
        }
    }

    static void numberMin(BigDecimal number, BigDecimal min) {
        if (number.compareTo(min) < 0) {
            throw new BusinessException(ExceptionType.PARAMETER_ILLEGAL);
        }
    }

    static void numberMin(Float number, Float min) {
        if (number < min) {
            throw new BusinessException(ExceptionType.PARAMETER_ILLEGAL);
        }
    }

}
