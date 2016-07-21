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
  User: Xiaolong Zuo
  Time: 16/6/4 16:26
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <jsp:include page="../common/head.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/nav.css">
</head>

<body>
    <jsp:include page="../common/top.jsp"/>
    <div class="container">
        <jsp:include page="../common/index_header.jsp"/>
        <div class="row">
            <div class="col-sm-8 blog-main">
                <div id="blog-rank">
                    <ul>
                        <c:if test="${topThreeUserArticles.mostRecommendArticle != null}">
                            <li id='most-recommend-article'>[最多推荐]<a href='${pageContext.request.contextPath}/Article/${topThreeUserArticles.mostRecommendArticle.id}'>${topThreeUserArticles.mostRecommendArticle.title}</a></li>
                        </c:if>
                        <c:if test="${topThreeUserArticles.mostCommentArticle != null}">
                            <li id='most-commend-article'>[最多评论]<a href='${pageContext.request.contextPath}/Article/${topThreeUserArticles.mostCommentArticle.id}'>${topThreeUserArticles.mostCommentArticle.title}</a></li>
                        </c:if>
                        <c:if test="${topThreeUserArticles.mostReadArticle != null}">
                            <li id='most-read-article'>[最多阅读]<a href='${pageContext.request.contextPath}/Article/${topThreeUserArticles.mostReadArticle.id}'>${topThreeUserArticles.mostReadArticle.title}</a></li>
                        </c:if>
                    </ul>
                </div>
                <c:forEach items="${homeArticleDTO.articles}" var="articleDTO">
                    <div class="blog-post1">
                        <h2 class="blog-post-title"><a href="${pageContext.request.contextPath}/Article/${articleDTO.userArticle.id}" class="blog-article">${articleDTO.userArticle.title}</a></h2>
                        <p class="blog-post-meta">${articleDTO.webUser.nickname}${articleDTO.friendlyTime}</p>
                        <p class="blog-post-meta">推荐(${articleDTO.userArticle.thumbupTimes}) 评论(${articleDTO.userArticle.commentTimes}) 阅读(${articleDTO.userArticle.readTimes})</p>
                    </div>
                </c:forEach>
                <div id="load-more" category-id="${homeArticleDTO.categoryId}" offset="${homeArticleDTO.offset}" size="${homeArticleDTO.size}"><a href="javascript:void(0)">加载更多</a></div>
            </div><!-- /.blog-main -->

            <jsp:include page="../common/contributor.jsp"/>
        </div><!-- /.row -->
    </div><!-- /.container -->
    <jsp:include page="../common/footer.jsp"/>
    <jsp:include page="../common/bottom.jsp"/>
    <script src="${pageContext.request.contextPath}/js/index.js"></script>
</body>
</html>


