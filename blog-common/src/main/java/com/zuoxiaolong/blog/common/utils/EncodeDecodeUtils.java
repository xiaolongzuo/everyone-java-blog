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

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author 郭松涛
 * @date 2016/5/14 23:24
 * @since 1.0.0
 */
public interface EncodeDecodeUtils {

    static String encodeBase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    static byte[] decodeBase64(String source) {
        return Base64.getDecoder().decode(source);
    }

    static String encryptDes(String source, String password) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKeySpec = new DESKeySpec(StringUtils.toBytes(password));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, random);
            return encodeByMd5(cipher.doFinal(StringUtils.toBytes(source)));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    static String encodeByMd5(String s) {
        return encodeByMd5(StringUtils.toBytes(s));
    }

    static String encodeByMd5(byte[] bytes) {
        MessageDigest messageDigest;
        String result;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            result = encodeBase64(messageDigest.digest(bytes));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}
