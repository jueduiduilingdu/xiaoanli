package com.ahapp.huantianxidi.utils.comm;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.doumee.model.response.city.CityListResponseParam;
import com.github.promeg.pinyinhelper.Pinyin;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2017/4/10 0010.
 */
public class CommUtils {

    private static final String DATE_FORMAT ="yyyy-MM-dd HH:mm:ss";

    //是否连接WIFI
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetworkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    public static String formatDate(Date date){
        SimpleDateFormat dateFormat=new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
        return dateFormat.format(date);
    }

    public static boolean isAppRunningForeground(Context var0) {
        ActivityManager var1 = (ActivityManager)var0.getSystemService(Context.ACTIVITY_SERVICE);
        try {
            List var2 = var1.getRunningTasks(1);
            if(var2 != null && var2.size() >= 1) {
                boolean var3 = var0.getPackageName().equalsIgnoreCase(((ActivityManager.RunningTaskInfo)var2.get(0)).baseActivity.getPackageName());
                Log.e("utils", "app running in foregroud：" + var3);
                return var3;
            } else {
                return false;
            }
        } catch (SecurityException var4) {
            Log.e("EasyUtils", "Apk doesn't hold GET_TASKS permission");
            var4.printStackTrace();
            return false;
        }
    }

    /**
     * 对数据进行排序
     *
     * @param list 要进行排序的数据源
     */
    public static void sortData(List<CityListResponseParam> list) {
        if (list == null || list.size() == 0) return;
        for (int i = 0; i < list.size(); i++) {
            CityListResponseParam bean = list.get(i);
            String tag = Pinyin.toPinyin(bean.getCityName().substring(0, 1).charAt(0)).substring(0, 1);
            if (tag.matches("[A-Z]")||tag.matches("[a-z]")) {
                bean.setCityCode(tag.toUpperCase());
            } else {
                bean.setCityCode("#");
            }
        }
        Collections.sort(list, new Comparator<CityListResponseParam>() {
            @Override
            public int compare(CityListResponseParam o1, CityListResponseParam o2) {
                if ("#".equals(o1.getCityCode())) {
                    return 1;
                } else if ("#".equals(o2.getCityCode())) {
                    return -1;
                } else {
                    return o1.getCityCode().compareTo(o2.getCityCode());
                }
            }
        });
    }

    /**
     * @param beans 数据源
     * @return tags 返回一个包含所有Tag字母在内的字符串
     */
    public static String getTags(List<CityListResponseParam> beans) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < beans.size(); i++) {
            if (!builder.toString().contains(beans.get(i).getCityCode())) {
                builder.append(beans.get(i).getCityCode());
            }
        }
        return builder.toString();
    }

    public static boolean checkMapAppsIsExist(Context context,String packageName){
        PackageInfo packageInfo;
        try{
            packageInfo = context.getPackageManager().getPackageInfo(packageName,0);
        }catch (Exception e){
            packageInfo = null;
            e.printStackTrace();
        }
        if (packageInfo == null){
            return false;
        }else{
            return true;
        }
    }

}
