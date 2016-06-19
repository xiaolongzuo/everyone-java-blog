/**
 * @author flutterfire
 * @date 2016/6/19
 * @version 1.0
 */

var offset = 0;
$(function () {
    loadMoreArticle();
})
function loadMoreArticle() {
    $.get("/WebBlog/getMyBlogArticle", {offset:offset}, function (result) {
        var len = result.data.userArticleList.length;
        for (var i = 0; i < len; i++) {

            $("#myBlog").before('<div class="blog-post">'
                +'<h2 class="blog-post-title"><a href="#" class="blog-article">'+ result.data.userArticleList[i].title+'</a></h2>'
                +'<p class="blog-post-meta">'+result.data.webUser.nickname+'发表于'+ result.data.userArticleList[i].createTime+'</p>'
                +'<p class="blog-post-meta">推荐('+result.data.userArticleList[i].thumbupTimes+') 评论('+result.data.userArticleList[i].commentTimes+') 阅读('+result.data.userArticleList[i].readTimes+')</p></div>');

            if (i == (len - 1)) {
                offset = result.data.userArticleList[i].id;
            }
        }

    })
}