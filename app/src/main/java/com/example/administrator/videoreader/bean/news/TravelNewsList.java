package com.example.administrator.videoreader.bean.news;

import com.example.administrator.videoreader.config.Constants;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by jinhui on 2017/5/31.
 * 邮箱: 1004260403@qq.com
 */

public class TravelNewsList {

    @SerializedName(Constants.NETEASY_NEWS_TRAVEL)

    private ArrayList<NewsBean> mTrivelNewsArrayList;

    public ArrayList<NewsBean> getTrivelNewsArrayList() {
        return mTrivelNewsArrayList;
    }
}
