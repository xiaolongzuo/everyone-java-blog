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
  Time: 16/7/19 01:25
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="zh_CN">
    <jsp:include page="../common/head.jsp"/>
    <style type="text/css">
        body {
            overflow: hidden;
        }
    </style>
    <title></title>
</head>
<body>
<form id="upload_image_form" action="${pageContext.request.contextPath}/File/Upload" method="post" enctype="multipart/form-data">
    <div class="form-group" style="padding: 10px;">
        <div class="form-group">
            <input type="file" name="imageFile" id="imageFile" style="font-size: 10px;">
            <p class="help-block">只支持jpg,png,gif格式的文件</p>
        </div>
        <button type="submit" class="btn btn-default btn-sm">上传文件</button>
    </div>
</form>
<jsp:include page="../common/bottom.jsp"/>
<script type="text/javascript">
    $(document).ready(function () {
        $("#upload_image_form").ajaxForm({
            beforeSubmit: function () {
                if (!$("input[name=imageFile]").val()) {
                    window.parent.alert("请选择图片");
                    return false;
                }
                return true;
            },
            success: function (url) {
                if (url == 'error') {
                    alert("上传图片失败!");
                } else {
                    top.tinymce.activeEditor.insertContent("<img src='" + url + "'/>");
                    top.tinymce.activeEditor.windowManager.close();
                }
            }
        });
    });
</script>
</body>
</html>
