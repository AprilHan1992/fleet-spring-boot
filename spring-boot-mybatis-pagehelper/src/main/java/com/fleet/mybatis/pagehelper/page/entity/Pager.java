//package com.fleet.mybatis.pagehelper.page.entity;
//
///**
// * 分页信息（可以设置查询条件）
// *
// * @author April Han
// */
//public class Pager {
//
//    /**
//     * 请求的页数（从1开始），不设则默认为1
//     */
//    private int pageIndex = 1;
//
//    /**
//     * 请求的每页行数，不设则默认为20
//     */
//    private int pageRows = 20;
//
//    /**
//     * 请求的页数的开始偏移量（包含），不设则默认为0
//     */
//    private int fromPageIndex = 0;
//
//    /**
//     * 请求的页数的结束偏移量（不包含），不设则默认为20
//     */
//    private int toPageIndex = 20;
//
//    /**
//     * 总行数
//     */
//    private int totalRows = 0;
//
//    /**
//     * 总页数
//     */
//    private int totalPages = 1;
//
//    /**
//     * 是否有上一页
//     */
//    private boolean hasPrevPage = false;
//
//    /**
//     * 是否有下一页
//     */
//    private boolean hasNextPage = false;
//
//    public Pager() {
//    }
//
//    public Pager(int pageIndex) {
//        setPageIndex(pageIndex);
//    }
//
//    public Pager(int pageIndex, int pageRows) {
//        setPageIndex(pageIndex);
//        setPageRows(pageRows);
//    }
//
//    public int getPageIndex() {
//        if (pageIndex > 0) {
//            return pageIndex;
//        }
//        return 1;
//    }
//
//    public void setPageIndex(int pageIndex) {
//        if (pageIndex > 0) {
//            this.pageIndex = pageIndex;
//
//            if (pageIndex > 1) {
//                setHasPrevPage(true);
//            }
//
//            int fromPageIndex = (pageIndex - 1) * getPageRows();
//            setFromPageIndex(fromPageIndex);
//
//            int toPageIndex = pageIndex * getPageRows();
//            setToPageIndex(toPageIndex);
//        }
//    }
//
//    public int getPageRows() {
//        if (pageRows > 0) {
//            return pageRows;
//        }
//        return 20;
//    }
//
//    public void setPageRows(int pageRows) {
//        if (pageRows > 0) {
//            this.pageRows = pageRows;
//        }
//    }
//
//    public int getFromPageIndex() {
////        return fromPageIndex;
//
//        fromPageIndex = (getPageIndex() - 1) * getPageRows();
//        fromPageIndex = Math.min(fromPageIndex, getTotalRows());
//        return fromPageIndex;
//    }
//
//    public void setFromPageIndex(int fromPageIndex) {
//        if (fromPageIndex >= 0) {
//            this.fromPageIndex = fromPageIndex;
//        }
//    }
//
//    public int getToPageIndex() {
//        toPageIndex = getPageIndex() * getPageRows();
//        toPageIndex = Math.min(toPageIndex, getTotalRows());
//        return toPageIndex;
//    }
//
//    public void setToPageIndex(int toPageIndex) {
//        if (toPageIndex >= 0) {
//            this.toPageIndex = toPageIndex;
//        }
//    }
//
//    public int getTotalRows() {
//        return totalRows;
//    }
//
//    public void setTotalRows(int totalRows) {
//        if (pageRows > 0) {
//            this.totalRows = totalRows;
//
//            int fromPageIndex = (getPageIndex() - 1) * pageRows;
//            setFromPageIndex(fromPageIndex);
//
//            int toPageIndex = getPageIndex() * pageRows;
//            setToPageIndex(toPageIndex);
//        }
//
//        if (totalRows >= 0) {
//            this.totalRows = totalRows;
//
//            int totalPages = (int) Math.ceil((float) totalRows / getPageRows());
//            if (totalPages > 0) {
//                setTotalPages(totalPages);
//
//                if (getPageIndex() > totalPages) {
//                    setPageIndex(totalPages);
//                }
//            }
//        }
//
//        if (totalRows >= 0) {
//            this.totalRows = totalRows;
//
//            int totalPages = (int) Math.ceil((float) totalRows / getPageRows());
//            if (totalPages > 0) {
//                setTotalPages(totalPages);
//
//                if (getPageIndex() > totalPages) {
//                    setPageIndex(totalPages);
//                }
//
//                if (getPageIndex() < totalPages) {
//                    setHasNextPage(true);
//                }
//            }
//        }
//    }
//
//    public int getTotalPages() {
//        return totalPages;
//    }
//
//    public void setTotalPages(int totalPages) {
//        this.totalPages = totalPages;
//    }
//
//    public boolean isHasPrevPage() {
//        return hasPrevPage;
//    }
//
//    public void setHasPrevPage(boolean hasPrevPage) {
//        this.hasPrevPage = hasPrevPage;
//    }
//
//    public boolean isHasNextPage() {
//        return hasNextPage;
//    }
//
//    public void setHasNextPage(boolean hasNextPage) {
//        this.hasNextPage = hasNextPage;
//    }
//
////    public int getPageIndex() {
////        int pageIndex = (int) super.get("pageIndex");
////        if (pageIndex > 0) {
////            return pageIndex;
////        }
////        return 1;
////    }
////
////    public void setPageIndex(int pageIndex) {
////        if (pageIndex > 0) {
////            super.put("pageIndex", pageIndex);
////        }
////    }
////
////    public int getFromPageIndex() {
////        int fromPageIndex = (getPageIndex() - 1) * getPageRows();
////        fromPageIndex = Math.min(fromPageIndex, getTotalRows());
////        return fromPageIndex;
////    }
////
////    public int getToPageIndex() {
////        int toPageIndex = getPageIndex() * getPageRows();
////        toPageIndex = Math.min(toPageIndex, getTotalRows());
////        return toPageIndex;
////    }
////
////    public int getPageRows() {
////        int pageRows = (int) super.get("pageRows");
////        if (pageRows > 0) {
////            return pageRows;
////        }
////        return 20;
////    }
////
////    public void setPageRows(int pageRows) {
////        if (pageRows > 0) {
////            super.put("pageRows", pageRows);
////        }
////    }
////
////    public int getTotalRows() {
////        return (int) super.get("totalRows");
////    }
////
////    public void setTotalRows(int totalRows) {
////        if (totalRows >= 0) {
////            super.put("totalRows", totalRows);
////
////            int totalPages = (int) Math.ceil((float) totalRows / getPageRows());
////            if (totalPages > 0) {
////                setTotalPages(totalPages);
////
////                if (getPageIndex() > totalPages) {
////                    setPageIndex(totalPages);
////                }
////            }
////        }
////    }
////
////    public int getTotalPages() {
////        return (int) super.get("totalPages");
////    }
////
////    public void setTotalPages(int totalPages) {
////        if (totalPages > 0) {
////            super.put("totalPages", totalPages);
////        }
////    }
//}
