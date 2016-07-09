/**
 * @author DeseverL
 * @date 2016/6/12 0:44
 * @version 1.0
 */
$(function () {
    //加载评论列表
    var articleid = $('#articleid').val();
    //加载评论 默认先加载十个
    getComment(articleid,0,10);

})
//全局变量
var COMMENT_NUM = 0;

//点攒
function thumbsUp(aHtml){
    var articleid = $('#articleid').val();
    $.ajax({
        url: contextPath + "/Article/AddThumbupTimes?articleid="+articleid,
        dataType: "json",
        type: "POST",
        cache: false,
        async: false,//false代表只有在等待ajax执行完毕后才执行后面的语句
        success: function () {
            $(aHtml).attr("disabled","disabled");
        }
    })
}

//加载更多评论
function getMoreComment(){
    var articleid = $('#articleid').val();
    var lastCommentid=$('#bottom-comment').prev().children('.meta-top').children('.reply-comment').attr('comment-id');
    //加载评论列表
    getComment(articleid,lastCommentid,10);
}

//加载更多回复评论
function getMoreReComment(aHtml){
    var articleid = $('#articleid').val();
    var commentid=$(aHtml).parent().parent().parent().children('.meta-top').children('.reply-comment').attr('comment-id');
    var lastReCommentid=$(aHtml).parent().prev().children('.reply-recomment').attr('comment-id');
    //加载回复列表
    $.ajax({
        url: contextPath + "/Article/GetMoreReComment?commondid=" + commentid + "&offset=" + lastReCommentid + "&size=10",
        dataType: "json",
        type: "GET",
        cache: false,
        async: false,//false代表只有在等待ajax执行完毕后才执行后面的语句
        success: function (data) {
            if(data['data']!=null){
                var len = data['data'].length;
                var recommentHtmls = editReComment(data['data']);
                $(aHtml).parent().before(recommentHtmls);
                //判断是否隐藏‘加载更多’框
                if(len < 10){
                    $(aHtml).parent().css('display', 'none');
                }else{
                    var reCommentfont = $(aHtml).parent().find("font");
                    reCommentfont.text(parseInt(reCommentfont.test())-10);
                    $(aHtml).parent().css('display', 'block');
                }
            }
        }
    })
}

//主评论回复点击事件
function replyComment(patent){
    //判断用户是否登录
    if(USER_NICKNAME == null || USER_NICKNAME ==''){
        alert("请先登录");
        return false
    }
    var parentdiv = $(patent).parent().parent();//获取外层div
    addReCommentText(patent,parentdiv);
};

//回复回复的评论点击事件
function replyReComment(patent){
    //判断用户是否登录
    if(USER_NICKNAME == null || USER_NICKNAME ==''){
        alert("请先登录");
        return false
    }
    var parentdiv = $(patent).parent().parent().parent();//获取外层div
    addReCommentText(patent,parentdiv);
};

/**
 * @author DeseverL
 * @date 2016/6/12 23:46
 * @version 1.0
 * @description: 添加回复框
 */
function addReCommentText(parent,parentdiv){
    //查看是否存在回复的评论框
    var newComment = parentdiv.find('.new-comment');
    //不存在则创建
    if(newComment.length == 0){
        var newcomment = "<form class='new-comment' style='display: block;'>" +
            "<div class='panel-body comment-text'>" +
            "<input type='hidden' value='"+$(parent).attr('comment-id')+"' name='replyCommentId'>" +
            "<textarea name='comment' class='textarea'' placeholder='写下你的评论…' maxlength='2000'></textarea>" +
            "<div>" +
            "<span class='pull-left'>@"+$(parent).attr('user-name')+"</span>"+
            "<input type='button' data-disable-with='提交中...' class='btn btn-small btn-info pull-right' onclick='addReComment(this)' value='发 表'>" +
            "</div>" +
            "</div>" +
            "</form>"
        parentdiv.append(newcomment);
    }else if(newComment.length == 1){
        //隐藏评论框
        if(newComment.css('display') == 'block'){
            newComment.css('display', 'none')
        //显示评论框
        }else if(newComment.css('display') == 'none'){
            //设置父评论的id，用于传到后台
            newComment.find("input[type='hidden']").val($(parent).attr('comment-id'));
            //设置回复人
            newComment.find("span[class='pull-left']").text($(parent).attr('user-name'));
            newComment.css('display', 'block');
        }
    }
}
/**
 * @author DeseverL
 * @date 2016/6/12 0:46
 * @version 1.0
 * @description: 根据articleid,异步请求评论列表
 */
