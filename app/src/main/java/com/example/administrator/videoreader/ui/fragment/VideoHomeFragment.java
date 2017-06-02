package com.example.administrator.videoreader.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.administrator.videoreader.R;
import com.example.administrator.videoreader.base.BaseFragment;
import com.example.administrator.videoreader.bean.movie.RetDataBean;
import com.example.administrator.videoreader.config.Constants;
import com.example.administrator.videoreader.mvp.movie.VideoHomePresenter;
import com.example.administrator.videoreader.rxbus.RxBus;
import com.example.administrator.videoreader.rxbus.RxConstants;
import com.example.administrator.videoreader.ui.activity.TypedVideosActivity;
import com.example.administrator.videoreader.ui.adapter.VideoTopPagerAdapter;
import com.example.administrator.videoreader.ui.adapter.VideoTypesAdapter;
import com.example.administrator.videoreader.utils.DensityUtil;
import com.example.administrator.videoreader.mvp.movie.VideoHomeContract;
import com.example.pandalib.loopbander.AutoScrollViewPager;
import com.example.pandalib.loopbander.ViewGroupIndicator;
import com.example.pandalib.magicrecyclerView.BaseItem;
import com.example.pandalib.magicrecyclerView.BaseRecyclerAdapter;
import com.example.pandalib.magicrecyclerView.MagicRecyclerView;
import com.example.pandalib.magicrecyclerView.SpaceDecoration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by jinhui on 2017/5/31.
 * 邮箱: 1004260403@qq.com
 *
 * 冒泡圈Fragment -  熊猫影院
 */

public class VideoHomeFragment extends BaseFragment implements VideoHomeContract.View, SwipeRefreshLayout.OnRefreshListener, BaseRecyclerAdapter.OnItemClickListener {

    @BindView(R.id.mrv_video)
    MagicRecyclerView mMrvVideo;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mSrlRefresh;
    @BindView(R.id.empty_msg)
    TextView mEmptyMsg;
    private AutoScrollViewPager scrollViewPager;
    private ViewGroupIndicator viewGroupIndicator;
    // 视频fragment顶部的ViewPager适配器
    private VideoTopPagerAdapter mPagerAdapter;
    private VideoTypesAdapter mAdapter;
    // P层
    private VideoHomePresenter mPresenter = new VideoHomePresenter(this);
    private ArrayList<BaseItem> mBaseItems;
    private Disposable mDisposable;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_videohome, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        onHiddenChanged(false);
    }

    @Override
    public void onPause() {
        super.onPause();
        mSrlRefresh.setRefreshing(false);
        mPresenter.dispose();
        onHiddenChanged(true);
    }

    private void initView() {
        mBaseItems = new ArrayList<>();
        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mMrvVideo.setLayoutManager(mStaggeredGridLayoutManager);
        mMrvVideo.setItemAnimator(new DefaultItemAnimator());
        mMrvVideo.getItemAnimator().setChangeDuration(0);
        SpaceDecoration itemDecoration = new SpaceDecoration(DensityUtil.dip2px(getContext(), 8));
        itemDecoration.setPaddingEdgeSide(true);
        itemDecoration.setPaddingStart(true);
        itemDecoration.setPaddingHeaderFooter(false);
        mMrvVideo.addItemDecoration(itemDecoration);
        FrameLayout headerView = (FrameLayout) mMrvVideo.getHeaderView();
        scrollViewPager = (AutoScrollViewPager) headerView.findViewById(R.id.scroll_pager);
        viewGroupIndicator = (ViewGroupIndicator) headerView.findViewById(R.id.scroll_pager_indicator);
        mSrlRefresh.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.white_FFFFFF));
        mSrlRefresh.setOnRefreshListener(this);
        mSrlRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        refreshData();
        mPresenter.loadCache();
        mMrvVideo.addOnItemClickListener(this);
    }

    @Override
    public void refreshData() {
        mPresenter.loadData();
    }

    @Override
    public void refreshSuccess(ArrayList<RetDataBean.ListBean> listBeen) {
        if (listBeen == null || listBeen.size() <= 0) {
            mEmptyMsg.setVisibility(View.VISIBLE);
            mMrvVideo.setVisibility(View.INVISIBLE);
            mSrlRefresh.requestFocus();
            return;
        } else {
            mEmptyMsg.setVisibility(View.GONE);
            mMrvVideo.setVisibility(View.VISIBLE);
        }
        RetDataBean.ListBean banner = listBeen.get(0);
        if (Constants.SHOW_TYPE_BANNER.equals(banner.getShowType())) { //判断是否为 banner
            //配置顶部故事
            if (mPagerAdapter == null) {
                mPagerAdapter = new VideoTopPagerAdapter(this, banner.getChildList());
                scrollViewPager.setAdapter(mPagerAdapter);
            } else {
                mPagerAdapter.resetData(banner.getChildList());
            }
            viewGroupIndicator.setParent(scrollViewPager);
            listBeen.remove(banner);
        }
        //配置底部列表故事s
        mBaseItems.clear();
        for (RetDataBean.ListBean listBean : listBeen) {
            if (!TextUtils.isEmpty(listBean.getMoreURL()) &&
                    !listBean.getTitle().equals("直播专区") && !listBean.getTitle().equals("专题")) {
                BaseItem<RetDataBean.ListBean> baseItem = new BaseItem<>();
                baseItem.setData(listBean);
                mBaseItems.add(baseItem);
            }
        }
        if (mAdapter == null) {
            mAdapter = new VideoTypesAdapter(this);
            mAdapter.setBaseDatas(mBaseItems);
            mMrvVideo.setAdapter(mAdapter);
        } else {
            if (listBeen.size() != 0) {
                mAdapter.setBaseDatas(mBaseItems);
            }
        }
    }

    @Override
    public void refreshFail(String errCode, String errMsg) {
        if (mAdapter == null) {
            mEmptyMsg.setVisibility(View.VISIBLE);
            mMrvVideo.setVisibility(View.INVISIBLE);
            mSrlRefresh.requestFocus();
        }
    }

    @Override
    public void showProgressBar() {
        if (!mSrlRefresh.isRefreshing())
            mSrlRefresh.setRefreshing(true);
    }

    @Override
    public void hideProgressBar() {
        mSrlRefresh.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        refreshData();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden && mSrlRefresh.isRefreshing()) { // 隐藏的时候停止 SwipeRefreshLayout 转动
            mSrlRefresh.setRefreshing(false);
        }
        if (!hidden) {
            RxBus.getDefault()
                    .toObservableWithCode(RxConstants.BACK_PRESSED_CODE, String.class)
                    .subscribeWith(new Observer<String>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            mDisposable = d;
                        }

                        @Override
                        public void onNext(String value) {
                            if (value.equals(RxConstants.BACK_PRESSED_DATA) && mMrvVideo != null) {
                                //滚动到顶部
                                mStaggeredGridLayoutManager.smoothScrollToPosition(mMrvVideo, null, 0);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        } else {
            if (mDisposable != null && !mDisposable.isDisposed()) {
                mDisposable.dispose();
            }
        }
    }

    @Override
    public void onItemClick(int position, BaseItem data, View view) {
        RetDataBean.ListBean dataBean = (RetDataBean.ListBean) data.getData();
        Intent intent = new Intent(this.getActivity(), TypedVideosActivity.class);
        intent.putExtra(Constants.TYPED_MORE_TITLE, dataBean.getTitle());
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
