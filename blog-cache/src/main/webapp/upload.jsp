<%--
  Created by IntelliJ IDEA.
  User: Jilinwula
  Date: 2016/5/17
  Time: 下午 04:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="http://127.0.0.1:8083/cache/upload" method="post" enctype="multipart/form-data">
    用户编号:<input type="text" name="uid">
    选择文件:<input type="file" name="file">
    选择文件:<input type="file" name="file">
    <input type="submit" value="提交">
</form>
</body>
</html>
