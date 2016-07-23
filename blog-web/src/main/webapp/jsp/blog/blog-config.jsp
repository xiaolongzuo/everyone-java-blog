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
  User: linjiedeng
  Time: 16/6/5 18:58
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <jsp:include page="../common/head.jsp"/>
</head>
<body>
<jsp:include page="../common/top.jsp"/>
<div class="container">
    <jsp:include page="../common/index-header.jsp"/>
    <div class="row">
        <div class="col-sm-8 blog-main auto-validate">
            <ul class="nav nav-tabs" id="config-tab" role="tablist">
                <li class="self-config ${'self-config' eq active? 'active':''}" role="presentation">
                    <a href="#self-config" aria-controls="self-config" role="tab"
                       data-toggle="tab">个人资料</a>
                </li>
                <li class="change-password ${'change-password' eq active? 'active':''}" role="presentation">
                    <a href="#change-password" class="" aria-controls="change-password" role="tab"
                       data-toggle="tab">修改密码</a>
                </li>
            </ul>
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane ${'self-config' eq active? 'active':''}" id="self-config">
                    <form action="${pageContext.request.contextPath}/WebBlog/Update/Config" method="post">
                        <div class="form-group">
                            <label>博客名称</label><br/>
                            <input required="required" data-error="包含非法字符" name="blogTitle" value="${result.data.blogTitle}" data-remote="${pageContext.request.contextPath}/WebUser/CheckUsername" placeholder="请填写博客名称" style="width: 100%"/><br/>
                        </div>
                        <div class="form-group">
                            <label>博客子标题</label><br/>
                            <textarea required="required" name="blogSubTitle" cols="20" rows="2" placeholder="请填写博客子标题" style="width: 100%">${result.data.blogSubTitle}</textarea><br/>
                        </div>
                        <div class="form-group">
                            <label>个人简介</label><br/>
                            <textarea name="introduction" cols="60" rows="10" style="width: 100%">${result.data.introduction}</textarea><br/>
                        </div>
                        <input type="submit" class="btn btn-info" value="修改"/>
                    </form>
                </div>

                <div role="tabpanel" class="tab-pane ${'change-password' eq active? 'active':''}" id="change-password">
                    <form action="${pageContext.request.contextPath}/WebUser/ModifyPassword" method="post">
                        <div class="form-group">
                            <label for="oldPassword">当前密码</label>
                            <input pattern="^[_A-z0-9]{6,36}$" type="password" data-error="密码为空或格式不正确" class="form-control" name="oldPassword" id="oldPassword" placeholder="只能输入6-36位的数字,字母,下划线" required>
                            <div class="help-block with-errors"></div>
                        </div>
                        <div class="form-group">
                            <label for="newPassword">新密码</label>
                            <input pattern="^[_A-z0-9]{6,36}$" type="password" data-error="密码为空或格式不正确" class="form-control" name="newPassword" id="newPassword" placeholder="只能输入6-36位的数字,字母,下划线" required>
                            <div class="help-block with-errors"></div>
                        </div>
                        <div class="form-group">
                            <label for="repeatPassword">确认密码</label>
                            <input data-match="#newPassword" data-match-error="输入的密码不一致" type="password" class="form-control" name="repeatPassword" id="repeatPassword" placeholder="请再次输入密码" required>
                            <div class="help-block with-errors"></div>
                        </div>
                        <div class="form-group">
                            <p style="color: red; font-size: 14px;">&nbsp;${error}</p>
                        </div>
                        <button type="submit" class="btn btn-info" style="margin-bottom: 20px;">修改</button>
                    </form>
                </div>
            </div>

        </div><!-- /.blog-main -->
    </div><!-- /.row -->
</div><!-- /.container -->
<jsp:include page="../common/footer.jsp"/>
<jsp:include page="../common/bottom.jsp"/>
</body>
</html>
