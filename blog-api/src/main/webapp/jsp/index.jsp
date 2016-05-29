<%--

    Copyright 2002-2016 the original author or authors.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
    
--%>
<%--
  User: Xiaolong Zuo
  Time: 16/5/8 18:22
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN" ng-app="starter">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no, width=device-width">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>首页 - Blog</title>

    <link href="${cacheUrl}/front/lib/css/materialize.min.css" rel="stylesheet">
    <link href="${cacheUrl}/front/lib/fonts/material-icons.css" rel="stylesheet">

    <!-- 自定义样式表 -->
    <link href="${cacheUrl}/front/css/style.min.css" rel="stylesheet">

    <!-- Materialize js -->
    <script src="${cacheUrl}/front/lib/js/jquery.min.js"></script>
    <script src="${cacheUrl}/front/lib/js/materialize.min.js"></script>

    <!-- 自己的js -->
    <!-- <script src="js/main.js"></script> -->
    <script src="${cacheUrl}/front/js/navbar.js"></script>


</head>
<body>
<div class="index rank output">

    <!-- 移动端顶部左侧抽屉菜单 开始 -->
    <div class="navbar-mobile">
        <nav>
            <ul id="slide-out" class="side-nav fixed">
                <li><a href="#"><i class="material-icons">home</i> 首页</a></li>
                <li><a href="#"><i class="material-icons">format_list_bulleted</i> 专题</a></li>
                <li class="divider"></li>
                <li><a href="#"><i class="material-icons">exit_to_app</i> 登录</a></li>
            </ul>
            <a href="#" data-activates="slide-out" class="button-collapse"><i class="material-icons">apps</i></a>

            <ul class="login-wrap right">
                <li><a href="#" class="login"><i class="material-icons">exit_to_app</i> 登录</a></li>
                <li><a href="#" class="signup"><i class="material-icons">person_add</i> 注册</a></li>
            </ul>
        </nav>
    </div>
    <!-- 移动端顶部左侧抽屉菜单 结束 -->


    <!-- 桌面端左侧菜单 开始 -->
    <div class="navbar navbar-blog expanded">
        <!-- 菜单靠顶部的按钮组 -->
        <div class="dropdown">
            <a class="active logo" role="button" title="首页" href="#">
                <!-- <b>叼</b> -->
                <i class="material-icons">home</i>
                <span class="title">首页</span>
            </a>
            <a title="专题" href="#">
                <!-- <i class="fa fa-th"></i> -->
                <i class="material-icons">format_list_bulleted</i>
                <span class="title">专题</span>
            </a>
            <a title="XXX" href="#">
                <i class="material-icons"></i>
                <span class="title">XXXX</span>
            </a>
        </div>

        <!-- 菜单靠底部的按钮组 -->
        <div class="nav-user">
            <a href="#" title="显示模式">
                <i class="material-icons">text_format</i>
                <span class="title">显示模式</span>
            </a>

            <a class="signin" title="登录" href="#">
                <i class="material-icons">exit_to_app</i>
                <span class="title">登录</span>
            </a>
        </div>
    </div>
    <!-- 桌面端左侧菜单 结束 -->

    <!-- 主体部分 开始 -->
    <div class="row-fluid container">
        <div class="recommended">
            <div class="row">
                <!-- 主体左侧大图部分 开始 -->
                <div class="col s0 m3 left-aside">
                    <!-- aside -->
                    <div class="cover-img" style="background-image: url('${cacheUrl}/front/img/recommended.jpg')"></div>
                    <div class="bottom-block">
                        <h1>大家的博客</h1>
                        <h3>学习的路，现在开始</h3>
                        <p>Java程序猿自己给自己打造的博客网站</p>
                        <a class="btn btn-success" id="write-button" href="#">提笔写篇文章</a>
                    </div>
                </div>
                <!-- 主体左侧大图部分 结束 -->

                <!-- 主体右侧文章列表部分 开始 -->
                <div class="col s12 m7 offset-m3 right-content">
                    <!-- 顶部文章列表分类 开始 -->
                    <div class="page-title">
                        <ul class="recommened-nav navigation clearfix">
                            <li class="active">
                                <a href="#">技术</a>
                            </li>
                            <li class="bonus">
                                <a href="#">职场</a>
                            </li>
                            <li class="bonus">
                                <a href="#">生活</a>
                            </li>
                            <!-- <img class="hide loader-tiny" src="img/tiny.gif" alt="Tiny"> -->
                        </ul>
                    </div><!-- page-title 结束 -->
                    <!-- 顶部文章列表分类 结束 -->

                    <!-- 精简的推荐文章列表部分 开始 -->
                    <div class="list-container container list-group-rank simple">
                        <ul class="article-list">
                            <li>
                                <h4 class="title">
                                    <span class="chip light-green darken-1">最多阅读</span>
                                    <a target="_blank" href="#">结合Jexus + Kestrel 部署 asp.net core 生产环境</a>
                                </h4>
                            </li>
                            <li>
                                <h4 class="title">
                                    <span class="chip light-green darken-1">最多评论</span>
                                    <a target="_blank" href="#">新版Chrome浏览器去掉了一个常用功能：点退格键回到之前网页</a>
                                </h4>
                            </li>
                            <li>
                                <h4 class="title">
                                    <span class="chip light-green darken-1">最叼文章</span>
                                    <a target="_blank" href="#">非科班出生的码农自述：我是如何在几周内从12W升至25W美元年薪</a>
                                </h4>
                            </li>
                        </ul>
                    </div>
                    <!-- 精简的推荐文章列表部分 结束 -->

                    <!-- 常规文章列表部分 开始 -->
                    <div class="list-container container list-group">
                        <div class="row">
                            <div class="col s12">
                                <ul class="article-list thumbnails">
                                    <!-- 一条常规文章 开始 -->
                                    <li class="have-img">
                                        <a class="wrap-img" href="#"><img src="${cacheUrl}/front/img/pic-1.png"></a>
                                        <div>
                                            <p class="list-top">
                                                <a class="author-name blue-link" target="_blank" href="#">测试作者</a>
                                                <em>·</em>
                                                <span class="time" data-shared-at="2016-05-13T21:47:39+08:00">1天之前</span>
                                            </p>
                                            <h4 class="title"><a target="_blank" href="#">测试标题测试标题测试标题测试标题测试标题</a></h4>
                                            <div class="list-footer">
                                                <a target="_blank" href="#">
                                                    阅 <span>2333</span>
                                                </a>
                                                <a target="_blank" href="#">
                                                    · 评 <span>23</span>
                                                </a>
                                                <a target="_blank" href="#">
                                                    · 叼 <span>233</span>
                                                </a>
                                                <a target="_blank" href="#">
                                                    · 赏 <span>233</span>
                                                </a>
                                            </div>
                                        </div>
                                    </li>
                                    <!-- 一条常规文章 结束 -->

                                    <!-- 一条常规文章 开始 -->
                                    <li class="have-img">
                                        <a class="wrap-img" href="#"><img src="${cacheUrl}/front/img/pic-1.png"></a>
                                        <div>
                                            <p class="list-top">
                                                <a class="author-name blue-link" target="_blank" href="#">测试作者</a>
                                                <em>·</em>
                                                <span class="time" data-shared-at="2016-05-13T21:47:39+08:00">1天之前</span>
                                            </p>
                                            <h4 class="title"><a target="_blank" href="#">测试标题测试标题测试标题测试标题测试标题</a></h4>
                                            <div class="list-footer">
                                                <a target="_blank" href="#">
                                                    阅 <span>2333</span>
                                                </a>
                                                <a target="_blank" href="#">
                                                    · 评 <span>23</span>
                                                </a>
                                                <a target="_blank" href="#">
                                                    · 叼 <span>233</span>
                                                </a>
                                                <a target="_blank" href="#">
                                                    · 赏 <span>233</span>
                                                </a>
                                            </div>
                                        </div>
                                    </li>
                                    <!-- 一条常规文章 结束 -->

                                    <!-- 一条常规文章 开始 -->
                                    <li class="have-img">
                                        <a class="wrap-img" href="#"><img src="${cacheUrl}/front/img/pic-1.png"></a>
                                        <div>
                                            <p class="list-top">
                                                <a class="author-name blue-link" target="_blank" href="#">测试作者</a>
                                                <em>·</em>
                                                <span class="time" data-shared-at="2016-05-13T21:47:39+08:00">1天之前</span>
                                            </p>
                                            <h4 class="title"><a target="_blank" href="#">测试标题测试标题测试标题测试标题测试标题</a></h4>
                                            <div class="list-footer">
                                                <a target="_blank" href="#">
                                                    阅 <span>2333</span>
                                                </a>
                                                <a target="_blank" href="#">
                                                    · 评 <span>23</span>
                                                </a>
                                                <a target="_blank" href="#">
                                                    · 叼 <span>233</span>
                                                </a>
                                                <a target="_blank" href="#">
                                                    · 赏 <span>233</span>
                                                </a>
                                            </div>
                                        </div>
                                    </li>
                                    <!-- 一条常规文章 结束 -->

                                    <!-- 一条常规文章 开始 -->
                                    <li class="have-img">
                                        <a class="wrap-img" href="#"><img src="${cacheUrl}/front/img/pic-1.png"></a>
                                        <div>
                                            <p class="list-top">
                                                <a class="author-name blue-link" target="_blank" href="#">测试作者</a>
                                                <em>·</em>
                                                <span class="time" data-shared-at="2016-05-13T21:47:39+08:00">1天之前</span>
                                            </p>
                                            <h4 class="title"><a target="_blank" href="#">测试标题测试标题测试标题测试标题测试标题</a></h4>
                                            <div class="list-footer">
                                                <a target="_blank" href="#">
                                                    阅 <span>2333</span>
                                                </a>
                                                <a target="_blank" href="#">
                                                    · 评 <span>23</span>
                                                </a>
                                                <a target="_blank" href="#">
                                                    · 叼 <span>233</span>
                                                </a>
                                                <a target="_blank" href="#">
                                                    · 赏 <span>233</span>
                                                </a>
                                            </div>
                                        </div>
                                    </li>
                                    <!-- 一条常规文章 结束 -->

                                    <!-- 一条常规文章 开始 -->
                                    <li class="have-img">
                                        <a class="wrap-img" href="#"><img src="${cacheUrl}/front/img/pic-1.png"></a>
                                        <div>
                                            <p class="list-top">
                                                <a class="author-name blue-link" target="_blank" href="#">测试作者</a>
                                                <em>·</em>
                                                <span class="time" data-shared-at="2016-05-13T21:47:39+08:00">1天之前</span>
                                            </p>
                                            <h4 class="title"><a target="_blank" href="#">测试标题测试标题测试标题测试标题测试标题</a></h4>
                                            <div class="list-footer">
                                                <a target="_blank" href="#">
                                                    阅 <span>2333</span>
                                                </a>
                                                <a target="_blank" href="#">
                                                    · 评 <span>23</span>
                                                </a>
                                                <a target="_blank" href="#">
                                                    · 叼 <span>233</span>
                                                </a>
                                                <a target="_blank" href="#">
                                                    · 赏 <span>233</span>
                                                </a>
                                            </div>
                                        </div>
                                    </li>
                                    <!-- 一条常规文章 结束 -->

                                    <!-- 一条常规文章 开始 -->
                                    <li class="have-img">
                                        <a class="wrap-img" href="#"><img src="${cacheUrl}/front/img/pic-1.png"></a>
                                        <div>
                                            <p class="list-top">
                                                <a class="author-name blue-link" target="_blank" href="#">测试作者</a>
                                                <em>·</em>
                                                <span class="time" data-shared-at="2016-05-13T21:47:39+08:00">1天之前</span>
                                            </p>
                                            <h4 class="title"><a target="_blank" href="#">测试标题测试标题测试标题测试标题测试标题</a></h4>
                                            <div class="list-footer">
                                                <a target="_blank" href="#">
                                                    阅 <span>2333</span>
                                                </a>
                                                <a target="_blank" href="#">
                                                    · 评 <span>23</span>
                                                </a>
                                                <a target="_blank" href="#">
                                                    · 叼 <span>233</span>
                                                </a>
                                                <a target="_blank" href="#">
                                                    · 赏 <span>233</span>
                                                </a>
                                            </div>
                                        </div>
                                    </li>
                                    <!-- 一条常规文章 结束 -->
                                </ul>
                            </div>
                        </div>
                    </div><!-- list-container 结束 -->
                    <!-- 常规文章列表部分 结束 -->

                </div><!-- 主体右侧文章列表部分 结束 -->
            </div>

        </div>


    </div><!-- 主体部分 结束 -->

</div>
</body>
</html>
