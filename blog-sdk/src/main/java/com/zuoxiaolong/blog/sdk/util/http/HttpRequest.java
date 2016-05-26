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
package com.zuoxiaolong.blog.sdk.util.http;

import com.zuoxiaolong.blog.sdk.exception.BlogSdkHttpRequestException;
import com.zuoxiaolong.blog.sdk.util.http.constants.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * @author Boren You
 * @dateTime 2016/5/25 22:08
 * @since 1.0.0
 */
public class HttpRequest {

    public String doGet(String url) throws Exception {
        StringBuffer resultBuffer = new StringBuffer();
        HttpURLConnection httpURLConnection = openConnection(url);
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        String tempLine;
        if (httpURLConnection.getResponseCode() != Constants.HTTP_CODE_200) {
            throw new BlogSdkHttpRequestException("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
        }
        try {
            inputStream = httpURLConnection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputStreamReader);
            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return resultBuffer.toString();
    }


    public String doPost(String url, Map parameters) throws Exception {
        String parameterString = HttpRequestHelper.map2String(parameters);
        System.out.println("POST parameter : " + parameterString);
        HttpURLConnection httpURLConnection = openConnection(url);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod(Constants.METHOD_POST);
        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(parameterString.length()));

        OutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine;

        try {
            outputStream = httpURLConnection.getOutputStream();
            outputStreamWriter = new OutputStreamWriter(outputStream);

            outputStreamWriter.write(parameterString);
            outputStreamWriter.flush();

            if (httpURLConnection.getResponseCode() >= 300) {
                throw new BlogSdkHttpRequestException("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
            }

            inputStream = httpURLConnection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputStreamReader);

            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }

        } finally {

            if (outputStreamWriter != null) {
                outputStreamWriter.close();
            }

            if (outputStream != null) {
                outputStream.close();
            }

            if (reader != null) {
                reader.close();
            }

            if (inputStreamReader != null) {
                inputStreamReader.close();
            }

            if (inputStream != null) {
                inputStream.close();
            }

        }

        return resultBuffer.toString();
    }

    private HttpURLConnection openConnection(String url) throws IOException {
        URLConnection connection = new URL(url).openConnection();
        connection.setRequestProperty("Accept-Charset", Constants.DEFAULT_CHARSET);
        connection.setRequestProperty("Content-Type", Constants.DEFAULT_CONTENT_TYPE);
        connection.setConnectTimeout(Constants.DEFAULT_CONNECT_TIMEOUT);
        connection.setReadTimeout(Constants.DEFAULT_READ_TIMEOUT);
        return (HttpURLConnection) connection;
    }

}
