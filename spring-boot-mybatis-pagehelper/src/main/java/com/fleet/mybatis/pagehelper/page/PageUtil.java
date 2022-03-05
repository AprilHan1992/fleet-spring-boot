package com.fleet.mybatis.pagehelper.page;

import com.fleet.mybatis.pagehelper.page.entity.Page;

import java.util.List;

/**
 * 分页工具类
 */
public class PageUtil<T> {

    private List<T> list;

    private Page page;

    public PageUtil() {
    }

    public PageUtil(List<T> list, Page page) {
        this.list = list;
        this.page = page;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
