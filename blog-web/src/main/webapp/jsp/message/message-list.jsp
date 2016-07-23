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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/nav.css">
</head>
<body>
<jsp:include page="../common/top.jsp"/>
<div class="container">
    <jsp:include page="../common/index-header.jsp"/>
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
                <div role="tabpanel" class="tab-pane auto-validate" id="message-write" >
                    <form class="form-horizontal" role="form" style="margin-top: 15px">
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="receiver">收件人</label>
                            <div class="col-sm-6">
                                <input class="form-control" id="receiver" name="receiver" onblur="check_receiver_input(event)" pattern="^[_A-z0-9]{1,36}$" data-error="收件人为空或格式不正确" type="text" placeholder="联系人账号或者用户名" required/>
                            </div>
                            <div id="receiver_tips" class="help-block with-errors" style="color: #a94442;"></div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="title">标题</label>
                            <div class="col-sm-6">
                                <input class="form-control" id="title" name="title" onblur="check_title_input(event)" data-error="标题不能为空" type="text" placeholder="短信息标题" required/>
                            </div>
                            <div id="title_tips" class="help-block with-errors" style="color: #a94442;"></div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="content">内容</label>
                            <div class="col-sm-10 ">
                            <textarea class="form-control" id="content" onblur="check_content_input()" style="width: 100%;height: 200px;" tabindex="1"
                                      id="txtContent" placeholder="短信息内容" data-error="短信息内容不能为空" required></textarea>
                                <div id="content_tips" class="help-block with-errors" style="color: #a94442;"></div>
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
    </div><!-- /.row -->
