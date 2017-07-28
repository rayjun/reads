package cn.rayjun.readhub.api;


import java.util.Map;

import cn.rayjun.readhub.entity.TechNewsEntity;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by ray on 26/07/2017.
 */

public interface TechNewsApi {
    @GET("/news")
    Observable<TechNewsEntity> getTechNews(@QueryMap Map<String, Object> params);
}
