package com.fleet.mybatis.pagehelper.page;

import com.fleet.mybatis.pagehelper.page.entity.Pager;

import java.util.List;

/**
 * 分页工具类
 */
public class PagerUtil<T> {

    private List<T> list;

    private Pager pager;

    public PagerUtil() {
    }

    public PagerUtil(List<T> list, Pager pager) {
        this.list = list;
        this.pager = pager;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Pager getPager() {
        return pager;
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }
}
