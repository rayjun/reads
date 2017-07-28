package cn.rayjun.readhub.entity;

import java.util.List;

import cn.rayjun.readhub.entity.model.TechNews;

/**
 * Created by ray on 26/07/2017.
 */

public class TechNewsEntity {

    private List<TechNews> data;

    private int pageSize;

    private int pageIndex;

    private long totalItems;

    private long totalPages;

    public List<TechNews> getData() {
        return data;
    }

    public void setData(List<TechNews> data) {
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
