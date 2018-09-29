package com.ahapp.huantianxidi.ui.activity.guanggao;

import com.ahapp.huantianxidi.R;
import com.ahapp.huantianxidi.base.BaseActivity;
import com.ahapp.huantianxidi.ui.adapter.guangGaoAdapter;
import com.ahapp.huantianxidi.view.myGallery;
import com.baidu.mapapi.map.MapView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;

import butterknife.Bind;

/**
 * Created by Administrator on 2018/9/25.
 * 广告
 */

public class guangGaoActivity extends BaseActivity {
    @Bind(R.id.fancyCoverFlow)
    myGallery galleryFlow;
    @Bind(R.id.title)
    TextView tvTitle;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_guang_gao;
    }

    @Override
    protected void initViewsAndEvents() {
        initRes();
    }
    private void initRes() {
        final guangGaoAdapter adapter = new guangGaoAdapter(this);
        adapter.createReflectedImages();
        galleryFlow.setAdapter(adapter);
        galleryFlow.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                tvTitle.setText(adapter.titles[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        galleryFlow.setOnItemClickListener(new OnItemClickListener() { // 设置点击事件监听
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
 /*               if (position == 1) {
                    Intent intent = new Intent(MainActivity.this,
                            ShitingActivity.class); // 试听中心
                    startActivity(intent);
                    // finish();
                } else if (position == 2) {
                    Intent intent = new Intent(MainActivity.this,
                            NewsActivity.class); // 新闻资讯
                    startActivity(intent);
                    // finish();
                } else if (position == 0) {
                    Intent intent = new Intent(MainActivity.this,
                            BoardActivity.class); // 大盘博弈
                    startActivity(intent);
                    // finish();
                } else if (position == 3) {
                    Intent intent = new Intent(MainActivity.this,
                            MyActivity.class); // 个人中心
                    startActivity(intent);
                    // finish();
                }*/

            }
        });

    }

}
