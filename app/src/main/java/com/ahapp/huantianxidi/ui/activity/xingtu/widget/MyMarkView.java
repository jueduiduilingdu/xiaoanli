package com.ahapp.huantianxidi.ui.activity.xingtu.widget;

import android.content.Context;
import android.widget.TextView;

import com.ahapp.huantianxidi.R;
import com.ahapp.huantianxidi.utils.UnitUtils;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;

import com.github.mikephil.charting.utils.Utils;


/**
 * 作者：zengtao
 * 邮箱：1039562669@qq.com
 * 时间：2015/10/13 0013 19:08
 */
public class MyMarkView extends MarkerView {
    private TextView tvMarkText;

    public MyMarkView(Context context) {
        super(context, R.layout.mark_view);
        tvMarkText = (TextView) findViewById(R.id.tvMarkText);
    }

    @Override
    public void refreshContent(Entry entry, Highlight highlight) {
        if (entry instanceof CandleEntry) {
            CandleEntry ce = (CandleEntry) entry;
            tvMarkText.setText("" + Utils.formatNumber(ce.getHigh(), 0, true));
        } else {
            tvMarkText.setText("" + entry.getVal());
        }
    }

    @Override
    public int getXOffset() {
        return -(getWidth() / 2);
    }

    @Override
    public int getYOffset() {
        return -getHeight() - UnitUtils.dp2px(getContext(), 2);
    }


}
