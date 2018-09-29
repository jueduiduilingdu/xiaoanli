package com.ahapp.huantianxidi.utils.comm;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ahapp.huantianxidi.BuildConfig;
import com.ahapp.huantianxidi.R;
import com.ahapp.huantianxidi.base.BaseActivity;
import com.ahapp.huantianxidi.base.Constants;
import com.ahapp.huantianxidi.utils.http.Apis;
import com.ahapp.huantianxidi.utils.http.HttpTool;
import com.doumee.model.request.appVersion.AppVersionRequestObject;
import com.doumee.model.response.appversion.AppVersionObject;
import com.doumee.model.response.appversion.AppVersionResponseObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017/4/10 0010.
 */
public class CheckVersionUtils {
    private int type;
    private Context mContext;
    private Dialog updateAppDialog;
    private ProgressBar progressUpdateBar;
    private TextView percentUpdateTxt;
    private TextView updateTotalTxt;
    private String downloadUrl;
    private long apkSize;
    private long readSize;
    // 准备安装新版本应用标记
    private static final int INSTALL_TOKEN = 1;

    public CheckVersionUtils(Context context){
        mContext=context;
    }

    public CheckVersionUtils(Context context, int type){
        mContext=context;
        this.type=type;
    }
    public void checkVersion(){
        HttpTool httpTool=HttpTool.newInstance((Activity) mContext);
        AppVersionRequestObject requestObject = new AppVersionRequestObject();
        httpTool.post(requestObject, Apis.APP_VERSION, new HttpTool.HttpCallBack<AppVersionResponseObject>() {
            @Override
            public void onSuccess(final AppVersionResponseObject resp) {
                AppVersionObject data = resp.getData();
                if (data.getIsNeedUpdate().equals("0")) {
                    if (type!=0){
                        ((BaseActivity)mContext).showToast("当前已是最新版本");
                    }
                }else {
                    final Dialog checkHasDialog = new Dialog(mContext, R.style.NoTitleDialogStyle);
                    checkHasDialog.setContentView(R.layout.dialog_version);
                    checkHasDialog.setCanceledOnTouchOutside(false);
                    if (data.getType().equals("0")) {
                        LinearLayout updateLyt= (LinearLayout) checkHasDialog.findViewById(R.id.dv_update_lyt);
                        TextView content = (TextView) checkHasDialog.findViewById(R.id.dv_content_txt);
                        TextView cancel = (TextView) checkHasDialog.findViewById(R.id.dv_cancel_txt);
                        TextView update = (TextView) checkHasDialog.findViewById(R.id.dv_update_txt);
                        content.setText(StringUtils.avoidNull(data.getInfo()));
                        updateLyt.setVisibility(View.VISIBLE);
                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                checkHasDialog.dismiss();
                            }
                        });
                        update.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (!CommUtils.isWifiConnected(mContext)) {
                                    showIsWifiDialog(resp.getData().getUpdateUrl());
                                }else {
                                    Intent intent = new Intent(mContext.getApplicationContext
                                            (), ApkDownloadService.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.putExtra(Constants.Keys.APK_DOWNLOAD_URL, resp.getData()
                                            .getUpdateUrl());
                                    mContext.startService(intent);
                                }
                                checkHasDialog.dismiss();
                            }
                        });
                    }else {
                        checkHasDialog.setCancelable(false);
                        TextView compulsiveUpdateTxt = (TextView) checkHasDialog.findViewById(R.id.dv_compulsive_update_txt);
                        TextView content = (TextView) checkHasDialog.findViewById(R.id.dv_content_txt);
                        content.setText(StringUtils.avoidNull(data.getInfo()));
                        compulsiveUpdateTxt.setVisibility(View.VISIBLE);
                        compulsiveUpdateTxt.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                downloadUrl=resp.getData().getUpdateUrl();
                                checkHasDialog.dismiss();
                                showAppDownloadDialog();
                            }
                        });
                    }
                    checkHasDialog.show();
                }
            }

            @Override
            public void onError(String message, String errorCode) {

            }
        });
    }


    private void showAppDownloadDialog() {
        updateAppDialog = new Dialog(mContext, R.style.NoTitleDialogStyle);
        updateAppDialog.setContentView(R.layout.dialog_version_update);
        updateAppDialog.setCanceledOnTouchOutside(false);
        updateAppDialog.setCancelable(false);
        progressUpdateBar = (ProgressBar) updateAppDialog.findViewById(R.id.dd_downloading_pab);
        percentUpdateTxt = (TextView) updateAppDialog.findViewById(R.id.dd_percent_txt);
        updateTotalTxt = (TextView) updateAppDialog.findViewById(R.id.dd_total_txt);
        updateAppDialog.show();
        new downloadAsyncTask().execute();
    }

    /**
     * wifi提示框
     */
    public void showIsWifiDialog(final String url) {
        final Dialog dialog = new Dialog(mContext, R.style.NoTitleDialogStyle);
        dialog.setContentView(R.layout.dialog_wifi);
        dialog.setCanceledOnTouchOutside(false);
        TextView cancelTxt = (TextView) dialog.findViewById(R.id.dw_cancel_txt);
        TextView sureTxt = (TextView) dialog.findViewById(R.id.dw_sure_txt);
        sureTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(mContext.getApplicationContext(), ApkDownloadService
                        .class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(Constants.Keys.APK_DOWNLOAD_URL, url);
                mContext.startService(intent);

            }
        });
        cancelTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    /**
     * 下载新版本应用
     */
    private class downloadAsyncTask extends AsyncTask<Void, Integer, Integer> {
        @Override
        protected void onPreExecute() {
            Log.e("onPreExecute", "执行至--onPreExecute");
            updateAppDialog.show();
        }

        @Override
        protected Integer doInBackground(Void... params) {
            Log.e("doInBackground", "执行至--doInBackground");
            URL url;
            HttpURLConnection connection = null;
            InputStream in = null;
            FileOutputStream out = null;
            try {
                url = new URL(downloadUrl);
                connection = (HttpURLConnection) url.openConnection();
                in = connection.getInputStream();
                apkSize = connection.getContentLength();
                out = new FileOutputStream(new File(FileUtils.createAppPath(mContext)));//为指定的文件路径创建文件输出流
                byte[] buffer = new byte[1024 * 1024];
                int len = 0;
                Log.e("doInBackground", "执行至--readLength = 0");
                while ((len = in.read(buffer)) != -1) {

                    out.write(buffer, 0, len);//从buffer的第0位开始读取len长度的字节到输出流
                    readSize += len;

                    int curProgress = (int) (((float) readSize /apkSize ) * 100);

                    Log.e("doInBackground", "当前下载进度：" + curProgress);
                    publishProgress(curProgress);
                    if (readSize >= apkSize) {
                        Log.e("doInBackground", "执行至--readLength >= fileLength");
                        break;
                    }
                }
                out.flush();
                return INSTALL_TOKEN;

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (connection != null) {
                    connection.disconnect();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.e("onProgressUpdate", "异步更新进度接收到的值：" + values[0]);
            if (updateAppDialog != null && updateAppDialog.isShowing()) {
                updateTotalTxt.setText(DataCleanManager.getFormatSize(readSize) + "/" + DataCleanManager.getFormatSize(apkSize));
                percentUpdateTxt.setText(values[0] + "%");
                progressUpdateBar.setProgress(values[0]);
            }
        }

        @Override
        protected void onPostExecute(Integer integer) {
            updateAppDialog.dismiss();//关闭进度条
            ((Activity)mContext).finish();
            installAPk(new File(FileUtils.createAppPath(mContext)));
        }
    }

    private void installAPk(File apkFile) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //如果没有设置SDCard写权限，或者没有sdcard,apk文件保存在内存中，需要授予权限才能安装
        try {
            String[] command = {"chmod", "777", apkFile.toString()};
            ProcessBuilder builder = new ProcessBuilder(command);
            builder.start();
        } catch (IOException ignored) {
        }
        //判断是否是AndroidN以及更高的版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(mContext, BuildConfig.APPLICATION_ID + ".provider", apkFile);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        mContext.startActivity(intent);
    }


}
