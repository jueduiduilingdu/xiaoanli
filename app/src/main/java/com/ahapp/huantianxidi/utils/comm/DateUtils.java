package com.ahapp.huantianxidi.utils.comm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/11/8 0008.
 */
public class DateUtils {

    /**
     * 显示动态与当前时间 查的分类
     *
     */
    public static String getTimeByCurrentTime(Date d) {
        long delta = (new Date().getTime() - d.getTime()) / 1000;
        if (delta / (60 * 60 * 24 * 365) > 0)
            return delta / (60 * 60 * 24 * 365) + "年前";
        if (delta / (60 * 60 * 24 * 30) > 0)
            return delta / (60 * 60 * 24 * 30) + "个月前";
        if (delta / (60 * 60 * 24 * 7) > 0)
            return delta / (60 * 60 * 24 * 7) + "周前";
        if (delta / (60 * 60 * 24) > 0)
            return delta / (60 * 60 * 24) + "天前";
        if (delta / (60 * 60) > 0)
            return delta / (60 * 60) + "小时前";
        if (delta / (60) > 0)
            return delta / (60) + "分钟前";
        return "刚刚";
    }

    // 注意时间格式yyyy-MM-dd HH:mm:ss
    public static String getTimeByCurrentTime(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d;
        Date now = new Date();
        if (date == null || date.trim().equals("")) {
            return null;
        }
        try {
            d = format.parse(date);
            if (now.getYear() - d.getYear() > 0) {
                SimpleDateFormat yFormat = new SimpleDateFormat(
                        "yyyy/MM/dd HH:mm");
                return yFormat.format(d);
            } else if ((now.getMonth() - d.getMonth() > 0)
                    || (now.getDate() - d.getDate() > 1)) {
                SimpleDateFormat dFormat = new SimpleDateFormat("MM/dd HH:mm");
                return dFormat.format(d);
            } else if (now.getDate() - d.getDate() > 0) {
                return "昨天  " + date.substring(11, 16);
            } else {
                long delta = (now.getTime() - d.getTime()) / 1000;
                if (delta / (60 * 60) > 0)
                    return delta / (60 * 60) + "小时前";
                if (delta / (60) > 0)
                    return delta / (60) + "分钟前";
                return "刚刚";
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;

    }

    // 注意时间格式yyyy-MM-dd HH:mm:ss
    public static String showTime(String date) {
        if (StringUtils.isEmpty(date)){
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d;
        Date now = new Date();
        try {
            d = format.parse(date);
            if (now.getYear() - d.getYear() > 0) {
                SimpleDateFormat yFormat = new SimpleDateFormat(
                        "yyyy/MM/dd HH:mm");
                return yFormat.format(d);
            } else if ((now.getMonth() - d.getMonth() > 0)
                    || (now.getDate() - d.getDate() > 1)) {
                SimpleDateFormat dFormat = new SimpleDateFormat("MM/dd HH:mm");
                return dFormat.format(d);
            } else if (now.getDate() - d.getDate() > 0) {
                return "昨天" + date.substring(11, 16);
            } else {
                long delta = (now.getTime() - d.getTime()) / 1000;
                if (delta / (60 * 60) > 0)
                    return delta / (60 * 60) + "小时前";
                if (delta / (60) > 0)
                    return delta / (60) + "分钟前";
                return "刚刚";
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;

    }

    public static String showTime(Date date) {
        Date now = new Date();
        if (now.getYear() - date.getYear() > 0) {
            SimpleDateFormat yFormat = new SimpleDateFormat("yyyy/MM/dd");
                return yFormat.format(date);
        } else if ((now.getMonth() - date.getMonth() > 0) || (now.getDate() - date.getDate() > 1)) {
            SimpleDateFormat dFormat = new SimpleDateFormat("MM/dd");
            return dFormat.format(date);
        } else if (now.getDate() - date.getDate() > 0) {
            return "昨天";
        } else {
            SimpleDateFormat dFormat = new SimpleDateFormat("HH:mm");
            return dFormat.format(date);
        }

    }

    public static String showDistanceTime(String date) {
        if (StringUtils.isEmpty(date)){
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d;
        Date now = new Date();
        try {
            d = format.parse(date);
            if (now.getYear() - d.getYear() > 0) {
                SimpleDateFormat yFormat = new SimpleDateFormat(
                        "yyyy/MM/dd");
                return yFormat.format(d);
            } else if ((now.getMonth() - d.getMonth() > 0)) {
                SimpleDateFormat dFormat = new SimpleDateFormat("MM/dd");
                return dFormat.format(d);
            } else if (now.getDate() - d.getDate() > 7) {
                return (now.getDate()-d.getDate())/7+"周前";
            } else if (now.getDate() - d.getDate() > 2){
                return (now.getDate() - d.getDate())+"天前";
            }else if (now.getDate() - d.getDate() == 2){
                return "前天";
            }else {
                long delta = (now.getTime() - d.getTime()) / 1000;
                if (delta / (60 * 60) > 0)
                    return delta / (60 * 60) + "小时前";
                if (delta / (60) > 0)
                    return delta / (60) + "分钟前";
                return "刚刚";
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }
}
