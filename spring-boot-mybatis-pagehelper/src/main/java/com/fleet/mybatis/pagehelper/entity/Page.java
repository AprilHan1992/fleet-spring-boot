package com.fleet.mybatis.pagehelper.entity;

import java.util.HashMap;

/**
 * 分页信息（可以设置查询条件）
 *
 * @author April Han
 */
public class Page extends HashMap<String, Object> {

    /**
     * 请求的页数（从1开始），不设则默认为1
     */
    private static final int PAGE_INDEX = 1;

    /**
     * 请求的页数的开始偏移量（从0开始），不设则默认为0
     */
    private static final int FROM_PAGE_INDEX = 0;

    /**
     * 请求的页数的结束偏移量（到0结束），不设则默认为0
     */
    public static final int TO_PAGE_INDEX = 0;

    /**
     * 请求的每页行数，不设则默认为20
     */
    private static final int PAGE_ROWS = 20;

    /**
     * 总行数
     */
    private static final int TOTAL_ROWS = 0;

    /**
     * 总页数
     */
    private static final int TOTAL_PAGES = 1;

    /**
     * 是否有上一页
     */
    private static final boolean HAS_PREV_PAGE = false;

    /**
     * 是否有下一页
     */
    private static final boolean HAS_NEXT_PAGE = false;

    {
        super.put("pageIndex", PAGE_INDEX);
        super.put("fromPageIndex", FROM_PAGE_INDEX);
        super.put("toPageIndex", TO_PAGE_INDEX);
        super.put("pageRows", PAGE_ROWS);
        super.put("totalRows", TOTAL_ROWS);
        super.put("totalPages", TOTAL_PAGES);
        super.put("hasPrevPage", HAS_PREV_PAGE);
        super.put("hasNextPage", HAS_NEXT_PAGE);
    }

    public Page() {

    }

    public Page(int pageIndex, int pageRows) {
        setPageIndex(pageIndex);
        setPageRows(pageRows);
    }

    public int getPageIndex() {
        return (int) super.get("pageIndex");
    }

    public void setPageIndex(int pageIndex) {
        if (pageIndex > 0) {
            super.put("pageIndex", pageIndex);

            if (pageIndex > 1) {
                setHasPrevPage(true);
            }

            int fromPageIndex = (pageIndex - 1) * getPageRows();
            setFromPageIndex(fromPageIndex);

            int toPageIndex = pageIndex * getPageRows();
            setToPageIndex(toPageIndex);
        }
    }

    public int getFromPageIndex() {
        int fromPageIndex = (int) super.get("fromPageIndex");
        if (fromPageIndex == 0) {
            fromPageIndex = (getPageIndex() - 1) * getPageRows();
            setFromPageIndex(fromPageIndex);
        }
        return fromPageIndex;
    }

    public void setFromPageIndex(int fromPageIndex) {
        if (fromPageIndex >= 0) {
            super.put("fromPageIndex", fromPageIndex);
        }
    }

    public int getToPageIndex() {
        int toPageIndex = (int) super.get("toPageIndex");
        if (toPageIndex == 0) {
            toPageIndex = getPageIndex() * getPageRows();
            setToPageIndex(toPageIndex);
        }
        return toPageIndex;
    }

    public void setToPageIndex(int toPageIndex) {
        if (toPageIndex >= 0) {
            super.put("toPageIndex", toPageIndex);
        }
    }

    public int getPageRows() {
        return (int) super.get("pageRows");
    }

    public void setPageRows(int pageRows) {
        if (pageRows > 0) {
            super.put("pageRows", pageRows);

            int fromPageIndex = (getPageIndex() - 1) * pageRows;
            setFromPageIndex(fromPageIndex);

            int toPageIndex = getPageIndex() * pageRows;
            setToPageIndex(toPageIndex);
        }
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

                if (getPageIndex() < totalPages) {
                    setHasNextPage(true);
                }
            }

            int toPageIndex = Math.min(getPageIndex() * getPageRows(), totalRows);
            setToPageIndex(toPageIndex);
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

    public boolean getHasPrevPage() {
        return (boolean) super.get("hasPrevPage");
    }

    public void setHasPrevPage(boolean hasPrevPage) {
        super.put("hasPrevPage", hasPrevPage);
    }

    public boolean getHasNextPage() {
        return (boolean) super.get("hasNextPage");
    }

    public void setHasNextPage(boolean hasNextPage) {
        super.put("hasNextPage", hasNextPage);
    }
}
