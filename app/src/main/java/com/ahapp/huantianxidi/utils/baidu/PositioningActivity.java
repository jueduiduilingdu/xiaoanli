package com.ahapp.huantianxidi.utils.baidu;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.ahapp.huantianxidi.R;
import com.ahapp.huantianxidi.base.BaseActivity;
import com.ahapp.huantianxidi.ui.adapter.BasicAdapter;
import com.ahapp.huantianxidi.ui.adapter.ViewHolder;
import com.ahapp.huantianxidi.utils.comm.StringUtils;
import com.ahapp.huantianxidi.view.ClearEditText;
import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/11/6 0006.
 * 百度定位
 */
public class PositioningActivity extends BaseActivity implements View.OnClickListener, BaiduMap.OnMapStatusChangeListener, BaiduMap.OnMapTouchListener {
    private static final int ACCESS_FINE_LOCATION_REQUEST_CODE = 0x12;


    @Bind(R.id.acl_address_list)
    ListView addressList;
    @Bind(R.id.al_search_et)
    ClearEditText searchEt;
    @Bind(R.id.acl_map_view)
    MapView mapView;

    @Bind(R.id.ll_et_address)
    LinearLayout ll_et_address;
    @Bind(R.id.et_address)
    EditText et_address;//手动输入地址
    @Bind(R.id.bt_confirm)
    Button bt_confirm;//确定按钮

    private double latitude, longitude;
    private String city = "";
    private GeoCoder geoCoder;

    private PoiSearch mPoiSearch;
    private String cityTxt;

    //位置数据
    private List<PoiInfo> locationData;
    //搜索位置数据
    private List<PoiInfo> searchData;

    private BaiduMap aMap;

    private BasicAdapter<PoiInfo> locationAdapter;

    private BaiduLocationTool baiduLocationTool;

    private int value = 0;// 0 显示列表 1 不显示

