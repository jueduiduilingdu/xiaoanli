package com.ahapp.huantianxidi.utils.http;


import com.ahapp.huantianxidi.base.Constants;

/**
 * Created by Administrator on 2017/3/27 0027.
 */
public class Apis {


    public static final String LOGIN_ACCENT = Constants.Urls.API_URL + "1001";//登录
    public static final String USER_INFO = Constants.Urls.API_URL + "1002";//用户信息
    public static final String UPDATE_USER_INFO = Constants.Urls.API_URL + "1003";//修改个人资料
    public static final String REGISTER = Constants.Urls.API_URL + "1004";//注册
    public static final String AD_LIST = Constants.Urls.API_URL + "1005";//广告列表
    public static final String SYS_MESSAGE = Constants.Urls.API_URL + "1006";//通知列表
    public static final String GOODS_MENU = Constants.Urls.API_URL + "1007";//商品,帖子一级分类列表
    public static final String GOODS_MENU_LIST = Constants.Urls.API_URL + "1008";//根据（商家、商品）一级分类编码查询二级分类
    public static final String SHOP_LIST = Constants.Urls.API_URL + "1009";//商家列表
    public static final String SHOP_QUERY = Constants.Urls.API_URL + "1010";//商品查询列表
    public static final String MINE_TUI_JIAN = Constants.Urls.API_URL + "1011";//直推会员列表
    public static final String USER_ADD_ADDRESS = Constants.Urls.API_URL + "1012";//新增收货地址
    public static final String USER_EDIT_ADDRESS = Constants.Urls.API_URL + "1013";//修改收货地址
    public static final String USER_DEL_ADDRESS = Constants.Urls.API_URL + "1014";//删除收货地址
    public static final String USER_ADDRESS_LIST = Constants.Urls.API_URL + "1015";//收货地址列表
    public static final String UPDATE_PAY_PASSWORD = Constants.Urls.API_URL + "1016";//设置修改支付密码
    public static final String UPDATE_PASSWORD = Constants.Urls.API_URL + "1017";//修改密码
    public static final String FIND_PWD = Constants.Urls.API_URL + "1018";//忘记密码
    public static final String EDIT_PAY_PASSWORD = Constants.Urls.API_URL + "1019";//忘记支付密码
    public static final String SYSTEM_INFORMATION = Constants.Urls.API_URL + "1020";//系统消息列表（平台公告）
    public static final String READBACK = Constants.Urls.API_URL + "1021";//意见反馈
    public static final String DATA_INDEX = Constants.Urls.API_URL + "1022";//数据字典
    public static final String APP_VERSION = Constants.Urls.API_URL + "1023";//更新检测接口
    public static final String SEND_CODE_USER = Constants.Urls.API_URL + "1024";//根据用户获取验证码
    public static final String SEND_CODE = Constants.Urls.API_URL + "1025";//根据手机号获取验证码
    public static final String USER_BY_LOGIN_NAME = Constants.Urls.API_URL + "1026";//根据登录账户查询用户信息
    public static final String SHOP_ORDER_LIST = Constants.Urls.API_URL + "1027";//商品账单列表
    public static final String SHOP_INFO = Constants.Urls.API_URL + "1028";//商家详情
    public static final String APPLY_SHOP = Constants.Urls.API_URL + "1029";//申请成为商家
    public static final String MY_ORDER_LIST = Constants.Urls.API_URL + "1030";//我的订单
    public static final String ORDER_INFO = Constants.Urls.API_URL + "1031";//订单详情
    public static final String ORDER_UPDATE = Constants.Urls.API_URL + "1032";//更新订单
    public static final String ORDER_CREATE = Constants.Urls.API_URL + "1033";//线上订单生成
    public static final String ORDER_WECHAT_PAY = Constants.Urls.API_URL + "1034";//微信支付生成订单
    public static final String ORDER_PAY_RESULT = Constants.Urls.API_URL + "1035";//订单支付结果
    public static final String CITY_LIST = Constants.Urls.API_URL + "1036";//城市列表
    public static final String USER_TIXIAN = Constants.Urls.API_URL + "1037";//提现（商家、会员惠宝）申请
    public static final String ORDER_COMMENT = Constants.Urls.API_URL + "1038";//订单评论
    public static final String PRODUCT_REVIEWS = Constants.Urls.API_URL + "1039";//商品评论记录列表
    public static final String SHOP_UPDATE = Constants.Urls.API_URL + "1040";//修改商家
    public static final String PRODUCT_DETAILS = Constants.Urls.API_URL + "1041";//商品详情
    public static final String SHOP_COLLECT = Constants.Urls.API_URL + "1042";//收藏列表
    public static final String COLLECT = Constants.Urls.API_URL + "1043";//收藏商品、商家,文章接口
    public static final String DIS_COLLECT = Constants.Urls.API_URL + "1044";//取消收藏
    public static final String CART_LIST = Constants.Urls.API_URL + "1045";//购物车列表
    public static final String CART_GOOD_EDIT = Constants.Urls.API_URL + "1046";//购物车商品删除、设置数量
    public static final String CLEAR_CART = Constants.Urls.API_URL + "1047";//清除购物车
    public static final String SHOP_CART_ADD = Constants.Urls.API_URL + "1048";//加入购物车
    public static final String REC_PEOPLE_NUM = Constants.Urls.API_URL + "1049";//查询用户推荐人数数量
    public static final String UPPER_LOWER_SHELVSE = Constants.Urls.API_URL + "1050";//商品上架,下架
    public static final String ORDER_LIST = Constants.Urls.API_URL + "1051";//余额明细列表
    public static final String EDIT_EXPRESS = Constants.Urls.API_URL + "1052";//编辑快递信息修改
    public static final String REA_SERIOUSLY = Constants.Urls.API_URL + "1053";//会员的实名认证
    public static final String SETUP_PHONET = Constants.Urls.API_URL + "1054";//绑定手机号
    public static final String BANK_LIST = Constants.Urls.API_URL + "1055";//银行列表接口    public static final String CITY_LIST = Constants.Urls.API_URL + "1037";//城市列表
    public static final String SERVICE_CITY_LIST = Constants.Urls.API_URL + "1056";//城市列表接口 用于第二个模块
    public static final String BANK_CARD_AUTHENTICATION = Constants.Urls.API_URL + "1057";//银行卡认证接口
    public static final String BIND_PHONE = Constants.Urls.API_URL + "1058";//验证码匹配
    public static final String CAUSE_RETURE_LIST = Constants.Urls.API_URL + "1059";//退货原因列表
    public static final String RETURE_REVIEW = Constants.Urls.API_URL + "1060";//商家退款退货审核
    public static final String EXCHANGE_COUPON = Constants.Urls.API_URL + "1061";//积分兑换优惠卷
    public static final String COUPON_LIST = Constants.Urls.API_URL + "1062";//优惠券列表
    public static final String MY_COUPON_LIST = Constants.Urls.API_URL + "1063";//我的优惠券列表
    public static final String EXCHANGE_LIST = Constants.Urls.API_URL + "1064";//积分兑换列表
    public static final String ATOM_LIST = Constants.Urls.API_URL + "1065";//帖子评论列表
    public static final String FIND_ATOM = Constants.Urls.API_URL + "1066";//发现帖子
    public static final String ATOM_INFO = Constants.Urls.API_URL + "1067";//帖子详情
    public static final String RELEASE_ATOM = Constants.Urls.API_URL + "1068";//发布帖子
    public static final String DELETE_POST = Constants.Urls.API_URL + "1069";//删除帖子
    public static final String TOWNHIP_LIST = Constants.Urls.API_URL + "1070";//乡镇列表
    public static final String COMMENT_POSD = Constants.Urls.API_URL + "1071";//评论帖子
    public static final String MEMBER_ACCOUT_ORDER_STATISTICS = Constants.Urls.API_URL + "1072";//会员账户订单统计
    public static final String PROVINCE_LIST = Constants.Urls.API_URL + "1073";//省市区列表
    public static final String MEMBER_ESSAY_ADD = Constants.Urls.API_URL + "1074";//分享付费帖子
    public static final String REPORT = Constants.Urls.API_URL + "1075";//举报
    public static final String REPLY = Constants.Urls.API_URL + "1076";//商家回复
    public static final String GOODS_ORDER_DIFF_ADD = Constants.Urls.API_URL + "1077";//补差价订单

}