function getComment(articleid,offset,size) {
    $.ajax({
        url: "/Article/GetCommentInfo?articleid="+articleid+"&offset="+offset+"&size="+size+"",
        dataType: "json",
        type: "GET",
        cache: false,
        async:false,//false代表只有在等待ajax执行完毕后才执行后面的语句
        success: function (data) {
            var commentObject = data['data'];//对json数组each
            var len = commentObject.length; //记录本次评论加载的个数
            $.each(commentObject,function(n,value) {
                var webuser = value['webUser']; //评论用户信息
                var mainComment = value['articleComment']; //主评论信息
                var reComments = value['articleCommentDTOList']; //评论下的回复评论
                var date = new Date(mainComment['createTime']).Format("yyyy.MM.dd hh:mm:ss");
                //主评论列表
                var commentHtml = "<div class='meta-top'>" +
                    "<a href='javascript:void(null)'>"+webuser['nickname']+"</a>" +
                    "&nbsp;&nbsp;&nbsp;<span class='reply-time'> "+(COMMENT_NUM+n+1)+"楼 · 发表于"+date+"</span>" +
                    "<a class='reply-comment pull-right' href='javascript:void(null)' onclick='replyComment(this)'" +
                    " comment-id='"+mainComment['id']+"' user-name='"+webuser['nickname']+"'>回复</a>" +
                    "</div>" +
                    "<p>"+mainComment['comment']+"</p>";
                //主评论列表下的回复列表
                var recommentHtmls = "";
                if(reComments!=null){
                    recommentHtmls = recommentHtmls+"<div class='child-comment-list'>";
                    recommentHtmls = recommentHtmls + editReComment(reComments); //获取三条回复
                    //回复数超过三条的情况
                    if(value['reCommentCount']>3){
                        recommentHtmls = recommentHtmls + "<div class='panel-body child-comment'>" +
                            "<span class='reply-time pull-left'>还有<font> "+(value['reCommentCount'] - 3)+"</font>条评论，</span>" +
                            "<a class='reply' href='javascript:void(null)' onclick='getMoreReComment(this)'>查看更多</a>" +
                            "</div>";
                    }
                    recommentHtmls = recommentHtmls+"</div>";
                }
                $('#bottom-comment').before("<div class='panel comment-note'>"+commentHtml+recommentHtmls+"</div>");
            });
            COMMENT_NUM = COMMENT_NUM+len;
            //判断是否加载'加载更多'按钮
            if(len < 10){
                $('#bottom-comment').css('display', 'none');
            }else{
                $('#bottom-comment').css('display', 'block');
            }
        },
        error: function () {

        }
    });
}

/**
 * @author DeseverL
 * @date 2016/6/16 16:46
 * @version 1.0
 * @description: 编辑回复html
 */
function editReComment(reComments){
    var recommentHtmls = "";
    $.each(reComments,function(n,value) {
        var reWebuser = value['webUser'];
        var reParentUser = value['parentUser'];
        var reMainComment = value['articleComment'];
        var date = new Date(reMainComment['createTime']).Format("yyyy.MM.dd hh:mm:ss");
        var recommentHtml = "<div class='panel-body child-comment'>" +
            "<p><a class='blue-link' href='javascript:void(null)'>"+reWebuser['nickname']+"</a>" +
            "：<a class='blue-link' href='javascript:void(null)'>@"+reParentUser['nickname']+"</a>"+reMainComment['comment']+"</p> " +
            "<span class='reply-time pull-left'>"+date+"</span> " +
            "<a class='reply-recomment pull-right' href='javascript:void(null)' onclick='replyReComment(this)'" +
            " comment-id='"+reMainComment['id']+"' user-name='"+reWebuser['nickname']+"'>回复</a> </div>";
        recommentHtmls = recommentHtmls+recommentHtml;
    })
    return recommentHtmls;
}

