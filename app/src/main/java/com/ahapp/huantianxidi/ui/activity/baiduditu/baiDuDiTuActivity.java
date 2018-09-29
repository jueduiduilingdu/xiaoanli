package com.ahapp.huantianxidi.ui.activity.baiduditu;

import android.view.View;

import com.ahapp.huantianxidi.R;
import com.ahapp.huantianxidi.base.BaseActivity;
import com.ahapp.huantianxidi.ui.activity.guanggao.guangGaoActivity;

import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/25.
 */

public class baiDuDiTuActivity extends BaseActivity {
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_bai_du_di_tu;
    }

    @Override
    protected void initViewsAndEvents() {

    }
    @OnClick({R.id.bt_fu_gai_wu,R.id.bt_fu_gai_wu2})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_fu_gai_wu://百度地图上的覆盖物 上面带文字 (根据经纬度)
                go(BaiduDiTuFuGaiWuActivity.class);
                break;
            case R.id.bt_fu_gai_wu2://百度地图上的覆盖物 上面带文字 (根据地名)
                go(BaiduDiTuFuGaiWuWenZiActivity.class);
                break;
        }
    }
}
