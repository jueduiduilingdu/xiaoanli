package com.ahapp.huantianxidi.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.ahapp.huantianxidi.R;
import com.ahapp.huantianxidi.utils.comm.GlideUtils;
import com.bigkoo.convenientbanner.holder.Holder;

/**
 * Created by Administrator on 2017/4/1 0001.
 */
public class BannerViewImageHolder implements Holder<String> {
    private ImageView imageView;

    @Override
    public View createView(Context context) {
        //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        GlideUtils.showImg(imageView, R.mipmap.default_img_ll,data);
    }
}
