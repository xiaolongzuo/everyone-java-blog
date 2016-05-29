$(document).ready(function($) {
	$('.js-jump-top').on('click', function(e) {
		e.preventDefault();
		$('html, body').animate({'scrollTop': 0});
	});
});