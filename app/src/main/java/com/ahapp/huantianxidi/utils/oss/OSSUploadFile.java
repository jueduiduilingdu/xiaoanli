package com.ahapp.huantianxidi.utils.oss;

import android.app.Activity;
import android.util.Log;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;


/**
 * Created by lenovo on 2017/4/7.
 */
public class OSSUploadFile {
    public final static String DEfaultServerPath2 = "/member/";
    public final static String MUltifilePath2 = "/multifile/";

    private String bucketName = "";
    private OSS oss;
    private WeakReference<Activity> activityWeakReference;
    private LinkedList<OSSFileResultBean> resultList;
    private int index = 0;

    private OSSUploadFile(){

    }

    public OSSUploadFile(Activity context, String endpoint, String accessKeyId, String accessKeySecret, String bucketName){
        // 明文设置secret的方式建议只在测试时使用，更多鉴权模式请参考后面的`访问控制`章节
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(accessKeyId, accessKeySecret);
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        oss = new OSSClient(context.getApplicationContext(), endpoint, credentialProvider,conf);
        activityWeakReference = new WeakReference<Activity>(context);
        resultList = new LinkedList<>();
        this.bucketName = bucketName;
    }
    //单个上传文件
    public void upload(final OSSFileBean ftpUploadBean, String path , final OSSResultCallback ossResultCallback){
        resultList.clear();
        final String localFile = ftpUploadBean.getFile().getPath();
        if (localFile == null){
            return;
        }
        String lastIndex = localFile.substring(localFile.lastIndexOf("."));
        String timeDir = getDateYYYYMMDD4( System.currentTimeMillis());
        final String uploadFilePath = timeDir + "/" + System.currentTimeMillis() + lastIndex;

        String uploadFile = path + uploadFilePath;

        PutObjectRequest put = new PutObjectRequest(bucketName, uploadFile, localFile);
        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                OSSFileResultBean fileRequestParam = new OSSFileResultBean();
                fileRequestParam.setType(ftpUploadBean.getType());
                fileRequestParam.setFilePath(uploadFilePath);
                fileRequestParam.setKey(ftpUploadBean.getKey());
                resultList.add(fileRequestParam);
                Activity activity = activityWeakReference.get();
                if (null != activity){
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ossResultCallback.onSuccess(resultList);
                        }
                    });
                }
            }
            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                Activity activity = activityWeakReference.get();
                if (null != activity){
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ossResultCallback.onFailure("网络异常，图片上传失败");
                        }
                    });
                }
                // 请求异常
                if (clientExcepion != null) {
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }
            }
        });
    }

    //多个文件上传
    public void uploadFileList(final List<OSSFileBean> fileList, String path, final OSSResultCallback ossResultCallback){
        index = 0;
        resultList.clear();
        uploadFileForList(fileList,fileList.get(index),path,ossResultCallback);
    }


    private void uploadFileForList(final List<OSSFileBean> fileList, final OSSFileBean ftpUploadBean, final String path, final OSSResultCallback ossResultCallback){
        String localFile = ftpUploadBean.getFile().getPath();
        if (localFile == null){
            return;
        }
        String lastIndex = ftpUploadBean.getFile().getPath().substring(ftpUploadBean.getFile().getPath().lastIndexOf("."));
        String timeDir = getDateYYYYMMDD4( System.currentTimeMillis());
        final String uploadFilePath = timeDir + "/" + System.currentTimeMillis() + lastIndex;
        String uploadFile = path + uploadFilePath;
        PutObjectRequest put = new PutObjectRequest(bucketName, uploadFile, localFile);
        oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                OSSFileResultBean fileRequestParam = new OSSFileResultBean();
                fileRequestParam.setType(ftpUploadBean.getType());
                fileRequestParam.setFilePath(uploadFilePath);
                fileRequestParam.setKey(ftpUploadBean.getKey());
                resultList.add(fileRequestParam);
                index++;
                if (index == fileList.size()){
                    Activity activity = activityWeakReference.get();
                    if (null != activity){
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ossResultCallback.onSuccess(resultList);
                            }
                        });
                    }
                }else{
                    OSSFileBean ftpUploadBean1 = fileList.get(index);
                    uploadFileForList(fileList,ftpUploadBean1,path,ossResultCallback);
                }
            }
            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                Activity activity = activityWeakReference.get();
                if (null != activity){
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ossResultCallback.onFailure("网络异常，图片上传失败");
                        }
                    });
                }
                // 请求异常
                if (clientExcepion != null) {
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }
            }
        });
    }

    public interface OSSResultCallback{
        void onSuccess(LinkedList<OSSFileResultBean> list);
        void onFailure(String errorMsg);
    }

    private String getDateYYYYMMDD4(long time){
        return new SimpleDateFormat("yyyyMMdd", Locale.CHINA).format(time);
    }
}
