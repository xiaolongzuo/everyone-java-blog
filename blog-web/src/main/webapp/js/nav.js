/**
 * @author iCodingStar
 * @date 2016/6/11 0:44
 * @version 1.0
 */
$(function () {
    //打开网站时加载技术类文章并显示出来
    var url = "/HomePage/TopThreeUserArticles";
    var categoryName = "技术";
    getDataAndParse(url, {categoryName:categoryName});
    //根据用户对菜单的选择显示相应的最多评论、最多推荐、最多阅读的文章
    $(".blog-nav").on("click", "a", function (event) {
        $(".blog-nav a").removeClass("active");
        $(this).addClass("active");
        categoryName = $(this).text();
        getDataAndParse(url, {categoryName:categoryName});
    });
})

/**
 * @author iCodingStar
 * @date 2016/6/11 0:46
 * @version 1.0
 * @description: 根据url和data向服务器发送请求,并显示数据
 */
function getDataAndParse(url, data) {
    $.ajax({
        url: url,
        dataType: "json",
        data: JSON.stringify(data),
        type: "POST",
        cache: false,
        success: function (data, status) {
            //alert(status);
            //alert(data);
        },
        error: function () {
            //alert("error");
        }
    });
}