package com.ahapp.huantianxidi.view;

import android.view.Gravity;
import android.widget.Toast;

import com.ahapp.huantianxidi.base.App;

/**
 * Created by Administrator on 2018/1/29.
 */

public class SHOWtoast {

    public static void showToast(String msg) {
        Toast mToast = null;
        if (null != msg) {
            if (mToast == null) {
                mToast = Toast.makeText(App.getInst(), msg, Toast.LENGTH_SHORT);
                mToast.setGravity(Gravity.CENTER, 0, 0);
            } else {
                mToast.setText(msg);
            }
            mToast.show();
        }
    }
}
