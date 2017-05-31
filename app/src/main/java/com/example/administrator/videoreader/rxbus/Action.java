package com.example.administrator.videoreader.rxbus;

/**
 * Created by jinhui on 2017/5/31.
 * 邮箱: 1004260403@qq.com
 */

public class Action {
    public int code;
    public Object data;

    public Action(int code, Object data) {
        this.code = code;
        this.data = data;
    }
}