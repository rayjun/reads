package cn.rayjun.readhub.entity;

import java.util.List;

import cn.rayjun.readhub.entity.model.DevNews;

/**
 * Created by ray on 26/07/2017.
 */

public class DevNewsEntity {

    private List<DevNews> data;

    private int pageSize;

    private int pageIndex;

    private long totalItems;

    private long totalPages;

    public List<DevNews> getData() {
        return data;
    }

    public void setData(List<DevNews> data) {
        this.data = data;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }
}
