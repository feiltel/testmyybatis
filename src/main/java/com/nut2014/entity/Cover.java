package com.nut2014.entity;


import java.io.Serializable;

public class Cover implements Serializable {
    /**
     * 默认构造方法必须有
     */
    public Cover() {
    }

    private static final long serialVersionUID = -3330851033429007657L;

    public Cover(int user_id, String coverImgPath, String coverDes, int likeNumber) {
        this.user_id = user_id;
        this.coverImgPath = coverImgPath;
        this.coverDes = coverDes;
        this.likeNumber = likeNumber;
    }
    private String avatarPath;
    private String userName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCoverImgPath() {
        return coverImgPath;
    }

    public void setCoverImgPath(String coverImgPath) {
        this.coverImgPath = coverImgPath;
    }

    public String getCoverDes() {
        return coverDes;
    }

    public void setCoverDes(String coverDes) {
        this.coverDes = coverDes;
    }

    public int getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(int likeNumber) {
        this.likeNumber = likeNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", user_id='" + user_id + '\'' +
                ", coverImgPath='" + coverImgPath + '\'' +
                ", coverDes='" + coverDes + '\'' +
                ", likeNumber='" + likeNumber + '\'' +
                '}';
    }

    private int id;
    private int user_id;
    private String coverImgPath;
    private String coverDes;
    private int likeNumber;
    private String tagName;
    private int tag_id;


    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }
}
