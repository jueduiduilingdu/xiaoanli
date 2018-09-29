package com.ahapp.huantianxidi.utils.http;

import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import com.ahapp.huantianxidi.base.App;


/**
 * Created by lenovo on 2016/12/9.
 */
public class SystemUtil {

    @SuppressLint("MissingPermission")
    public static String getIMEI() {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) App.getInst()
                    .getSystemService(Context.TELEPHONY_SERVICE);
            String appDeviceNumber = telephonyManager.getDeviceId();
            Log.d("appDeviceNumber" , appDeviceNumber);
            if (TextUtils.isEmpty(appDeviceNumber)){
                appDeviceNumber = System.currentTimeMillis()+"";
            }
            Log.d("appDeviceNumber" , appDeviceNumber);
            return appDeviceNumber;
        } catch (Exception e) {
            return System.currentTimeMillis()+"";
        }
    }
}
