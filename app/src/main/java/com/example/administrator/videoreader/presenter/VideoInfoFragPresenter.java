package com.example.administrator.videoreader.presenter;

import com.example.administrator.videoreader.api.ApiManager;
import com.example.administrator.videoreader.base.BasePresenter;
import com.example.administrator.videoreader.bean.movie.MovieInfo;
import com.example.administrator.videoreader.bean.movie.MovieResponse;
import com.example.administrator.videoreader.config.Constants;
import com.example.administrator.videoreader.rxbus.RxBus;
import com.example.administrator.videoreader.rxbus.RxConstants;
import com.example.administrator.videoreader.view.VideoInfoContract;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jinhui on 2017/5/31.
 * 邮箱: 1004260403@qq.com
 *
 * 视频详情
 */

public class VideoInfoFragPresenter extends BasePresenter implements VideoInfoContract.Presenter {

    private VideoInfoContract.View mVideoInfoFrag;

    public VideoInfoFragPresenter(VideoInfoContract.View videoInfoFrag) {
        mVideoInfoFrag = videoInfoFrag;
    }

    /**
     * 获取视频详情页
     */
    public void loadVideoInfo() {
        ApiManager.getInstence()
                .getMovieService()
                .getMovieDaily(mVideoInfoFrag.getDataId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieResponse<MovieInfo>>() {
                    @Override
                    public void onError(Throwable e) {
                        mVideoInfoFrag.loadInfoFail(Constants.ERRO, e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(MovieResponse<MovieInfo> movieInfo) {
                        if (movieInfo.getData().getList() != null) {
                            mVideoInfoFrag.loadInfoSuccess(movieInfo.getData());
                            RxBus.getDefault().postWithCode(RxConstants.LOADED_DATA_CODE, movieInfo.getData());
                        } else {
                            mVideoInfoFrag.loadInfoFail(movieInfo.getCode(), movieInfo.getMsg());
                        }
                    }
                });
    }
}
