package com.example.administrator.videoreader.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.videoreader.R;

/**
 * Created by jinhui on 2017/5/31.
 * 邮箱: 1004260403@qq.com
 *
 * 评论 fragment
 */

public class VideoCommentFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
