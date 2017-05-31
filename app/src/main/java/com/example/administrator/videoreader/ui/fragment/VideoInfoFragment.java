package com.example.administrator.videoreader.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.videoreader.R;
import com.example.administrator.videoreader.base.BaseFragment;
import com.example.administrator.videoreader.bean.movie.MovieInfo;
import com.example.administrator.videoreader.config.Constants;
import com.example.administrator.videoreader.presenter.VideoInfoFragPresenter;
import com.example.administrator.videoreader.rxbus.RxBus;
import com.example.administrator.videoreader.rxbus.RxConstants;
import com.example.administrator.videoreader.ui.adapter.VideoInfoAdapter;
import com.example.administrator.videoreader.utils.DensityUtil;
import com.example.administrator.videoreader.view.VideoInfoContract;
import com.example.pandalib.FoldableTextView;
import com.example.pandalib.magicrecyclerView.BaseItem;
import com.example.pandalib.magicrecyclerView.BaseRecyclerAdapter;
import com.example.pandalib.magicrecyclerView.MagicRecyclerView;
import com.example.pandalib.magicrecyclerView.SpaceDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jinhui on 2017/5/31.
 * 邮箱: 1004260403@qq.com
 *
 * 视频简介 fragment
 */

public class VideoInfoFragment extends BaseFragment implements VideoInfoContract.View, BaseRecyclerAdapter.OnItemClickListener {

    @BindView(R.id.tv_empty_msg)
    TextView mTvEmptyMsg;
    @BindView(R.id.mrv_recommend)
    MagicRecyclerView mMrvRecommend;
    private FoldableTextView mFoldableTextView;
    private ArrayList<BaseItem> mBaseItems;
    private VideoInfoAdapter mAdapter;
    private String currentId = "";
    private VideoInfoFragPresenter mPresenter = new VideoInfoFragPresenter(this);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video_description_fragment, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        mBaseItems = new ArrayList<>();
        mFoldableTextView = (FoldableTextView) mMrvRecommend.getHeaderView().findViewById(R.id.ftv_content_text);
        mMrvRecommend.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        mMrvRecommend.setItemAnimator(new DefaultItemAnimator());
        mMrvRecommend.getItemAnimator().setChangeDuration(0);
        // recyclerView 分割线
        SpaceDecoration itemDecoration = new SpaceDecoration(DensityUtil.dip2px(getContext(), 8));
        itemDecoration.setPaddingEdgeSide(true);
        itemDecoration.setPaddingStart(true);
        itemDecoration.setPaddingHeaderFooter(false);
        mMrvRecommend.addItemDecoration(itemDecoration);
        currentId = getArguments().getString(Constants.BUNDLE_KEY_DATAID);
        loadInfo();
    }

    @Override
    public void onItemClick(int position, BaseItem data, View view) {
        MovieInfo.ListBean.ChildListBean childListBean = (MovieInfo.ListBean.ChildListBean) data.getData();
        currentId = childListBean.getDataId();
        loadInfo();
        // 通知Activity 修改播放器图片
        RxBus.getDefault().postWithCode(RxConstants.LOADED_VIDEO_PIC_CODE, childListBean.getPic());
        //通知评论Fragment Id已变更
        RxBus.getDefault().postWithCode(RxConstants.ACCEPT_VIDEO_DATAID, childListBean.getDataId());
    }

    @Override
    public void loadInfo() {
        mPresenter.loadVideoInfo();
    }

    @Override
    public String getDataId() {
        return currentId;
    }

    @Override
    public void loadInfoFail(String errCode, String errMsg) {
        mTvEmptyMsg.setVisibility(View.VISIBLE);
        mTvEmptyMsg.setText(errMsg);
    }

    @Override
    public void loadInfoSuccess(MovieInfo movieInfo) {
        mTvEmptyMsg.setVisibility(View.GONE);
        String sb = "上映时间：" + movieInfo.getAirTime() + "\n" +
                "导演：" + movieInfo.getDirector() + "\n" +
                "主演：" + movieInfo.getActors() + "\n" +
                "剧情简介：" + movieInfo.getDescription();
        mFoldableTextView.setText(sb);
        mBaseItems.clear();
        List<MovieInfo.ListBean> listBeen = movieInfo.getList();
        for (MovieInfo.ListBean.ChildListBean listBean : listBeen.get(listBeen.size() - 1).getChildList()) {
            BaseItem<MovieInfo.ListBean.ChildListBean> baseItem = new BaseItem<>();
            baseItem.setData(listBean);
            mBaseItems.add(baseItem);
        }
        if (mAdapter == null) {
            mAdapter = new VideoInfoAdapter(this);
            mAdapter.setBaseDatas(mBaseItems);
            mMrvRecommend.setAdapter(mAdapter);
            mMrvRecommend.addOnItemClickListener(this);
        } else {
            mAdapter.setBaseDatas(mBaseItems);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.dispose();
    }
}

