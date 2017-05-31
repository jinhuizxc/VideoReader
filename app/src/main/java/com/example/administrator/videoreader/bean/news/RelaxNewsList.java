package com.example.administrator.videoreader.bean.news;

import com.example.administrator.videoreader.config.Constants;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by jinhui on 2017/5/31.
 * 邮箱: 1004260403@qq.com
 */

public class RelaxNewsList {

    @SerializedName(Constants.NETEASY_NEWS_DADA)

    private ArrayList<NewsBean> mDadaNewsArrayList;

    public ArrayList<NewsBean> getDadaNewsArrayList() {
        return mDadaNewsArrayList;
    }
}
