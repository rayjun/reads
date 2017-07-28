package cn.rayjun.readhub.api.impl;

import java.util.Map;

import cn.rayjun.readhub.api.TechNewsApi;
import cn.rayjun.readhub.entity.TechNewsEntity;
import io.reactivex.Observable;

/**
 * Created by ray on 26/07/2017.
 */

public class TechNewsApiImpl extends BaseApi<TechNewsApi> {


    public Observable<TechNewsEntity> getTechNews(Map<String, Object> params) {
        return getService().getTechNews(params);
    }


    @Override
    protected Class<TechNewsApi> getServiceClass() {
        return TechNewsApi.class;
    }
}
