package com.ahapp.huantianxidi.view;

import android.content.Context;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/12/5.
 */

public class ColorUtil {
    /**
     * 根据颜色资源Id，取得颜色
     *
     * @param colorId
     * @return color
     */
    public static int getResourcesColor(Context context, int colorId) {
        int color = 0x00ffffff;
        try {
            color = context.getResources().getColor(colorId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return color;
    }

    /**
     * 将十六进制 颜色代码 转换为 int
     *
     * @return color
     */
    public static int HextoColor(String color) {
        // #00000000 - #ffffffff
        String reg = "#[a-f0-9A-F]{8}";
        if (!Pattern.matches(reg, color)) {
            color = "#ffffffff";
        }
        return Color.parseColor(color);
    }

    /**
     * 设置颜色透明度
     *
     * @param color
     * @param alpha
     * @return color
     */
    public static int setColorAlpha(int color, int alpha) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }

    public static int[] ranColor = {
            0xff2bbdff, 0xfff7b55e, 0xffbc94c4, 0xff3de6b0,
            0xff6099b0, 0xffedd899, 0xffe280d2, 0xffabdb92,
            0xff00afab, 0xffff647e, 0xffb6b8de, 0xffbed09c,
            0xff00bfd7, 0xffc78590, 0xff0071ce, 0xffc9a892,

    };

    /**
     * Get the random color.
     *
     * @return
     */
    public static String getRandomColor() {
        List<String> colorList = new ArrayList<String>();
        colorList.add("#303F9F");
        colorList.add("#FF4081");
        colorList.add("#59dbe0");
        colorList.add("#f57f68");
        colorList.add("#87d288");
        colorList.add("#f8b552");
        colorList.add("#990099");
        colorList.add("#90a4ae");
        colorList.add("#7baaf7");
        colorList.add("#4dd0e1");
        colorList.add("#4db6ac");
        colorList.add("#aed581");
        colorList.add("#fdd835");
        colorList.add("#f2a600");
        colorList.add("#ff8a65");
        colorList.add("#f48fb1");
        colorList.add("#7986cb");
        colorList.add("#FFFFE0");
        colorList.add("#ADD8E6");
        colorList.add("#DEB887");
        colorList.add("#C0C0C0");
        colorList.add("#AFEEEE");
        colorList.add("#F0FFF0");
        colorList.add("#FF69B4");
        colorList.add("#FFE4B5");
        colorList.add("#FFE4E1");
        colorList.add("#FFEBCD");
        colorList.add("#FFEFD5");
        colorList.add("#FFF0F5");
        colorList.add("#FFF5EE");
        colorList.add("#FFF8DC");
        colorList.add("#FFFACD");

        return colorList.get((int) (Math.random() * colorList.size()));
    }


}