</div><!-- /.container -->
<jsp:include page="../common/footer.jsp"/>
<jsp:include page="../common/bottom.jsp"/>
<script>
    /**
     * @author iCodingStar
     * @date 2016/6/27 10:14
     * @version 1.0
     */
    var MESSAGE_RECEIVE = 0;
    var MESSAGE_SEND = 1;
    var MESSAGE_UNREAD = 2;
    var MESSAGE_STATUS_READ = 0;
    var MESSAGE_STATUS_UNREAD = 1;
    var MESSAGE_STATUS_DELETE = 2;
    var MESSAGE_STATUS = ['已读', '未读', '已删除'];

    $(function () {
        var pageSize = 10;
        var pageNumber = 0;

        $("#send-message").attr('disabled', true);//禁用发送按钮

        //获取收件箱中的消息
        parseReceiveMessages(pageNumber, pageSize, MESSAGE_RECEIVE, null);

        //获取收件箱中的消息
        $(".message-receive").on('click', function () {
            $(".message-content").css("display", "none");
            $("#message-content").css("display", "none");
            parseReceiveMessages(pageNumber, pageSize, MESSAGE_RECEIVE, null);
        });

        //获取发件箱中的短消息
        $(".message-send").on('click', function () {
            $(".message-content").css("display", "none");
            $("#message-content").css("display", "none");
            parseSendMessages(pageNumber, pageSize, MESSAGE_SEND, null);
        });

        //获取收件箱中的未读消息
        $(".message-unread").on('click', function () {
            $(".message-content").css("display", "none");
            $("#message-content").css("display", "none");
            parseUnreadMessages(pageNumber, pageSize, MESSAGE_RECEIVE, MESSAGE_STATUS_UNREAD);
        });

        //发消息
        $("#send-message").on('click', function () {
            $(".message-content").css("display", "none");
            $("#message-content").css("display", "none");
            var username = $("#message-write #receiver").val();
            var title = $("#message-write #title").val();
            var content = $('#message-write #content').val();

            postMessageDataAndParse(contextPath + "/MessageBox/Send", {
                username: username,
                title: title,
                content: content,
                status: 0
            }, function (result) {
                if (result.data == 1) {
                    console.table(result);
                    $(".message-send a").trigger("click");
                }else {
                    console.log("发送失败！");
                }
            });
        });
    });

    /***
     * 显示消息详情
     *
     * @param id
     */
    function read_message_content(id) {
        getMessageDataAndParse(contextPath + "/MessageBox/Content", {
            id: id
        }, function (result) {
            var message = result.data.message;
            var sender = result.data.sender;
            var receiver = result.data.receiver;
            $(".message-content").css('display', 'block');
            $("#message-content").css("display", "block");
            $("#message-content .title").text(message.title);
            $("#message-content .sender").text(sender.username);
            $("#message-content .receiver").text(receiver.username);
            $("#message-content .content").text(message.content);
            $(".message-content a").trigger("click");
        });
    }

    /***
     * 更新短消息状态
     *
     * @param id
     * @param status
     */
    function update_message_status(id, status) {
        getMessageDataAndParse(contextPath + "/MessageBox/Update", {
            id: id,
            status: status
        }, function (result) {
            console.log("更新成功");
        });
    }
    /***
     * 检查内容是否合法
     */
    function check_content_input(event) {
        $("#send-message").attr('disabled', false);//解禁发送按钮
        var content_value = $('#content').val();
        if (content_value.length < 6){
            $('#content_tips').text("短信息内容不少于6个字符!");
            $("#send-message").attr('disabled', true);//禁用发送按钮
        }else if (content_value.length > 2000){
            $('#content_tips').text("短信息内容不超过512个字符！");
            $("#send-message").attr('disabled', true);//禁用发送按钮
        }
    }
    /***
     *检查标题是否合法
     */
    function check_title_input(event) {
        $("#send-message").attr('disabled', false);//解禁发送按钮
        var title_value = $('#title').val();
        if (title_value.length < 2){
            $('#title_tips').text("短信息标题不少于2个字符!");
            $("#send-message").attr('disabled', true);//禁用发送按钮
        }else if (title_value.length > 128){
            $('#title_tips').text("短信息标题不超过128个字符！");
            $("#send-message").attr('disabled', true);//禁用发送按钮
        }
    }
    /***
     * 检查收件人是否合法
     */
    function check_receiver_input(event) {
        var username = $("#message-write #receiver").val();
        checkUser(username);
    }

    /***
     * 判断收件人是否存在
     * @param username
     * @param receiver
     * @returns {boolean}
     */
    function checkUser(username) {
        $.ajax({
            url: contextPath + "/WebUser/CheckUsername",
            dataType: "json",
            data: {username:username},
            type: "GET",
            cache: false,
            success: function (result) {
                $('#receiver_tips').text("用户不存在！");
                $("#send-message").attr('disabled', true);//禁用发送按钮
                console.log("用户不存在！");
            },
            error: function (result) {
                $('#receiver_tips').text(" ");//提示文本置空
                $("#send-message").attr('disabled', false);//解禁发送按钮
                console.log("用户存在！");
            }
        });
    }

    /***
     *  获取数据并解析
     *
     * @param url
     * @param data
     */
    function getMessageDataAndParse(url, data, callback) {
        $.ajax({
            url: url,
            dataType: "json",
            data: data,
            type: "GET",
            cache: false,
            success: function (result) {
                callback(result);
            },
            error: function () {
                console.log("获取消息失败！");
            }
        });
    }


    /***
     * 向服务器发送数据并显示反馈信息
     *
     * @param url
     * @param data
     * @param callback
     */
    function postMessageDataAndParse(url, data, callback) {
        $.ajax({
            url: url,
            dataType: "json",
            data: data,
            type: "POST",
            cache: false,
            success: function (result) {
                callback(result);
            },
            error: function () {
                console.log("获取消息失败！");
            }
        });
    }


    //解析收件箱短信数据
    function parseReceiveMessages(currentPageNumber, pageSize, type, status) {
        //从服务器端获取数据并解析
        getMessageDataAndParse(contextPath + "/MessageBox/List", {
            currentPageNumber: currentPageNumber,
            pageSize: pageSize,
            type: type,
            status: status
        }, function (result) {
            $("#message-receive .table tr:not(:first)").remove();
            if (result == null) {
                return;
            }
            var node = "";
            /***
             * 修改短消息状态
             * 0:已读
             * 1:未读
             * 2:已删除
             *
             * @param messageBoxDto
             * @return
             */

            $.each(result.data.data, function (index, item) {
                var webUserName = item.sender.username;
                var webUserId = item.sender.id;
                node += "<tr><td>" + MESSAGE_STATUS[item.message.status] + "</td>"
                        + "<td> <a href='#author' >" + webUserName + "</a></td>"
                        + "<td> <a href='#message-content' onclick='read_message_content(" + item.message.id + ")'>" + item.message.title + "</a></td>"
                        + "<td>" + item.message.createTime + "</td>";
                var params = item.message.id + "," + MESSAGE_STATUS_DELETE;
                node += "<td>" + "<a href='#' onclick='update_message_status(" + params + ")'>" + "删除" + "</a>" + "<td>";
            });
            $("#message-receive .table").append(node);

            //分页
            $("#message-receive .page-nav .pagination li").remove();
            var page = result.data;
            var message = page.data;
            var paramStr = "";
            if (page.totalCount == 0){
                var info = "<li><span>暂无消息</span></li>";
                $("#message-receive .page-nav .pagination").append(info);
            }
            if (page.totalPageNumber > 1) {
                if (page.currentPageNumber > 1) {
                    paramStr += page.currentPageNumber - 1 + "," + page.pageSize + "," + MESSAGE_RECEIVE + "," + null;
                    var prevPage = "<li><span aria-hidden='true' onclick=parseReceiveMessages(" + paramStr + ")>上一页</span></li>";
                    $("#message-receive .page-nav .pagination").append(prevPage);
                    paramStr = "";
                }
                for (var i = 1; i <= page.totalPageNumber; i++) {
                    paramStr += i + "," + page.pageSize + "," + MESSAGE_RECEIVE + "," + null;
                    var pageNav = "";
                    if (page.currentPageNumber == i) {
                        pageNav = "<li class='active'><span aria-hidden='true' onclick=parseReceiveMessages(" + paramStr + ")>" + i + "</span></li>"
                    } else {
                        pageNav = "<li><span aria-hidden='true' onclick=parseReceiveMessages(" + paramStr + ")>" + i + "</span></li>";
                    }
                    $("#message-receive .page-nav .pagination").append(pageNav);
                    paramStr = ""
                }
                if (page.currentPageNumber < page.totalPageNumber) {
                    paramStr += page.currentPageNumber + 1 + "," + page.pageSize + "," + MESSAGE_RECEIVE + "," + null;
                    var nextPage = "<li><span aria-hidden='true' onclick='parseReceiveMessages(" + paramStr + ")'>下一页</span></li>";
                    $("#message-receive .page-nav .pagination").append(nextPage);
                    paramStr = "";
                }
            }
        });
    }

    //解析发件箱短信数据
    function parseSendMessages(currentPageNumber, pageSize, type, status) {
        //从服务器端获取数据并解析
        getMessageDataAndParse(contextPath + "/MessageBox/List", {
            currentPageNumber: currentPageNumber,
            pageSize: pageSize,
            type: type,
            status: status
        }, function (result) {
            $("#message-send .table tr:not(:first)").remove();
            if (result == null) {
                return;
            }
            var node = "";
            /***
             * 修改短消息状态
             * 0:已读
             * 1:未读
             * 2:已删除
             *
             * @param messageBoxDto
             * @return
             */

            $.each(result.data.data, function (index, item) {
                var webUserName = item.receiver.username;
                var webUserId = item.receiver.id;
                node += "<tr><td>" + MESSAGE_STATUS[item.message.status] + "</td>"
                        + "<td> <a href='#author' >" + webUserName + "</a></td>"
                        + "<td> <a href='#message-content' onclick='read_message_content(" + item.message.id + ")'>" + item.message.title + "</a></td>"
                        + "<td>" + item.message.createTime + "</td>";
                var params = item.message.id + "," + MESSAGE_STATUS_DELETE;
                node += "<td>" + "<a href='#' onclick='update_message_status(" + params + ")'>" + "删除" + "</a>" + "<td>";
            });

            $("#message-send .table").append(node);

            //分页
            $("#message-send  .page-nav .pagination li").remove();
            var page = result.data;
            var message = page.data;
            var paramStr = "";
            if (page.totalCount == 0){
                var info = "<li><span>暂无消息</span></li>";
                $("#message-send .page-nav .pagination").append(info);
            }
            if (page.totalPageNumber > 1) {
                if (page.currentPageNumber > 1) {
                    paramStr += page.currentPageNumber - 1 + "," + page.pageSize + "," + MESSAGE_SEND + "," + null;
                    var prevPage = "<li><span aria-hidden='true' onclick=parseSendMessages(" + paramStr + ")>上一页</span></li>";
                    $("#message-send .page-nav .pagination").append(prevPage);
                    paramStr = "";
                }
                for (var i = 1; i <= page.totalPageNumber; i++) {
                    paramStr += i + "," + page.pageSize + "," + MESSAGE_SEND + "," + null;
                    var pageNav = "";
                    if (page.currentPageNumber == i) {
                        pageNav = "<li class='active'><span aria-hidden='true' onclick=parseReceiveMessages(" + paramStr + ")>" + i + "</span></li>"
                    } else {
                        pageNav = "<li><span aria-hidden='true' onclick=parseSendMessages(" + paramStr + ")>" + i + "</span></li>";
                    }
                    $("#message-send .page-nav .pagination").append(pageNav);
                    paramStr = ""
                }
                if (page.currentPageNumber < page.totalPageNumber) {
                    paramStr += page.currentPageNumber + 1 + "," + page.pageSize + "," + MESSAGE_SEND + "," + null;
                    var nextPage = "<li><span aria-hidden='true' onclick='parseSendMessages(" + paramStr + ")'>下一页</span></li>";
                    $("#message-send .page-nav .pagination").append(nextPage);
                    paramStr = "";
                }
            }
        });
    }

    //解析未读短信数据
    function parseUnreadMessages(currentPageNumber, pageSize, type, status) {
        //从服务器端获取数据并解析
        getMessageDataAndParse(contextPath + "/MessageBox/List", {
            currentPageNumber: currentPageNumber,
            pageSize: pageSize,
            type: type,
            status: status
        }, function (result) {
            $("#message-unread .table tr:not(:first)").remove();
            if (result == null) {
                return;
            }
            var node = "";
            /***
             * 修改短消息状态
             * 0:已读
             * 1:未读
             * 2:已删除
             *
             * @param messageBoxDto
             * @return
             */

            $.each(result.data.data, function (index, item) {
                var webUserName = item.sender.username;
                var webUserId = item.sender.id;
                node += "<tr><td>" + MESSAGE_STATUS[item.message.status] + "</td>"
                        + "<td> <a href='#author' >" + webUserName + "</a></td>"
                        + "<td> <a href='#message-content' onclick='read_message_content(" + item.message.id + ")'>" + item.message.title + "</a></td>"
                        + "<td>" + item.message.createTime + "</td>";
                var params = item.message.id + "," + MESSAGE_STATUS_DELETE;
                node += "<td>" + "<a href='#' onclick='update_message_status(" + params + ")'>" + "删除" + "</a>" + "<td>";
            });

            $("#message-unread .table").append(node);

            $("#message-unread  .page-nav .pagination li").remove();
            var page = result.data;
            var message = page.data;
            var paramStr = "";
            if (page.totalCount == 0){
                var info = "<li><span>暂无消息</span></li>";
                $("#message-unread .page-nav .pagination").append(info);
            }
            if (page.totalPageNumber > 1) {
                if (page.currentPageNumber > 1) {
                    paramStr += page.currentPageNumber - 1 + "," + page.pageSize + "," + MESSAGE_RECEIVE + "," + null;
                    var prevPage = "<li><span aria-hidden='true' onclick=parseUnreadMessages(" + paramStr + ")>上一页</span></li>";
                    $("#message-unread .page-nav .pagination").append(prevPage);
                    paramStr = "";
                }
                for (var i = 1; i <= page.totalPageNumber; i++) {
                    paramStr += i + "," + page.pageSize + "," + MESSAGE_RECEIVE + "," + null;
                    var pageNav = "";
                    if (page.currentPageNumber == i) {
                        pageNav = "<li class='active'><span aria-hidden='true' onclick=parseUnreadMessages(" + paramStr + ")>" + i + "</span></li>"
                    } else {
                        pageNav = "<li><span aria-hidden='true' onclick=parseUnreadMessages(" + paramStr + ")>" + i + "</span></li>";
                    }
                    $("#message-unread .page-nav .pagination").append(pageNav);
                    paramStr = ""
                }
                if (page.currentPageNumber < page.totalPageNumber) {
                    paramStr += page.currentPageNumber + 1 + "," + page.pageSize + "," + MESSAGE_RECEIVE + "," + null;
                    var nextPage = "<li><span aria-hidden='true' onclick='parseUnreadMessages(" + paramStr + ")'>下一页</span></li>";
                    $("#message-unread .page-nav .pagination").append(nextPage);
                    paramStr = "";
                }
            }
        });
    }
</script>
</body>
</html>


