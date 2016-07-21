CREATE SCHEMA `java_blog` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `java_blog`.`blog_config` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `web_user_id` INT NOT NULL COMMENT '用户ID',
  `introduction` VARCHAR(200) NOT NULL DEFAULT '' COMMENT '用户简介',
  `address` VARCHAR(100) NOT NULL COMMENT '用户博客地址',
  PRIMARY KEY (`id`)
)  ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='WEB端用户博客配置表';

CREATE TABLE `java_blog`.`web_user` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `username` VARCHAR(45) NOT NULL COMMENT '用户名',
  `password` VARCHAR(45) NOT NULL COMMENT '密码',
  `password_salt` VARCHAR(45) NOT NULL COMMENT '加密密码',
  `nickname` VARCHAR(45) NOT NULL DEFAULT '' COMMENT '昵称',
  `enable` TINYINT(1) NOT NULL DEFAULT 1 COMMENT '权限',
  PRIMARY KEY (`id`)
)  ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='WEB端用户表';

CREATE TABLE `java_blog`.`message_box` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `content` VARCHAR(2000) NOT NULL COMMENT '消息内容',
  `sender` INT NOT NULL COMMENT '发送者',
  `receiver` INT NOT NULL COMMENT '接收者',
  `status` TINYINT(2) NOT NULL COMMENT '状态  1:已读;  2:未读;  3:接收者已删除;  4:发送者已删除;  5:已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT = '短信发送记录表';

CREATE TABLE `java_blog`.`user_article` (
	`id` INT NOT NULL AUTO_INCREMENT COMMENT '主键',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	`web_user_id` INT NOT NULL COMMENT '用户ID',
	`category_id` INT NOT NULL COMMENT '分类id',
	`title` VARCHAR(128) NOT NULL COMMENT '文章标题',
	`content` TEXT NOT NULL COMMENT '文章内容',
	`read_times` INT NOT NULL COMMENT '阅读次数',
	`thumbup_times` INT NOT NULL COMMENT '点赞次数',
	`is_main_page` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否在主页显示',
	`status` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '状态:0,草稿 1,发布 2,删除',
	PRIMARY KEY (`id`)
)  ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户文章表';

CREATE TABLE `java_blog`.`article_comment` (
	`id` INT NOT NULL AUTO_INCREMENT COMMENT '主键',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	`web_user_id` INT NOT NULL COMMENT '用户id',
	`article_id` INT NOT NULL COMMENT '文章ID',
	`comment` VARCHAR(2000) NOT NULL COMMENT '评论内容',
	`reply_comment_id` INT NULL COMMENT '父级评论',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='文章评论表';

CREATE TABLE `java_blog`.`user_opeartion_record` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `web_user_id` INT NOT NULL COMMENT '用户ID',
  `operation_type` TINYINT(2) NOT NULL COMMENT '操作类型',
  PRIMARY KEY (`id`)
)  ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户操作类型表';

CREATE  TABLE `java_blog`.`article_category` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `category_name` VARCHAR (20) NOT NULL COMMENT '博客文种名称',
  PRIMARY  KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT  CHARSET=utf8 COMMENT='博客文种分类表';

CREATE TABLE `java_blog`.`contributor` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `contributor_name` VARCHAR(20) NOT NULL COMMENT '贡献者名称',
  `personal_url` VARCHAR(200) NOT NULL COMMENT '贡献者地址',
  PRIMARY KEY (`id`)
)  ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='贡献者列表';

CREATE TABLE `java_blog`.`resource` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `resource_path` VARCHAR(100) NOT NULL COMMENT '资源路径',
  `resource_type` TINYINT(2) NOT NULL COMMENT '资源类型',
  PRIMARY KEY (`id`)
)  ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='博客资源表';

ALTER TABLE `java_blog`.`blog_config` ADD UNIQUE ( `web_user_id` );
ALTER TABLE `java_blog`.`blog_config` ADD UNIQUE ( `address` );
ALTER TABLE `java_blog`.`web_user` ADD UNIQUE ( `username` );
ALTER TABLE `java_blog`.`web_user` ADD UNIQUE ( `nickname` );
ALTER TABLE `java_blog`.`contributor` ADD UNIQUE ( `contributor_name` );
ALTER TABLE `java_blog`.`resource` ADD UNIQUE ( `resource_path` );

ALTER TABLE `java_blog`.`message_box` ADD COLUMN `title` VARCHAR(128) NOT null DEFAULT '' COMMENT '标题';
ALTER TABLE `java_blog`.`web_user` ADD COLUMN `token` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '移动端TOKEN';

ALTER TABLE `java_blog`.`user_article` ADD COLUMN `comment_times` INT(11) NOT NULL COMMENT '评论次数';

ALTER TABLE `java_blog`.`blog_config` ADD COLUMN `blog_title` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '博客标题';
ALTER TABLE `java_blog`.`blog_config` ADD COLUMN `blog_sub_title` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '博客子标题';

ALTER TABLE `java_blog`.`article_comment` ADD COLUMN `parents_comment_id` VARCHAR(128) NOT NULL DEFAULT '0,' COMMENT '父级评论路径';
ALTER TABLE `java_blog`.`article_comment` MODIFY `reply_comment_id` INT(11) NOT NULL DEFAULT 0 COMMENT '父级评论';

ALTER TABLE `java_blog`.`user_article` MODIFY `read_times` int(11) NOT NULL DEFAULT 0 COMMENT '阅读次数';
ALTER TABLE `java_blog`.`user_article` MODIFY `thumbup_times` int(11) NOT NULL DEFAULT 0 COMMENT '点赞次数';
ALTER TABLE `java_blog`.`user_article` MODIFY `comment_times` int(11) NOT NULL DEFAULT 0 COMMENT '评论次数';
ALTER TABLE `java_blog`.`user_article` MODIFY  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态:0,草稿 1,发布 2,删除';

CREATE TABLE `java_blog`.`article_thumbup` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `article_id` INT NOT NULL COMMENT '文章ID',
  `ip_address` VARCHAR(100) NOT NULL COMMENT 'ip地址',
  PRIMARY KEY (`id`)
)  ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='文章点赞表';

ALTER TABLE `java_blog`.`user_article` ADD COLUMN `publish_time` DATETIME COMMENT '发布时间';



