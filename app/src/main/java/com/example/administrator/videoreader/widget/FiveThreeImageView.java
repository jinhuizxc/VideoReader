package com.example.administrator.videoreader.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by jinhui on 2017/6/2.
 * 邮箱: 1004260403@qq.com
 *
 * 长宽比为4:3的imageView
 */

public class FiveThreeImageView extends AppCompatImageView {
    public FiveThreeImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FiveThreeImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec) * 3 / 5, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, height);
    }
}
