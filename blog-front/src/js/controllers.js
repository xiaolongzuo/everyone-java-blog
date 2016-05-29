angular.module('starter.controllers', [])

.controller('mainCtrl', function($scope, $location){
	$scope.test = '载入中';
	$scope.now_page = "post";
})

.controller('recommendCtrl', function($scope, $location) {
	console.log("recommendCtrl");
	// 改变最外层 div 的当前页类型标识 class ；
	$scope.$parent.now_page = "index";
})

.controller('articleCtrl', function($scope, $stateParams) {
	console.log("articleCtrl");
	// 改变最外层 div 的当前页类型标识 class ；
	$scope.$parent.now_page = "post";

	// 输出当前文章id
	var current_article_id = $stateParams.articleId;
	console.log(current_article_id);
})