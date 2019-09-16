package com.nut2014.base;

import java.util.List;

public  interface BaseMapper<T> {
    /**************增**************/
     int add(T t);

    /**************删**************/
     abstract int delete(int id);

    /**************改**************/
     int update(T t);

    /**************查**************/
     T get(int id);

     List<T> getAll();
}
