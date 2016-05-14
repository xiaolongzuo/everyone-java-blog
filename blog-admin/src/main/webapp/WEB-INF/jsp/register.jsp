<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/5/13
  Time: 23:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form id="editForm" name="editForm" action="http://localhost:8080/register"
      class="form-horizontal" method="post" accept-charset="UTF-8"
      enctype="application/x-www-form-urlencoded" data-option="edit">
    用户名：<input type="text" name="userName">
    密码：<input type="password" name="passWork">
    再次输入密码：<input type="password" name="confimPassWork">
    <input type="submit" value="提交">
</form>
</body>
</html>
