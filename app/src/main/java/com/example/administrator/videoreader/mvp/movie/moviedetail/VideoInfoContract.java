package com.example.administrator.videoreader.mvp.movie.moviedetail;

import com.example.administrator.videoreader.bean.movie.MovieInfo;

/**
 * Created by jinhui on 2017/5/31.
 * 邮箱: 1004260403@qq.com
 */

public interface VideoInfoContract {

    interface View {
        void loadInfo();

        String getDataId();

        void loadInfoFail(String errCode, String errMsg);

        void loadInfoSuccess(MovieInfo movieInfo);
    }

    interface Presenter {
        void loadVideoInfo();
    }
}
