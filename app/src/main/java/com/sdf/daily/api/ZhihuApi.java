package com.sdf.daily.api;




import com.sdf.daily.model.bean.ZhihuDailyListBean;
import com.sdf.daily.model.zhihu.ZhihuDailyDetailBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface ZhihuApi {
    public final String HOST = "http://news-at.zhihu.com";

    @GET("/api/4/news/latest")
    Observable<ZhihuDailyListBean> getLastDailyList();

    @GET("/api/4/news/before/{date}")
    Observable<ZhihuDailyListBean> getDailyListWithDate(@Path("date") String date);

    @GET("/api/4/news/{id}")
    Observable<ZhihuDailyDetailBean> getZhihuDailyDetail(@Path("id") String id);
}
