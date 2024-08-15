package com.apps.stocktrading.base.web.request;

public abstract class CommonFilterRequest {
    protected int page;
    protected int pageSize;
    protected String sort;
    protected boolean sortAscFlag;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public boolean isSortAscFlag() {
        return sortAscFlag;
    }

    public void setSortAscFlag(boolean sortAscFlag) {
        this.sortAscFlag = sortAscFlag;
    }
}
