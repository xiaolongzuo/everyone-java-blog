/**
 * @author 郭松涛
 * @date 2016/6/18 10:20
 * @version 1.0
 */

$(document).ready(function () {
    //首先加载20篇文章
    getArticles(categoryId);

    $('#loadmore').on('click', function (e) {
        getArticles(categoryId);
    });
    $('#technology').on('click', function (e) {
        categoryId =1;
        offset = null;
        $('.blog-post1').remove();
        getArticles(categoryId);
    });
    $('#work').on('click', function (e) {
        categoryId =2;
        offset = null;
        $('.blog-post1').remove();
        getArticles(categoryId);
    });
    $('#life').on('click', function (e) {
        categoryId =3;
        offset = null;
        $('.blog-post1').remove();
        getArticles(categoryId);
    });
});
//全局变量
var  offset = null;//加载到达时间
var  categoryId = 1;//类目id

function getArticles(categoryId) {
    $.ajax({
        url: contextPath + "/HomePage/Articles",
        dataType: "JSON",
        data: {
            categoryId:categoryId,
            offset:offset,
            size:10
        },
        type: "POST",
        cache: false,
        success: function (result) {
            var articlesObject = result['data'];
            //console.table(articlesObject);
            $.each(articlesObject,function(n,value) {
                console.table(value);
                var articleHtml = '<div class="blog-post1">'+
                    '<h2 class="blog-post-title"><a href="#" class="blog-article">'+value['userArticle']['title'] +'</a></h2>'+
                '<p class="blog-post-meta">'+value['webUser']['nickname']+value['friendlyTime']+'</p>'+
                    '<p class="blog-post-meta">推荐('+value['userArticle']['thumbupTimes']+') 评论(' +
                    value['userArticle']['commentTimes']+') 阅读('+value['userArticle']['readTimes']+')</p>'+
                '</div>';
                $('#loadmore').before(articleHtml);
                if(n==(articlesObject.length-1)){
                    offset = (value['userArticle']['updateTime']);
                }
            });
            if(articlesObject.length < 10){
                $('#loadmore').css('display', 'none');
            }else{
                $('#loadmore').css('display', 'block');
            }
        },
        error:function (re) {
            console.table(re)
        }
    });

}