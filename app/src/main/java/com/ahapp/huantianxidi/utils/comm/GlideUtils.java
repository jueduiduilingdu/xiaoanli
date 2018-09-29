package com.ahapp.huantianxidi.utils.comm;

import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.ahapp.huantianxidi.R;
import com.ahapp.huantianxidi.base.App;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

/**
 * Created by Administrator on 2017/10/26 0026.
 */
public class GlideUtils {


    public static  void showImg(ImageView imageView, String path){
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.default_img_z)
                .error(R.mipmap.default_img_z);
        Glide.with(App.getInst()).load(path).apply(options).into(imageView);
    }

    public static  void showImg(ImageView imageView, int placeholder, String path){
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(placeholder)
                .error(placeholder);
        Glide.with(App.getInst()).load(path).apply(options).into(imageView);
    }

    public static  void showImgInside(ImageView imageView, String path){
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.default_img_z)
                .error(R.mipmap.default_img_z);
        Glide.with(App.getInst()).load(path).apply(options).into(imageView);
    }
    public static  void showImg(ImageView imageView, int resource){
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.default_img_z)
                .error(R.mipmap.default_img_z);
        Glide.with(App.getInst()).load(resource).apply(options).into(imageView);
    }

    public static void concerImg(final ImageView imageView, String path){
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.default_img_z)
                .error(R.mipmap.default_img_z);
        Glide.with(App.getInst()).asBitmap().load(path).apply(options).into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(App.getInst().getResources(), resource);
                circularBitmapDrawable.setCornerRadius(5);
                if (imageView!=null) {
                    imageView.setImageDrawable(circularBitmapDrawable);
                }
            }
        });
    }

    public static void concerImg(final ImageView imageView, int placeholder, String path){
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(placeholder)
                .error(placeholder);
        Glide.with(App.getInst()).asBitmap().load(path).apply(options).into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(App.getInst().getResources(), resource);
                circularBitmapDrawable.setCornerRadius(5);
                if (imageView!=null) {
                    imageView.setImageDrawable(circularBitmapDrawable);
                }
            }
        });
    }

    public static void concerImg(final ImageView imageView, int resource){
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.default_img_z)
                .error(R.mipmap.default_img_z);
        Glide.with(App.getInst()).asBitmap().load(resource).apply(options).into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(App.getInst().getResources(), resource);
                circularBitmapDrawable.setCornerRadius(5);
                if (imageView!=null) {
                    imageView.setImageDrawable(circularBitmapDrawable);
                }
            }
        });
    }

    public static void concerImg(final ImageView imageView, String path, final int radio){
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.default_img_z)
                .error(R.mipmap.default_img_z);
        Glide.with(App.getInst()).asBitmap().load(path).apply(options).into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(App.getInst().getResources(), resource);
                circularBitmapDrawable.setCornerRadius(radio);
                if (imageView!=null) {
                    imageView.setImageDrawable(circularBitmapDrawable);
                }
            }
        });
    }

    public static void concerImg(final ImageView imageView, int placeholder, String path, final int radio){
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(placeholder)
                .error(placeholder);
        Glide.with(App.getInst()).asBitmap().load(path).apply(options).into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(App.getInst().getResources(), resource);
                circularBitmapDrawable.setCornerRadius(radio);
                if (imageView!=null) {
                    imageView.setImageDrawable(circularBitmapDrawable);
                }
            }
        });
    }

    public static void concerImg(final ImageView imageView, int resource, final int radio){
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.default_img_z)
                .error(R.mipmap.default_img_z);
        Glide.with(App.getInst()).asBitmap().load(resource).apply(options).into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(App.getInst().getResources(), resource);
                circularBitmapDrawable.setCornerRadius(radio);
                if (imageView!=null) {
                    imageView.setImageDrawable(circularBitmapDrawable);
                }
            }
        });
    }

    public static void circleImg(final ImageView imageView, String path){
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.default_img_c)
                .error(R.mipmap.default_img_c);
        Glide.with(App.getInst()).asBitmap().load(path).apply(options).into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(App.getInst().getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                if (imageView!=null) {
                    imageView.setImageDrawable(circularBitmapDrawable);
                }
            }
        });
    }

    public static void circleImg(final ImageView imageView, int resource){
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.default_img_c)
                .error(R.mipmap.default_img_c);
        Glide.with(App.getInst()).asBitmap().load(resource).apply(options).into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(App.getInst().getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                if (imageView!=null) {
                    imageView.setImageDrawable(circularBitmapDrawable);
                }
            }
        });
    }

    public static void circleImg(final ImageView imageView, int placeholder, String path){
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(placeholder)
                .error(placeholder);
        Glide.with(App.getInst()).asBitmap().load(path).apply(options).into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(App.getInst().getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                if (imageView!=null) {
                    imageView.setImageDrawable(circularBitmapDrawable);
                }
            }
        });
    }

    public static void ReleaseShowImg(ImageView imageView, String path) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.icon_fb_sczp)
                .error(R.mipmap.icon_fb_sczp);
        Glide.with(App.getInst()).load(path).apply(options).into(imageView);
    }
}
