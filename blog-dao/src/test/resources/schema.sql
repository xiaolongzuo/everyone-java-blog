--2016-5-8 zuoxiaolong
--创建数据库
DROP SCHEMA `java_blog_test`;

CREATE SCHEMA `java_blog_test` DEFAULT CHARACTER SET utf8 ;

--创建WEB端用户表
CREATE TABLE `java_blog_test`.`web_user` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` VARCHAR(45) NOT NULL COMMENT '用户名',
  `password` VARCHAR(45) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
)  ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='WEB端用户表';



