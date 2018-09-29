package com.ahapp.huantianxidi.ui.activity.xingtu.gaobanben;

import android.graphics.Color;

import com.ahapp.huantianxidi.R;
import com.ahapp.huantianxidi.base.BaseActivity;
//import com.ahapp.huantianxidi.ui.activity.xingtu.widget.MyMarkView;
//import com.github.mikephil.charting.charts.LineChart;
//import com.github.mikephil.charting.components.Description;
//import com.github.mikephil.charting.components.Legend;
//import com.github.mikephil.charting.components.XAxis;
//import com.github.mikephil.charting.components.YAxis;
//import com.github.mikephil.charting.data.Entry;
//import com.github.mikephil.charting.data.LineData;
//import com.github.mikephil.charting.data.LineDataSet;
//import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by Administrator on 2018/9/28.
 */

public class LineChartActivity2 extends BaseActivity {
    @Override
    protected int getContentViewLayoutID() {
        return 0;
    }

    @Override
    protected void initViewsAndEvents() {

    }

//    @Bind(R.id.lineChart)
//    LineChart mChart;
//
//
//
//    @Override
//    protected int getContentViewLayoutID() {
//        return R.layout.activity_line_chart;
//    }
//
//    @Override
//    protected void initViewsAndEvents() {
//        initChart();
//    }
//
//    /**
//     * 1.初始化LineChart
//     * 2.添加数据x，y轴数据
//     * 3.刷新图表
//     */
//    private void initChart() {
//        /**
//         * ====================1.初始化-自由配置===========================
//         */
//        // 是否在折线图上添加边框
//        mChart.setDrawGridBackground(false);
//        mChart.setDrawBorders(false);
//        // 设置右下角描述
//        //1.隐藏描述
////        Description description = new Description();
////        description.setEnabled(false);
////        mChart.setDescription(description);
//        // 设置右下角描述
//        //2.设置描述内容
//        Description description = new Description();
//        description.setText("X轴描述");
//        description.setTextColor(Color.RED);
//        mChart.setDescription(description);
//
//        //设置透明度
//        mChart.setAlpha(0.8f);
//        //设置网格底下的那条线的颜色
//        mChart.setBorderColor(Color.rgb(213, 216, 214));
//        //设置高亮显示
//       // mChart.setHighlightEnabled(true);
//        //设置是否可以触摸，如为false，则不能拖动，缩放等
//        mChart.setTouchEnabled(true);
//        //设置是否可以拖拽
//        mChart.setDragEnabled(false);
//        //设置是否可以缩放
//        mChart.setScaleEnabled(false);
//        //设置是否能扩大扩小
//        mChart.setPinchZoom(false);
//
//        XAxis xAxis = mChart.getXAxis();
//        //设置X轴的文字在底部
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        /**
//         * ====================2.布局点添加数据-自由布局===========================
//         */
//        // 折线图的点，点击战士的布局和数据
//        MyMarkView mv = new MyMarkView(this);
//        mChart.setMarkerView(mv);
//        // 加载数据
//        LineData data = getLineData();
//        mChart.setData(data);
//        //设置Y轴
//        YAxis rightYAxis = mChart.getAxisRight();
//        rightYAxis.setEnabled(false); //右侧Y轴不显示 右侧Y轴不显示4.X轴和Y轴类似，都具有相同的属性方法rightYAxis.setGranularity(1f);
////        rightYAxis.setLabelCount(11,false);
////        rightYAxis.setTextColor(Color.BLUE); //文字颜色
////        rightYAxis.setGridColor(Color.RED); //网格线颜色
////        rightYAxis.setAxisLineColor(Color.GREEN); //Y轴颜色
//
//
//        /**
//         * ====================3.x，y动画效果和刷新图表等===========================
//         */
//        //从X轴进入的动画
//        mChart.animateX(4000);
//        mChart.animateY(3000);   //从Y轴进入的动画
//        mChart.animateXY(3000, 3000);    //从XY轴一起进入的动画
//        //设置最小的缩放
//        mChart.setScaleMinima(0.5f, 1f);
//        Legend l = mChart.getLegend();
//        l.setForm(Legend.LegendForm.LINE);  //设置图最下面显示的类型
//        l.setTextSize(15);
//        l.setTextColor(Color.rgb(104, 241, 175));
//        l.setFormSize(30f);
//        // 刷新图表
//        mChart.invalidate();
//    }
//
//    private LineData getLineData() {
//        String[] xx = {"2", "4", "6", "8", "10", "12", "14", "16", "18"};
//        String[] yy = {"20", "80", "10", "60", "30", "70", "55", "22", "40"};
//
//        ArrayList<String> xVals = new ArrayList<String>();
//        for (int i = 0; i < xx.length; i++) {
//            xVals.add(xx[i]);
//        }
//
//        ArrayList<Entry> yVals = new ArrayList<Entry>();
//        for (int i = 0; i < yy.length; i++) {
//            yVals.add(new Entry(Float.parseFloat(yy[i]), i));
//        }
//
//        LineDataSet set1 = new LineDataSet(yVals, "LineChart Test");
//        set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);  //设置线性类型 CUBIC_BEZIER 圆滑曲线（默认折线）
//        set1.setCubicIntensity(0.2f);
//        set1.setDrawFilled(true);  //设置包括的范围区域填充颜色   显示圆点下面 是否覆盖
//        set1.setDrawCircles(true);  //设置有圆点
//        set1.setLineWidth(2f);    //设置线的宽度
//        set1.setCircleSize(5f);   //设置小圆的大小
//        set1.setHighLightColor(Color.rgb(244, 117, 117));
//        set1.setColor(Color.rgb(104, 241, 175));    //设置曲线的颜色
//        //set1.setColor(Color.RED);    //设置曲线的颜色
//        //保存LineDataSet集合
//        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
//        dataSets.add(set1); // add the datasets
//        return new LineData(xVals, dataSets);
//    }
}
