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
        <label for="language_select" class="col-sm-2 control-label">语言:</label>
        <div class="col-sm-10">
            <select name="language" id="language_select" class="form-control" style="width: 200px;">
                <option value="applescript">AppleScript</option>
                <option value="as3">AS3</option>
                <option value="bash">Bash</option>
                <option value="coldfusion">ColdFusion</option>
                <option value="cpp">C++</option>
                <option value="csharp">C#</option>
                <option value="css">Css</option>
                <option value="delphi">Delphi</option>
                <option value="diff">Diff</option>
                <option value="erlang">Erlang</option>
                <option value="groovy">Groovy</option>
                <option value="java" selected="selected">Java</option>
                <option value="javafx">JavaFX</option>
                <option value="js">JScript</option>
                <option value="perl">Perl</option>
                <option value="php">Php</option>
                <option value="plain">Plain</option>
                <option value="powershell">PowerShell</option>
                <option value="python">Python</option>
                <option value="ruby">Ruby</option>
                <option value="sass">Sass</option>
                <option value="scala">Scala</option>
                <option value="sql">Sql</option>
                <option value="vb">Vb</option>
                <option value="xml">Xml</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <div class="checkbox">
                <label>
                    <input id="is_collapse_input" type="checkbox" name="isCollapse"> 是否折叠
                </label>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label for="code_textarea" class="col-sm-2 control-label">代码:</label>
        <div class="col-sm-10">
            <textarea id="code_textarea" class="form-control" rows="10" style="width: 600px;"></textarea>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="button" id="insert_code_button" class="btn btn-default">确定</button>
        </div>
    </div>
</form>
<jsp:include page="../common/bottom.jsp"/>
<script type="text/javascript">
    $(document).ready(function(){
        $("#insert_code_button").click(function(){
            var content = '<pre class="brush: ' + $("#language_select").val() + '; ';
            content = content + 'collapse :' + $("#is_collapse_input").is(":checked") + ';';
            content = content + 'gutter :true;">';
            content = content + $("#code_textarea").val() + '</pre><br/>';
            top.tinymce.activeEditor.insertContent(content);
            top.tinymce.activeEditor.windowManager.close();
        });
    });
</script>
</body>
</html>

