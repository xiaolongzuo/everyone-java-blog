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
  Time: 16/1/15 02:04
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery/jquery.form.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/bootstrap-validator/validator.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/ie10-viewport-bug-workaround.js"></script>
<!-- forbidden backspace -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/forbidden.backspace.js"></script>
<!-- tinymce -->
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/shbrush/js/shCore.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/shbrush/js/shBrushAppleScript.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/shbrush/js/shBrushAS3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/shbrush/js/shBrushBash.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/shbrush/js/shBrushColdFusion.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/shbrush/js/shBrushCpp.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/shbrush/js/shBrushCSharp.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/shbrush/js/shBrushCss.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/shbrush/js/shBrushDelphi.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/shbrush/js/shBrushDiff.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/shbrush/js/shBrushErlang.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/shbrush/js/shBrushGroovy.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/shbrush/js/shBrushJava.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/shbrush/js/shBrushJScript.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/shbrush/js/shBrushJavaFX.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/shbrush/js/shBrushPerl.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/shbrush/js/shBrushPhp.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/shbrush/js/shBrushPlain.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/shbrush/js/shBrushJava.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/shbrush/js/shBrushPowerShell.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/shbrush/js/shBrushPython.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/shbrush/js/shBrushRuby.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/shbrush/js/shBrushSass.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/shbrush/js/shBrushScala.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/shbrush/js/shBrushSql.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/shbrush/js/shBrushVb.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/shbrush/js/shBrushXml.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/tinymce/tinymce.min.js"></script>
<!-- init script -->
<script type="application/javascript">
    SyntaxHighlighter.all();
    $(document).ready(function() {
        $(".auto-validate").validator();
    });
    /*
    $(document).on('focusin', function(e) {
        if ($(e.target).closest(".mce-window").length) {
            e.stopImmediatePropagation();
        }
    });
    */
</script>