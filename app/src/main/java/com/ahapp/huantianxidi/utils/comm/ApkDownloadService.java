package com.ahapp.huantianxidi.utils.comm;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;

import com.ahapp.huantianxidi.BuildConfig;
import com.ahapp.huantianxidi.R;
import com.ahapp.huantianxidi.base.Constants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/8/18.
 */
public class ApkDownloadService extends IntentService {

   private static final int BUFFER_SIZE = 10 * 1024; // 8k ~ 32K
   private static final String TAG = "ApkDownloadService";

   private static final int NOTIFICATION_ID = 0;

   private NotificationManager mNotifyManager;
   private NotificationCompat.Builder mBuilder;

   public ApkDownloadService() {
      super("ApkDownloadService");
   }

   @Override
   protected void onHandleIntent(Intent intent) {

      mNotifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
      mBuilder = new NotificationCompat.Builder(this);

      String appName = getString(getApplicationInfo().labelRes);
      int icon = getApplicationInfo().icon;

      mBuilder.setContentTitle(appName).setSmallIcon(icon);
      String urlStr = intent.getStringExtra(Constants.Keys.APK_DOWNLOAD_URL);
      InputStream in = null;
      FileOutputStream out = null;
      try {
         URL url = new URL(urlStr);
         HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

         urlConnection.setRequestMethod("GET");
         urlConnection.setDoOutput(false);
         urlConnection.setConnectTimeout(10 * 1000);
         urlConnection.setReadTimeout(10 * 1000);
         urlConnection.setRequestProperty("Connection", "Keep-Alive");
         urlConnection.setRequestProperty("Charset", "UTF-8");
         urlConnection.setRequestProperty("Accept-Encoding", "gzip, deflate");

         urlConnection.connect();
         long bytetotal = urlConnection.getContentLength();
         long bytesum = 0;
         int byteread = 0;
         in = urlConnection.getInputStream();
         File dir = FileUtils.getAppDir(this);
         String apkName = urlStr.substring(urlStr.lastIndexOf("/") + 1, urlStr.length());
         File apkFile = new File(dir, apkName);
         out = new FileOutputStream(apkFile);
         byte[] buffer = new byte[BUFFER_SIZE];

         int oldProgress = 0;

         while ((byteread = in.read(buffer)) != -1) {
            bytesum += byteread;
            out.write(buffer, 0, byteread);

            int progress = (int) (bytesum * 100L / bytetotal);
            // 如果进度与之前进度相等，则不更新，如果更新太频繁，否则会造成界面卡顿
            if (progress != oldProgress) {
               updateProgress(progress);
            }
            oldProgress = progress;
         }
         // 下载完成

         installAPk(apkFile);

         mNotifyManager.cancel(NOTIFICATION_ID);

      } catch (Exception e) {
//         MainThreadPostUtils.toast("下载出现问题...");
      } finally {
         if (out != null) {
            try {
               out.close();
            } catch (IOException ignored) {
//               MainThreadPostUtils.toast("下载出现问题...");
            }
         }
         if (in != null) {
            try {
               in.close();
            } catch (IOException ignored) {
//               MainThreadPostUtils.toast("下载出现问题...");
            }
         }
      }
   }

   private void updateProgress(int progress) {
      //"正在下载:" + progress + "%"
      mBuilder.setContentText(this.getString(R.string.updateDownloadProgress,
            progress)).setProgress(100, progress, false);
      //setContentInent如果不设置在4.0+上没有问题，在4.0以下会报异常
      PendingIntent pendingintent = PendingIntent.getActivity(this, 0, new Intent(),
            PendingIntent.FLAG_CANCEL_CURRENT);
      mBuilder.setContentIntent(pendingintent);
      mNotifyManager.notify(NOTIFICATION_ID, mBuilder.build());
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
         Uri contentUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", apkFile);
         intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
      } else {
         intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
         intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      }
      startActivity(intent);

   }
}
