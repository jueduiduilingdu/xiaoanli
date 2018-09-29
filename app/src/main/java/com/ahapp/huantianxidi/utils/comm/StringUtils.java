package com.ahapp.huantianxidi.utils.comm;

import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class StringUtils {
    public static boolean isEmpty(String str) {
        if (str == null
                || str.length() == 0
                || str.isEmpty()
                || str.equalsIgnoreCase("null")
                || str.trim().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isCaptcha(String verifyCode) {
        return verifyCode != null && verifyCode.matches("^\\d{4}$");
    }

    public static String avoidNull(String str) {
        return str == null ? "" : str;
    }

    public static String avoidDouble(String str) {
        return str == null ? "0" : str;
    }

    public static boolean noEmptyList(List list) {
        if (list == null || list.isEmpty() || list.size() == 0) return false;
        return true;
    }

    public static String getEditString(EditText view) {
        return view.getText().toString().trim();
    }

    public static boolean isNull(Object... obj) {
        for (Object o : obj) {
            if (o == null) return true;
        }
        return false;
    }

    public static boolean isNull2(Object... obj) {
        for (Object o : obj) {
            if (o == null || o.equals("")) return true;
        }
        return false;
    }

    public static String arrayToString(String[] array) {
        if (array != null) {
            StringBuffer sb = new StringBuffer();
            for (String s : array) {
                sb.append(s);
            }
            return sb.toString();
        }
        return "";
    }

    public static String decimalPoint(final EditText view) {

        view.addTextChangedListener(new TextWatcher() {   // 这是主要方法，下面为一些处理

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                String str = view.getText().toString().trim();

                if (str.indexOf('.') == 0) {

                    view.setText("");
                    //                   return;
                }


            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub

            }
        });

        return view.getText().toString().trim();
    }

    public static SpannableStringBuilder getWordColor(String string, int colorStateList, String... args) {
        //设置局部字体颜色变化
        SpannableStringBuilder builder = new SpannableStringBuilder(string);
        //ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
        ForegroundColorSpan redSpan = new ForegroundColorSpan(colorStateList);
        for (int i = 0; i < args.length; i++) {
            builder.setSpan(redSpan, string.indexOf(args[i]), string.indexOf(args[i]) + args[i].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return builder;
    }


    public static void setWordColor(String string, int start, int end, TextView view, int colorStateList) {

        //设置局部字体颜色变化
        SpannableStringBuilder builder = new SpannableStringBuilder(string);
        //ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
        ForegroundColorSpan redSpan = new ForegroundColorSpan(colorStateList);
        builder.setSpan(redSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        view.setText(builder);
    }

}
