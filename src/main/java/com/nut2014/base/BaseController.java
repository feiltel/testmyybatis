package com.nut2014.base;

import com.nut2014.entity.User;
import com.nut2014.pojo.BaseResponse;
import com.nut2014.pojo.PageBaseResponse;

import java.util.List;

public interface BaseController<T> {
    /**************增**************/
    BaseResponse<String> add(T t);

    /**************删**************/
    BaseResponse<String> delete(int id);

    /**************改**************/
    BaseResponse<String> update(T t);

    /**************查**************/
    BaseResponse<T> get(int id);

    BaseResponse<List<T>> getAll();

    PageBaseResponse<List<T>> getAllPage(int pageNum);

}
