package com.ahapp.huantianxidi.ui.activity.xingtu.dibanben;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ahapp.huantianxidi.R;
import com.ahapp.huantianxidi.base.BaseActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2018/9/28.
 */

public class LineChartActivity1B extends BaseActivity implements View.OnClickListener {
    @Override
    public void onClick(View view) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return 0;
    }

    @Override
    protected void initViewsAndEvents() {

    }
//    @Bind(R.id.lineChart)
//    LineChart mLineChart;
//
//
//    @Override
//    protected int getContentViewLayoutID() {
//        return R.layout.activity_line_chart;
//    }
//
//    @Override
//    protected void initViewsAndEvents() {
//        requestTrendChart();
//    }
//
//    private void requestTrendChart() {
//
//
////                List<String> XLables = new ArrayList<>();
////                List<String> Points = new ArrayList<>();
////                for (int i = 0; i < data.size(); i++) {
////                    XLables.add(data.get(i).getIncomeTime());
////                    Points.add(data.get(i).getDailyIncome());
////                }
//
//
//        XAxis xAxis = mLineChart.getXAxis();
//
//        //设置X轴的文字在底部
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setSpaceBetweenLabels(2);//设置X轴间隔
//        //设置描述文字
////                mLineChart.setDescription("拆分走势图");
//        mLineChart.setDescription("");
//        //模拟一个x轴的数据  12/1 12/2 ... 12/7
//        ArrayList<String> xValues = new ArrayList<>();
//        for (int i = 1; i < 20; i++) {
//            xValues.add("12/" + i);
//        }
//        Log.e("wing", xValues.size() + "");
//        //模拟一组y轴数据(存放y轴数据的是一个Entry的ArrayList) 他是构建LineDataSet的参数之一
//        ArrayList<Entry> yValue = new ArrayList<>();
//
//        yValue.add(new Entry(7, 0));
//        yValue.add(new Entry(17, 1));
//        yValue.add(new Entry(3, 2));
//        yValue.add(new Entry(5, 3));
//        yValue.add(new Entry(4, 4));
//        yValue.add(new Entry(3, 5));
//        yValue.add(new Entry(7, 6));
//        yValue.add(new Entry(7, 7));
//        yValue.add(new Entry((float) 17.3, 8));
//        yValue.add(new Entry(3, 9));
//        yValue.add(new Entry(5, 10));
//        yValue.add(new Entry(4, 11));
//        yValue.add(new Entry(3, 12));
//        yValue.add(new Entry(7, 13));
////                //设置一页最大显示个数为6，超出部分就滑动
////                float ratio = (float) xValues.size()/(float) 6;
////                //显示的时候是按照多大的比率缩放显示,1f表示不放大缩小
////                mLineChart.zoom(ratio,1f,0,0);
//        //构建一个LineDataSet 代表一组Y轴数据 （比如不同的彩票： 七星彩  双色球）
//        LineDataSet dataSet = new LineDataSet(yValue, "公共拆分率");
//        //模拟第二组组y轴数据(存放y轴数据的是一个Entry的ArrayList) 他是构建LineDataSet的参数之一
//
//
//        //设置一页最大显示个数为6，超出部分就滑动
//        float ratio = (float) xValues.size() / (float) 6;
//        //显示的时候是按照多大的比率缩放显示,1f表示不放大缩小
//        mLineChart.zoom(ratio, 1f, 0, 0);
//        Log.e("wing", yValue.size() + "");
//
//        //构建一个LineDataSet 代表一组Y轴数据 （比如不同的彩票： 七星彩  双色球）
//
//
//        dataSet.setColor(Color.BLACK);
//        dataSet.setCircleColor(Color.BLACK);// 圆形的颜色
//        //构建一个类型为LineDataSet的ArrayList 用来存放所有 y的LineDataSet   他是构建最终加入LineChart数据集所需要的参数
//        ArrayList<LineDataSet> dataSets = new ArrayList<>();
//        //设置线宽
//        dataSet.setLineWidth(2f);
//        //设置线宽
//        dataSet.setLineWidth(2f);
//        //将数据加入dataSets
//        dataSets.add(dataSet);
//
//
//        //构建一个LineData  将dataSets放入
//        LineData lineData = new LineData(xValues, dataSets);
//
//        //将数据插入
//        mLineChart.setData(lineData);
//
//
//    }
//
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.iv_return:
//                finish();
//                break;
//        }
//    }
}
