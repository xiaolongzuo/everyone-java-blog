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
    <link rel="stylesheet" href="../../css/nav.css">
    <link rel="stylesheet" href="../../css/richtext.css">

</head>
@import url(http://weloveiconfonts.com/api/?family=fontawesome);
<body>
<jsp:include page="../common/top.jsp"/>
<div class="container">
    <jsp:include page="../common/index_header.jsp"/>
    <div class="row">
        <div id="new-note">
            <a href="javascript:void(0)">新建文章 </a>
        </div>
        <div class="col-sm-3 article-list">
            <ul class="list-group">
                <li class="list-group-item">一篇文章（草稿）</li>
                <li class="list-group-item">第二篇(发布的)</li>
                <li class="list-group-item">第三篇</li>
                <li class="list-group-item">第四篇</li>
                <li class="list-group-item">第五篇</li>
            </ul>
        </div>
        <div class="col-sm-9 article-content">
            <div class="content">
                <h4 contenteditable="true">标题</h4>
                <div class="textEditing">
                    <input type="checkbox" id="bold"><label for="bold"><span class="fontawesome-bold"></span></label></input>
                    <input type="checkbox" id="italic"><label for="italic"><span class="fontawesome-italic"></span></label></input>
                    <input type="checkbox" id="underline"><label for="underline"><span class="fontawesome-underline"></span></label></input>
                    <input type="radio" name="textStyle" id="left" checked><label for="left"><span class="fontawesome-align-left"></span></label></input>
                    <input type="radio" name="textStyle" id="center"><label for="center"><span class="fontawesome-align-center"></span></label></input>
                    <input type="radio" name="textStyle" id="right"><label for="right"><span class="fontawesome-align-right"></span></label></input>
                    <input type="radio" name="textStyle" id="justify"><label for="justify"><span class="fontawesome-align-justify"></span></label></input>
                    <input type="file" id="image"><label for="image"><span class="fontawesome-picture"></span></label></input>
                    <input type="checkbox" id="attachment"><label for="attachment"><span class="fontawesome-link"></span></label></input>
                    <input type="" id="link"><label for="link" id="linkLable"><span id=""></span></label></input>
                    <input type="checkbox" id="code"><label for="code"><span class="fontawesome-quote-right"></span></label></input>
                </div>
                <p id="contentText" contenteditable="true">Enter text here</p>
            </div>
        </div>
    </div><!-- /.row -->
</div><!-- /.container -->
<jsp:include page="../common/footer.jsp"/>
<jsp:include page="../common/bottom.jsp"/>
<script src="${pageContext.request.contextPath}/js/message.js"></script>
<script src="${pageContext.request.contextPath}/lib/richtext/prefixfree.min.js"></script>
<script src="${pageContext.request.contextPath}/js/richtext.js"></script>
</body>
</html>


