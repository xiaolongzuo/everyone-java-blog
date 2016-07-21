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
    var tinymceSettings = {width:800,height:400,content:''};
    var newNoteHtml = '<li class="list-group-item selected" article-category="1" is-main-page="1" ><span class="article-status">[草稿]</span><a href="#" class="selected">无标题文章</a></li>';
    var newNoteTitle = '无标题文章';
    var newNoteContent = '';

    $(document).ready(function() {
        $("#new-note a").click(function() {
            $(".list-group-item.selected").removeClass("selected");
            $(".list-group-item a.selected").removeClass("selected");

            $("#new-note").after(newNoteHtml);
            $("#title").val(newNoteTitle);
            tinymce.activeEditor.setContent(newNoteContent);

            createOrUpdateArticle('create');
        });
        $("#title").keyup(function() {
            $(".list-group-item.selected a.selected").text($(this).val());
        });
        $(".col-sm-3.article-list").on("click", ".list-group-item", function() {
            if ($(this).hasClass("selected")) {
                return;
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
        if (!$item || $item.length == 0) {
            tinymceInit(tinymceSettings);
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

    function tinymceInit(settings) {
        $(document).ready(function() {
            var defaultSettings = {width:600,height:400,content:'',skin:'lightgray'};
            $.extend(defaultSettings,settings);
            tinymce.init({
                selector: "textarea.html_editor",
                language: "zh_CN",
                menubar : false,
                skin: defaultSettings.skin,
                width: defaultSettings.width,
                height: defaultSettings.height,
                content_css: contextPath + '/css/content.css',
                toolbar_items_size:'medium',
                setup: function(editor) {
                    editor.addButton('upload',
                            {
                                icon: 'print',
                                title: '上传本地图片',
                                onclick: function() {
                                    openTinymceWindow(editor, "上传本地图片", "/jsp/article/upload-image.jsp", 400, 150);
                                }
                            });
                    editor.addButton('insertcode',
                            {
                                icon: 'paste',
                                title: '插入代码',
                                onclick: function() {
                                    openTinymceWindow(editor, "插入代码", "/jsp/article/insert-code.jsp", 800, 400);
                                }
                            });
                    editor.addButton('settings',
                            {
                                text: '文章设置',
                                title: '文章设置',
                                onclick: function() {
                                    openTinymceWindow(editor, "文章设置", "/jsp/article/article-settings.jsp", 800, 200);
                                }
                            });
                    editor.addButton('save',
                            {
                                text: '保存文章',
                                title: '保存文章',
                                onclick: function() {
                                    createOrUpdateArticle('update');
                                }
                            });
                    editor.addButton('publish',
                            {
                                title: '发布文章',
                                text: '发布文章',
                                onclick: function() {
                                    createOrUpdateArticle('update', '1');
                                }
                            });
                    editor.on('init', function(e) {
                        if (defaultSettings.content) {
                            editor.setContent(defaultSettings.content);
                        }
                    });
                },
                plugins: [
                    "advlist autolink lists link image charmap print preview anchor textcolor",
                    "searchreplace visualblocks code fullscreen",
                    "insertdatetime media table contextmenu paste emoticons"
                ],
                toolbar: ["undo redo | styleselect bold italic forecolor backcolor | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | code preview fullscreen ",
                    "link upload image insertcode table blockquote media emoticons | settings | save | publish"]
            });
        });
    }

    function openTinymceWindow(editor, title, url, width, height) {
        editor.windowManager.open({
            title: title,
            url: contextPath + url,
            width: width,
            height: height
        });
    }

    function createOrUpdateArticle(action, status) {
        var param = {
            "categoryId":$(".list-group-item.selected").attr("article-category"),
            "isMainPage":$(".list-group-item.selected").attr("is-main-page"),
            "title":$("#title").val(),
            "content":tinymce.activeEditor.getContent()
        };
        if (action == 'create') {
            $.ajax({
                url: contextPath + "/Article/Create",
                type: "post",
                data: param,
                dataType: "json",
                success: function(result) {
                    if (result.code == 200) {
                        $(".list-group-item.selected").attr("article-id", result.data);
                    } else {
                        alert(result.message);
                    }
                }
            });
        } else if (action == 'update') {
            param.id = $(".list-group-item.selected").attr("article-id");
            var message = "保存成功!";
            if (status) {
                param.status = status;
                message = "发表成功!";
            }
            $.ajax({
                url: contextPath + "/Article/Update",
                type: "post",
                data: param,
                dataType: "json",
                success: function(data) {
                    if (data.code == 200) {
                        if (status) {
                            $(".list-group-item.selected span.article-status").text("[已发布]");
                        }
                        alert(message);
                    } else {
                        alert(data.message);
                    }
                }
            });
        }
    }
</script>
</body>
</html>


