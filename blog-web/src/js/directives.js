angular.module('starter.directives', [])

.directive('leftAside', function () {
	return {
		restrict: 'E',
		replace: 'true',
		templateUrl: '../templates/left-aside.html',
		link: function (scope, iElement, iAttrs) {
			scope.img_url = "background-image: url('img/bg.jpg')";
			scope.aside_title = "大家的博客";
			scope.aside_sub_title = "学习的路，现在开始";
			scope.aside_description = "Java程序猿自己给自己打造的博客网站";
		}
	};
})

.directive('recommendNav', function (Recommend) {
	return {
		restrict: 'E',
		replace: 'true',
		templateUrl: '../templates/recommend-nav.html',
		link: function (scope, iElement, iAttrs) {
			Recommend.get_nav().then(function (result) {
				scope.items = result;
			}, function (error) {
				console.log("上方分类导航读取失败");
			});
		}
	};
})

.directive('listRecommend', function (Recommend) {
	return {
		restrict: 'E',
		replace: 'true',
		templateUrl: '../templates/list-recommend.html',
		link: function (scope, iElement, iAttrs) {
			Recommend.get_list('recommend').then(function (result) {
				scope.recommend_items = result;
			}, function (error) {
				console.log("推荐文章读取失败");
			});
		}
	};
})

.directive('listArticle', function (Recommend) {
	return {
		restrict: 'E',
		replace: 'true',
		templateUrl: '../templates/list-article.html',
		link: function (scope, iElement, iAttrs) {
			Recommend.get_list('article').then(function (result) {
				scope.list_items = result;
			}, function (error) {
				console.log("文章列表读取失败");
			});
		}
	};
})

.directive('articleContent', function (Article) {
	return {
		restrict: 'E',
		replace: 'true',
		templateUrl: '../templates/article-content.html',
		link: function (scope, iElement, iAttrs) {
			Article.get_content().then(function (result) {
				scope.article_data = result;
			}, function (error) {
				console.log("文章数据读取失败");
			});

		}
	};
})

.filter('trustHtml', function ($sce) {
	return function (text) {
		return $sce.trustAsHtml(text);
	}
})

.directive('articleSupport', function (Article) {
	return {
		restrict: 'E',
		replace: 'true',
		templateUrl: '../templates/article-support.html',
		link: function (scope, iElement, iAttrs) {
			Article.get_support_data().then(function (result) {
				scope.support_data = result;
				scope.users = result.users;
			}, function (error) {
				console.log("文章数据读取失败");
			});
		}
	};
})

.directive('articleLike', function (Article) {
	return {
		restrict: 'E',
		replace: 'true',
		templateUrl: '../templates/article-like.html',
		link: function (scope, iElement, iAttrs) {
			Article.get_like_data().then(function (result) {
				scope.like_data = result;
			}, function (error) {
				console.log("文章数据读取失败");
			});
		}
	};
})

.directive('articleComment', function (Article) {
	return {
		restrict: 'E',
		replace: 'true',
		templateUrl: '../templates/article-comment.html',
		link: function (scope, iElement, iAttrs) {
			Article.get_comment().then(function (result) {
				scope.comment_data = result;
				scope.comments = result.items;
			}, function (error) {
				console.log("文章数据读取失败");
			});
		}
	};
})

// .directive('childComment', function () {
// 	return {
// 		restrict: 'E',
// 		replace: 'true',
// 		templateUrl: '../templates/article-comment-child.html',
// 		link: function (scope, iElement, iAttrs) {
// 			scope.children = scope.$parent.comments;

// 		}
// 	};
// })