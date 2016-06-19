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
  User: linjiedeng
  Time: 16/6/5 18:58
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <jsp:include page="../common/head.jsp"/>
</head>
<body>
<form action="${pageContext.request.contextPath}/WebBlog/Update/Config" method="post">
    博客名称: <input name="blogTitle" value="${result.data.blogTitle}"/><br/>
    博客子标题: <textarea name="blogSubTitle" cols="20" rows="10">${result.data.blogSubTitle}</textarea><br/>
    个人简介: <textarea name="introduction" cols="30" rows="20">${result.data.introduction}</textarea><br/>
    <input type="submit" value="修改"/>
</form>
</body>
</html>
