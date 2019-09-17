

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