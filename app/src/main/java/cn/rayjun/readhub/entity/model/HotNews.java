package cn.rayjun.readhub.entity.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ray on 26/07/2017.
 */

public class HotNews {

    @SerializedName("id")
    String id;

    String title;

    @SerializedName("summary")
    String newsAbstract;

    int resourcesCount;

    String createdAt;

    String updatedAt;

    String publishDate;

    String time;

    int order;

    @SerializedName("newsArray")
    List<HotNewsDetail> hotNewsDetailList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNewsAbstract() {
        return newsAbstract;
    }

    public void setNewsAbstract(String newsAbstract) {
        this.newsAbstract = newsAbstract;
    }

    public int getResourcesCount() {
        return resourcesCount;
    }

    public void setResourcesCount(int resourcesCount) {
        this.resourcesCount = resourcesCount;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public List<HotNewsDetail> getHotNewsDetailList() {
        return hotNewsDetailList;
    }

    public void setHotNewsDetailList(List<HotNewsDetail> hotNewsDetailList) {
        this.hotNewsDetailList = hotNewsDetailList;
    }
}
