package com.nut2014.entity;



import java.io.Serializable;

public class User implements Serializable {
    public User() {
    }

    private static final long serialVersionUID = -3330851033429007657L;

    public User(String userName, String passWord, String realName) {
        this.userName = userName;
        this.passWord = passWord;
        this.realName = realName;
    }
    public User(String userName, String passWord, String realName,String avatarPath) {
        this.userName = userName;
        this.passWord = passWord;
        this.realName = realName;
        this.avatarPath=avatarPath;
    }



    private String avatarPath ;

    private Integer id;
    private String userName;
    private String passWord;
    private String realName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", realName='" + realName + '\'' +
                ", avatarPath='" + avatarPath + '\'' +
                '}';
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }
}
