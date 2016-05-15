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
  User: cnJun
  Time: 16/5/22 22:22
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form id="editForm" name="editForm" action="http://localhost:8080/contributor/insert"
      class="form-horizontal" method="post" accept-charset="UTF-8"
      enctype="application/x-www-form-urlencoded" data-option="edit">
    <p>贡献者名称：<input type="text" name="contributorName"></p>
    <p>贡献者地址：<input type="text" name="personalUrl"></p>

    <input type="submit" value="提交">
</form>
</body>
</html>