/**
 * @author DeseverL
 * @date 2016/6/14 0:46
 * @version 1.0
 * @description: 异步添加评论
 */
function addComment(parent){
    //判断用户是否登录
    if(USER_NICKNAME == null || USER_NICKNAME ==''){
        alert("请先登录");
        return false
    }
    var formElement = $(parent).parent().parent().parent();
    var commentText =  formElement.find('textarea').val();
    if(commentText == null || commentText ==""){
        alert("内容不能为空！");
        return false;
    }
    $.ajax({
        cache: true,
        type: "POST",
        url:'/Article/AddComment',
        data:formElement.serialize(),//form
        async: false,
        success: function(data) {
            if(data['code'] == 200) {
                var comment = "<div class='meta-top'>" +
                    "<a href='javascript:void(null)'>" + USER_NICKNAME + "</a>" +
                    "&nbsp;&nbsp;&nbsp;<span class='reply-time'> " + (parseInt($('#commentCount').text()) + 1) + "楼 · 发表于" + new Date().Format("yyyy.MM.dd hh:mm:ss") + "</span>" +
                    "<a class='reply-comment pull-right' href='javascript:void(null)'  onclick='replyComment(this)'" +
                    " comment-id='" + data['data'] + "' user-name='" + USER_NICKNAME + "'>回复</a>" +
                    "</div>" +
                    "<p>" + commentText + "</p>";
                $('#bottom-comment').after("<div class='panel comment-note'>" + comment + "</div>");

                //清空回复框的值
                formElement.find('textarea').val("");
            }else{
                alert(data['message']);
            }
        },
        error: function(request) {
            alert("Connection error");
        }
    });
}

/**
 * @author DeseverL
 * @date 2016/6/14 0:46
 * @version 1.0
 * @description: 异步添加回复
 */
function addReComment(parent){
    var formElement = $(parent).parent().parent().parent();
    var commentText =  formElement.find('textarea').val();
    if(commentText == null || commentText ==""){
        alert("内容不能为空！");
        return false;
    }
    $.ajax({
        cache: true,
        type: "POST",
        url:'/Article/AddComment',
        data:formElement.serialize(),//form
        async: false,
        success: function(data) {
            if(data['code'] == 200) {
                var recomment = "<div class='panel-body child-comment'>" +
                    "<p><a class='blue-link' href='javascript:void(null)'>"+USER_NICKNAME+"</a>" +
                    "：<a class='blue-link' href='javascript:void(null)'>"+$(parent).parent().find('span').text()+"</a>"+commentText+"</p> " +
                    "<span class='reply-time pull-left'>"+new Date().Format("yyyy.MM.dd hh:mm:ss")+"</span> " +
                    "<a class='reply-recomment pull-right' href='javascript:void(null)' onclick='replyReComment(this)'" +
                    " comment-id='"+data['data']+"' user-name='"+USER_NICKNAME+"'>回复</a> </div>";
                var parentdiv = formElement.parent();

                if(parentdiv.find('.child-comment-list').length == 0){
                    formElement.before("<div class='child-comment-list'>"+recomment+"</div>");
                }else{
                    parentdiv.find('.child-comment-list').append(recomment);
                }
                //隐藏回复框和清空回复框的值
                formElement.find('textarea').val("");
                formElement.css('display', 'none');
            }else{
                alert(data['message']);
            }
        },
        error: function(request) {
            alert("Connection error");
        }
    });
}

// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.Format = function(fmt)
{ //author: meizz
    var o = {
        "M+" : this.getMonth()+1,                 //月份
        "d+" : this.getDate(),                    //日
        "h+" : this.getHours(),                   //小时
        "m+" : this.getMinutes(),                 //分
        "s+" : this.getSeconds(),                 //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S"  : this.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt))
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    return fmt;
}