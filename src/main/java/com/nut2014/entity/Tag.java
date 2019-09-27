package com.nut2014.entity;

import java.io.Serializable;

public class Tag implements Serializable {
    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    private String name;
    private int id;

    public Tag(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
