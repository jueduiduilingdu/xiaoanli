package com.ahapp.huantianxidi.ui.fragment;


import android.os.Bundle;
import android.view.View;

import com.ahapp.huantianxidi.R;
import com.ahapp.huantianxidi.base.App;
import com.ahapp.huantianxidi.base.BaseFragment;
import com.ahapp.huantianxidi.base.Constants;
import com.ahapp.huantianxidi.ui.activity.baiduditu.BaiduDiTuFuGaiWuActivity;
import com.ahapp.huantianxidi.ui.activity.baiduditu.baiDuDiTuActivity;
import com.ahapp.huantianxidi.ui.activity.guanggao.guangGaoActivity;
import com.ahapp.huantianxidi.ui.activity.xingtu.xingTuActivity;
import com.ahapp.huantianxidi.utils.comm.StringUtils;
import com.ahapp.huantianxidi.view.RefreshScrollviewLayout;

import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/3 0003.
 */

public class HomeFragment extends BaseFragment{



    @Override
    protected int getContentViewLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViewsAndEvents() {

    }

    @OnClick({R.id.bt_ditu,R.id.bt_guanggao,R.id.bt_xingtu})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_ditu://百度地图上的覆盖物 上面带文字
                go(baiDuDiTuActivity.class);
                break;
            case R.id.bt_guanggao://广告
                go(guangGaoActivity.class);
                break;
            case R.id.bt_xingtu://形图
                go(xingTuActivity.class);
                break;
        }
    }

}
