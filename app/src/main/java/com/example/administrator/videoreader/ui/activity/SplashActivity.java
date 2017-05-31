package com.example.administrator.videoreader.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.administrator.videoreader.R;
import com.example.administrator.videoreader.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jinhui on 2017/5/31.
 * 邮箱: 1004260403@qq.com
 * <p>
 * 闪屏页，优化启动体验
 */

public class SplashActivity extends BaseActivity {

    @BindView(R.id.iv_splash_image)
    ImageView mIvSplashImage;
    @BindView(R.id.iv_background)
    ImageView mIvBackground;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        // 加载图片这里之后在解决，这里还是有些问题！
//        String[] images = getResources().getStringArray(R.array.splash_background);
//        int position = new Random().nextInt(images.length - 1) % (images.length);
//        // 加载背景
//        Picasso.with(this).load(images[position]).into(mIvBackground);
//        // 加载头像
//        Picasso.with(this)
//                .load("file://" + ViewUtils.getAppFile(this, "images/user.png"))
//                .error(getResources().getDrawable(R.drawable.userimage))
//                .into(mIvSplashImage);
        AlphaAnimation animation = new AlphaAnimation(0.1f, 1.0f);
        animation.setDuration(1000);
        mIvSplashImage.startAnimation(animation);
        animation.setAnimationListener(new AnimationImpl());
    }

    private class AnimationImpl implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            SplashActivity.this.finish();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }

    }

}