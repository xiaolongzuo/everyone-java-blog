var gulp = require('gulp'),
	less = require('gulp-less'),
	concat = require('gulp-concat'),
	cssmin = require('gulp-minify-css'),
	notify = require('gulp-notify'),
	plumber = require('gulp-plumber'),
	autoprefixer = require('gulp-autoprefixer'),
	livereload = require('gulp-livereload'),
	connect = require('gulp-connect'),
	clean = require('gulp-clean'),
	imagemin = require('gulp-imagemin'),
	cache = require('gulp-cache');

gulp.task('default', function() {
	// 将你的默认的任务代码放在这
	gulp.start('webserver', 'watch', 'imagemin', 'buildJsLib', 'buildCssLib', 'buildFontsLib', 'initDistJs');
});

gulp.task('webserver', function() {
	connect.server({
		root: 'src',
		port: 8004,
		livereload: true
	});
});

gulp.task('setLess', ['cleanCss'], function () {
	gulp.src('src/less/*.less')
		.pipe(plumber({errorHandler: notify.onError('Error: <%= error.message %>')}))
		.pipe(less())
		.pipe(autoprefixer({
			browsers: ['last 2 versions'],
			cascade: true,
			remove: true
		}))
		.pipe(gulp.dest('src/css'))
		.pipe(concat('style.min.css'))
		.pipe(cssmin())
		.pipe(gulp.dest('src/css'))
        .pipe(gulp.dest('../blog-cache/src/main/webapp/front/css'))
		.pipe(connect.reload());
});

gulp.task('watch',['setLess'], function() {
	gulp.watch('src/less/*.less', ['setLess']);
	gulp.watch(['src/img-original/*.{png,jpg,gif,ico}', 'src/img-original/*/*.{png,jpg,gif,ico}'], ['imagemin']);
	gulp.watch(['src/*.html', 'src/templates/*.html'], ['re_html']);
	gulp.watch(['src/js/*.js'], ['re_js']);
	gulp.watch(['src/js/data-test/*.json'], ['re_html']);
});

gulp.task('re_html', function() {
	gulp.src(['src/*.html', 'src/templates/*.html'])
		.pipe(connect.reload());
});
gulp.task('re_js', function() {
	gulp.src(['src/js/*.js', 'src/js/data-test/*.json'])
		.pipe(connect.reload());
});

// 压缩图片
gulp.task('imagemin', ['cleanImg'], function() {
	gulp.src(['src/img-original/*.{png,jpg,gif,ico}', 'src/img-original/*/*.{png,jpg,gif,ico}'])
		.pipe(cache(imagemin()))
		.pipe(gulp.dest('src/img'))
        .pipe(gulp.dest('../blog-cache/src/main/webapp/front/img'))
		.pipe(connect.reload());
});

// 生成调用库
gulp.task('buildJsLib', ['cleanLib'], function() {
	gulp.src([
		'bower_components/angular/angular.min.js',
		'bower_components/jquery/dist/jquery.min.js',
		'bower_components/Materialize/dist/js/materialize.min.js',
		'bower_components/angular-ui-router/release/angular-ui-router.min.js'
		])
		.pipe(gulp.dest('src/lib/js'))
        .pipe(gulp.dest('../blog-cache/src/main/webapp/front/lib/js'));
});

gulp.task('buildCssLib', ['cleanLib'], function() {
	gulp.src([
		'bower_components/Materialize/dist/css/materialize.min.css'
		])
		.pipe(gulp.dest('src/lib/css'))
        .pipe(gulp.dest('../blog-cache/src/main/webapp/front/lib/css'));
});

gulp.task('buildFontsLib', ['cleanLib'], function() {
	gulp.src([
		'bower_components/material-design-icons/iconfont/*'
		])
		.pipe(gulp.dest('src/lib/fonts'))
        .pipe(gulp.dest('../blog-cache/src/main/webapp/front/lib/fonts'));
});

// 生成dist
// gulp.task('initDistHtml', ['cleanDist'], function() {
// 	gulp.src([
// 		'src/*.html'
// 		])
// 		.pipe(gulp.dest('dist'));
// });
gulp.task('initDistJs', [], function() {
	gulp.src([
		'src/js/*'
		])
        .pipe(gulp.dest('../blog-cache/src/main/webapp/front/js'));
});


// 清空样式
gulp.task('cleanCss', function() {
	return gulp.src(['src/css/*.css', '../blog-cache/src/main/webapp/front/css/*.css'], {read: false})
	.pipe(clean({force: true}));
});
// 清空图片
gulp.task('cleanImg', function() {
	return gulp.src(['src/img/*', '../blog-cache/src/main/webapp/front/img/*'], {read: false})
	.pipe(clean({force: true}));
});
// 清空js
gulp.task('cleanJs', function() {
	return gulp.src(['src/js/*', '../blog-cache/src/main/webapp/front/js/*'], {read: false})
	.pipe(clean({force: true}));
});
// 清空lib
gulp.task('cleanLib', function() {
	return gulp.src(['src/lib/js/*', 'src/lib/css/*', 'src/lib/fonts/*', '../blog-cache/src/main/webapp/front/lib/js/*', '../blog-cache/src/main/webapp/front/lib/css/*', '../blog-cache/src/main/webapp/front/lib/fonts/*'], {read: false})
	.pipe(clean({force: true}));
});
// 清空全部
gulp.task('cleanAll', function() {
	return gulp.src(['src/css/*.css', 'src/img/*', 'src/lib/js/*', 'src/lib/css/*', 'src/lib/fonts/*', '../blog-cache/src/main/webapp/front/css/*.css', '../blog-cache/src/main/webapp/front/img/*', '../blog-cache/src/main/webapp/front/lib/js/*', '../blog-cache/src/main/webapp/front/lib/css/*', '../blog-cache/src/main/webapp/front/lib/fonts/*'], {read: false})
	.pipe(clean({force: true}));
});
// 清空dist
gulp.task('cleanDistJS', function() {
	return gulp.src(['../blog-cache/src/main/webapp/front/lib/js/*'], {read: false})
	.pipe(clean({force: true}));
});