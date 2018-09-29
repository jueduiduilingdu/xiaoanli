package com.ahapp.huantianxidi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahapp.huantianxidi.R;

import java.util.List;


/**
 * Created by Administrator on 2017/7/22 0022.
 */
public class HotFlowGridView extends ViewGroup {
    private int marginLeft= 24;
    private int marginTop= 10;
    private Context mContext;
    public HotFlowGridView(Context context) {
        this(context,null);
    }

    public HotFlowGridView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HotFlowGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int n=getChildCount();
        int maxLineWidth=0;//当前行的子组件的总宽度
        int totalHeight=0;//累计高度
        int width=getMeasuredWidth();//容器宽度
        for (int i=0;i<n;i++){
            View child=getChildAt(i);
            if (maxLineWidth==0){
                layoutChild(child,maxLineWidth+marginLeft,totalHeight+marginTop,maxLineWidth+child.getMeasuredWidth()+marginLeft,totalHeight+child.getMeasuredHeight()+marginTop);
                if (child.getMeasuredWidth()+marginLeft>width-getPaddingLeft()-getPaddingRight()){
                    totalHeight+=child.getMeasuredHeight()+marginTop;
                }else {
                    maxLineWidth=child.getMeasuredWidth()+marginLeft;
                }
            }else {
                if (child.getMeasuredWidth()+marginLeft>width-getPaddingLeft()-getPaddingRight()){
                    try {
                        totalHeight+=getChildAt(i-1).getMeasuredHeight()+marginTop;
                    }catch (Exception e){
                        Log.e("e",e.getMessage());
                    }
                    maxLineWidth=0;
                    layoutChild(child,maxLineWidth+marginLeft,totalHeight+marginTop,maxLineWidth+child.getMeasuredWidth()+marginLeft,totalHeight+child.getMeasuredHeight()+marginTop);
                    totalHeight+=child.getMeasuredHeight()+marginTop;
                }else if (maxLineWidth+child.getMeasuredWidth()+marginLeft >width-getPaddingLeft()-getPaddingRight()){
                    try {
                        totalHeight+=getChildAt(i-1).getMeasuredHeight()+marginTop;
                    }catch (Exception e){
                        Log.e("e",e.getMessage());
                    }
                    maxLineWidth=0;
                    layoutChild(child,maxLineWidth+marginLeft,totalHeight+marginTop,maxLineWidth+child.getMeasuredWidth()+marginLeft,totalHeight+child.getMeasuredHeight()+marginTop);
                    maxLineWidth=child.getMeasuredWidth()+marginLeft;
                }else {
                    layoutChild(child,maxLineWidth+marginLeft,totalHeight+marginTop,maxLineWidth+child.getMeasuredWidth()+marginLeft,totalHeight+child.getMeasuredHeight()+marginTop);
                    maxLineWidth+=child.getMeasuredWidth()+marginLeft;
                }
            }
        }
    }

    private void layoutChild(View child, int l, int t, int r, int b){
        //所有子组件要统一向右和向下平移指定的padding
        child.layout(l+getPaddingLeft(),t+getPaddingTop(),r+getPaddingLeft(),b+getPaddingTop());
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        measureChildren(widthMeasureSpec,expandSpec);
        int width=measureWidth(widthMeasureSpec);
        int height=measureHeight(expandSpec);
        setMeasuredDimension(width,height);

    }

    private int measureWidth(int widthMeasureSpec){
        int mode= MeasureSpec.getMode(widthMeasureSpec);
        int size= MeasureSpec.getSize(widthMeasureSpec);
        int width=0;
        if(mode== MeasureSpec.EXACTLY){
            width=size;
        }else if (mode== MeasureSpec.AT_MOST){
            //计算出多有子组件占的总宽度
            int n=getChildCount();
            int childrenWidth=0;
            for (int i=0;i<n;i++){
                View child=getChildAt(i);
                int childWidth=child.getMeasuredWidth()+marginLeft;
                if (childWidth>size){
                    throw new IllegalStateException("子控件的宽度不能大于父控件");
                }
                childrenWidth+=childWidth;
            }
            //在wrap_content的情况下，如果子组件占的总宽度>容器的最大宽度，则应该取容器的最大宽度
            if (childrenWidth>size){
                width=size;
            }else {
                width=childrenWidth;
            }
            width+=this.getPaddingLeft()+this.getPaddingRight();
        }
        return width;
    }

    private int measureHeight(int heightMeasureSpec){
        int mode= MeasureSpec.getMode(heightMeasureSpec);
        int size= MeasureSpec.getSize(heightMeasureSpec);
        int height=0;
        if(mode== MeasureSpec.EXACTLY){
            height=size;
        }else if (mode== MeasureSpec.AT_MOST){
            //计算出多有子组件占的总宽度
            int width=getMeasuredWidth();
            int n=getChildCount();
            int maxLineWidth=0;//当前行的子组件的总宽度
            for (int i=0;i<n;i++){
                View child=getChildAt(i);
                maxLineWidth+=child.getMeasuredWidth()+marginLeft;
                if (i<n-1&&maxLineWidth+getChildAt(i+1).getMeasuredWidth()+marginLeft>width-getPaddingLeft()-getPaddingRight()){
                    height+=child.getMeasuredHeight()+marginTop;
                    maxLineWidth=0;
                }else if (i==n-1){
                    height+=child.getMeasuredHeight()+marginTop;
                }
            }
            height+=this.getPaddingTop()+this.getPaddingBottom();
        }
        return height;
    }

    public void setOptionList(List<String> optionList,boolean showDelete){
        removeAllViews();
        if (optionList!=null&&optionList.size()>0){
            for (int i=0;i<optionList.size();i++) {
                final String item=optionList.get(i);
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_hot_flow_card, null);
                ImageView deleteImg=view.findViewById(R.id.delete_img);
                if (showDelete){
                    deleteImg.setVisibility(VISIBLE);
                }else {
                    deleteImg.setVisibility(GONE);
                }
                TextView childName = view.findViewById(R.id.ic_content_tv);
                final int position = i;
                childName.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onItemClickListener!=null){
                            onItemClickListener.onItemClick(item, position);
                        }
                    }
                });
                deleteImg.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onDeleteItemClickListener!=null){
                            onDeleteItemClickListener.onItemClick(item, position);
                        }
                    }
                });
                childName.setText(item);
                addView(view);
            }
        }
    }

    private OnItemClickListener onItemClickListener;
    private OnDeleteItemClickListener onDeleteItemClickListener;

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public OnDeleteItemClickListener getOnDeleteItemClickListener() {
        return onDeleteItemClickListener;
    }

    public void setOnDeleteItemClickListener(OnDeleteItemClickListener onDeleteItemClickListener) {
        this.onDeleteItemClickListener = onDeleteItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(String value, int position);
    }

    public interface OnDeleteItemClickListener{
        void onItemClick(String value, int position);
    }

}
