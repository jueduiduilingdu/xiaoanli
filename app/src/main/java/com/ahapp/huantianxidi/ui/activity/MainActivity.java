package com.ahapp.huantianxidi.ui.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.PermissionChecker;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ahapp.huantianxidi.R;
import com.ahapp.huantianxidi.base.App;
import com.ahapp.huantianxidi.base.BaseActivity;
import com.ahapp.huantianxidi.base.Constants;
import com.ahapp.huantianxidi.base.SaveUserTool;
import com.ahapp.huantianxidi.ui.adapter.BaseFragmentPagerAdapter;
import com.ahapp.huantianxidi.ui.fragment.FindFragment;
import com.ahapp.huantianxidi.ui.fragment.HomeFragment;
import com.ahapp.huantianxidi.ui.fragment.MeFragment;
import com.ahapp.huantianxidi.ui.fragment.ShoppingCartFragment;
import com.ahapp.huantianxidi.ui.fragment.SortsFragment;
import com.ahapp.huantianxidi.utils.comm.CheckVersionUtils;
import com.ahapp.huantianxidi.utils.comm.StringUtils;
import com.ahapp.huantianxidi.utils.http.Apis;
import com.ahapp.huantianxidi.utils.http.HttpTool;
import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.doumee.model.request.appDicInfo.AppDicInfoParam;
import com.doumee.model.request.appDicInfo.AppDicInfoRequestObject;
import com.doumee.model.request.userInfo.MemberInfoParamObject;
import com.doumee.model.request.userInfo.MemberInfoRequestObject;
import com.doumee.model.response.appDicInfo.AppConfigureResponseObject;
import com.doumee.model.response.appDicInfo.AppConfigureResponseParam;
import com.doumee.model.response.userinfo.MemberInfoResponseObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private final int LOCATION=0x10;

    @Bind(R.id.am_home_page)
    TextView homeTv;
    @Bind(R.id.am_find)
    TextView findTv;
    @Bind(R.id.am_business)
    TextView businessTv;
    @Bind(R.id.am_shopping_cart)
    TextView ShoppingCartTv;
    @Bind(R.id.am_me)
    TextView meTv;
    @Bind(R.id.am_content_lyt)
    ViewPager contentLyt;

    private long exitTime = 0;

    private ArrayList<Fragment> fragments;


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViewsAndEvents() {

//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
//
//        }
        initData();
        if (App.getUser()!=null) {
            requestMemberInfo();
            PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY, Constants.ThirdParty.BAIDU_KEY);
        }
        CheckVersionUtils versionUtils=new CheckVersionUtils(this);
        versionUtils.checkVersion();
        requestDataIndex();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Build.VERSION.SDK_INT >= 23){
            checkPermission();
        }
    }

    private void initData() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new SortsFragment());
        fragments.add(new FindFragment());
        fragments.add(new ShoppingCartFragment());
        fragments.add(new MeFragment());
        BaseFragmentPagerAdapter adapter = new BaseFragmentPagerAdapter(getSupportFragmentManager(),fragments);
        contentLyt.setAdapter(adapter);
        contentLyt.setOffscreenPageLimit(5);
        contentLyt.setCurrentItem(0);
        homeTv.setSelected(true);
        contentLyt.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        homeTv.setSelected(true);
                        findTv.setSelected(false);
                        businessTv.setSelected(false);
                        ShoppingCartTv.setSelected(false);
                        meTv.setSelected(false);
//                        mImmersionBar = ImmersionBar.with(MainActivity.this);
//                        mImmersionBar.fitsSystemWindows(true).init();
//                        if (ImmersionBar.isSupportStatusBarDarkFont()) {
//                            mImmersionBar.statusBarDarkFont(true).statusBarColor(R.color.colorPrimary).init();
//                        }else {
//                            mImmersionBar.statusBarDarkFont(false).statusBarColor(R.color.colorMain).init();
//                        }
                        break;
                    case 1:
                        homeTv.setSelected(false);
                        findTv.setSelected(true);
                        businessTv.setSelected(false);
                        ShoppingCartTv.setSelected(false);
                        meTv.setSelected(false);
//                        mImmersionBar = ImmersionBar.with(MainActivity.this);
//                        mImmersionBar.fitsSystemWindows(true).init();
//                        if (ImmersionBar.isSupportStatusBarDarkFont()){
//                            mImmersionBar.statusBarDarkFont(true).statusBarColor(R.color.colorPrimary).init();
//                        }else {
//                            mImmersionBar.statusBarDarkFont(false).statusBarColor(R.color.colorMain).init();
//                        }
                        break;
                    case 2:
                        homeTv.setSelected(false);
                        findTv.setSelected(false);
                        businessTv.setSelected(true);
                        ShoppingCartTv.setSelected(false);
                        meTv.setSelected(false);
//                        mImmersionBar = ImmersionBar.with(MainActivity.this);
//                        mImmersionBar.fitsSystemWindows(true).init();
//                        if (ImmersionBar.isSupportStatusBarDarkFont()){
//                            mImmersionBar.statusBarDarkFont(true).statusBarColor(R.color.colorPrimary).init();
//                        }else {
//                            mImmersionBar.statusBarDarkFont(false).statusBarColor(R.color.colorMain).init();
//                        }
                        break;
                    case 3:
                        homeTv.setSelected(false);
                        findTv.setSelected(false);
                        businessTv.setSelected(false);
                        ShoppingCartTv.setSelected(true);
                        meTv.setSelected(false);

