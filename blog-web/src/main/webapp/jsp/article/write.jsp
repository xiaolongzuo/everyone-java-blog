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
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <jsp:include page="../common/head.jsp"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/write.css"/>
</head>
<body>
<jsp:include page="../common/top.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-sm-3 article-list">
            <div id="new-note">
                <a  href="javascript:void(0)">
                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                    +新建文章
                </a>
            </div>
            <c:if test="${userArticles != null}">
                <c:forEach items="${userArticles}" var="article" varStatus="status">
                    <c:if test="${status.index == 0}">
                        <li class="list-group-item selected" article-id="${article.id}" article-category="${article.categoryId}" is-main-page="${article.isMainPage}">
                        <span class="article-status">[
                            <c:if test="${article.status == 1}">
                                已发布
                            </c:if>
                            <c:if test="${article.status == 0}">
                                草稿
                            </c:if>
                            ]</span>
                            <a href="#" class="selected">${article.title}</a>
                            <!-- <p class="article-operation">操作</p> -->
                        </li>
                    </c:if>
                    <c:if test="${status.index > 0}">
                        <li class="list-group-item" article-id="${article.id}" article-category="${article.categoryId}" is-main-page="${article.isMainPage}">
                        <span class="article-status">[
                            <c:if test="${article.status == 1}">
                                已发布
                            </c:if>
                            <c:if test="${article.status == 0}">
                                草稿
                            </c:if>
                            ]</span>
                            <a href="#">${article.title}</a>
                        </li>
                    </c:if>
                </c:forEach>
            </c:if>
        </div>
        <div class="col-sm-9 article-content">
            <div class="content">
                <div class="form-group">
                    <input type="text" class="form-control" id="title" placeholder="无标题文章" >
                </div>
                <textarea class="html_editor" style="width:100%"></textarea>
            </div>
        </div><!-- /.blog-main -->
    </div><!-- /.row -->
</div><!-- /.container -->
<jsp:include page="../common/footer.jsp"/>
<jsp:include page="../common/bottom.jsp"/>
<script type="application/javascript">
    var draftArray = new Array();

    $(document).ready(function() {
        $("#new-note a").click(function() {
            $(".list-group-item.selected").removeClass("selected");
            $(".list-group-item a.selected").removeClass("selected");
            $("#new-note").after(
                    '<li class="list-group-item selected" article-category="1" is-main-page="1" draft-article-id="'
                    + draftArray.length + '"><span class="article-status">[草稿]</span>' +
                    '<a href="#" class="selected">无标题文章</a></li>'
            );
            $("#title").val('无标题文章');
            tinymce.activeEditor.setContent('');
            var draftArticle = {};
            draftArticle.title = '无标题文章';
            draftArticle.content = '';
            draftArray.push(draftArticle);
        });
        $("#title").keyup(function() {
            $(".list-group-item.selected a.selected").text($(this).val());
        });
        $(".col-sm-3.article-list").on("click", ".list-group-item", function() {
            if ($(this).hasClass("selected")) {
                return;
            }
            if (!$(".list-group-item.selected").attr("article-id")) {
                var draftArticle = {};
                draftArticle.title = $("#title").val();
                draftArticle.content = tinymce.activeEditor.getContent();
                draftArray[$(".list-group-item.selected").attr("draft-article-id")] = draftArticle;
            }
            $(".list-group-item.selected").removeClass("selected");
            $(".list-group-item a.selected").removeClass("selected");
            $(this).addClass("selected");
            $("a", this).addClass("selected");
            loadArticle($(this), false);
        });
        loadArticle($(".list-group-item.selected"), true);
    });

    function loadArticle($item, isInit) {
        if (!$item) {
            return;
        }
        if ($item.attr("draft-article-id")) {
            $("#title").val(draftArray[$item.attr("draft-article-id")].title);
            tinymce.activeEditor.setContent(draftArray[$item.attr("draft-article-id")].content);
            return;
        }
        $.ajax({
            url: "${pageContext.request.contextPath}/Article/Write/" + $item.attr("article-id"),
            dataType: "json",
            success: function(data) {
                if (!data || !data.userArticle) {
                    alert("获取文章发生错误!");
                } else {
                    $("#title").val(data.userArticle.title);
                    if (isInit) {
                        tinymceSettings.content = data.userArticle.content;
                        tinymceInit(tinymceSettings);
                    } else {
                        tinymce.activeEditor.setContent(data.userArticle.content);
                    }
                }
            }
        });
    }
</script>
</body>
</html>


