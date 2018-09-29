package com.ahapp.huantianxidi.ui.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.ahapp.huantianxidi.R;
import com.ahapp.huantianxidi.view.GalleryFlow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/9/25.
 */

public class guangGaoAdapter extends BaseAdapter {

    int mGalleryItemBackground;
    private Context mContext;
    private ImageView[] mImages;		// 保存倒影图片的数组
    public List<Map<String, Object>> list;


    public Integer[] imgs = { R.drawable.banner01, R.drawable.banner02, R.drawable.banner03,
            R.drawable.banner04};
    public String[] titles = { "大盘博弈", "视听中心", "新闻资讯", "个人中心"};

    public guangGaoAdapter(Context c)
    {
        this.mContext = c;
        list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < imgs.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("image", imgs[i]);
            list.add(map);
        }
        mImages = new ImageView[list.size()];
    }


    public boolean createReflectedImages() {
        final int reflectionGap = 4;
        int index = 0;

        for (Map<String, Object> map : list) {
            Integer id = (Integer) map.get("image");
            Bitmap originalImage = BitmapFactory.decodeResource(mContext
                    .getResources(), id);
            int width = originalImage.getWidth();
            int height = originalImage.getHeight();

            Matrix matrix = new Matrix();
//			matrix.setRotate(30);
            matrix.preScale(1, -1);

            Bitmap reflectionImage = Bitmap.createBitmap(originalImage, 0,
                    height / 2, width, height / 2, matrix, false);

            Bitmap bitmapWithReflection = Bitmap.createBitmap(width,
                    (height + height / 2), Bitmap.Config.ARGB_8888);

            Canvas canvas = new Canvas(bitmapWithReflection);
            canvas.drawBitmap(originalImage, 0, 0, null);

            Paint deafaultPaint = new Paint();

            canvas.drawRect(0, height, width, height + reflectionGap,
                    deafaultPaint);

            canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);

            Paint paint = new Paint();

            LinearGradient shader = new LinearGradient(0,
                    originalImage.getHeight(), 0,
                    bitmapWithReflection.getHeight() + reflectionGap,
                    0x70ffffff, 0x00ffffff, Shader.TileMode.CLAMP);

            paint.setShader(shader);

            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            canvas.drawRect(0, height, width, bitmapWithReflection.getHeight()
                    + reflectionGap, paint);

            ImageView imageView = new ImageView(mContext);


            imageView.setImageBitmap(bitmapWithReflection);
            WindowManager wm = (WindowManager)mContext.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
            int width1 = wm.getDefaultDisplay().getWidth();
            int height1 = wm.getDefaultDisplay().getHeight();
            imageView.setLayoutParams(new GalleryFlow.LayoutParams(220, 260));
//			 imageView.setScaleType(ScaleType.MATRIX);
            mImages[index++] = imageView;
        }
        return true;
    }

    private Resources getResources() {
        // TODO Auto-generated method stub
        return null;
    }

    public int getCount() {
        return imgs.length;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        return mImages[position];
    }

    public float getScale(boolean focused, int offset) {
        return Math.max(0, 1.0f / (float) Math.pow(2, Math.abs(offset)));
    }

}
