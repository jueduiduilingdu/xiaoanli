package com.ahapp.huantianxidi.ui.fragment;


import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.ahapp.huantianxidi.R;
import com.ahapp.huantianxidi.base.BaseFragment;

import com.ahapp.huantianxidi.view.RefreshLayout;


/**
 * Created by Administrator on 2018/1/3 0003.
 */

public class SortsFragment extends BaseFragment implements RefreshLayout.ILoadListener, RefreshLayout.OnRefreshListener {


    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoad() {

    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.fragment_sorts;
    }

    @Override
    protected void initViewsAndEvents() {

    }
}
