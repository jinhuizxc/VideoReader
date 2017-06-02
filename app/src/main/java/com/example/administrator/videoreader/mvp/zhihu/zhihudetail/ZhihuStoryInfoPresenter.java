package com.example.administrator.videoreader.mvp.zhihu.zhihudetail;

import com.example.administrator.videoreader.api.ApiManager;
import com.example.administrator.videoreader.base.BasePresenter;
import com.example.administrator.videoreader.bean.ZhihuStoryContent;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jinhui on 2017/6/2.
 * 邮箱: 1004260403@qq.com
 */

public class ZhihuStoryInfoPresenter extends BasePresenter implements ZhiHuDetailContract.Presenter {

    private ZhiHuDetailContract.View mActivity;

    public ZhihuStoryInfoPresenter(ZhiHuDetailContract.View iZhihuStoryInfoActivity) {
        mActivity = iZhihuStoryInfoActivity;
    }

    public void loadStory(String id) {
        mActivity.showProgressBar();
        ApiManager.getInstence().getZhihuService()
                .getStoryContent(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhihuStoryContent>() {

                    @Override
                    public void onError(Throwable e) {
                        mActivity.loadFail(e.getMessage());
                        mActivity.hideProgressBar();
                    }

                    @Override
                    public void onComplete() {
                        mActivity.hideProgressBar();
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(ZhihuStoryContent zhihuStoryContent) {
                        mActivity.loadSuccess(zhihuStoryContent);
                        mActivity.hideProgressBar();
                    }
                });

    }
}

