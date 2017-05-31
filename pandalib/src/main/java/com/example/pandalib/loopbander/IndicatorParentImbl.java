package com.example.pandalib.loopbander;

/**
 * Created by jinhui on 2017/5/31.
 * 邮箱: 1004260403@qq.com
 */

public interface IndicatorParentImbl {

    void setIndicatorListener(IndicatorListener indicatorListener);

    int getIndicatorCount();

    void startAutoScroll();

    void stopAutoScroll();

    void onReDraw(int index);
}