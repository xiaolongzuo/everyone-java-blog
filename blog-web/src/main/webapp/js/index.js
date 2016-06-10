/**
 * Created by Administrator on 2016/6/10.
 */

$(document).ready(function () {

$('#test').on('click', function (e) {
//            alert("hello");
    $.ajax({
        url: "/HomePage/Articles",
        dataType: "JSON",
        data: {
            categoryId:1
        },
        type: "POST",
        cache: false,
        success: function (re) {
            $("aaa");
        },
        error:function (re) {
            console.table(re)
            alert("bbb");
        }
    });
});

});