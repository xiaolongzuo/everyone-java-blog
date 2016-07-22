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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
                <div id="blog-charts">
                    <ul>
                        <c:if test="${articleCharts.thumbupCharts != null}">
                            <li>[最多推荐]<a href='${pageContext.request.contextPath}/Article/${articleCharts.thumbupCharts.id}'>${articleCharts.thumbupCharts.title}</a></li>
                        </c:if>
                        <c:if test="${articleCharts.commentCharts != null}">
                            <li>[最多评论]<a href='${pageContext.request.contextPath}/Article/${articleCharts.commentCharts.id}'>${articleCharts.commentCharts.title}</a></li>
                        </c:if>
                        <c:if test="${articleCharts.readCharts != null}">
                            <li>[最多阅读]<a href='${pageContext.request.contextPath}/Article/${articleCharts.readCharts.id}'>${articleCharts.readCharts.title}</a></li>
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
                <c:if test="${homeArticleDTO.articles != null && fn:length(homeArticleDTO.articles) == homeArticleDTO.size}">
                    <div id="load-more" category-id="${homeArticleDTO.categoryId}" offset="${homeArticleDTO.offset}" size="${homeArticleDTO.size}"><a href="javascript:void(0)">加载更多</a></div>
                </c:if>
            </div><!-- /.blog-main -->

            <jsp:include page="../common/contributor.jsp"/>
        </div><!-- /.row -->
    </div><!-- /.container -->
    <jsp:include page="../common/footer.jsp"/>
    <jsp:include page="../common/bottom.jsp"/>
    <script type="application/javascript">
        /**
         * @author 郭松涛
         * @date 2016/6/18 10:20
         * @version 1.0
         */

        $(document).ready(function () {
            $('#load-more').click(function () {
                var categoryId = $('#load-more').attr("category-id");
                var offset = $('#load-more').attr("offset");
                var size = $('#load-more').attr("size");
                $.ajax({
                    url: contextPath + "/HomePage/Articles?categoryId=" + categoryId + "&offset=" + offset + "&size=" + size,
                    dataType: "JSON",
                    type: "GET",
                    cache: false,
                    success: function (result) {
                        if (result == "error") {
                            alert("加载失败!");
                            return;
                        }
                        var articlesObject = result['articles'];
                        var articleHtml = '';
                        $.each(articlesObject, function (n, value) {
                            articleHtml = articleHtml + '<div class="blog-post1"><h2 class="blog-post-title">';
                            articleHtml = articleHtml + '<a href="' + contextPath + '/Article/' + value['userArticle']['id'] + '" class="blog-article">' + value['userArticle']['title'] + '</a></h2>';
                            articleHtml = articleHtml + '<p class="blog-post-meta">' + value['webUser']['nickname'] + value['friendlyTime'] + '</p>';
                            articleHtml = articleHtml + '<p class="blog-post-meta">推荐(' + value['userArticle']['thumbupTimes'] + ') 评论(' + value['userArticle']['commentTimes'] + ') 阅读(' + value['userArticle']['readTimes'] + ')</p>' ;
                            articleHtml = articleHtml + '</div>';
                        });
                        $('#load-more').attr("category-id", result['categoryId']);
                        $('#load-more').attr("offset", result['offset']);
                        $('#load-more').attr("size", result['size']);
                        $('#load-more').before(articleHtml);
                        if (articlesObject.length < size) {
                            $('#load-more').css('display', 'none');
                        } else {
                            $('#load-more').css('display', 'block');
                        }
                    }
                });
            });
        });
    </script>
</body>
</html>


