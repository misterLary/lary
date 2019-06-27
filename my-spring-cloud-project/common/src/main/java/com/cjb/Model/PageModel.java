package com.cjb.Model;

public class PageModel {

    private int from;

    private int currentPage;

    private int pageSize;

    public PageModel() {
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
