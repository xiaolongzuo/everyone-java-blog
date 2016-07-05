/**
 * @author iCodingStar
 * @date 2016/6/27 10:14
 * @version 1.0
 */
$(function () {
    var MESSAGE_RECEIVE = 0;
    var MESSAGE_SEND = 1;
    var MESSAGE_UNREAD = 2;
    var type = MESSAGE_RECEIVE;

    var pageSize = 5;
    var pageNumber = 0;
    var status = null;
    getMessageDataAndParse("/MessageBox/List", {
        pageNumber: pageNumber,
        pageSize: pageSize,
        type: type,
        status: status
    }, type, parseMessages);

    //收件箱
    $(".message-receive").on('click', function () {
        $(".message-content").css("display", "none");
        $("#message-content").css("display", "none");
        type = MESSAGE_RECEIVE;
        status = null;
        getMessageDataAndParse("/MessageBox/List", {
            pageNumber: pageNumber,
            pageSize: pageSize,
            type: 0,
            status: status
        }, type, parseMessages);
    });

    //发件箱
    $(".message-send").on('click', function () {
        $(".message-content").css("display", "none");
        $("#message-content").css("display", "none");
        type = MESSAGE_SEND;
        status = null;
        getMessageDataAndParse("/MessageBox/List", {
            pageNumber: pageNumber,
            pageSize: pageSize,
            type: 1,
            status: status
        }, type, parseMessages);
    });

    //未读消息
    $(".message-unread").on('click', function () {
        $(".message-content").css("display", "none");
        $("#message-content").css("display", "none");
        type = MESSAGE_UNREAD;
        status = 1;
        getMessageDataAndParse("/MessageBox/List", {
            pageNumber: pageNumber,
            pageSize: pageSize,
            type: 0,
            status: status
        }, type, parseMessages);
    });

    //获取短信数据并解析
    getMessageDataAndParse("/MessageBox/List", {
        pageNumber: pageNumber,
        pageSize: pageSize,
        type: type,
        status: status
    }, type, parseMessages);

    //发消息
    $("#send-message").on('click', function () {
        $(".message-content").css("display", "none");
        $("#message-content").css("display", "none");
        var username = $("#message-write #receiver").val();
        if (isNaN(username)) {
            receiver = null;
        } else {
            receiver = username;
        }
        var title = $("#message-write #title").val();
        var content = $('#message-write #content').val();

        postMessageDataAndParse("/MessageBox/Send", {
            username: username,
            receiver: receiver,
            title: title,
            content: content,
            status: 0
        }, function (result) {
            alert("发送成功！")
            $(".message-send a").trigger("click");
        });
    });

    //解析短信数据
    function parseMessages(type, result) {
        switch (type) {
            case MESSAGE_RECEIVE:
                $("#message-receive .table tr:not(:first)").remove();
                break;
            case MESSAGE_SEND:
                $("#message-send .table tr:not(:first)").remove();
                break;
            case MESSAGE_UNREAD:
                $("#message-unread .table tr:not(:first)").remove();
                break;
            default:
                result;
        }
        if (result == null) {
            return;
        }
        var node = "";
        /***
         * 修改短消息状态
         * 0:已读
         * 1:未读
         * 2:接收者已删除
         * 3:发送者已删除
         * 4:已删除
         *
         * @param messageBoxDto
         * @return
         */
        var MESSAGE_STATUS = ['已读', '未读', '接收者已删除', '发送者已删除', '已删除'];

        $.each(result.data, function (index, item) {
            var webUserName = null;
            var webUserId = null;
            if (type == MESSAGE_RECEIVE || type == MESSAGE_UNREAD) {
                webUserName = item.sender.username;
                webUserId = item.sender.username;
            } else if (type == MESSAGE_SEND) {
                webUserName = item.receiver.username;
                webUserId = item.receiver.username;
            }
            node += "<tr><td>" + MESSAGE_STATUS[item.message.status] + "</td>"
                + "<td> <a href='#author' >" + webUserName + "</a></td>"
                + "<td> <a href='#message-content' onclick='read_message_content(" + item.message.id + ")'>" + item.message.title + "</a></td>"
                + "<td>" + item.message.createTime + "</td>";
            var params = item.message.id + "," + (type + 2);
            node += "<td>" + "<a href='#' onclick='update_message_status(" + params + ")'>" + "删除" + "</a>" + "<td>";
        });

        switch (type) {
            case MESSAGE_RECEIVE:
                $("#message-receive .table").append(node);
                break;
            case MESSAGE_SEND:
                $("#message-send .table").append(node);
                break;
            case MESSAGE_UNREAD:
                $("#message-unread .table").append(node);
                break;
            default:
                result;
        }
    }

});

/***
 * 显示消息详情
 *
 * @param id
 */
function read_message_content(id) {
    getMessageDataAndParse("/MessageBox/Content", {
        id: id
    }, null, function (type, result) {
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
    getMessageDataAndParse("/MessageBox/Update", {
        id: id,
        status: status
    }, null, function (type, result) {
        console.log("更新成功");

    });
}

/***
 *  获取数据并解析
 *
 * @param url
 * @param data
 */
function getMessageDataAndParse(url, data, type, callback) {
    $.ajax({
        url: url,
        dataType: "json",
        data: data,
        type: "GET",
        cache: false,
        success: function (result) {
            callback(type, result);
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