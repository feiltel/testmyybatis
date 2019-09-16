package com.nut2014.pojo;

public class SearchBean {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    private String key;

    public SearchBean(String name, String key) {
        this.name = name;
        this.key = key;
    }
}
