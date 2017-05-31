package com.example.administrator.videoreader.view;

import com.example.administrator.videoreader.bean.UserInfo;

/**
 * Created by jinhui on 2017/5/31.
 * 邮箱: 1004260403@qq.com
 */

public interface AboutMeContract {
    interface View {
        void loadMyInfo();

        void showMyInfo(UserInfo myInfo);

        void loadMyInfoFail();
    }

    interface Presenter {
        void loadInfo(String user);
    }
}
