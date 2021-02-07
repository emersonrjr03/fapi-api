package com.herms.pages;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

public class PageRequest {
    @QueryParam("pageNumber")
    @DefaultValue("0")
    private int pageNumber;

    @QueryParam("pageSize")
    @DefaultValue("15")
    private int pageSize;

    public PageRequest() {
    }

    public PageRequest(int pageNum, int pageSize) {
        this.pageNumber = pageNum;
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }
}
