package com.ahapp.huantianxidi.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2017/3/31 0031.
 */
public class LoadMoreRecyclerView extends RecyclerView {

    private OnLoadMoreListener mLoadMoreListener;
    private boolean mIsLoadingMore;
    private RecyclerView.Adapter mAdapter;
    private Context mContext;
    private LayoutManager mLayoutManager;

    public interface OnLoadMoreListener{
        void onLoadMore();
    }


    public LoadMoreRecyclerView(Context context) {
        super(context);
        mContext=context;
        init();
    }

    public LoadMoreRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        init();
    }

    public LoadMoreRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext=context;
        init();
    }
    private void init(){
        mLayoutManager=new LinearLayoutManager(mContext);
        setLayoutManager(mLayoutManager);
        setHasFixedSize(true);
        this.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (null != mLoadMoreListener) {
                    if (getLayoutManager() instanceof LinearLayoutManager) {
                        int lastVisiblePosition = ((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
                        if (!mIsLoadingMore && dy > 0 && lastVisiblePosition + 1 == mAdapter.getItemCount()) {
                            mIsLoadingMore = true;
                            mLoadMoreListener.onLoadMore();
                        }
                    }else if (getLayoutManager() instanceof GridLayoutManager){
                        int lastVisiblePosition =((GridLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
                        if (!mIsLoadingMore && dy > 0 && lastVisiblePosition + 1 == mAdapter.getItemCount()) {
                            mIsLoadingMore = true;
                            mLoadMoreListener.onLoadMore();
                        }
                    }
                }
            }
        });
    }

    @Override
    public void setLayoutManager(LayoutManager layout) {
        super.setLayoutManager(layout);
        mLayoutManager=layout;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mLoadMoreListener) {
        this.mLoadMoreListener = mLoadMoreListener;
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        this.mAdapter= adapter;
    }

    public void onLoadMoreComplete(){
        mIsLoadingMore=false;
    }
}
