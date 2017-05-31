package com.example.administrator.videoreader.api;

import com.example.administrator.videoreader.bean.news.HealthNewsList;
import com.example.administrator.videoreader.bean.news.MilitaryNewsList;
import com.example.administrator.videoreader.bean.news.RelaxNewsList;
import com.example.administrator.videoreader.bean.news.SportNewsList;
import com.example.administrator.videoreader.bean.news.TecNewsList;
import com.example.administrator.videoreader.bean.news.TopNewsList;
import com.example.administrator.videoreader.bean.news.TravelNewsList;
import com.example.administrator.videoreader.config.Constants;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by jinhui on 2017/5/31.
 * 邮箱: 1004260403@qq.com
 * <p>
 * 网易新闻Api 都默认一次加载20条
 */

public interface NetEasyNewsApi {
    @GET("list/" + Constants.NETEASY_NEWS_HEADLINE + "/{index}-20.html")
    Observable<TopNewsList> getTopNews(@Path("index") String index);

    @GET("list/" + Constants.NETEASY_NEWS_TEC + "/{index}-20.html")
    Observable<TecNewsList> getTecNews(@Path("index") String index);

    @GET("list/" + Constants.NETEASY_NEWS_SPORT + "/{index}-20.html")
    Observable<SportNewsList> getSportNews(@Path("index") String index);

    @GET("list/" + Constants.NETEASY_NEWS_HEALTH + "/{index}-20.html")
    Observable<HealthNewsList> getRecommendNews(@Path("index") String index);

    @GET("list/" + Constants.NETEASY_NEWS_DADA + "/{index}-20.html")
    Observable<RelaxNewsList> getDadaNews(@Path("index") String index);

    @GET("list/" + Constants.NETEASY_NEWS_MILITARY + "/{index}-20.html")
    Observable<MilitaryNewsList> getMilitaryNews(@Path("index") String index);

    @GET("list/" + Constants.NETEASY_NEWS_TRAVEL + "/{index}-20.html")
    Observable<TravelNewsList> getTravelNews(@Path("index") String index);

    @GET("{id}/full.html")
    Observable<ResponseBody> getNewsContent(@Path("id") String id);
}
