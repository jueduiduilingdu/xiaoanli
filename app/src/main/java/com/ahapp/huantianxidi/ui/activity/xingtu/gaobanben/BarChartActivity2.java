package com.ahapp.huantianxidi.ui.activity.xingtu.gaobanben;

import android.graphics.Color;

import com.ahapp.huantianxidi.R;
import com.ahapp.huantianxidi.base.BaseActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by Administrator on 2018/9/28.
 */

public class BarChartActivity2 extends BaseActivity {
    @Override
    protected int getContentViewLayoutID() {
        return 0;
    }

    @Override
    protected void initViewsAndEvents() {

    }
//    @Bind(R.id.barChart)
//    BarChart barChart;
//
//
//
//    @Override
//    protected int getContentViewLayoutID() {
//        return R.layout.activity_bar_chart;
//    }
//
//    @Override
//    protected void initViewsAndEvents() {
//        initBarChart();
//    }
//
//    private void initBarChart() {
//        barChart.setDrawGridBackground(false);
//        barChart.setDrawBorders(false);  //是否在折线图上添加边框
//        barChart.setDescription("");// 数据描述
//        //barChart.setNoDataTextDescription("no data to display"); // 如果没有数据，显示
//        barChart.setDrawGridBackground(false); // 是否显示表格颜色
//        barChart.setGridBackgroundColor(Color.WHITE); // 表格的的颜色，在这里是是给颜色设置一个透明度
//
//        barChart.setTouchEnabled(false); // 设置是否可以触摸
//        barChart.setDragEnabled(false);// 是否可以拖拽
//        barChart.setScaleEnabled(false);// 是否可以缩放
//        barChart.setPinchZoom(false);//设置x轴和y轴能否同时缩放。默认否
//        barChart.setDrawBarShadow(false); //绘制当前展示的内容顶部阴影
//
//        // 设置数据
//        BarData mBarData = getBarData(4);
//        barChart.setData(mBarData);
//
//        Legend mLegend = barChart.getLegend(); // 设置比例图标示
//        mLegend.setForm(Legend.LegendForm.CIRCLE);// 样式
//        mLegend.setFormSize(6f);// 字体
//        mLegend.setTextColor(Color.BLACK);// 颜色
//
//        // X轴设定
//        barChart.animateY(3000);
//        barChart.invalidate();
//    }
//
//    private BarData getBarData(int count) {
//        int[] yy = {60, 40, 80, 50};
//
//        ArrayList<String> xValues = new ArrayList<String>();
//        for (int i = 0; i < count; i++) {
//            xValues.add(String.format("爱情公寓%s", (i + 1)));
//        }
//
//        ArrayList<BarEntry> yValues = new ArrayList<BarEntry>();
//        for (int i = 0; i < count; i++) {
//            yValues.add(new BarEntry(yy[i], i));
//        }
//        XAxis xAxis = barChart.getXAxis();
//
//        //设置X轴的文字在底部
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        // y轴的数据集合
//        BarDataSet barDataSet = new BarDataSet(yValues, "BarChart Test");
//        barDataSet.setBarSpacePercent(50f);//设置条形图 的宽度
//        barDataSet.setColor(Color.rgb(114, 188, 223));
//        ArrayList<BarDataSet> barDataSets = new ArrayList<BarDataSet>();
//        barDataSets.add(barDataSet); // add the datasets
//        BarData barData = new BarData(xValues, barDataSets);
//        return barData;
//    }
}
