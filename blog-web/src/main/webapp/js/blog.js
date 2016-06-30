/**
 * @author flutterfire
 * @date 2016/6/19
 * @version 1.0
 */

var offset = "";
$(function () {
    loadMoreArticle();

    $(".active").removeClass("active");

    $('#technology').on('click', function (e) {
        window.location.href = '/HomePage/index?categoryId=1';
    });
    $('#work').on('click', function (e) {
        window.location.href = '/HomePage/index?categoryId=2';
    });
    $('#life').on('click', function (e) {
        window.location.href = '/HomePage/index?categoryId=3';
    });
})
function loadMoreArticle() {
    $.get("/WebBlog/getMyBlogArticle", {offset: offset}, function (result) {
        var len = result.data.userArticleList.length;

        for (var i = 0; i < len; i++) {

            $("#myBlog").before('<div class="blog-post">'
                + '<h2 class="blog-post-title"><a href="'+ ctx + '/Article/' + result.data.userArticleList[i].id +'" class="blog-article">' + result.data.userArticleList[i].title + '</a></h2>'
                + '<p class="blog-post-meta">' + result.data.webUser.nickname + '发表于' + result.data.userArticleList[i].createTime + '</p>'
                + '<p class="blog-post-meta">推荐(' + result.data.userArticleList[i].thumbupTimes + ') ' +
                '评论(' + result.data.userArticleList[i].commentTimes + ') ' +
                '阅读(' + result.data.userArticleList[i].readTimes + ') ' +
                '[<a href="' + ctx + '/webArticle/form?id=' + result.data.userArticleList[i].id + '">编辑</a>|' +
                '<a href="' + ctx + '/webArticle/delete?id=' + result.data.userArticleList[i].id + '">删除</a>]</p></div>');

            if (i == (len - 1)) {
                offset = result.data.userArticleList[i].id;
            }
        }

    })
}

function goArticle(id) {
    window.location.href = '/Article/' + id;
}