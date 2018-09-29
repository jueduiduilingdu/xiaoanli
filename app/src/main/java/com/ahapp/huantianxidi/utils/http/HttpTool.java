package com.ahapp.huantianxidi.utils.http;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.ahapp.huantianxidi.base.App;
import com.ahapp.huantianxidi.base.Constants;
import com.ahapp.huantianxidi.base.SaveUserTool;

import com.ahapp.huantianxidi.view.SHOWtoast;
import com.alibaba.fastjson.JSON;
import com.baidu.android.pushservice.PushManager;
import com.doumee.model.request.base.RequestBaseObject;
import com.doumee.model.response.base.ResponseBaseObject;
import com.doumee.model.response.userinfo.UserInfoResponseParam;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import org.apache.http.entity.StringEntity;

import java.lang.ref.WeakReference;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 网络请求
 */
public class HttpTool {

    private WeakReference<Activity> weakReference;
    private HttpUtils httpUtils;
    private HttpRequest.HttpMethod requestMethod;
    private SharedPreferences sharedPreferencespassToken;//

    public static HttpTool newInstance(Activity context){
        HttpTool httpTool = new HttpTool(context);
        return  httpTool;
    }

    private HttpTool(Activity context){
        weakReference = new WeakReference<Activity>(context);
        httpUtils = new HttpUtils();
        requestMethod = HttpRequest.HttpMethod.POST;
        sharedPreferencespassToken = App.getAppUserSharedPreferencesToken();
    }

    public void clear(){
        weakReference.clear();
    }

    public void post(RequestBaseObject p, String url, final HttpCallBack mCallBack) {
        final String code=url.substring(Constants.Urls.API_URL.length()-1);
//        String requestUrl=url+"&token="+ App.getAppUserSharedPreferences().getString("token","");
        RequestParams params = buildRequestParams(p, code);
        httpUtils.send(requestMethod, url, params, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                final String str = parseData(responseInfo.result);
                Type typeClazz = mCallBack.getClass().getGenericInterfaces()[0];
                Type type = ((ParameterizedType) typeClazz).getActualTypeArguments()[0];
                Object e = JSON.parseObject(str, type);
                final Activity activity = weakReference.get();
                if(null != e && null != activity){
                    final ResponseBaseObject re = (ResponseBaseObject) e;
                    final String error = re.getErrorCode();
                    final String msg = re.getErrorMsg();
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(TextUtils.equals(error,"00000")){
                                mCallBack.onSuccess(re);
                                Log.e("HTTP_NET","请求的返回:----"+code+str);
                            }else{
                                Log.e("请求失败",error);
                                Log.e("请求失败",code+msg);
                                 mCallBack.onError(msg,error);

                                if (TextUtils.equals(error, Constants.tokenCode.TokenCode)) {
                                    int token = sharedPreferencespassToken.getInt(Constants.APPTOKEN, 0);
                                    token++;
                                    sharedPreferencespassToken.edit().putInt(Constants.APPTOKEN, token).commit();
                                    if (sharedPreferencespassToken.getInt(Constants.APPTOKEN, 0) == 1) {

                                        PushManager.delTags(activity, Constants.tags);
                                        PushManager.stopWork(activity);
                                        SaveUserTool.saveObject(null);
                                        App.setUser(null);
                                        App.getAppUserSharedPreferences().edit().putString("token", "").commit();

                                    }

                                }

                            }

                        }
                    });
                }
            }
            @Override
            public void onFailure(HttpException e, String s) {
                Activity activity = weakReference.get();
                if(null != activity){
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mCallBack.onError("网络错误","-1");
                        }
                    });
                }
            }
        });
    }


    private RequestParams buildRequestParams(RequestBaseObject p, String code) {
        RequestBaseObject requestParams = p;
        requestParams.setAppDeviceNumber(SystemUtil.getIMEI());
        requestParams.setPlatform(Constants.httpConfig.PLATFORM);
        requestParams.setVersion(Constants.httpConfig.API_VERSION);
        if (App.getUser()!=null) {
            requestParams.setUserId(App.getUser().getMemberId());
        }
        String token = "";
        UserInfoResponseParam user = App.getUser();
        if (null != user) {
            token = user.getToken();
        }
        requestParams.setToken(token);
        RequestParams params = new RequestParams();
        params.addHeader("Content-Type", "application/json");
        String jsonParams = JSON.toJSONString(requestParams);
        Log.e("请求参数", code + jsonParams);

        byte[] bytes = CompressUtil.compressByGzip(jsonParams.getBytes());//Gzip压缩处理
        try {
            bytes = EncryptUtil.encryptDES(bytes, Constants.httpConfig.NET_KEY);//加密操作
            params.setBodyEntity(new StringEntity(new String(bytes, "utf-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return params;
    }
    private String parseData(String str) {
        String sendString = "";
        try {
            byte[] response = str.getBytes("UTF-8");
            byte[] newstrsi = EncryptUtil.decryptDES(response, Constants.httpConfig.NET_KEY);//解密
            byte[] newstrsan = CompressUtil.uncompressByGzip(newstrsi, "UTF-8");//解压缩
            sendString = new String(newstrsan, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendString;
    }

    public interface HttpCallBack<T> {
        void onSuccess(T resp);
        void onError(String message, String errorCode);
    }


}
