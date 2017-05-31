package com.example.pandalib.magicrecyclerView;

import java.io.Serializable;

import static com.example.pandalib.magicrecyclerView.BaseRecyclerAdapter.RecyclerItemType.TYPE_NORMAL;

/**
 * Created by jinhui on 2017/5/31.
 * 邮箱: 1004260403@qq.com
 */

public class BaseItem<T> implements Serializable {

    //数据类型
    private BaseRecyclerAdapter.RecyclerItemType mItemType;
    //实际使用的数据
    private T data;

    public BaseRecyclerAdapter.RecyclerItemType getItemType() {
        return mItemType == null ? TYPE_NORMAL : mItemType;
    }

    public void setItemType(BaseRecyclerAdapter.RecyclerItemType itemType) {
        mItemType = itemType;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}

