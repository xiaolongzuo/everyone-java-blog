# everyone-java-blog

这是一个Java程序猿共同打造的Java博客网站。

## 宗旨

打造中国最受Java程序猿欢迎的Java博客网站，你可以贡献你自己的力量去完善它，它将是你Java路上的伙伴。

## 特色

 > 1、将管理员和编辑的权限尽可能降低，打造一个完全自由的交流环境。
 > 
 > 2、智能学习计划推送，帮助你更好的走好Java之路。
 > 
 > 3、文章写作提醒，更好的完成自己的写作计划。

## 如何贡献代码

 > #### 1、请先fork本项目，在你自己的仓库里创建一个副本。
 > 
 > #### 2、进入到你的workspace，执行以下命令下载项目，并且你需要给你的开发工具安装lombok插件，强烈建议使用idea作为你的开发工具。
 > 
 > ```
 > git clone git@github.com:EveryoneJavaBlog/everyone-java-blog.git
 > ```
 > 
 > #### 3、进入到clone好的everyone-java-blog目录里，执行以下命令，将你的副本地址加入到你的远端仓库中。
 > 
 > ```
 > git remote add remote [你fork的地址，比如：git@github.com:your_username/everyone-java-blog.git]
 > ```
 > 
 > #### 4、进行你想要进行的任何代码修改。
 > 
 > #### 5、提交之前，请先执行以下命令与主库的代码保持同步。在这个过程中，你可能需要处理冲突。(PS：该过程必须要经常做，时刻让本地的代码与主库代码保持一致，这样做有助于减少冲突。)
 > 
 > ```
 > git pull origin master
 > ```
 > 
 > #### 6、当你处理完冲突，并在本地测试完毕以后，请执行以下命令先提交到自己fork的副本仓库。
 > 
 > ```
 > git push remote master
 > ```
 > 
 > #### 7、在github上面创建一个Pull Request，将你的代码提交到主库。
 >
 > #### 8、重复4-7步即可持续贡献你的代码。
 
## 代码规范

请务必遵守规范进行编码，否则你提交的Pull Request将会被拒绝。

#### 1、命名规范
 > 
 > Java类名、局域变量、类变量名、方法名：必须采用标准驼峰命名，类名首字母大写，其余首字母小写，禁止简写。示例：Connection【类名】，connection【变量名】，getConnection【方法名】
 > 
 > 包名：由小写字母和点组成，禁止简写。示例：com.zuoxiaolong.blog.open.api.controller 
 > 
 > 常量名：大写字母与下划线组成，禁止简写。示例：CONNECTION_TIMEOUT，DEFAULT_SIZE
 > 
 > 数据库表名，字段名：小写字母加下划线组成。最多长度30个字母，允许适当使用简写。示例：user_blog，comment_time
 >
 > 除java文件以外的任何文件,包括xml,jsp,properties等：由小写字母和横线组成。示例：user-profile.jsp，article-edit.jsp,application-context.xml,web-config.properties

#### 2、Java文件规范

###### 以下是一个Java文件示例，需要特别强调以下两点，你可以通过设置你的类模板来做到。

 > 1、必须将Apache开源协议内容加入到文件首部。
 >
 > 2、必须写上@author和@since注释。

```java
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

package com.zuoxiaolong.blog.open.api.controller;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 该类代表一个bean对象.
 *
 * @author Xiaolong Zuo
 * @since 1.0.0
 */

@Getter
@Setter
public class Bean {

    private String name;

    private Date data;

}
```

#### 3、JSP文件规范

###### 以下是一个JSP文件示例，需要特别强调以下两点，你可以通过设置你的类模板来做到。

 > 1、必须将Apache开源协议内容加入到文件首部。
 >
 > 2、必须写上User和Time注释。

```html
<%--

    Copyright 2002-2016 the original author or authors.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
    
--%>
<%--
  User: Xiaolong Zuo
  Time: 16/5/8 18:22
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>大家的Java博客</title>

    <!-- Bootstrap -->
    <link href="${cacheUrl}/bootstrap/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<h1>你好，世界！</h1>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="${cacheUrl}/jquery/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${cacheUrl}/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
```
 
#### 4、注释规范

 > 类必须有注释
 > 
 > 方法必须有注释
 >
 > 如果该类是一个数据库model,则每个字段必须有注释
 
###### 以下为一个简单的示例。

```java
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

package com.zuoxiaolong.blog.open.api.controller;

/**
 * 该类封装了关于string操作的工具方法
 *
 * @author Xiaolong Zuo
 * @since 1.0.0
 */
public interface StringUtils {

    /**
     * 判断一个字符串是否为空
     *
     * @param s 需要检查的字符串对象
     * @return 如果s为null或空字符串返回true,否则返回false.
     */
    static boolean isEmpty(String s) {
        return s == null || s.trim().length() == 0;
    }

}
```

## 提交代码需要注意的问题

##### 下面是本人在review pull request时发现的一些问题，请贡献代码的同学务必记得，要避免以下问题出现。

 * 请严格按照以上代码规范检查自己提交的代码
 * 避免提交不该提交的代码，例如构建生成的文件。
 * 类当中切勿带有main函数，如果需要测试，放到test下使用junit进行测试
 * 一定类型的类放在一定的包下，具体的规则如下

 > 数据库model：放在blog-model下的com.zuoxiaolong.blog.model.persistent包内

 > 传给前端的值对象：放在blog-model下的com.zuoxiaolong.blog.model.dto包内

 > 数据库的DAO类：放在blog-dao下的com.zuoxiaolong.blog.mapper包内

 > 业务逻辑service：接口放在blog-service下的com.zuoxiaolong.blog.service包内，实现类放在com.zuoxiaolong.blog.service.impl包内

 > 工具类：放在blog-common下的com.zuoxiaolong.blog.common.utils包内

 > web框架通用的类：放在blog-common的com.zuoxiaolong.blog.common.web包内

 > blog-{module}下的controller：放在blog-{module}下的com.zuoxiaolong.blog.{module}.controller包内，其中module为admin、api、cache、web其中一个
