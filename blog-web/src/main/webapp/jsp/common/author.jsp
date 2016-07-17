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
  Time: 16/6/5 17:21
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/taglib.jsp"%>
<div class="col-sm-3 col-sm-offset-1 blog-sidebar">
    <div class="sidebar-module sidebar-module-inset">
        <h4>个人介绍</h4>
        <p>${result.data.blogConfig.introduction}</p>
    </div>
    <div class="sidebar-module">
        <h4>最热文章</h4>
        <ol class="list-unstyled">
            <c:choose>
                <c:when test="${result.data.userHotestArticleList!=null && fn:length(result.data.userHotestArticleList) > 0}">
                    <c:forEach var="article" items="${result.data.userHotestArticleList}" varStatus="index">
                        <li><a href="javascript:goArticle(${article.id})">${article.title}</a></li>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    没有热门文章
                </c:otherwise>
            </c:choose>
        </ol>
    </div>
</div><!-- /.blog-sidebar -->
