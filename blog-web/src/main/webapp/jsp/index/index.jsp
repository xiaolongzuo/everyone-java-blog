
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
  Time: 16/6/4 16:26
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <jsp:include page="../common/head.jsp"/>
    <link rel="stylesheet" href="../../css/nav.css">
</head>

<body>
    <jsp:include page="../common/top.jsp"/>
    <div class="container">
        <jsp:include page="../common/index_header.jsp"/>
        <div class="row">
            <div class="col-sm-8 blog-main">
                <div id="blog-rank">
                    <ul></ul>
                </div>
                <div id ="loadmore"><a>加载更多</a></div>
            </div><!-- /.blog-main -->

            <jsp:include page="../common/contributor.jsp"/>
        </div><!-- /.row -->
    </div><!-- /.container -->
    <jsp:include page="../common/footer.jsp"/>
    <jsp:include page="../common/bottom.jsp"/>
    <script src="${pageContext.request.contextPath}/js/index.js"></script>
</body>
</html>


