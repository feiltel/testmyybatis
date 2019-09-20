####MySql 支持Emoji
/*1.my.ini 增加配置*/
/*

*/
/*2.重启mysql服务*/
/*显示数据库编码*/
SHOW VARIABLES WHERE Variable_name LIKE 'character_set_%' OR Variable_name LIKE 'collation%';
/*修改数据库编码*/
ALTER DATABASE test CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
/*修改表编码*/
ALTER TABLE cover CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;


/*蠕虫复制*/
insert into user (userName, passWord,realName,avatarPath) select userName, passWord,realName,avatarPath from user;

insert into cover (user_id, coverImgPath,coverDes,likeNumber,tag_id) select user_id, coverImgPath,coverDes,likeNumber,tag_id from cover;

SELECT
  cover.likeNumber,
  cover.coverDes,
  cover.imgPath,
  cover.user_id,
  user.avatarPath
FROM
  cover
  LEFT JOIN user ON cover.user_id = user.id where cover.id=1


