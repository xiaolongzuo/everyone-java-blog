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
<%@include file="../common/top.jsp"%>
<div style="margin-left: 50px">
    <form action="${pageContext.request.contextPath}/WebBlog/Update/Config" method="post">
        <div class="form-group">
            <label>博客名称:</label><br/>
            <input name="blogTitle" value="${result.data.blogTitle}" style="width: 30%"/><br/>
        </div>
        <div class="form-group">
            <label>博客子标题:</label><br/>
            <textarea name="blogSubTitle" cols="20" rows="5" style="width: 30%">${result.data.blogSubTitle}</textarea><br/>
        </div>
        <div class="form-group">
            <label>个人简介:</label><br/>
            <textarea name="introduction" cols="60" rows="10" style="width: 30%">${result.data.introduction}</textarea><br/>
        </div>
        <input type="submit" class="btn btn-success" value="修改"/>
    </form>
</div>
</body>
</html>
