package com.fleet.flowable.page;

import java.util.HashMap;

/**
 * 分页信息（可以设置查询条件）
 */
public class Page extends HashMap<String, Object> {

    private static final long serialVersionUID = 8139795323126696426L;

    // 请求的页数（从1开始），不设则默认为1
    public static final int PAGE_INDEX = 1;
    // 请求的每页行数，不设则默认为20
    public static final int PAGE_ROWS = 20;
    // 总行数
    public static final int TOTAL_ROWS = 0;
    // 总页数
    public static final int TOTAL_PAGES = 1;
    // 是否有上一页
    public static final boolean HAS_PREV_PAGE = false;
    // 是否有下一页
    public static final boolean HAS_NEXT_PAGE = false;
    // 请求的页数的开始偏移量（从0开始），不设则默认为0
    public static final int FROM_PAGE_INDEX = 0;
    // 请求的页数的结束偏移量（到0结束），不设则默认为0
    public static final int TO_PAGE_INDEX = 0;

    public Page() {
        super.put("pageIndex", PAGE_INDEX);
        super.put("pageRows", PAGE_ROWS);
        super.put("totalRows", TOTAL_ROWS);
        super.put("totalPages", TOTAL_PAGES);
        super.put("hasPrevPage", HAS_PREV_PAGE);
        super.put("hasNextPage", HAS_NEXT_PAGE);
        super.put("fromPageIndex", FROM_PAGE_INDEX);
        super.put("toPageIndex", TO_PAGE_INDEX);
    }

    public Integer getPageIndex() {
        Integer pageIndex = (Integer) super.get("pageIndex");
        if (pageIndex != null) {
            return pageIndex;
        } else {
            super.put("pageIndex", PAGE_INDEX);
            return PAGE_INDEX;
        }
    }

    public void setPageIndex(Integer pageIndex) {
        if (pageIndex != null && pageIndex > 0) {
            super.put("pageIndex", pageIndex);
        }
        setValue();
    }

    public Integer getFromPageIndex() {
        Integer fromPageIndex = (Integer) super.get("fromPageIndex");
        if (fromPageIndex != null) {
            return fromPageIndex;
        } else {
            super.put("fromPageIndex", FROM_PAGE_INDEX);
            return FROM_PAGE_INDEX;
        }
    }

    public void setFromPageIndex(Integer fromPageIndex) {
        super.put("fromPageIndex", fromPageIndex);
    }

    public Integer getToPageIndex() {
        Integer toPageIndex = (Integer) super.get("toPageIndex");
        if (toPageIndex != null) {
            return toPageIndex;
        } else {
            super.put("toPageIndex", TO_PAGE_INDEX);
            return TO_PAGE_INDEX;
        }
    }

    public void setToPageIndex(Integer toPageIndex) {
        super.put("toPageIndex", toPageIndex);
    }

    public Integer getPageRows() {
        Integer pageRows = (Integer) super.get("pageRows");
        if (pageRows != null) {
            return pageRows;
        } else {
            super.put("pageRows", PAGE_ROWS);
            return PAGE_ROWS;
        }
    }

    public void setPageRows(Integer pageRows) {
        if (pageRows != null && pageRows > 0) {
            super.put("pageRows", pageRows);
        }
        setValue();
    }

    public Integer getTotalRows() {
        Integer totalRows = (Integer) super.get("totalRows");
        if (totalRows != null) {
            return totalRows;
        } else {
            super.put("totalRows", TOTAL_ROWS);
            return TOTAL_ROWS;
        }
    }

    public void setTotalRows(Integer totalRows) {
        if (totalRows != null && totalRows > 0) {
            super.put("totalRows", totalRows);
        }
        setValue();
    }

    public Integer getTotalPages() {
        Integer totalPages = (Integer) super.get("totalPages");
        if (totalPages != null) {
            return totalPages;
        } else {
            super.put("totalPages", TOTAL_PAGES);
            return TOTAL_PAGES;
        }
    }

    public void setTotalPages(Integer totalPages) {
        super.put("totalPages", totalPages);
    }

    public Boolean getHasPrevPage() {
        return (Boolean) super.get("hasPrevPage");
    }

    public void setHasPrevPage(Boolean hasPrevPage) {
        super.put("hasPrevPage", hasPrevPage);
    }

    public Boolean getHasNextPage() {
        return (Boolean) super.get("hasNextPage");
    }

    public void setHasNextPage(Boolean hasNextPage) {
        super.put("hasNextPage", hasNextPage);
    }

    private void setValue() {
        int totalPages = (int) Math.ceil((float) getTotalRows() / getPageRows());
        if (totalPages > 0) {
            super.put("totalPages", totalPages);
        }

        if (getPageIndex() > getTotalPages()) {
            super.put("pageIndex", getTotalPages());
        }

        if (getPageIndex() < getTotalPages()) {
            super.put("hasNextPage", true);
        }

        if (getPageIndex() > 1) {
            super.put("hasPrevPage", true);
        }

        Integer fromPageIndex = (getPageIndex() - 1) * getPageRows();
        super.put("fromPageIndex", fromPageIndex);
        Integer toPageIndex = getPageIndex() * getPageRows() > getTotalRows() ? getTotalRows() : getPageIndex() * getPageRows();
        super.put("toPageIndex", toPageIndex);
    }
}
