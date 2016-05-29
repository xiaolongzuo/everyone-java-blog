angular.module('starter', ['ui.router', 'starter.controllers', 'starter.services', 'starter.directives'])

.config(function($stateProvider, $urlRouterProvider) {
	$urlRouterProvider.otherwise("/")

	$stateProvider

		.state("rank", {
			url: '/',
			templateUrl: 'templates/recommend.html',
			controller: 'recommendCtrl'
		})

		.state("article", {
			url: '/article/:articleId',
			templateUrl: '../templates/article.html',
			controller: 'articleCtrl'
		})
		
})