package com.fleet.mybatis.pagehelper.page.entity;

/**
 * 分页信息（可以设置查询条件）
 *
 * @author April Han
 */
public class Pager {

    /**
     * 请求的页数（从1开始），不设则默认为1
     */
    private int pageIndex = 1;

    /**
     * 请求的每页行数，不设则默认为20
     */
    private int pageRows = 20;

    /**
     * 请求的页数的开始偏移量（包含），不设则默认为0
     */
    private int fromPageIndex = 0;

    /**
     * 请求的页数的结束偏移量（不包含），不设则默认为20
     */
    private int toPageIndex = 20;

    /**
     * 总行数
     */
    private int totalRows = 0;

    /**
     * 总页数
     */
    private int totalPages = 1;

    /**
     * 是否有上一页
     */
    private boolean hasPrevPage = false;

    /**
     * 是否有下一页
     */
    private boolean hasNextPage = false;

    public Pager() {
    }

    public Pager(int pageIndex) {
        if (pageIndex > 0) {
            this.pageIndex = pageIndex;
            if (pageIndex > 1) {
                this.hasPrevPage = true;
            }
        }
        this.fromPageIndex = (this.pageIndex - 1) * this.pageRows;
        this.toPageIndex = this.pageIndex * this.pageRows;
    }

    public Pager(int pageIndex, int pageRows) {
        if (pageIndex > 0) {
            this.pageIndex = pageIndex;
            if (pageIndex > 1) {
                this.hasPrevPage = true;
            }
        }
        if (pageRows > 0) {
            this.pageRows = pageRows;
        }
        this.fromPageIndex = (this.pageIndex - 1) * this.pageRows;
        this.toPageIndex = this.pageIndex * this.pageRows;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        if (pageIndex > 0) {
            this.pageIndex = pageIndex;
        }
    }

    public int getPageRows() {
        return pageRows;
    }

    public void setPageRows(int pageRows) {
        if (pageRows > 0) {
            this.pageRows = pageRows;
        }
    }

    public int getFromPageIndex() {
        return fromPageIndex;
    }

    public void setFromPageIndex(int fromPageIndex) {
        if (fromPageIndex >= 0) {
            this.fromPageIndex = fromPageIndex;
        }
    }

    public int getToPageIndex() {
        return toPageIndex;
    }

    public void setToPageIndex(int toPageIndex) {
        if (toPageIndex >= 0) {
            this.toPageIndex = toPageIndex;
        }
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        if (totalRows >= 0) {
            this.totalRows = totalRows;

            int totalPages = (int) Math.ceil((float) totalRows / this.pageRows);
            if (totalPages > 0) {
                this.totalPages = totalPages;

                if (this.pageIndex < totalPages) {
                    this.hasNextPage = true;
                } else {
                    if (this.pageIndex > totalPages) {
                        this.pageIndex = totalPages;
                        if (this.pageIndex > 1) {
                            this.hasPrevPage = true;
                        }
                    }

                    int fromPageIndex = (this.pageIndex - 1) * this.pageRows;
                    this.fromPageIndex = Math.min(fromPageIndex, totalRows);
                    int toPageIndex = this.pageIndex * this.pageRows;
                    this.toPageIndex = Math.min(toPageIndex, totalRows);
                }
            }
        }
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        if (totalPages > 0) {
            this.totalPages = totalPages;
        }
    }

    public boolean isHasPrevPage() {
        return hasPrevPage;
    }

    public void setHasPrevPage(boolean hasPrevPage) {
        this.hasPrevPage = hasPrevPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }
}
