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
  User: DeseverL
  Time: 16/6/5 18:58
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@include file="../common/head.jsp"%>
    <link href="${pageContext.request.contextPath}/css/article.css" rel="stylesheet">
    <script type="text/javascript">
        //TODO 用户昵称
        var USER_NICKNAME = "${sessionScope.username}";
    </script>
</head>
<body>
<%@include file="../common/top.jsp"%>
<div class="container">
    <%@include file="../common/blog_header.jsp"%>
    <div class="row">
        <div class="col-sm-8 blog-main">
            <div class="blog-post">
                <h2 class="blog-post-title"><a href="#" class="blog-article">${result.data.userArticle.title}</a></h2>
                <jsp:useBean id="now" class="java.util.Date" />
                <fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd" var="today"/>
                <fmt:formatDate value="${result.data.userArticle.createTime}" type="both" dateStyle="long" pattern="yyyy-MM-dd" var="date"/>
                <c:choose>
                    <c:when test="${today == date}">
                        <p class="blog-post-meta">${result.data.webUser.nickname}发表于今天<fmt:formatDate value="${result.data.userArticle.createTime}" type="both" pattern="HH:mm:ss" />&nbsp;&nbsp;&nbsp;&nbsp;推荐(${result.data.userArticle.thumbupTimes}) 评论(${result.data.userArticle.commentTimes}) 阅读(${result.data.userArticle.readTimes})</p>
                    </c:when>
                    <c:otherwise>
                        <p class="blog-post-meta">${result.data.webUser.nickname}发表于<fmt:formatDate value="${result.data.userArticle.createTime}" type="both" pattern="yyyy.MM.dd HH:mm" />&nbsp;&nbsp;&nbsp;&nbsp;推荐(${result.data.userArticle.thumbupTimes}) 评论(${result.data.userArticle.commentTimes}) 阅读(${result.data.userArticle.readTimes})</p>
                    </c:otherwise>
                </c:choose>
                <p>${result.data.userArticle.content}</p>
            </div><!-- /.blog-post -->
            <div class="blog-thumbs-up center-block">
                <a href="javascript:void(0)" class="btn btn-danger btn-lg btn-block" onclick="thumbsUp(this)">推荐</a>
            </div>
            <div>
                <div class="panel comment-head">
                    <div class="panel-body">
                        <span id = 'commentCount'>${result.data.userArticle.commentTimes}</span>条评论
                        <a href="#comment-textarea" class="btn pull-right"><span class="glyphicon glyphicon-pencil"> 添加新评论</span></a>
                    </div>
                </div>
                <div id="bottom-comment" class="panel load-more">
                    <a class="btn " href="javascript:void(0)" onclick='getMoreComment()'>加载更多<span class="glyphicon glyphicon-arrow-down"></span></a>
                </div>
                <form class='new-comment' style='display: block;'>
                    <div class='panel-body comment-text'>
                        <input type='hidden' value='${result.data.userArticle.id}' name='articleId'>
                        <textarea id='comment-textarea' name='comment' class='textarea' placeholder='写下你的评论…' maxlength='2000'></textarea>
                        <div>
                            <input type='button' data-disable-with='提交中...' class='btn btn-small btn-info pull-right' onclick='addComment(this)' value='发 表'>
                        </div>
                    </div>
                </form>
            </div>
        </div><!-- /.blog-main -->

       <%--<jsp:include page="../common/author.jsp"/>--%>
    </div><!-- /.row -->
</div><!-- /.container -->
<input id="articleid" type="hidden" value="${result.data.userArticle.id}"/>
<%@include file="../common/footer.jsp"%>
<%@include file="../common/bottom.jsp"%>
<script src="${pageContext.request.contextPath}/js/article.js"></script>
</body>
</html>
