package com.example.administrator.videoreader.mvp.zhihu.zhihudetail;

import com.example.administrator.videoreader.bean.ZhihuStoryContent;

/**
 * Created by jinhui on 2017/6/2.
 * 邮箱: 1004260403@qq.com
 */

public interface ZhiHuDetailContract {

    public interface View {
        void showProgressBar();

        void hideProgressBar();

        void loadZhihuStory();

        void loadFail(String errmsg);

        void loadSuccess(ZhihuStoryContent zhihuStory);
    }

    interface Presenter {
        void loadStory(String id);
    }
}
