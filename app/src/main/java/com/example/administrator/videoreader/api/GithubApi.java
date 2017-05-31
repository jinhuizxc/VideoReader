package com.example.administrator.videoreader.api;

import com.example.administrator.videoreader.bean.UserInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by jinhui on 2017/5/31.
 * 邮箱: 1004260403@qq.com
 *
 * 拉取我的个人信息到关于界面
 */

public interface GithubApi {

    //获取某一用户的信息
    @GET("users/{user}")
    Observable<UserInfo> getMyInfo(@Path("user") String path);
}
