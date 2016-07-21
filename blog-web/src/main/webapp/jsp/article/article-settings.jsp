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
  Time: 16/7/19 01:18
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../common/head.jsp"/>
    <title>插入代码</title>
    <style>
        body { overflow: hidden;}
    </style>
</head>
<body>
<form class="form-horizontal" style="margin: 20px 0px;">
    <div class="form-group">
        <label for="article-category" class="col-sm-2 control-label">文章种类:</label>
        <div class="col-sm-10">
            <select id="article-category" class="form-control" style="width: 200px;">
                <option value="1">技术</option>
                <option value="2">职场</option>
                <option value="3">人生</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <div class="checkbox">
                <label>
                    <input id="is_main_page" type="checkbox"> 是否发布到首页
                </label>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="button" id="save_settings_button" class="btn btn-default">保存设置</button>
        </div>
    </div>
</form>
<jsp:include page="../common/bottom.jsp"/>
<script type="text/javascript">
    $(document).ready(function() {
        $("#article-category option").each(function() {
            if ($(this).val() == $(".list-group-item.selected", top.document).attr("article-category")) {
                $(this).attr("selected", true);
            }
        });
        if ($(".list-group-item.selected", top.document).attr("is-main-page") == 1) {
            $("#is_main_page").attr("checked", true);
        } else {
            $("#is_main_page").attr("checked", false);
        }

        $("#save_settings_button").click(function() {
            $(".list-group-item.selected", top.document).attr("article-category", $("#article-category").val());
            if ($("#is_main_page").is(":checked")) {
                $(".list-group-item.selected", top.document).attr("is-main-page", "1");
            } else {
                $(".list-group-item.selected", top.document).attr("is-main-page", "0");
            }
            top.tinymce.activeEditor.windowManager.close();
        });
    });
</script>
</body>
</html>

