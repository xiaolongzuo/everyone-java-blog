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
  User: iCodingStar
  Time: 16/6/4 16:26
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <jsp:include page="../common/head.jsp"/>
    <link rel="stylesheet" href="../../css/nav.css">
</head>

<body>
<jsp:include page="../common/top.jsp"/>
<div class="container">
    <jsp:include page="../common/index_header.jsp"/>
    <div class="row">
        <div class="col-sm-8 message-main">
            <ul class="nav nav-tabs" id="message-tab" role="tablist">
                <li class="message-receive active" role="presentation">
                    <a href="#message-receive" aria-controls="message-receive" role="tab"
                       data-toggle="tab">收件箱</a>
                </li>
                <li class="message-send" role="presentation">
                    <a href="#message-send" class="" aria-controls="message-send" role="tab"
                       data-toggle="tab">发件箱</a>
                </li>
                <li class="message-unread" role="presentation">
                    <a href="#message-unread" class="" aria-controls="message-unread" role="tab"
                       data-toggle="tab">未读消息</a>
                </li>
                <li class="message-content" style="display: none" role="presentation">
                    <a href="#message-content" class="" aria-controls="" role="tab"
                       data-toggle="tab">消息详情</a>
                </li>
                <li class="message-write" role="presentation">
                    <a href="#message-write" class="" aria-controls="" role="tab"
                       data-toggle="tab">撰写消息</a>
                </li>
                <li class="message-export" style="display: none" role="presentation">
                    <a href="#message-export" class="" aria-controls="" role="tab"
                       data-toggle="tab">导出消息</a>
                </li>
            </ul>
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="message-receive">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <table class="table table">
                                <tr>
                                    <th>状态</th>
                                    <th>发件人</th>
                                    <th>标题</th>
                                    <th>时间</th>
                                    <th>处理</th>
                                </tr>
                            </table>

                            <nav class="page-nav">
                                <ul class="pagination pagination-lg">
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane" id="message-send">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <table class="table table">
                                <tr>
                                    <th>状态</th>
                                    <th>收件人</th>
                                    <th>标题</th>
                                    <th>时间</th>
                                    <th>处理</th>
                                </tr>
                            </table>

                            <nav class="page-nav">
                                <ul class="pagination pagination-lg">
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane" id="message-unread">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <table class="table table">
                                <tr>
                                    <th>状态</th>
                                    <th>发件人</th>
                                    <th>标题</th>
                                    <th>时间</th>
                                    <th>处理</th>
                                </tr>
                            </table>

                            <nav class="page-nav">
                                <ul class="pagination pagination-lg">
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane" id="message-content">
                    <div class="panel panel-default">
                        <div class="panel panel-heading">
                            <div>
                                <span>标题:</span>
                                <span class="title"></span>
                            </div>
                            <div>
                                <span>收件人:</span>
                                <span class="receiver"></span>
                            </div>
                            <div>
                                <span>发信人:</span>
                                <span class="sender"></span>
                            </div>
                        </div>
                        <div class="panel panel-body">
                            <div class="content"></div>
                        </div>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane" id="message-write">
                    <form class="form-horizontal" role="form" style="margin-top: 15px">
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="receiver">收件人</label>
                            <div class="col-sm-6">
                                <input class="form-control" id="receiver" type="text" placeholder=""/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="title">标题</label>
                            <div class="col-sm-6">
                                <input class="form-control" id="title" type="text" placeholder=""/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="content">内容</label>
                            <div class="col-sm-10 ">
                            <textarea class="form-control" id="content" style="width: 100%;height: 200px;" tabindex="1"
                                      id="txtContent"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="send-message"></label>
                            <div class="col-sm-3">
                                <input class="form-control" id="send-message" type="button" value="发送"/>
                            </div>
                        </div>
                    </form>
                </div>
                <div role="tabpanel" class="tab-pane" id="message-export">
                </div>
            </div>
        </div>
        <jsp:include page="../common/contributor.jsp"/>
    </div><!-- /.row -->
</div><!-- /.container -->
<jsp:include page="../common/footer.jsp"/>
<jsp:include page="../common/bottom.jsp"/>
<script src="${pageContext.request.contextPath}/js/message.js"></script>
</body>
</html>


