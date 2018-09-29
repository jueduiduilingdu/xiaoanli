package com.ahapp.huantianxidi.view;

/**
 * Created by Administrator on 2018/1/19.
 */

public class HideDataUtil {
    /**
     * 前六后四 隐藏银行卡号
     *
     * @param cardNo
     * @return java.lang.String
     * @Date:16:57 2017/11/7
     */
    public static String hideCardNo(String cardNo) {
//        if (StringUtils.isEmpty(cardNo)) {
//            return cardNo;
//        }
        if (cardNo.length() > 10) {
            //前六后四
            StringBuilder stringBuilder = new StringBuilder();
            return stringBuilder.append(cardNo.substring(0, 6)).append("****")
                    .append(cardNo.substring(cardNo.length() - 4)).toString();
        } else {
            return cardNo;
        }
    }

    /**
     * 前三后四 隐藏手机号
     *
     * @param phoneNo
     * @return java.lang.String
     * @Date:17:00 2017/11/7
     */
    public static String hidePhoneNo(String phoneNo) {
//        if (StringUtils.isEmpty(phoneNo)) {
//            return phoneNo;
//        }
        if (phoneNo.length() > 7) {
//        前3后四
            StringBuilder stringBuilder = new StringBuilder();
            return stringBuilder.append(phoneNo.substring(0, 3)).append("****")
                    .append(phoneNo.substring(phoneNo.length() - 4)).toString();
        } else {
            return phoneNo;
        }
    }


}
