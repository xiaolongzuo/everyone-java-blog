/**
 * @author 郭松涛
 * @date 2016/6/18 10:20
 * @version 1.0
 */

$(document).ready(function () {
    $('#load-more').click(function () {
        var categoryId = $('#load-more').attr("category-id");
        var offset = $('#load-more').attr("offset");
        var size = $('#load-more').attr("size");
        $.ajax({
            url: contextPath + "/HomePage/Articles?categoryId=" + categoryId + "&offset=" + offset + "&size=" + size,
            dataType: "JSON",
            type: "GET",
            cache: false,
            success: function (result) {
                if (result == "error") {
                    alert("加载失败!");
                    return;
                }
                var articlesObject = result['articles'];
                var articleHtml = '';
                $.each(articlesObject, function (n, value) {
                    articleHtml = articleHtml + '<div class="blog-post1"><h2 class="blog-post-title">';
                    articleHtml = articleHtml + '<a href="' + contextPath + '/Article/' + value['userArticle']['id'] + '" class="blog-article">' + value['userArticle']['title'] + '</a></h2>';
                    articleHtml = articleHtml + '<p class="blog-post-meta">' + value['webUser']['nickname'] + value['friendlyTime'] + '</p>';
                    articleHtml = articleHtml + '<p class="blog-post-meta">推荐(' + value['userArticle']['thumbupTimes'] + ') 评论(' + value['userArticle']['commentTimes'] + ') 阅读(' + value['userArticle']['readTimes'] + ')</p>' ;
                    articleHtml = articleHtml + '</div>';
                });
                $('#load-more').attr("category-id", result['categoryId']);
                $('#load-more').attr("offset", result['offset']);
                $('#load-more').attr("size", result['size']);
                $('#load-more').before(articleHtml);
                if (articlesObject.length < size) {
                    $('#load-more').css('display', 'none');
                } else {
                    $('#load-more').css('display', 'block');
                }
            }
        });
    });
});
