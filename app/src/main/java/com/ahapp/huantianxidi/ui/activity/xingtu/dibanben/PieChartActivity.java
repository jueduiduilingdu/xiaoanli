package com.ahapp.huantianxidi.ui.activity.xingtu.dibanben;

import android.graphics.Color;
import android.util.DisplayMetrics;

import com.ahapp.huantianxidi.R;
import com.ahapp.huantianxidi.base.BaseActivity;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;

import butterknife.Bind;


/**
 * 作者：zengtao
 * 邮箱：1039562669@qq.com
 * 时间：2015/10/14 0014 10:05
 */
public class PieChartActivity extends BaseActivity {
    @Bind(R.id.pieChart)
    PieChart pieChart;



    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_pie_chart;
    }

    @Override
    protected void initViewsAndEvents() {
        initPieChart();
    }

    private void initPieChart() {
        pieChart.setDescription("BarChart Test");
        pieChart.setHoleColorTransparent(true);
        pieChart.setHoleRadius(60f);  //半径
        pieChart.setTransparentCircleRadius(0f); // 半透明圈
        pieChart.setHoleRadius(0);  //实心圆

        pieChart.setDrawCenterText(true);  //饼状图中间可以添加文字
        pieChart.setDrawHoleEnabled(true);
        pieChart.setRotationAngle(90); // 初始旋转角度

        pieChart.setRotationEnabled(true); // 可以手动旋转
        pieChart.setUsePercentValues(true);  //显示成百分比
//        pieChart.setCenterText("PieChart");  //饼状图中间的文字

        //设置数据
        PieData pieData = getPieData(6);
        pieChart.setData(pieData);
        //左边 的 方块及 文字
        Legend mLegend = pieChart.getLegend();  //设置比例图
        mLegend.setPosition(Legend.LegendPosition.LEFT_OF_CHART_CENTER);  //最左边显示
        mLegend.setForm(Legend.LegendForm.SQUARE);  //设置比例图的形状，默认是方形 SQUARE
        mLegend.setXEntrySpace(7f);
        mLegend.setYEntrySpace(5f);
        mLegend.setFormSize(12f);//比例块字体大小
        pieChart.animateXY(1000, 1000);  //设置动画
        pieChart.invalidate();
    }

    private PieData getPieData(int count) {
        int[] yy = {12, 12, 18, 20, 28, 10};

        ArrayList<String> xValues = new ArrayList<String>();  //xVals用来表示每个饼块上的内容
        for (int i = 0; i < count; i++) {
            xValues.add("PieChart" + (i + 1));  //饼块上显示成PieChart1, PieChart2, PieChart3, PieChart4，PieChart5，PieChart6
        }

        /**
         * 将一个饼形图分成六部分， 各个部分的数值比例为12:12:18:20:28:10
         * 所以 12代表的百分比就是12%
         * 在具体的实现过程中，这里是获取网络请求的list的数据
         */
        ArrayList<Entry> yValues = new ArrayList<Entry>();  //yVals用来表示封装每个饼块的实际数据
        for (int i = 0; i < count; i++) {
            yValues.add(new Entry(yy[i], i));
        }

        //y轴的集合
        PieDataSet pieDataSet = new PieDataSet(yValues, "PieChart Revenue 2014");
        pieDataSet.setSliceSpace(2f); //设置个饼状图之间的距离

        // 饼图颜色
        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(Color.rgb(205, 205, 205));
        colors.add(Color.rgb(114, 188, 223));
        colors.add(Color.rgb(255, 123, 124));
        colors.add(Color.rgb(57, 135, 200));
        colors.add(Color.rgb(30, 20, 200));
        colors.add(Color.rgb(80, 60, 150));
        pieDataSet.setColors(colors);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float px = 5 * (metrics.densityDpi / 160f);
        pieDataSet.setSelectionShift(px); // 选中态多出的长度
        PieData pieData = new PieData(xValues, pieDataSet);
        pieData.setValueTextSize(12f);//饼状图上的文字大小
        pieData.setValueTextColor(getResources().getColor(R.color.white));//饼状图上的文字颜色
        return pieData;
    }
}
