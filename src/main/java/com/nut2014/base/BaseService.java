package com.nut2014.base;

import java.util.List;

public interface BaseService<T> {
    int add(T t);

    int delete(int id);

    int update(T t);

    T get(int id);

    List<T> getAll();
}
