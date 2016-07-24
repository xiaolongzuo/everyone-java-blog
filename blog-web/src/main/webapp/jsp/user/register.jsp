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
  Time: 16/6/11 02:51
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@include file="../common/head.jsp"%>
</head>
<body>
<%@include file="../common/top.jsp"%>
<div class="container">
    <%@include file="../common/index-header.jsp"%>
    <div class="row">
        <div class="col-sm-8 blog-main auto-validate">
            <form action="${pageContext.request.contextPath}/WebUser/Register" method="post">
                <div class="form-group">
                    <label for="username">用户名</label>
                    <input pattern="^[_A-z0-9]{6,36}$" type="text" data-remote="${pageContext.request.contextPath}/WebUser/CheckUsername" data-error="用户名为空或格式不正确或已存在" class="form-control" name="username" id="username" placeholder="只能输入6-36位的数字,字母,下划线" required>
                    <div class="help-block with-errors"></div>
                </div>
                <div class="form-group">
                    <label for="password">密码</label>
                    <input pattern="^[_A-z0-9]{6,36}$" type="password" data-error="密码为空或格式不正确" class="form-control" name="password" id="password" placeholder="只能输入6-36位的数字,字母,下划线" required>
                    <div class="help-block with-errors"></div>
                </div>
                <div class="form-group">
                    <label for="repeatPassword">确认密码</label>
                    <input data-match="#password" data-match-error="输入的密码不一致" type="password" class="form-control" name="repeatPassword" id="repeatPassword" placeholder="请再次输入密码" required>
                    <div class="help-block with-errors"></div>
                </div>
                <div class="form-group">
                    <p style="color: red; font-size: 14px;">&nbsp;${error}</p>
                </div>
                <button type="submit" class="btn btn-info" style="margin-bottom: 20px;">注册</button>
            </form>
        </div><!-- /.blog-main -->
    </div><!-- /.row -->
</div><!-- /.container -->
<%@include file="../common/footer.jsp"%>
<%@include file="../common/bottom.jsp"%>
<script type="application/javascript">
    $(document).ready(function() {
        $("#username").keyup(function() {
            $(this).val($.trim($(this).val()));
        });
    });
</script>
</body>
</html>
