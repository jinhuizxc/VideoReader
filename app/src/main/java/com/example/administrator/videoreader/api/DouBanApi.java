package com.example.administrator.videoreader.api;

import com.example.administrator.videoreader.bean.MovieTop250;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jinhui on 2017/5/31.
 * 邮箱: 1004260403@qq.com
 *
 * 豆瓣API接口
 */

public interface DouBanApi {

    @GET("movie/top250")
    Observable<MovieTop250> getTop250(@Query("start") int start, @Query("count") int count);
}