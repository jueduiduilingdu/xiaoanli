package com.ahapp.huantianxidi.ui.activity.xingtu;

import android.view.View;

import com.ahapp.huantianxidi.R;
import com.ahapp.huantianxidi.base.BaseActivity;
import com.ahapp.huantianxidi.ui.activity.baiduditu.baiDuDiTuActivity;
import com.ahapp.huantianxidi.ui.activity.guanggao.guangGaoActivity;
import com.ahapp.huantianxidi.ui.activity.xingtu.dibanben.BarChartActivity;
import com.ahapp.huantianxidi.ui.activity.xingtu.dibanben.LineChartActivity;
import com.ahapp.huantianxidi.ui.activity.xingtu.dibanben.LineChartActivity1B;
import com.ahapp.huantianxidi.ui.activity.xingtu.dibanben.PieChartActivity;

import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/28.
 */

public class xingTuActivity extends BaseActivity {
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_xing_tu;
    }

    @Override
    protected void initViewsAndEvents() {

    }
    @OnClick({R.id.bt01,R.id.bt02,R.id.bt03})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt01://折线图
                go(LineChartActivity.class);
                //   go(LineChartActivity1B.class);
                break;
            case R.id.bt02://条形图
                go(BarChartActivity.class);
                break;
            case R.id.bt03://扇形图
                go(PieChartActivity.class);
                break;
        }
    }
}