    private Handler mHandler = new Handler();
    private Runnable locRunnable = new Runnable() {
        @Override
        public void run() {
            searchVenue();
        }
    };

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_positioning;
    }

    @Override
    protected void initViewsAndEvents() {


        Intent intent = getIntent();
        city = intent.getStringExtra("city");
        longitude = Double.parseDouble(intent.getStringExtra("longitude"));
        latitude = Double.parseDouble(intent.getStringExtra("latitude"));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                baiduLocationTool = BaiduLocationTool.newInstance(this);
                initData();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, ACCESS_FINE_LOCATION_REQUEST_CODE);
            }
        } else {
            baiduLocationTool = BaiduLocationTool.newInstance(this);
            initData();
        }
        bt_confirm.setOnClickListener(this);
    }

    private void initMap() {
        if (aMap == null) {
            aMap = mapView.getMap();
        }
        showView();
        aMap.setOnMapStatusChangeListener(this);// 对amap添加移动地图事件监听器
        aMap.setOnMapTouchListener(this);
    }


    //显示地图位置
    private void showView() {
        LatLng p = new LatLng(latitude, longitude);
        //绘制marker
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(p)
                .zoom(16)
                .build();
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        aMap.setMapStatus(mMapStatusUpdate);
    }

    private void initData() {
        locationData = new ArrayList<>();
        locationAdapter = new BasicAdapter<PoiInfo>(this, locationData, R.layout.item_positioning) {
            @Override
            protected void render(ViewHolder holder, PoiInfo item, int position) {
                holder.setText(R.id.il_name_txt, item.name != null ? item.name : "暂无信息");
                holder.setText(R.id.il_detail_txt, item.address != null ? item.address : "暂无信息");
            }
        };
        addressList.setAdapter(locationAdapter);
        addressList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                isTouch = false;
                Intent intent = new Intent();
//                intent.putExtra("address",locationAdapter.getDataList().get(position).name);
//                intent.putExtra("location",locationAdapter.getDataList().get(position).location);

                intent.putExtra("data", locationAdapter.getDataList().get(position).address + locationAdapter.getDataList().get(position).name);
                intent.putExtra("longitude", locationAdapter.getDataList().get(position).location.longitude + "");
                intent.putExtra("latitude", locationAdapter.getDataList().get(position).location.latitude + "");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        baiduLocationTool.startLocation(new BaiduLocationTool.LocationListener() {
            @Override
            public void onReceiveLocation(BDLocation location) {
//                longitude=location.getLongitude();
//                //纬度
//                latitude=location.getLatitude();
//                cityTxt=location.getCity();
                cityTxt = city;
                if (!StringUtils.isEmpty(cityTxt)) {
                    initListener();
                    initGeoCoder();
                    initMap();
                    geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(new LatLng(latitude, longitude)));
                }
            }
        });
    }

    //初始化反地理编码
    private void initGeoCoder() {
        geoCoder = GeoCoder.newInstance();
        OnGetGeoCoderResultListener listener = new OnGetGeoCoderResultListener() {
            // 反地理编码查询结果回调函数
            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
                if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                    // 没有检测到结果
                    return;
                }
                if (result.getPoiList() != null && result.getPoiList().size() > 0) {
                    locationData = result.getPoiList();
                    locationAdapter.getDataList().clear();
                    locationAdapter.getDataList().addAll(locationData);
                    locationAdapter.notifyDataSetChanged();
                }

            }

            // 地理编码查询结果回调函数
            @Override
            public void onGetGeoCodeResult(GeoCodeResult result) {
                if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                    // 没有检测到结果
                    return;
                }


            }
        };
        // 设置地理编码检索监听者
        geoCoder.setOnGetGeoCodeResultListener(listener);
    }


    private void initListener() {
        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String key = searchEt.getText().toString().trim();
                if (StringUtils.isEmpty(key)) {
                    locationAdapter.setDataList(locationData);
                    locationAdapter.notifyDataSetChanged();
                    if (locationData.size() > 0) {
                        addOverlay(locationData.get(0));
                    }
                } else {
                    searchData = new ArrayList<>();
                    locationAdapter.setDataList(searchData);
                    locationAdapter.notifyDataSetChanged();
                    if (mPoiSearch == null) {
                        mPoiSearch = PoiSearch.newInstance();
                        OnGetPoiSearchResultListener poiListener = new OnGetPoiSearchResultListener() {
                            @Override
                            public void onGetPoiResult(PoiResult poiResult) {
                                if (StringUtils.isEmpty(searchEt.getText().toString().trim())) {
                                    locationAdapter.setDataList(locationData);
                                    locationAdapter.notifyDataSetChanged();
                                    return;
                                }
                                if (poiResult == null || poiResult.error != SearchResult.ERRORNO.NO_ERROR) {
                                    // 没有检测到结果
                                    ll_et_address.setVisibility(View.VISIBLE);
                                    addressList.setVisibility(View.GONE);
                                    value = 1;
                                    return;
                                }
                                if (poiResult.getAllPoi() != null) {
                                    searchData = poiResult.getAllPoi();
                                    ll_et_address.setVisibility(View.GONE);
                                    addressList.setVisibility(View.VISIBLE);
                                    value = 0;
                                    addOverlay(searchData.get(0));
                                }
                                locationAdapter.setDataList(searchData);
                                locationAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
                            }

                            @Override
                            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
                            }
                        };
                        mPoiSearch.setOnGetPoiSearchResultListener(poiListener);
                    }

                    mPoiSearch.searchInCity((new PoiCitySearchOption()).city(cityTxt).keyword(key));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void addOverlay(PoiInfo poiInfo) {
        if (null != poiInfo && null != poiInfo.location) {
            LatLng point = new LatLng(poiInfo.location.latitude, poiInfo.location.longitude);
            //绘制marker
            MapStatus mMapStatus = new MapStatus.Builder()
                    .target(point)
                    .zoom(16)
                    .build();
            //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
            MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
            //改变地图状态
            aMap.setMapStatus(mMapStatusUpdate);
        }
    }

    @Override
    public void onMapStatusChangeStart(MapStatus mapStatus) {

    }

    @Override
    public void onMapStatusChangeStart(MapStatus mapStatus, int i) {

    }

    @Override
    public void onMapStatusChange(MapStatus mapStatus) {

    }

    @Override
    public void onMapStatusChangeFinish(MapStatus mapStatus) {
        if (isTouch) {
            Log.e("touch", "拖动地图");
            LatLng latLng = mapStatus.target;
            latitude = latLng.latitude;
            longitude = latLng.longitude;
            getWindow().getDecorView().post(new Runnable() {
                @Override
                public void run() {
                    mHandler.post(locRunnable);
                }
            });
        }
    }

    private boolean isTouch = false;

    @Override
    public void onTouch(MotionEvent motionEvent) {
        isTouch = true;
    }

    private void searchVenue() {
        Log.e("search", "查询信息");
        if (value == 1) {
            ll_et_address.setVisibility(View.VISIBLE);
            addressList.setVisibility(View.GONE);
        } else {
            ll_et_address.setVisibility(View.GONE);
            addressList.setVisibility(View.VISIBLE);
        }
        geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(new LatLng(latitude, longitude)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mapView != null) {
            mapView.onResume();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == ACCESS_FINE_LOCATION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                baiduLocationTool = BaiduLocationTool.newInstance(this);
                initData();
            } else {
                showToast("请到设置界面设置定位权限");
            }
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
        if (geoCoder != null) {
            geoCoder.destroy();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_confirm:
                if (StringUtils.getEditString(et_address).equals("")) {
                    showToast("请输入地址");
                    return;
                }
                isTouch = false;
                //    Log.e("longitude1",longitude+";"+latitude);
                Intent intent = new Intent();
                intent.putExtra("data", StringUtils.getEditString(et_address));
                intent.putExtra("longitude", longitude + "");
                intent.putExtra("latitude", latitude + "");
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }
}
