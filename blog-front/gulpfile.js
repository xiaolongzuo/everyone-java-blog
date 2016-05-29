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
	gulp.start('webserver', 'watch', 'initDistHtml', 'initDistJs', 'imagemin', 'buildJsLib', 'buildCssLib', 'buildFontsLib');
});

gulp.task('webserver', function() {
	connect.server({
		root: 'dist',
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
		.pipe(gulp.dest('dist/css'))
		.pipe(connect.reload());
});

gulp.task('watch',['setLess'], function() {
	gulp.watch('src/less/*.less', ['setLess']);
	gulp.watch(['src/img-original/*.{png,jpg,gif,ico}', 'src/img-original/*/*.{png,jpg,gif,ico}'], ['imagemin']);
	gulp.watch(['src/*.html'], ['re_html']);
	gulp.watch(['src/js/*.js'], ['re_js']);
});

gulp.task('re_html', function() {
	gulp.src(['src/*.html'])
		.pipe(gulp.dest('dist'))
		.pipe(connect.reload());
});
gulp.task('re_js', function() {
	gulp.src(['src/js/*.js'])
		.pipe(gulp.dest('dist/js'))
		.pipe(connect.reload());
});

// 压缩图片
gulp.task('imagemin', ['cleanImg'], function() {
	gulp.src(['src/img-original/*.{png,jpg,gif,ico}', 'src/img-original/*/*.{png,jpg,gif,ico}'])
		.pipe(gulp.dest('src/img'))
		.pipe(cache(imagemin()))
		.pipe(gulp.dest('dist/img'))
		.pipe(connect.reload());
});

// 生成调用库
gulp.task('buildJsLib', ['cleanLib'], function() {
	gulp.src([
		'bower_components/jquery/dist/jquery.min.js',
		'bower_components/Materialize/dist/js/materialize.min.js'
		])
		.pipe(gulp.dest('src/lib/js'))
		.pipe(gulp.dest('dist/lib/js'));
});

gulp.task('buildCssLib', ['cleanLib'], function() {
	gulp.src([
		'bower_components/Materialize/dist/css/materialize.min.css'
		])
		.pipe(gulp.dest('src/lib/css'))
		.pipe(gulp.dest('dist/lib/css'));
});

gulp.task('buildFontsLib', ['cleanLib'], function() {
	gulp.src([
		'bower_components/material-design-icons/iconfont/*'
		])
		.pipe(gulp.dest('src/lib/fonts'))
		.pipe(gulp.dest('dist/lib/fonts'));
});

// 生成dist
gulp.task('initDistHtml', ['cleanDist'], function() {
	gulp.src([
		'src/*.html'
		])
		.pipe(gulp.dest('dist'));
});
gulp.task('initDistJs', ['cleanDist'], function() {
	gulp.src([
		'src/js/*.js'
		])
		.pipe(gulp.dest('dist/js'));
});


// 清空样式
gulp.task('cleanCss', function() {
	return gulp.src(['src/css/*.css', 'dist/css/*.css'], {read: false})
	.pipe(clean({force: true}));
});
// 清空图片
gulp.task('cleanImg', function() {
	return gulp.src(['src/img/*', 'dist/img/*'], {read: false})
	.pipe(clean({force: true}));
});
// 清空js
gulp.task('cleanJs', function() {
	return gulp.src(['src/js/*', 'dist/js/*'], {read: false})
	.pipe(clean({force: true}));
});
// 清空lib
gulp.task('cleanLib', function() {
	return gulp.src(['src/lib/js/*', 'src/lib/css/*', 'src/lib/fonts/*', 'dist/lib/js/*', 'dist/lib/css/*', 'dist/lib/fonts/*'], {read: false})
	.pipe(clean({force: true}));
});
// 清空全部
gulp.task('cleanAll', function() {
	return gulp.src(['src/css/*.css', 'src/img/*', 'src/lib/js/*', 'src/lib/css/*', 'src/lib/fonts/*', 'dist/css/*.css', 'dist/img/*', 'dist/lib/js/*', 'dist/lib/css/*', 'dist/lib/fonts/*'], {read: false})
	.pipe(clean({force: true}));
});
// 清空dist
gulp.task('cleanDist', function() {
	return gulp.src(['dist/*.html'], {read: false})
	.pipe(clean({force: true}));
});