//                        mImmersionBar = ImmersionBar.with(MainActivity.this);
//                        mImmersionBar.fitsSystemWindows(false).statusBarDarkFont(false).statusBarColor(R.color.transparent).init();
                        break;
                    case 4:
                        homeTv.setSelected(false);
                        findTv.setSelected(false);
                        businessTv.setSelected(false);
                        ShoppingCartTv.setSelected(false);
                        meTv.setSelected(true);
                        contentLyt.setCurrentItem(4);
//                        mImmersionBar = ImmersionBar.with(MainActivity.this);
//                        mImmersionBar.fitsSystemWindows(false).statusBarDarkFont(false).statusBarColor(R.color.transparent).init();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        mImmersionBar = ImmersionBar.with(this);
//        mImmersionBar.fitsSystemWindows(true).init();
//        if (ImmersionBar.isSupportStatusBarDarkFont()){
//            mImmersionBar.statusBarDarkFont(true).statusBarColor(R.color.colorPrimary).init();
//        }else {
//            mImmersionBar.statusBarDarkFont(false).statusBarColor(R.color.colorMain).init();
//        }
    }

    /**
     * 获取会员信息
     * */
    private void requestMemberInfo(){
        MemberInfoParamObject Param = new MemberInfoParamObject();
        MemberInfoRequestObject RequestObject = new MemberInfoRequestObject();
        Param.setMemberId(StringUtils.avoidNull(App.getUser().getMemberId()));
        RequestObject.setParam(Param);
        httpTool.post(RequestObject, Apis.USER_INFO, new HttpTool.HttpCallBack<MemberInfoResponseObject>() {
            @Override
            public void onSuccess(MemberInfoResponseObject resp) {
                SaveUserTool.saveObject(resp.getRecord());
                App.setUser(resp.getRecord());
            }

            @Override
            public void onError(String message, String errorCode) {
            }
        });
    }

    List<String> requestType = new ArrayList<>();
    //获取数据字典信息
    private void requestDataIndex(){
        requestType.clear();
        requestType.add(Constants.DateIndex.ALIYUN_UPLOAD_ID);
        requestType.add(Constants.DateIndex.ALIYUN_UPLOAD_KEY);
        requestType.add(Constants.DateIndex.ALIYUN_UPLOAD_ENDPOINT);
        requestType.add(Constants.DateIndex.ALIYUN_UPLOAD_BUCKETNMAE);
        requestType.add(Constants.DateIndex.SHOP_IMG);
        requestType.add(Constants.DateIndex.ESSAY);
        requestType.add(Constants.DateIndex.RESOURCE_PATH);
        requestType.add(Constants.DateIndex.MEMBER_IMG);
        requestType.add(Constants.DateIndex.PRODUCT_IMG);
        requestType.add(Constants.DateIndex.AD_IMG);
        requestType.add(Constants.DateIndex.TRANFER_IMG);
        AppDicInfoParam appDicInfoParam = new AppDicInfoParam();
        appDicInfoParam.setRequestType(requestType);
        AppDicInfoRequestObject Object = new AppDicInfoRequestObject();
        Object.setParam(appDicInfoParam);
        httpTool.post(Object, Apis.DATA_INDEX, new HttpTool.HttpCallBack<AppConfigureResponseObject>() {
            @Override
            public void onSuccess(AppConfigureResponseObject resp) {
                App.getDataIndex().clear();
                for (AppConfigureResponseParam data : resp.getDataList()) {
                    App.getDataIndex().put(data.getName(), data.getContent());
                }
            }

            @Override
            public void onError(String message,String errorCode) {


            }
        });
    }
    @TargetApi(23)
    private void checkPermission(){
        if (PermissionChecker.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || PermissionChecker.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE}, LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==LOCATION){
            try {
                if (grantResults[0]!=PackageManager.PERMISSION_GRANTED){
                    showToast(getResources().getString(R.string.settingLocationPermission));
                }
            }catch (Exception e){
                Log.e("message",e.getMessage());
            }

        }
    }
//    @TargetApi(23)
//    private void checkPermission(){
//        if (PermissionChecker.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
//            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},LOCATION);
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode==LOCATION){
//            try {
//                if (grantResults[0]!=PackageManager.PERMISSION_GRANTED){
//                    showToast(getResources().getString(R.string.settingLocationPermission));
//                }
//            }catch (Exception e){
//                Log.e("message",e.getMessage());
//            }
//
//        }
//    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - exitTime > 2000){
            exitTime = System.currentTimeMillis();
            showToast("再按一次退出");
        }else{
            finish();
            //参数用作状态码；根据惯例，非 0 的状态码表示异常终止。
            System.exit(0);
            super.onBackPressed();
        }
    }

    @OnClick({R.id.am_home_page,R.id.am_find,R.id.am_business,R.id.am_shopping_cart,R.id.am_me})
    void onClick(View view){
        switch (view.getId()){
            case R.id.am_home_page:
                contentLyt.setCurrentItem(0);
                break;
            case R.id.am_find:
                contentLyt.setCurrentItem(1);
                break;
            case R.id.am_business:
                contentLyt.setCurrentItem(2);
                break;
            case R.id.am_shopping_cart:
                contentLyt.setCurrentItem(3);
                break;
            case R.id.am_me:
                contentLyt.setCurrentItem(4);
                break;
        }
    }
}

