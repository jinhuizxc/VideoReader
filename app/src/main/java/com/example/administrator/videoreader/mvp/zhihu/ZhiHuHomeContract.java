package com.example.administrator.videoreader.mvp.zhihu;

import com.example.administrator.videoreader.bean.ZhiHuDaily;
import com.example.pandalib.magicrecyclerView.BaseItem;

import java.util.ArrayList;

/**
 * Created by jinhui on 2017/6/2.
 * 邮箱: 1004260403@qq.com
 *
 * 知乎日报接口
 */

public interface ZhiHuHomeContract {
    interface View {
        void showRefreshBar();

        void hideRefreshBar();

        void refreshData();

        void refreshSuccessed(ZhiHuDaily stories);

        void refreshFail(String errMsg);

        void loadMoreData();

        void loadSuccessed(ArrayList<BaseItem> stories);

        void loadFail(String errMsg);
    }

    interface Presenter {
        void refreshZhihuDaily();

        void loadMoreData();

        void loadCache();
    }
}

