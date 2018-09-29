package com.ahapp.huantianxidi.base;

import android.os.Environment;

import java.io.File;
import java.util.LinkedList;

public class Constants {

    public static LinkedList<String> tags = new LinkedList<>();


    public static final String APP_TOKEN = "app_token";//
    public static final String APPTOKEN = "apptoken";//0初始值  1 第一次出现 大于1 多次出现
    public static final int PAGE_SIZE = 10;
    public static final String HISTORiCAL_RECORD = "historical_record";
    public static final String RECORD = "record";

    public static class Keys {

        public static String APK_DOWNLOAD_URL = "DOWNLOAD_URL";
        public static String START_COUNT = "start_count";
        public static String APP_INFO = "app_info";

    }

    public static class ThirdParty {
        public static final String WECHAT_ID = "wxd2f8432209c26b56";
        public static final String WECHAT_APPSECRET = "29da05d7de2b7c9ad3c9956dbf9f516d";

        public static final String SHARE_KEY = "2546dff488cee";
        public static final String SHARE_APPSECRET = "2a3178962f932f6b95227e2b49a92a78";

        public static final String BAIDU_KEY = "O7mpkHR0t2upib9HYnXMFfbxKpsa8WgI";
    }

    public static class Codes {

    }

    public static class tool {
        public static final String RMB = "￥";
    }

    public static class Urls {
        public static final String API_URL = "http://106.12.27.138:8080/huantianxidi_interface/do?c=";
        //   public static final String API_URL = "http://114.215.188.193/huantianxidi_interface/do?c=";
        // public static final String API_URL = "http://www.xplw.shop/huantianxidi_interface/do?c=";

    }

    public static class httpConfig {
        public static final String PLATFORM = "0";
        public static final String NET_KEY = "ABD#-*EY";
        public static final String API_VERSION = "1.5";
    }

    public static class DateIndex {
        // 阿里云oss上传服务APPID access_id
        public static final String ALIYUN_UPLOAD_ID = "ALIYUN_UPLOAD_ID";// LTAIAwMTyfagfDrR
        // 阿里云osss上传服务key access_key
        public static final String ALIYUN_UPLOAD_KEY = "ALIYUN_UPLOAD_KEY";// y1tPu7sS6wRAn8jaFPovvk7fK9r275
        // 阿里云oss上传服务endpoint
        public static final String ALIYUN_UPLOAD_ENDPOINT = "ALIYUN_UPLOAD_ENDPOINT";// http://oss-cn-shanghai.aliyuncs.com
        // 阿里云oss上传服务bucketName :第一层文件夹的名字
        public static final String ALIYUN_UPLOAD_BUCKETNMAE = "ALIYUN_UPLOAD_BUCKETNMAE";// yudahu

        public static final String RESOURCE_PATH = "RESOURCE_PATH";//图片和附件存放地址
        public static final String MEMBER_IMG = "MEMBER_IMG";// 会员头像存放
        public static final String PRODUCT_IMG = "PRODUCT_IMG";//商品的图片
        public static final String AD_IMG = "AD_IMG";//广告的
        public static final String TRANFER_IMG = "TRANFER_IMG";//线下转账图片
        public static final String SYS_PROTOCOL = "SYS_PROTOCOL";// 用户协议

        public static final String NOTICE_NUM = "NOTICE_NUM";//消息列表未读
        public static final String ORDER_IMGS = "ORDER_IMGS";// 快速发布
        public static final String SHOP_IMG = "SHOP_IMG";//商家图片
        public static final String ESSAY = "ESSAY";//发布
        public static final String SERVICE_PHONE = "SERVICE_PHONE";//客服电话
        public static final String SHOP_PROTOCOL = "SHOP_PROTOCOL";//商家协议
        public static final String ABOUT_US = "ABOUT_US";//关于
        public static final String MEMBER_WITHDRAW_RATE = "MEMBER_WITHDRAW_RATE";//会员提现手续费率
        public static final String WITHDRAW_MULTIPLE = "WITHDRAW_MULTIPLE";//会员提现倍数

        public static final String CATEGORY_IMG = "CATEGORY_IMG";//分类的
        public static final String SHARE_LINK = "SHARE_LINK";//分享链接
        public static final String RECOMMEND_CONTENT = "RECOMMEND_CONTENT";//推荐内容
        public static final String PLAT_ACCOUNTNAME = "PLAT_ACCOUNTNAME";//平台收款账户名称
        public static final String PLAT_BANKNO = "PLAT_BANKNO";//平台收款账户卡号
        public static final String PLAT_BANKNAME = "PLAT_BANKNAME";//平台收款开户支行
        public static final String TODAY_PV_COUNT = "TODAY_PV_COUNT";//今日平台PV发放
        public static final String TODAY_SHOP_SALE = "TODAY_SHOP_SALE";//今日商家销售额
        public static final String PRODUCT_ADD = "PRODUCT_ADD";//商品发布页
        public static final String SHARE_DOWNLOAD = "SHARE_DOWNLOAD";//分享下载
        public static final String MONEY_INTEGRAL = "MONEY_INTEGRAL";//现金积分比
        public static final String CHUANDKE_MONEY = "TODAY_SHOP_SALE";//会员升级费
        public static final String CONFIRM_MONEY = "CONFIRM_MONEY";//商家的保证金
        public static final String BUY_INTEGRAL_RATE = "BUY_INTEGRAL_RATE";//购买库存积分认购比
        public static final String BUY_INTEGRAL_TRANFER_RATE = "BUY_INTEGRAL_TRANFER_RATE";//购买库存积分转换比
        public static final String INVESTMENT = "INVESTMENT";//投资申请

    }

    public static class tokenCode {
        public static final String TokenCode = "00022";
        public static final String TokenCode_16 = "00016";
        public static final String TokenCode_03 = "00003";

    }

    public static class OrderCode {
        //订单的状态 0待确认 1待发货 2待使用 3已发货 4已取消 5已退款
        //6待发货取消 7已发货取消 8已退货 9已完成
        //10待评价 11退货/售后
        public static final String TAB_00 = "-1";//全部
        public static final String TAB_0 = "0";//待付款
        public static final String TAB_1 = "1";//待发货
        public static final String TAB_2 = "2";//待使用
        public static final String TAB_3 = "3";//待收货
        public static final String TAB_4 = "4";//已取消
        public static final String TAB_5 = "5";//已退款
        public static final String TAB_6 = "6";//待发货取消
        public static final String TAB_7 = "7";//已发货取消
        public static final String TAB_8 = "8";//已退货
        public static final String TAB_9 = "9";//已完成
        public static final String TAB_10 = "10";//待评价
        public static final String TAB_11 = "11";//退货/售后
        public static final String TAB_12 = "12";//拼单
    }

    public static final String SHOP_IMAGE_PATH_CODE = Environment.getExternalStorageDirectory() + "/huantianxidi" + File.separator + "shopcode" + File.separator;
    public static final String SHOP_SHARE_IMAGE_CODE = Environment.getExternalStorageDirectory() + "/huantianxidi" + File.separator + "shopcode" + File.separator + "shop_code" + ".jpg";

}
