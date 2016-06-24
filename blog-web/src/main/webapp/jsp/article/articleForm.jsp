<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
  User: goozi
  Date: 2016/6/11
  Time: 21:46
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@include file="../common/head.jsp" %>
    <script src='${ctx}/lib/tinymce/tinymce.min.js'></script>
    <script src='${ctx}/lib/tinymce/jquery.tinymce.min.js'></script>
    <style type="text/css">
        .select2-container .select2-selection {
            height: 34px;
        }

        div#mceu_21 {
            padding-right: 1px;
        }

        span.status {
            font-size: 14px;
        }
    </style>

    <script type="text/javascript">
        $(document).ready(function () {
            $("select").select2();

        });

        tinymce.init({
            selector: 'textarea#content',
            height: 300,
            language: "zh_CN",
            plugins: [
                'advlist autolink lists link image charmap print preview hr anchor pagebreak',
                'searchreplace visualblocks visualchars code fullscreen',
                'insertdatetime media nonbreaking save table contextmenu directionality',
                'emoticons template paste textcolor colorpicker textpattern imagetools'
            ],
            toolbar1: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
            toolbar2: 'print preview media | forecolor backcolor emoticons'
        });
    </script>
</head>

<body>
<%@include file="../common/top.jsp" %>
<div class="container">
    <%@include file="../common/blog_header.jsp" %>
    <div class="row">
        <div class="col-sm-8 blog-main">
            <form:form id="inputForm" action="${ctx}/webArticle/save" modelAttribute="userArticle" method="post"
                       cssClass="form-horizontal">
                <form:hidden path="id"/>
                <div class="form-group">
                    <label for="categoryId">文章类别:</label>
                    <form:select path="categoryId" cssClass="form-control">
                        <form:option value="" label=""/>
                        <form:options items="${articleCategories}" itemLabel="categoryName" itemValue="id"
                                      htmlEscape="false"/>
                    </form:select>
                </div>
                <div class="form-group">
                    <label for="title">文章标题:</label>
                    <form:input path="title" cssClass="form-control" htmlEscape="false" maxlength="60"/>
                </div>
                <div class="form-group">
                    <label for="title">文章内容:</label>
                    <form:textarea id="content" htmlEscape="true" path="content" rows="5" class="form-control"/>
                </div>
                <div class="form-group">
                    <label for="status">状态:</label>
                    <form:radiobutton path="status" htmlEscape="false" value="0" cssClass="radiobutton"/><span
                        class="status">发布</span>
                    <form:radiobutton path="status" htmlEscape="false" value="1" cssClass="radiobutton"/><span
                        class="status">草稿</span>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-4">
                        <input id="btnSubmit" type="submit" class="btn btn-success" value="保存"/>
                        <input id="btnCancel" type="button" class="btn btn-default" value="返回"
                               onclick="history.go(-1)"/>
                    </div>
                </div>
            </form:form>
        </div><!-- /.blog-main -->
        <%@include file="../common/contributor.jsp" %>

    </div><!-- /.row -->
</div><!-- /.container -->
<%@include file="../common/footer.jsp" %>
<%@include file="../common/bottom.jsp" %>
</body>
</html>
