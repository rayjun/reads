package cn.rayjun.readhub.api.impl;

import java.util.Map;

import cn.rayjun.readhub.api.DevNewsApi;
import cn.rayjun.readhub.entity.DevNewsEntity;
import io.reactivex.Observable;

/**
 * Created by ray on 26/07/2017.
 */

public class DevNewsApiImpl extends BaseApi<DevNewsApi> {


    public Observable<DevNewsEntity> getDevNews(Map<String, Object> params) {
        return getService().getDevNews(params);
    }


    @Override
    protected Class<DevNewsApi> getServiceClass() {
        return DevNewsApi.class;
    }
}
