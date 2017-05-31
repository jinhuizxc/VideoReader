package com.example.administrator.videoreader.bean.news;

import com.example.administrator.videoreader.config.Constants;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by jinhui on 2017/5/31.
 * 邮箱: 1004260403@qq.com
 */

public class MilitaryNewsList {

    @SerializedName(Constants.NETEASY_NEWS_MILITARY)

    private ArrayList<NewsBean> mMilitaryNewsArrayList;

    public ArrayList<NewsBean> getMilitaryNewsArrayList() {
        return mMilitaryNewsArrayList;
    }
}
