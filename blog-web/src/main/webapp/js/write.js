/**
 * @author 郭松涛
 * @date 2016/7/16 11:53
 * @version 1.0
 */
$(document).ready(function () {

    $('#new-note').click(function () {
        $.ajax({
            url: contextPath + "/Article/Add/UserArticle?content=",
            dataType: "JSON",
            type: "POST",
            cache: false,
            success: function (result) {
                window.location.href=contextPath+"/Article/Write/"+result['data'];
            }
        });

    });

    $(".selected").bind("mousedown",function (e) {
        if (e.which == 3) {

            var opertionn = {
                name: "",
                offsetX: 2,
                offsetY: 2,
                textLimit: 10,
                beforeShow: $.noop,
                afterShow: $.noop
            };

            var imageMenuData = [
                [{
                    text: "保存",
                    func: function(){
                        modify(1);
                    }
                }, {
                    text: "发布",
                    func: function(){
                        modify(2);
                    }
                }],
                [{
                    text: "删除",
                    func:function(){
                        modify(3);
                        window.location.href=contextPath+"/Article/Write"
                    }
                }]
            ];

            $(this).smartMenu(imageMenuData, opertionn);
        }
    });

})

/**
 * 根据传入的值修改
 * @param data
 */
function modify(type){
    var data;
    var currentId =$(".selected").attr("currentId");
    var title = $("#title").html().trim();
    var content = $("#contentText").html().trim();
    var categoryId = 1;//先默认技术类
    switch (type){
        case 1:
            data = {id:currentId,title:title,content:content,categoryId:categoryId};
            break;
        case 2:
            data = {id:currentId,status:1};
            break;
        case 3:
            data = {id:currentId,status:2};
            break;
    }
    $.ajax({
        url: contextPath + "/Article/UpdUserArticle",
        dataType: "JSON",
        data: data,
        type: "POST",
        cache: false,
        success: function (result) {
            alert("操作成功");
        }
    });
}