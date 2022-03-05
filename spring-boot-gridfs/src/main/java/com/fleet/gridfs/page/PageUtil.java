package com.fleet.gridfs.page;

import java.util.List;

/**
 * 分页工具类
 */
public class PageUtil<T> {

    private List<T> list;

    private Page page;

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
