package com.ahapp.huantianxidi.base;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.baidu.mapapi.SDKInitializer;
import com.doumee.model.response.userinfo.UserInfoResponseParam;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class App extends MultiDexApplication {

    private static final String TAG = "App";
    private static App inst;
    private static UserInfoResponseParam user;
    private static HashMap<String,String> DATA_INDEX;//数据字典缓存

    public static App getInst() {
        return inst;
    }

    public static UserInfoResponseParam getUser() {
        if (user == null) {
            user =SaveUserTool.openUserInfoResponseParam();
            return user;
        }
        return user;
    }

    public static void setUser(UserInfoResponseParam userModel) {
        App.user = userModel;
    }

    /**
     * 保存用户00019的时候用于退出登录
     */
    public static SharedPreferences getAppUserSharedPreferencesToken() {
        return inst.getSharedPreferences(Constants.APP_TOKEN, MODE_MULTI_PROCESS);
    }

    /**
     * 保存历史记录
     */
    public static SharedPreferences getAppUserSharedPreferencesRecord() {
        return inst.getSharedPreferences(Constants.HISTORiCAL_RECORD, MODE_PRIVATE);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        inst = this;
        SDKInitializer.initialize(this);
        initBugly();
    }

    private void initBugly(){
        Context context = getApplicationContext();
        // 获取当前包名
        String packageName = context.getPackageName();
        // 获取当前进程名
        String processName = getAppName(android.os.Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        // 初始化Bugly
        CrashReport.initCrashReport(context, "9d14fe0fcc", true, strategy);
    }

    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
            }
        }
        return processName;
    }

    public static SharedPreferences getAppUserSharedPreferences(){
        return inst.getSharedPreferences(Constants.Keys.APP_INFO, MODE_PRIVATE);
    }

    //数据字典缓存
    public static HashMap<String,String> getDataIndex(){
        if (DATA_INDEX == null){
            DATA_INDEX = new HashMap<>();
        }
        return DATA_INDEX;
    }

    public static boolean Islogin() {
        boolean isLogin;
        UserInfoResponseParam user = getUser();

        if (user == null) {
            isLogin = true;
        } else {
            isLogin = false;
        }
        return isLogin;
    }
}
