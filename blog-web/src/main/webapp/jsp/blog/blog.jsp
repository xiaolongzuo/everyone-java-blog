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
  Time: 16/6/5 18:58
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@include file="../common/head.jsp"%>
    <%--<jsp:include page="../common/head.jsp"/>--%>
</head>
<body>
<%@include file="../common/top.jsp"%>
<%--<jsp:include page="../common/top.jsp"/>--%>
<div class="container">
    <%@include file="../common/blog_header.jsp"%>
    <%--<jsp:include page="../common/blog_header.jsp"/>--%>
    <div class="row">
        <div class="col-sm-8 blog-main">
            <%--<c:choose>--%>
                <%--<c:when test="${result.data.userArticleList!=null && fn:length(result.data.userArticleList) > 0}">--%>
                    <%--<c:forEach var="article" items="${result.data.userArticleList}" varStatus="index">--%>
                        <%--<div class="blog-post">--%>
                            <%--<h2 class="blog-post-title"><a href="#" class="blog-article">${article.title}</a></h2>--%>
                            <%--<jsp:useBean id="now" class="java.util.Date" />--%>
                            <%--<fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd" var="today"/>--%>
                            <%--<fmt:formatDate value="${article.createTime}" type="both" dateStyle="long" pattern="yyyy-MM-dd" var="date"/>--%>
                            <%--<c:choose>--%>
                                <%--<c:when test="${today == date}">--%>
                                    <%--<p class="blog-post-meta">${result.data.webUser.nickname}发表于今天<fmt:formatDate value="${article.createTime}" type="both" pattern="HH:mm:ss" /></p>--%>
                                <%--</c:when>--%>
                                <%--<c:otherwise>--%>
                                    <%--<p class="blog-post-meta">${result.data.webUser.nickname}发表于<fmt:formatDate value="${article.createTime}" type="both" pattern="yyyy-MM-dd" /></p>--%>
                                <%--</c:otherwise>--%>
                            <%--</c:choose>--%>
                            <%--<p class="blog-post-meta">推荐(${article.thumbupTimes}) 评论(${article.commentTimes}) 阅读(${article.readTimes})</p>--%>
                        <%--</div>--%>
                    <%--</c:forEach>--%>
                <%--</c:when>--%>
                <%--<c:otherwise>--%>
                    <%--你还没有发表文章--%>
                <%--</c:otherwise>--%>
            <%--</c:choose>--%>


            <%--<div class="blog-post">--%>
                <%--<h2 class="blog-post-title"><a href="#" class="blog-article">第一篇文章第一篇文章第一篇文章第一篇文章第一篇文章</a></h2>--%>
                <%--<p class="blog-post-meta">左潇龙发表于1天前</p>--%>
                <%--<p class="blog-post-meta">推荐(10) 评论(10) 阅读(1000)</p>--%>
            <%--</div><!-- /.blog-post -->--%>

            <%--<div class="blog-post">--%>
                <%--<h2 class="blog-post-title"><a href="#" class="blog-article">第二篇文章</a></h2>--%>
                <%--<p class="blog-post-meta">左潇龙发表于1天前</p>--%>
                <%--<p class="blog-post-meta">推荐(10) 评论(10) 阅读(1000)</p>--%>
            <%--</div><!-- /.blog-post -->--%>

            <%--<div class="blog-post">--%>
                <%--<h2 class="blog-post-title"><a href="#" class="blog-article">第三篇文章</a></h2>--%>
                <%--<p class="blog-post-meta">左潇龙发表于1天前</p>--%>
                <%--<p class="blog-post-meta">推荐(10) 评论(10) 阅读(1000)</p>--%>
            <%--</div><!-- /.blog-post -->--%>

            <nav  id="myBlog">
                <ul class="pager">
                    <li><a href="javascript:loadMoreArticle()">加载更多</a></li>
                    <%--<li><a href="javascript:goNext()">下一页</a></li>--%>
                </ul>
            </nav>

        </div><!-- /.blog-main -->
        <%@include file="../common/author.jsp"%>
        <%--<jsp:include page="../common/author.jsp"/>--%>

    </div><!-- /.row -->
</div><!-- /.container -->
<%@include file="../common/footer.jsp"%>
<%--<jsp:include page="../common/footer.jsp"/>--%>
<%@include file="../common/bottom.jsp"%>
<%--<jsp:include page="../common/bottom.jsp"/>--%>
<script>
    var ctx = "${ctx}";
</script>
<script src="${pageContext.request.contextPath}/js/blog.js"></script>
</body>
</html>
