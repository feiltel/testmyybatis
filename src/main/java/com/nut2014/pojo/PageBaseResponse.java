package com.nut2014.pojo;

import java.io.Serializable;

public class PageBaseResponse<T> extends BaseResponse implements Serializable {
    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    //总记录数
    private long total;


    //当前页
    private int pageNum;

    //总页数
    private int pages;

    public PageBaseResponse(int code, String msg, T data, long total, int pageNum, int pages) {
        super(code, msg, data);
        this.total = total;
        this.pageNum = pageNum;
        this.pages = pages;
    }
    public PageBaseResponse(int code, String msg, T data) {
        super(code, msg, data);

    }
}
