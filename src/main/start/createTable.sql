CREATE TABLE `user` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `userName` varchar(32) NOT NULL comment '用户名',
  `passWord` varchar(50) NOT NULL comment '密码',
  `realName` varchar(32) DEFAULT NULL comment '真实名称',
  `avatarPath` varchar(200) DEFAULT NULL comment '头像路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;




CREATE TABLE `tag` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

CREATE TABLE `cover` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `user_id` int(120) NOT NULL,
  `coverImgPath` varchar(120) NOT NULL,
  `coverDes` varchar(120) NOT NULL,
  `likeNumber` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE `myLog` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `user_id` int(32) NOT NULL,
  `content` varchar(200) NOT NULL,
  `description` varchar(10000) DEFAULT NULL,
  `update_time`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

/*蠕虫复制*/
insert into user (userName, passWord,realName,avatarPath) select userName, passWord,realName,avatarPath from user;

insert into cover (user_id, coverImgPath,coverDes,likeNumber,tag_id) select user_id, coverImgPath,coverDes,likeNumber,tag_id from cover;

SELECT
  cover.likeNumber,
  cover.coverDes,
  cover.coverImgPath,
  cover.user_id,
  user.avatarPath
FROM
  cover
  LEFT JOIN user ON cover.user_id = user.id where cover.id=1