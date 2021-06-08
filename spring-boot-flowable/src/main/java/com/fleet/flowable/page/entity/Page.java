package com.fleet.flowable.page.entity;

import java.util.HashMap;

/**
 * 分页信息（可以设置查询条件）
 *
 * @author April Han
 */
public class Page extends HashMap<String, Object> {

    {
        // 请求的页数（从1开始），不设则默认为1
        super.put("pageIndex", 1);
        // 请求的每页行数，不设则默认为20
        super.put("pageRows", 20);
        // 总行数
        super.put("totalRows", 0);
        // 总页数
        super.put("totalPages", 1);
    }

    public Page() {
    }

    public Page(int pageIndex, int pageRows) {
        setPageIndex(pageIndex);
        setPageRows(pageRows);
    }

    public int getPageIndex() {
        int pageIndex = (int) super.get("pageIndex");
        if (pageIndex > 0) {
            return pageIndex;
        }
        return 1;
    }

    public void setPageIndex(int pageIndex) {
        if (pageIndex > 0) {
            super.put("pageIndex", pageIndex);
        }
    }

    public int getPageRows() {
        int pageRows = (int) super.get("pageRows");
        if (pageRows > 0) {
            return pageRows;
        }
        return 20;
    }

    public void setPageRows(int pageRows) {
        if (pageRows > 0) {
            super.put("pageRows", pageRows);
        }
    }

    public int getFromPageIndex() {
        int fromPageIndex = (getPageIndex() - 1) * getPageRows();
        fromPageIndex = Math.min(fromPageIndex, getTotalRows());
        return fromPageIndex;
    }

    public int getToPageIndex() {
        int toPageIndex = getPageIndex() * getPageRows();
        toPageIndex = Math.min(toPageIndex, getTotalRows());
        return toPageIndex;
    }

    public int getTotalRows() {
        return (int) super.get("totalRows");
    }

    public void setTotalRows(int totalRows) {
        if (totalRows >= 0) {
            super.put("totalRows", totalRows);

            int totalPages = (int) Math.ceil((float) totalRows / getPageRows());
            if (totalPages > 0) {
                setTotalPages(totalPages);

                if (getPageIndex() > totalPages) {
                    setPageIndex(totalPages);
                }
            }
        }
    }

    public int getTotalPages() {
        return (int) super.get("totalPages");
    }

    public void setTotalPages(int totalPages) {
        if (totalPages > 0) {
            super.put("totalPages", totalPages);
        }
    }
}
