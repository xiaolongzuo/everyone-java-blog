<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  User: 郭松涛
  Time: 16/7/14 22:26
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <jsp:include page="../common/head.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/write.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/richtext.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/smartMenu.css">
</head>
<body>
<jsp:include page="../common/top.jsp"/>
<div class="container">
    <%--<jsp:include page="../common/index_header.jsp"/>--%>
    <div class="row">
        <div id = "blankrow">

        </div>
        <div class="col-sm-3 article-list">
            <div id="new-note">
                <a href="javascript:void(0)">新建文章 </a>
            </div>
            <c:forEach items="${UserArticles}" var="article">
                <li class="list-group-item">
                    <c:if test="${article.id eq ArticleInfoDTO.userArticle.id}">
                        <a class="selected" href="${pageContext.request.contextPath}/Article/Write/${article.id}"
                           currentId = "${article.id}">
                                ${article.title}
                        </a>
                        <apan id ="opration">
                            操作
                        </apan>
                    </c:if>
                    <c:if test="${!(article.id eq ArticleInfoDTO.userArticle.id)}">
                        <a href="${pageContext.request.contextPath}/Article/Write/${article.id}">
                                ${article.title}
                        </a>
                    </c:if>
                </li>
            </c:forEach>
        </div>
        <div class="col-sm-9 article-content">
            <div class="content">
                <h3 id = "title" contenteditable="true">
                        ${not empty ArticleInfoDTO ? ArticleInfoDTO.userArticle.title : "无标题文章"}
                </h3>
                <div class="textEditing">
                    <input type="checkbox" id="bold"><label for="bold"><span class="fontawesome-bold"></span></label></input>
                    <input type="checkbox" id="italic"><label for="italic"><span class="fontawesome-italic"></span></label></input>
                    <input type="checkbox" id="underline"><label for="underline"><span class="fontawesome-underline"></span></label></input>
                    <input type="radio" name="textStyle" id="left" checked><label for="left"><span class="fontawesome-align-left"></span></label></input>
                    <input type="radio" name="textStyle" id="center"><label for="center"><span class="fontawesome-align-center"></span></label></input>
                    <input type="radio" name="textStyle" id="right"><label for="right"><span class="fontawesome-align-right"></span></label></input>
                    <input type="radio" name="textStyle" id="justify"><label for="justify"><span class="fontawesome-align-justify"></span></label></input>
                    <input type="file" id="image"><label for="image"><span class="fontawesome-picture"></span></label></input>
                </div>
                <p id="contentText" contenteditable="true">
                    ${not empty ArticleInfoDTO ? ArticleInfoDTO.userArticle.content : "每一个不曾起舞的日子，都是对生命的辜负"}
                </p>
            </div>
        </div>
    </div><!-- /.row -->
</div><!-- /.container -->
<jsp:include page="../common/footer.jsp"/>
<jsp:include page="../common/bottom.jsp"/>
<script src="${pageContext.request.contextPath}/js/message.js"></script>
<script src="${pageContext.request.contextPath}/lib/richtext/prefixfree.min.js"></script>
<script src="${pageContext.request.contextPath}/js/richtext.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-smartMenu.js"></script>
<script src="${pageContext.request.contextPath}/js/write.js"></script>

</body>
</html>


