package cn.rayjun.readhub.api;


import java.util.Map;

import cn.rayjun.readhub.entity.HotNewsEntity;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by ray on 26/07/2017.
 */

public interface HotNewsApi {
    @GET("/topic")
    Observable<HotNewsEntity> getHotNews(@QueryMap Map<String, Object> params);
}
