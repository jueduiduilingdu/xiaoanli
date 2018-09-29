package com.ahapp.huantianxidi.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ahapp.huantianxidi.R;
import com.ahapp.huantianxidi.utils.comm.GlideUtils;
import com.ahapp.huantianxidi.utils.comm.StringUtils;
import com.ahapp.huantianxidi.view.MyGridView;
import com.doumee.model.response.comment.PrdCommentListResponseParam;

import java.util.List;


/**
 * Created by Administrator on 2017/5/31.
 * 商品详情 评论详情 adapter
 */
public class ReviewDetailsAdapter extends AppAdapter<PrdCommentListResponseParam> {


    public ReviewDetailsAdapter(List<PrdCommentListResponseParam> list, Context context) {
        super(list, context);
    }

    @Override
    public View createItemView(int position, View converView, ViewGroup parent) {
        ViewHolder viewHolder;
        //可填
        final PrdCommentListResponseParam aetTeachersRoows = list.get(position);
        if (converView == null) {
            converView = LayoutInflater.from(context).inflate(R.layout.fragment_ping_item, null, false);
            //找控件
            viewHolder = new ViewHolder();


            viewHolder.headView = (ImageView) converView.findViewById(R.id.iv_head);
            viewHolder.nameView = (TextView) converView.findViewById(R.id.tv_item_service_cellphone);
            viewHolder.ratingBar = (RatingBar) converView.findViewById(R.id.rb_service_item_ping);
            viewHolder.timeView = (TextView) converView.findViewById(R.id.tv_item_service_datetime);
            viewHolder.contentView = (TextView) converView.findViewById(R.id.tv_service_item_ping_comment);
            viewHolder.imgGv = (MyGridView) converView.findViewById(R.id.gv_img);
            //关联
            converView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) converView.getTag();
        }

        String imagePath = aetTeachersRoows.getImgurl();
        if (StringUtils.isEmpty(imagePath)) {
            GlideUtils.showImg(viewHolder.headView, R.mipmap.default_img_z);
        } else {
            GlideUtils.showImg(viewHolder.headView, R.mipmap.default_img_z, imagePath);
        }
        if (aetTeachersRoows.getMemberName().equals("") || aetTeachersRoows.getMemberName() == null) {
            viewHolder.nameView.setText(context.getString(R.string.No_information_for_the_time_being));
        } else {
            if (aetTeachersRoows.getMemberName().length() >= 1) {

                char MemberName = aetTeachersRoows.getMemberName().charAt(0);//获取字符串的第一个字符
                viewHolder.nameView.setText(MemberName + "*****");
            }
        }
        viewHolder.ratingBar.setRating(Float.parseFloat(aetTeachersRoows.getScore()));
        viewHolder.timeView.setText(aetTeachersRoows.getCreateDate());
        viewHolder.contentView.setText(aetTeachersRoows.getContent());

        return converView;
    }

    //持有者模式
    public class ViewHolder {
        ImageView headView;
        TextView nameView;
        RatingBar ratingBar;
        TextView timeView;
        TextView contentView;
        MyGridView imgGv;
    }
}
