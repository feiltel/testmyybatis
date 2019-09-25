/*创建数据库*/
create database test2 charset utf8mb4 COLLATE utf8mb4_general_ci;

drop table appVersion;
CREATE TABLE `appVersion` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `updateInfo` varchar(512) NOT NULL comment '更新信息',
  `versionCode` int(50) NOT NULL comment '版本',
  `versionName` varchar(125) DEFAULT NULL comment '版本名称',
  `downloadPath` varchar(256) DEFAULT NULL comment '下载地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4;
/*插入初始化数据*/
insert into appVersion(updateInfo,versionCode,versionName,downloadPath)values ('第一个版本',1,'0.0.1','http://192.168.31.196/upload/app2.apl')


drop table user;
CREATE TABLE `user` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `userName` varchar(32) NOT NULL unique comment '用户名',
  `passWord` varchar(50) NOT NULL comment '密码',
  `realName` varchar(32) NOT NULL comment '真实名称',
  `avatarPath` varchar(200) NOT NULL comment '头像路径',
  `bgImg` varchar(200) NOT NULL comment '主背景',
  `token` varchar(200) DEFAULT  '' comment 'TOKEN',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4;

insert into user(userName,passWord,realName)values ('admin','123456','admin')



drop table tag;
CREATE TABLE `tag` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4;

insert into tag(name)values ('music')

drop table cover;
CREATE TABLE `cover` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `user_id` int(120) NOT NULL,
  `coverImgPath` varchar(120) NOT NULL,
  `coverDes` varchar(120) NOT NULL,
  `likeNumber` int NOT NULL,
  `tag_id` int(120) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;

insert into cover(user_id,coverImgPath,coverDes,likeNumber,tag_id)values (1,'http://192.168.31.196::8080/image/default_avator.png',
'这是第一张专辑',1,1)

drop table myLog;
CREATE TABLE `myLog` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `user_id` int(32) NOT NULL,
  `content` varchar(200) NOT NULL,
  `description` varchar(10000) DEFAULT NULL,
  `update_time`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;

