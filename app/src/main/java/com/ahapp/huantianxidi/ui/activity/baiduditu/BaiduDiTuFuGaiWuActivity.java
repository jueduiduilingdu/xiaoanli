package com.ahapp.huantianxidi.ui.activity.baiduditu;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahapp.huantianxidi.R;
import com.ahapp.huantianxidi.base.BaseActivity;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by Administrator on 2018/9/21.
 * <p>
 * 百度地图带覆盖物带文字 (根据经纬度)
 */

public class BaiduDiTuFuGaiWuActivity extends BaseActivity implements  BaiduMap.OnMapStatusChangeListener{
    @Bind(R.id.bmapView)
    MapView mapView;
    private BaiduMap aMap;
    Marker marker;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_baidu_di_tu_fu_gai_wu;
    }

    @Override
    protected void initViewsAndEvents() {
        if (aMap == null) {
            aMap = mapView.getMap();
        }
        // 定位信息 传输进去 比例尺传输进去
        // 20、19、18、17、16、15、14、13、12、11、10、9、8、7、6、5、4、3
        //"10m", "20m", "50m", "100m", "200m", "500m", "1km", "2km", "5km", "10km", "20km", "25km", "50km", "100km", "200km", "500km", "1000km", "2000km"

        MapStatusUpdate u = MapStatusUpdateFactory.zoomTo(Float.parseFloat("6"));
        aMap.animateMapStatus(u);
        LatLng llA1 = new LatLng(39.963175, 116.400244);//北京
        LatLng llA2 = new LatLng(31.835432, 117.262358);//合肥 安徽
        LatLng llA3 = new LatLng(31.229259, 121.461855);//上海
        ArrayList<LatLng> llB = new ArrayList<>();
        llB.add(llA1);
        llB.add(llA2);
        llB.add(llA3);
        ArrayList<String> name = new ArrayList<>();
        name.add("北京");
        name.add("安徽");
        name.add("上海");
        View view = View.inflate(BaiduDiTuFuGaiWuActivity.this, R.layout.fu_gai_wu, null);
        TextView nameView = (TextView) view.findViewById(R.id.tv_name);
        for (int i = 0; i < name.size(); i++) {
            nameView.setText(name.get(i).toString());
            // 构建BitmapDescriptor
            BitmapDescriptor bitmap = BitmapDescriptorFactory.fromView(view);
            //构建MarkerOption，用于在地图上添加Marker
            OverlayOptions option = new MarkerOptions()
                    .position(llB.get(i))
                    .animateType(MarkerOptions.MarkerAnimateType.grow)
                    .zIndex(10)
                    .period(10)
                    .title("place")
                    .icon(bitmap);
            //在地图上添加Marker，并显示
          //  Marker marker = (Marker) aMap.addOverlay(option);
            marker = (Marker) aMap.addOverlay(option);
        }

        aMap.setOnMapStatusChangeListener(this);// 对amap添加移动地图事件监听器

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mapView != null) {
            mapView.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mapView != null) {
            mapView.onPause();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mapView != null) {
            mapView.onSaveInstanceState(outState);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mapView != null) {
            try {
                mapView.onDestroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    @Override
    public void onMapStatusChangeStart(MapStatus mapStatus) {
        Log.e("开始", "onMapStatusChangeStart");
    }

    @Override
    public void onMapStatusChangeStart(MapStatus mapStatus, int i) {

    }

    @Override
    public void onMapStatusChange(MapStatus status) {
        Log.e("改变中", "onMapStatusChange");
//        if (status.zoom > 10) {
//            try {
//                float zoomLevel = Float.parseFloat("10");
//                Log.e("s", "1" + zoomLevel);
//                MapStatusUpdate u = MapStatusUpdateFactory
//                        .zoomTo(zoomLevel);
//                aMap.animateMapStatus(u);
//
//
//                LatLng llA1 = new LatLng(30.525731, 117.054409);//安庆
//                LatLng llA2 = new LatLng(31.835432, 117.262358);//合肥
//                LatLng llA3 = new LatLng(32.920796, 117.38689);//上海
//                ArrayList<LatLng> llB = new ArrayList<>();
//                llB.add(llA1);
//                llB.add(llA2);
//                llB.add(llA3);
//                ArrayList<String> name = new ArrayList<>();
//                name.add("安庆");
//                name.add("合肥");
//                name.add("上海");
//                View view = View.inflate(BaiduDiTuFuGaiWuActivity.this, R.layout.fu_gai_wu, null);
//                TextView nameView = (TextView) view.findViewById(R.id.tv_name);
//                for (int i = 0; i < name.size(); i++) {
//                    nameView.setText(name.get(i).toString());
//                    // 构建BitmapDescriptor
//                    BitmapDescriptor bitmap = BitmapDescriptorFactory.fromView(view);
//                    //构建MarkerOption，用于在地图上添加Marker
//                    OverlayOptions option = new MarkerOptions()
//                            .position(llB.get(i))
//                            .animateType(MarkerOptions.MarkerAnimateType.grow)
//                            .zIndex(10)
//                            .period(10)
//                            .title("place")
//                            .icon(bitmap);
//                    //在地图上添加Marker，并显示
//                    Marker marker = (Marker) aMap.addOverlay(option);
//                }
//
//
//            } catch (NumberFormatException e) {
//                // Toast.makeText(this, "请输入正确的缩放级别",
//                // Toast.LENGTH_SHORT).show();
//            }
//
//        } else if (status.zoom < 6){
//            try {
//                float zoomLevel = Float.parseFloat("6");
//                Log.e("s", "1" + zoomLevel);
//                MapStatusUpdate u = MapStatusUpdateFactory
//                        .zoomTo(zoomLevel);
//                aMap.animateMapStatus(u);
//
//
//                LatLng llA1 = new LatLng(39.963175, 116.400244);//北京
//                LatLng llA2 = new LatLng(31.835432, 117.262358);//合肥 安徽
//                LatLng llA3 = new LatLng(31.229259, 121.461855);//上海
//                ArrayList<LatLng> llB = new ArrayList<>();
//                llB.add(llA1);
//                llB.add(llA2);
//                llB.add(llA3);
//                ArrayList<String> name = new ArrayList<>();
//                name.add("北京");
//                name.add("安徽");
//                name.add("上海");
//                View view = View.inflate(BaiduDiTuFuGaiWuActivity.this, R.layout.fu_gai_wu, null);
//                TextView nameView = (TextView) view.findViewById(R.id.tv_name);
//                for (int i = 0; i < name.size(); i++) {
//                    nameView.setText(name.get(i).toString());
//                    // 构建BitmapDescriptor
//                    BitmapDescriptor bitmap = BitmapDescriptorFactory.fromView(view);
//                    //构建MarkerOption，用于在地图上添加Marker
//                    OverlayOptions option = new MarkerOptions()
//                            .position(llB.get(i))
//                            .animateType(MarkerOptions.MarkerAnimateType.grow)
//                            .zIndex(10)
//                            .period(10)
//                            .title("place")
//                            .icon(bitmap);
//                    //在地图上添加Marker，并显示
//                    Marker marker = (Marker) aMap.addOverlay(option);
//                }
//
//
//            } catch (NumberFormatException e) {
//                // Toast.makeText(this, "请输入正确的缩放级别",
//                // Toast.LENGTH_SHORT).show();
//            }
//
//        }else {
//
//        }
    }

    @Override
    public void onMapStatusChangeFinish(MapStatus status) {
        Log.e("改变结束", "map" + status.zoom);

        if (status.zoom >= 10) {
            clearOverlay();
            try {
                float zoomLevel = Float.parseFloat("10");
                Log.e("s", "1" + zoomLevel);
                MapStatusUpdate u = MapStatusUpdateFactory
                        .zoomTo(zoomLevel);
                aMap.animateMapStatus(u);


                LatLng llA1 = new LatLng(30.525731, 117.054409);//安庆
                LatLng llA2 = new LatLng(31.835432, 117.262358);//合肥
                LatLng llA3 = new LatLng(32.920796, 117.38689);//上海
                ArrayList<LatLng> llB = new ArrayList<>();
                llB.add(llA1);
                llB.add(llA2);
                llB.add(llA3);
                ArrayList<String> name = new ArrayList<>();
                name.add("安庆");
                name.add("合肥");
                name.add("上海");
                View view = View.inflate(BaiduDiTuFuGaiWuActivity.this, R.layout.fu_gai_wu, null);
                TextView nameView = (TextView) view.findViewById(R.id.tv_name);
                for (int i = 0; i < name.size(); i++) {
                    nameView.setText(name.get(i).toString());
                    // 构建BitmapDescriptor
                    BitmapDescriptor bitmap = BitmapDescriptorFactory.fromView(view);
                    //构建MarkerOption，用于在地图上添加Marker
                    OverlayOptions option = new MarkerOptions()
                            .position(llB.get(i))
                            .animateType(MarkerOptions.MarkerAnimateType.grow)
                            .zIndex(10)
                            .period(10)
                            .title("place")
                            .icon(bitmap);
                    //在地图上添加Marker，并显示
                  //  Marker marker = (Marker) aMap.addOverlay(option);
                    marker = (Marker) aMap.addOverlay(option);
                }


            } catch (NumberFormatException e) {
                // Toast.makeText(this, "请输入正确的缩放级别",
                // Toast.LENGTH_SHORT).show();
            }

        } else if (status.zoom <= 6){
            clearOverlay();
            try {
                float zoomLevel = Float.parseFloat("6");
                Log.e("s", "1" + zoomLevel);
                MapStatusUpdate u = MapStatusUpdateFactory
                        .zoomTo(zoomLevel);
                aMap.animateMapStatus(u);


                LatLng llA1 = new LatLng(39.963175, 116.400244);//北京
                LatLng llA2 = new LatLng(31.835432, 117.262358);//合肥 安徽
                LatLng llA3 = new LatLng(31.229259, 121.461855);//上海
                ArrayList<LatLng> llB = new ArrayList<>();
                llB.add(llA1);
                llB.add(llA2);
                llB.add(llA3);
                ArrayList<String> name = new ArrayList<>();
                name.add("北京");
                name.add("安徽");
                name.add("上海");
                View view = View.inflate(BaiduDiTuFuGaiWuActivity.this, R.layout.fu_gai_wu, null);
                TextView nameView = (TextView) view.findViewById(R.id.tv_name);
                for (int i = 0; i < name.size(); i++) {
                    nameView.setText(name.get(i).toString());
                    // 构建BitmapDescriptor
                    BitmapDescriptor bitmap = BitmapDescriptorFactory.fromView(view);
                    //构建MarkerOption，用于在地图上添加Marker
                    OverlayOptions option = new MarkerOptions()
                            .position(llB.get(i))
                            .animateType(MarkerOptions.MarkerAnimateType.grow)
                            .zIndex(10)
                            .period(10)
                            .title("place")
                            .icon(bitmap);
                    //在地图上添加Marker，并显示
                  //  Marker marker = (Marker) aMap.addOverlay(option);
                    marker = (Marker) aMap.addOverlay(option);
                }


            } catch (NumberFormatException e) {
                // Toast.makeText(this, "请输入正确的缩放级别",
                // Toast.LENGTH_SHORT).show();
            }

        }else {

        }


    }
    private void clearOverlay() {
        aMap.clear();
        marker = null;

    }
}
