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
    <%@include file="../common/blog-header.jsp"%>
    <div class="row">
        <div class="col-sm-8 blog-main">
            <nav id="myBlog" style="display: none;">
                <ul class="pager">
                    <li><a class="btn btn-default" href="javascript:void(0)" id="load-more">加载更多</a></li>
                </ul>
            </nav>
        </div><!-- /.blog-main -->
        <%@include file="../common/author.jsp"%>
    </div><!-- /.row -->
</div><!-- /.container -->
<%@include file="../common/footer.jsp"%>
<%--<jsp:include page="../common/footer.jsp"/>--%>
<%@include file="../common/bottom.jsp"%>
<%--<jsp:include page="../common/bottom.jsp"/>--%>
<script type="application/javascript">
    /**
     * @author flutterfire
     * @date 2016/6/19
     * @version 1.0
     */

    var offset = "";
    var username = "${username}";
    $(function () {
        $("#load-more").click(function() {
            loadMoreArticle(false);
        });
        loadMoreArticle(true);
    })
    function loadMoreArticle(isInit) {
        $.get(contextPath + "/WebBlog/getMyBlogArticle", {offset:offset, username:username}, function (result) {
            var len = result.data.userArticleList.length;
            for (var i = 0; i < len; i++) {

                $("#myBlog").before('<div class="blog-post">'
                        +'<h2 class="blog-post-title"><a href="javascript:goArticle('+result.data.userArticleList[i].id+')" class="blog-article">'+ result.data.userArticleList[i].title+'</a></h2>'
                        +'<p class="blog-post-meta">'+result.data.webUser.nickname+'发表于'+ result.data.userArticleList[i].createTime+'</p>'
                        +'<p class="blog-post-meta">推荐('+result.data.userArticleList[i].thumbupTimes+') 评论('+result.data.userArticleList[i].commentTimes+') 阅读('+result.data.userArticleList[i].readTimes+')</p></div>');

                if (i == (len - 1)) {
                    offset = result.data.userArticleList[i].id;
                }
            }
            if (isInit && len == 10) {
                $("#myBlog").show();
            }
            if (!isInit && len < 10) {
                $("#myBlog").hide();
            }
        })
    }

    function goArticle(id) {
        window.location.href = contextPath + '/Article/' + id;
    }
</script>
</body>
</html>
