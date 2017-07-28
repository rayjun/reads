package cn.rayjun.readhub.api.impl;

import java.util.Map;

import cn.rayjun.readhub.api.HotNewsApi;
import cn.rayjun.readhub.entity.HotNewsEntity;
import io.reactivex.Observable;

/**
 * Created by ray on 26/07/2017.
 */

public class HotNewsApiImpl extends BaseApi<HotNewsApi> {


    public Observable<HotNewsEntity> getHotNews(Map<String, Object> params) {
        return getService().getHotNews(params);
    }


    @Override
    protected Class<HotNewsApi> getServiceClass() {
        return HotNewsApi.class;
    }
}
