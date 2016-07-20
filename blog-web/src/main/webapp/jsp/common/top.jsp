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
  Time: 16/1/15 02:01
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/taglib.jsp"%>
<!--top-Header-menu-->
<div class="blog-masthead">
    <div class="container">
        <nav class="blog-nav">
            <c:if test="${homeArticleDTO == null}">
                <a href="${pageContext.request.contextPath}/HomePage/Index?categoryId=1" class="pull-left blog-nav-item">技术</a>
                <a href="${pageContext.request.contextPath}/HomePage/Index?categoryId=2" class="pull-left blog-nav-item">职场</a>
                <a href="${pageContext.request.contextPath}/HomePage/Index?categoryId=3" class="pull-left blog-nav-item">人生</a>
            </c:if>
            <c:if test="${homeArticleDTO != null}">
                <a href="${pageContext.request.contextPath}/HomePage/Index?categoryId=1" class="pull-left blog-nav-item <c:if test='${homeArticleDTO.categoryId == 1}'>active</c:if>">技术</a>
                <a href="${pageContext.request.contextPath}/HomePage/Index?categoryId=2" class="pull-left blog-nav-item <c:if test='${homeArticleDTO.categoryId == 2}'>active</c:if>">职场</a>
                <a href="${pageContext.request.contextPath}/HomePage/Index?categoryId=3" class="pull-left blog-nav-item <c:if test='${homeArticleDTO.categoryId == 3}'>active</c:if>">人生</a>
            </c:if>
            <a href="${pageContext.request.contextPath}/html/api-index.html" class="pull-left blog-nav-item">API文档</a>
            <c:if test="${sessionScope.token == null}">
                <a class="pull-right blog-nav-item blog-nav-right-first-item" href="${pageContext.request.contextPath}/WebUser/Register">注册</a>
                <a class="pull-right blog-nav-item" href="${pageContext.request.contextPath}/WebUser/Login">登录</a>
            </c:if>
            <c:if test="${sessionScope.token != null}">
                <a class="pull-right blog-nav-item blog-nav-right-first-item" href="${pageContext.request.contextPath}/WebUser/Logout">注销</a>
                <a class="pull-right blog-nav-item" href="${pageContext.request.contextPath}/MessageBox/HomePage">短信箱</a>
                <a class="pull-right blog-nav-item" href="${pageContext.request.contextPath}/Article/Write">写文章</a>
                <a class="pull-right blog-nav-item" href="${pageContext.request.contextPath}/WebBlog/HomePage/${sessionScope.username}">我的博客</a>
                <a class="pull-right blog-nav-item" href="${pageContext.request.contextPath}/WebBlog/Select/Config">${sessionScope.nickname}</a>
            </c:if>
        </nav>
    </div>
</div>
<!--close-top-Header-menu-->
