package com.ahapp.huantianxidi.utils.comm;

import android.content.Context;
import android.os.Environment;

import java.io.File;

public class FileUtils {
    public static File getAppDir(Context context) {
        String dirPath = "";
        File appDir=null;
        //SD卡是否存在
        boolean isSdCardExists = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
        // Environment.getExternalStorageDirectory()相当于File file=new File("/sdcard")
        boolean isRootDirExists = Environment.getExternalStorageDirectory().exists();
        if (isSdCardExists && isRootDirExists&&hasExternalStoragePermission(context)) {
            dirPath = String.format("%s/%s/", Environment.getExternalStorageDirectory().getAbsolutePath(), context.getPackageName());
            appDir = new File(dirPath);
            if (!appDir.exists()) {
                appDir.mkdirs();
            }
        } else {
            appDir = context.getCacheDir();
            if(appDir == null) {
                String cacheDirPath = "/data/data/" + context.getPackageName() + "/cache/";
                appDir = new File(cacheDirPath);
            }
        }
        return appDir;
    }

    public static File getAppPhotoDir(Context context) {
        File appDir = getAppDir(context);
        File photoDir = new File(appDir.getAbsolutePath(), "photo");
        if (!photoDir.exists()) {
            photoDir.mkdir();
        }
        return photoDir;
    }

    public static File getTempDir(Context context) {
        File appDir = getAppDir(context);
        File photoDir = new File(appDir.getAbsolutePath(), "tmp");
        if (!photoDir.exists()) {
            photoDir.mkdir();
        }
        return photoDir;
    }

    public static File getVideoDir(Context context) {
        File appDir = getAppDir(context);
        File videoDir = new File(appDir.getAbsolutePath(), "video");
        if (!videoDir.exists()) {
            videoDir.mkdir();
        }
        return videoDir;
    }

    public static String createPhotoPath(Context context) {
        File photoDir = getAppPhotoDir(context);
        return photoDir.getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg";
    }

    public static String createTmpImgPath(Context context) {
        File photoDir = getTempDir(context);
        return photoDir.getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg";
    }

    public static String createScanCodeImg(Context context) {
        File photoDir = getTempDir(context);
        return photoDir.getAbsolutePath() + "/scanCode.jpg";
    }

    public static String createAppPath(Context context) {
        File photoDir = getAppDir(context);
        return photoDir.getAbsolutePath() + "/" + "file.apk";
    }

    private static boolean hasExternalStoragePermission(Context context) {
        int perm = context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
        return perm == 0;
    }
}
