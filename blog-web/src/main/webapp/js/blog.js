/**
 * @author flutterfire
 * @date 2016/6/19
 * @version 1.0
 */

var offset = "";
var username = "";
$(function () {
    username = $("#username").val();
    loadMoreArticle();

    $(".active").removeClass("active");

    $('#technology').on('click', function (e) {
        window.location.href = contextPath + '/HomePage/Index?categoryId=1';
    });
    $('#work').on('click', function (e) {
        window.location.href = contextPath + '/HomePage/Index?categoryId=2';
    });
    $('#life').on('click', function (e) {
        window.location.href = contextPath + '/HomePage/Index?categoryId=3';
    });
})
function loadMoreArticle() {
    $.get(contextPath + "/WebBlog/getMyBlogArticle", {offset:offset, username:username}, function (result) {
        var len = result.data.userArticleList.length;
        for (var i = 0; i < len; i++) {

            $("#myBlog").before('<div class="blog-post">'
                +'<h2 class="blog-post-title"><a href="javascript:goArticle('+result.data.userArticleList[i].id+')" class="blog-article">'+ result.data.userArticleList[i].title+'</a></h2>'
                +'<p class="blog-post-meta">'+result.data.webUser.nickname+'发表于'+ result.data.userArticleList[i].createTime+'</p>'
                +'<p class="blog-post-meta">推荐('+result.data.userArticleList[i].thumbupTimes+') 评论('+result.data.userArticleList[i].commentTimes+') 阅读('+result.data.userArticleList[i].readTimes+')</p></div>');

            if (i == (len - 1)) {
                offset = result.data.userArticleList[i].id;
            }
        }

        if (len < 10) {
            $("#myBlog").hide();
        }

    })
}

function goArticle(id) {
    window.location.href = contextPath + '/Article/' + id;
}