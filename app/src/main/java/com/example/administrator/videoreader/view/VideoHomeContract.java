package com.example.administrator.videoreader.view;

import com.example.administrator.videoreader.bean.movie.RetDataBean;

import java.util.ArrayList;

/**
 * Created by jinhui on 2017/5/31.
 * 邮箱: 1004260403@qq.com
 */

public interface VideoHomeContract {

    interface View {
        void refreshData();

        void refreshSuccess(ArrayList<RetDataBean.ListBean> listBeen);

        void refreshFail(String errCode, String errMsg);

        void showProgressBar();

        void hideProgressBar();
    }

    interface Presenter {
        void loadData();

        void loadCache();
    }
}
