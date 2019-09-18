package com.nut2014.entity;


import java.io.Serializable;
import java.util.Date;

public class MyLog implements Serializable {
    /**
     * 默认构造方法必须有
     */
    public MyLog() {
    }

    public MyLog(int user_id, String content, String description) {
        this.user_id = user_id;
        this.content = content;
        this.description = description;
    }

    private static final long serialVersionUID = -3330851033429007657L;

    private int user_id;
    private int id;
    private String content;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    private String description;
    private Date update_time;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
