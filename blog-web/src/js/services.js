angular.module('starter.services', [])

.factory('Recommend', function($q, $http){
	var recommend_nav_url = "/js/data-test/recommendNav.json";
	var recommend_list_url = "/js/data-test/listRecommend.json";
	var article_list_url = "/js/data-test/listArticle.json";

	return {
		get_nav: function () {
			var deferred = $q.defer();
			var promise = deferred.promise;

			$http.get(recommend_nav_url)
			.success(function(response) {
				deferred.resolve(response);
			})
			.error(function() {
				deferred.reject(error);
			});

			return promise;
		},
		get_list: function (_type) {
			if (_type === 'recommend') {
				_url = recommend_list_url;
			} else {
				_url = article_list_url;
			};
			var deferred = $q.defer();
			var promise = deferred.promise;

			$http.get(_url)
			.success(function(response) {
				deferred.resolve(response);
			})
			.error(function() {
				deferred.reject(error);
			});

			return promise;
		}
	}
})

.factory('Article', function ($q, $http) {
	var article_content_url = "/js/data-test/articleContent.json";
	var get_support_url = "/js/data-test/articleSupport.json";
	var get_like_url = "/js/data-test/articleLike.json";
	var get_comment_url = "/js/data-test/articleComment.json";


	return {
		get_content: function () {
			var deferred = $q.defer();
			var promise = deferred.promise;

			$http.get(article_content_url)
			.success(function(response) {
				deferred.resolve(response);
			})
			.error(function() {
				deferred.reject(error);
			});

			return promise;
		},
		get_support_data: function () {
			var deferred = $q.defer();
			var promise = deferred.promise;

			$http.get(get_support_url)
			.success(function(response) {
				deferred.resolve(response);
			})
			.error(function() {
				deferred.reject(error);
			});

			return promise;
		},
		get_like_data: function () {
			var deferred = $q.defer();
			var promise = deferred.promise;

			$http.get(get_like_url)
			.success(function(response) {
				deferred.resolve(response);
			})
			.error(function() {
				deferred.reject(error);
			});

			return promise;
		},
		get_comment: function () {
			var deferred = $q.defer();
			var promise = deferred.promise;

			$http.get(get_comment_url)
			.success(function(response) {
				deferred.resolve(response);
			})
			.error(function() {
				deferred.reject(error);
			});

			return promise;
		}
	};
})
