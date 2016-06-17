/**
 * Created by 郭松涛 on 2016/6/10.
 */

$(document).ready(function () {

    //首先加载20篇文章
    getArticles(categoryId);

    $('#loadmore').on('click', function (e) {
        getArticles(categoryId);
    });

});
//全局变量
var  offset = null;//加载到达时间
var  categoryId = 1;//类目id

function getArticles(categoryId) {
    //var articleHtml = '<div class="blog-post">'+
    //    '<h2 class="blog-post-title"><a href="#" class="blog-article">第一篇文章第一篇文章第一篇文章第一篇文章第一篇文章</a></h2>'+
    //'<p class="blog-post-meta">左潇龙发表于1天前</p>'+
    //    '<p class="blog-post-meta">推荐(10) 评论(10) 阅读(1000)</p>'+
    //'</div>';
    //$('#loadmore').before(articleHtml);

    $.ajax({
        url: "/HomePage/Articles",
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
                var articleHtml = '<div class="blog-post">'+
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
                alert("没有更多了")
            }else{
                $('#loadmore').css('display', 'block');
            }
        },
        error:function (re) {
            console.table(re)
        }
    });

}