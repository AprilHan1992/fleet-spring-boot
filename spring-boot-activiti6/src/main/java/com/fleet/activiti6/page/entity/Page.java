package com.fleet.activiti6.page.entity;

/**
 * 分页信息
 *
 * @author April Han
 */
public class Page {

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
    private int fromIndex = 0;

    /**
     * 请求的页数的结束偏移量（不包含），不设则默认为20
     */
    private int toIndex = 20;

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

    public Page() {
    }

    public Page(int pageIndex) {
        if (pageIndex > 0) {
            this.pageIndex = pageIndex;
            if (pageIndex > 1) {
                this.hasPrevPage = true;
            }
        }
        this.fromIndex = (this.pageIndex - 1) * this.pageRows;
        this.toIndex = this.pageIndex * this.pageRows;
    }

    public Page(int pageIndex, int pageRows) {
        if (pageIndex > 0) {
            this.pageIndex = pageIndex;
            if (pageIndex > 1) {
                this.hasPrevPage = true;
            }
        }
        if (pageRows > 0) {
            this.pageRows = pageRows;
        }
        this.fromIndex = (this.pageIndex - 1) * this.pageRows;
        this.toIndex = this.pageIndex * this.pageRows;
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

    public int getFromIndex() {
        return fromIndex;
    }

    public void setFromIndex(int fromIndex) {
        if (fromIndex >= 0) {
            this.fromIndex = fromIndex;
        }
    }

    public int getToIndex() {
        return toIndex;
    }

    public void setToIndex(int toIndex) {
        if (toIndex >= 0) {
            this.toIndex = toIndex;
        }
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        if (totalRows == 0) {
            this.pageIndex = 1;
            this.fromIndex = 0;
            this.toIndex = 0;
            this.totalRows = 0;
            this.totalPages = 1;
            this.hasPrevPage = false;
            this.hasNextPage = false;
        } else {
            if (totalRows > 0) {
                this.totalRows = totalRows;

                int totalPages = (int) Math.ceil((float) totalRows / this.pageRows);
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

                    int fromIndex = (this.pageIndex - 1) * this.pageRows;
                    this.fromIndex = Math.min(fromIndex, totalRows);
                    int toIndex = this.pageIndex * this.pageRows;
                    this.toIndex = Math.min(toIndex, totalRows);
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

    public boolean getHasPrevPage() {
        return hasPrevPage;
    }

    public void setHasPrevPage(boolean hasPrevPage) {
        this.hasPrevPage = hasPrevPage;
    }

    public boolean getHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }
}
