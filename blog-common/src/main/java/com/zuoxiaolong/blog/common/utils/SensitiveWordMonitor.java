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

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 脏词配置文件监听器
 *
 * @author linjiedeng
 * @date 16/5/28 下午4:26
 * @since 1.0.0
 */
public class SensitiveWordMonitor extends FileAlterationListenerAdaptor {

    private static SensitiveWordMonitor sensitiveWordMonitor;

    public static Map sensitiveWordMap = new HashMap<>();

    private static String SENSITIVE_WORD_FILE_NAME = "bad-word.properties";

    //服务启动的时候载入脏词文件,并实现对文件的实时监听, 每10分钟监听一次
    static {
        String classPathHole = SensitiveWordMonitor.class.getResource("/").toString();
        String classPath = classPathHole.substring(5);
        File file = new File(classPath + SENSITIVE_WORD_FILE_NAME);
        Set<String> badWordSet = loadBadWord(file);
        sensitiveWordMap = addSensitiveWordToHashMap(badWordSet);
        SensitiveWordMonitor.getSensitiveWordMonitor().monitor(classPath, 10 * 60 * 1000);
    }

    private SensitiveWordMonitor() {

    }

    public static SensitiveWordMonitor getSensitiveWordMonitor() {
        if(sensitiveWordMonitor == null) {
            synchronized (SensitiveWordMonitor.class) {
                if(sensitiveWordMonitor == null) {
                    sensitiveWordMonitor = new SensitiveWordMonitor();
                }
            }
        }

        return sensitiveWordMonitor;
    }

    private static Set<String> loadBadWord(File file) {
        Set<String> badWordSet = new HashSet<>();
        try {
            LineIterator it = FileUtils.lineIterator(file);
            while(it.hasNext()) {
                String badWord = it.nextLine();
                badWordSet.add(badWord);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return badWordSet;
    }

    @Override
    public void onFileChange(File file) {
        Set<String> badWordSet = loadBadWord(file);
        sensitiveWordMap = addSensitiveWordToHashMap(badWordSet);
    }

    private static Map addSensitiveWordToHashMap(Set<String> badWordSet) {

        Map wordMap = new HashMap(badWordSet.size());

        for (String word : badWordSet) {
            Map currentMap = wordMap;
            for (int i = 0; i < word.length(); i++) {

                char keyChar = word.charAt(i);
                Object tempMap = currentMap.get(keyChar);

                if (tempMap != null) {
                    currentMap = (Map) tempMap;
                } else {
                    Map<String, String> newMap = new HashMap<String, String>();
                    newMap.put("isEnd", "0");

                    currentMap.put(keyChar, newMap);
                    currentMap = newMap;
                }

                if (i == word.length() - 1) {
                    currentMap.put("isEnd", "1");
                }
            }
        }

        return wordMap;
    }

    public void monitor(String directory, int interval) {
        FileAlterationObserver fileAlterationObserver = new FileAlterationObserver(directory, FileFilterUtils.and(FileFilterUtils.nameFileFilter(SENSITIVE_WORD_FILE_NAME)), null);
        fileAlterationObserver.addListener(this);
        FileAlterationMonitor fileAlterationMonitor = new FileAlterationMonitor(interval, fileAlterationObserver);

        try {
            fileAlterationMonitor.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
