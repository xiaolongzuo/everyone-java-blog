# 前端说明文档

> 本文档为 `everyone-java-blog` 前端部分说明文档，供内部开发人员阅读。
> 
> 更新日志见：[CHANGELOG.md](https://github.com/xiaolongzuo/everyone-java-blog/blob/master/blog-front/README.md)

## 目录（index）

* [文档结构](#contents)
* [页面说明](#instruction)
    - [UI框架（Materialize）](#materialize)
    - [index.html](#indexhtml)
    - [article.html](#articlehtml)
* [前端开发参与说明](#develop)
    - [包管理](#bower)
    - [自动化构建](#gulp)


-----------------------------------------------------------------


## 文档结构（Contents）

* dist/                         【**提供给后端的静态页面目录**】
    - css/                      【样式】
    - img/                      【图片资源】
    - js/                       【脚本文件】
    - lib/                      【第三方库】
    - index.html                【首页】
    - article.html              【文章页】
    - list.html                 【没有榜单那三条的列表页，暂时没啥用】


-----------------------------------------------------------------


**以下目录结构后端请忽略**

* src/                          【后端请忽略】【前端源文件】
    - css/                      【预编译后的 CSS 文件，没啥卵用】
    - img/                      【压缩后的图片，无需手动修改】
    - img-original/             【未压缩的图片，新增/删改图片需要放入此目录】
    - js/                       【脚本文件】
    - less/                     【 LESS 样式文件，新增/删改样式在此目录进行】【样式现在较乱，有待重构】
        + article.less
        + color.less
        + comment.less
        + index.less
        + main.less
        + media-query.less
        + navbar.less
    - lib/                      【第三方库文件】
    - index.html                【首页】
    - article.html              【文章页】
    - list.html                 【没有榜单那三条的列表页，暂时没啥用】
* bower.json                    【Bower 配置文件】
* gulpfile.js                   【Gulp 配置文件】
* package.json                  【npm 配置文件】
* README.md                     【项目说明】
* CHANGELOG.md                  【更新日志】


-----------------------------------------------------------------


## 页面详细说明（Instruction）

### 框架（Materialize）

前端部分没有使用 `Bootstrap` 作 UI 框架，使用了和 `Bootstrap` 同类的 `Materialize` 。因为 `Bootstrap 3` 的组件在处理移动端响应式时候有缺陷，而且 UI 风格和咱们项目差别较大；而 `Bootstrap 4` 还在初期测试，所以没有采用。

`Materialize` 和 `Bootstrap` 使用方法基本一致。

> `Materialize` 官方网站：[http://materializecss.com/](http://materializecss.com/)

### index.html

> 首页 - 对应 技术/职场/生活 三个分类的页面

**头部样式和脚本引用，要保证 css、img、js、lib 四个目录间的相对路径和我这里的文件结构一致。**

* 移动端顶部左侧抽屉菜单
    - 放在 `ul.side-nav` 中的内容，会被折叠到左侧抽屉，默认不显示，点击左上角按钮显示；
* 桌面端左侧菜单
    - 内容还没确定，里面几个链接是随便写的；
    - 需要靠顶部排列的，就放在 `div.dropdown` 中；需要靠底部排列的，就放在 `div.nav-user` 中。
* 主体左侧大图部分
    - 图片以后可能会做成好几张可以替换或随机显示的，所以我把图片地址的样式写在了行内： `<div class="cover-img" style="background-image: url('img/recommended.jpg')"></div>` ；
* 主体右侧文章列表部分
    - 文章分类
        + 现在分为三类，当前分类的样式 控制 `<li>` 标签的 `class="active"` 就可以切换了；
    - ~~推荐文章 列表（`div.list-group-rank`）~~
        + ~~比下面的 常规列表 ，多了个~~
        > ~~`<div class="rank-ctg"> <span>最多阅读</span> </div>`~~
        + ~~其余部分一样~~
    - **推荐文章 列表 精简版** （`div.list-group-rank.simple`）
        + 新的简化的推荐文章列表，没有缩略图、作者信息、文章阅读量等信息；
    - 常规文章 列表（`div.list-group`）
        + 和上面结构基本一致


### article.html

> 文章页

* 沿用首页的左侧导航；
* 比首页多了个 `js/go_to_top.js` 脚本引用。


-----------------------------------------------------------------


## 前端开发参与说明（Develop）【后端请忽略】

*开发过程中使用的包依赖管理工具 `Bower` 和自动化构建工具 `Gulp` 依赖于 `NodeJS` 。*


### 包管理（Bower）

本项目前端部分使用 `Bower` 进行外部的包和依赖的管理。

> `Bower` 官方网站：[http://bower.io/](http://bower.io/)
> 
> `Bower` 使用教程：[Bower——前端开发包管理工具](http://www.tuicool.com/articles/v2a2yq)

安装完成后，在命令行中进入项目目录，执行 `bower install --save-dev` ，下载相关资源。


### 自动化构建（Gulp）

本项目前端部分使用 `Gulp` 进行外部的包和依赖的管理。

> `Gulp` 官方网站：[http://gulpjs.com/](http://gulpjs.com/)

安装完成后，在命令行中进入项目目录，执行 `npm install` ，下载 `Gulp` 及相关插件。

本项目自动构建的内容包括：

* 创建本地服务器： [http://localhost:8004/](http://localhost:8004/)
* 将项目相关第三方库整合在 `src/lib` 目录下；
* 监听项目文件修改（html/less/js/img），文件修改后，浏览器自动重载资源；
* 保存 `LESS` 文件后，自动执行预编译生成 `CSS` 文件到 `src/css` 目录；
* 压缩 `img-original` 中的原始图片，存放到 `src/img` 目录下；
* 打包项目生产内容到 `dist` 目录，供后端人员使用。

前端人员开始开发前，在命令行中进入项目目录，执行 `gulp` ，即可开始执行上述构建工作。

*在 Git 上提交时，不需要提交 `bower_components` 、 `node_modules` 目录。*

