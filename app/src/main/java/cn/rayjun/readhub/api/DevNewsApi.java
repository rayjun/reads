package cn.rayjun.readhub.api;


import java.util.Map;

import cn.rayjun.readhub.entity.DevNewsEntity;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by ray on 26/07/2017.
 */

public interface DevNewsApi {
    @GET("/technews")
    Observable<DevNewsEntity> getDevNews(@QueryMap Map<String, Object> params);
}
