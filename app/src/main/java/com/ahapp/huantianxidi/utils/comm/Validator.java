package com.ahapp.huantianxidi.utils.comm;

import android.text.TextUtils;

import java.text.DecimalFormat;


/**
 * Created by tiangongyipin on 16/2/21.
 */
public class Validator {

    public static final String PHONE_REGEX = "^[1][23456789][0-9]{9}$";
    private static final String CAPTCHA_REGEX = "[0-9]{6}";
    private static final String PASSWORD_REGEX = "^[0-9A-Za-z]{6,12}$";

    /**
     * 手机号是否合法
     *
     * @param phone
     * @return
     */
    public static boolean isValidatedPhone(String phone) {
        return (!StringUtils.isEmpty(phone) && phone.matches(PHONE_REGEX));
    }

    public static boolean isValidatedCaptcha(String captcha) {
        return (!StringUtils.isEmpty(captcha) && captcha.matches(CAPTCHA_REGEX));
    }

    public static boolean isValidatedPwd(String pwd) {
        return (!TextUtils.isEmpty(pwd) && pwd.matches(PASSWORD_REGEX));
    }

    public static boolean isNumber(String str) {// 判断整型
        return str.matches("^\\d+$$");
    }

    public static boolean isFloat(String str) {// 判断小数，与判断整型的区别在与d后面的小数点（红色）
        return str.matches("\\d+\\.\\d+$");
    }

    //三位数字加“,”
    public static String num2Dorllar(String s) {
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(Integer.parseInt(s));
    }

    //去除空格
    public static String getNoBlank(String str) {// 判断小数，与判断整型的区别在与d后面的小数点（红色）
        return str.replaceAll("\\s+", "");
    }

    /**
     * 验证输入银行卡
     *
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isBankcard(String cardId) {
        char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
        if(bit == 'N'){
            return false;
        }
        return cardId.charAt(cardId.length() - 1) == bit;
    }

    public static char getBankCardCheckCode(String nonCheckCodeCardId){
        if(nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for(int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if(j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char)((10 - luhmSum % 10) + '0');
    }

}
