package com.ahapp.huantianxidi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahapp.huantianxidi.R;

import java.util.List;

/**
 * Created by Administrator on 2017/11/16.
 */

public class HistoricalRecordGridViewViewGroup extends ViewGroup {
    private int marginLeft = 24;
    private int marginTop = 16;
    private Context mContext;

    public HistoricalRecordGridViewViewGroup(Context context) {
        this(context, null);
    }

    public HistoricalRecordGridViewViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HistoricalRecordGridViewViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int n = getChildCount();
        int maxLineWidth = 0;//当前行的子组件的总宽度
        int totalHeight = 0;//累计高度
        int width = getMeasuredWidth();//容器宽度
        for (int i = 0; i < n; i++) {
            View child = getChildAt(i);
            //判断是否要换行显示
            if (maxLineWidth + child.getMeasuredWidth() + marginLeft
                    > width - getPaddingLeft() - getPaddingRight()) {
                //换行后累计已显示的行的总高度
                totalHeight += child.getMeasuredHeight() + marginTop;
                maxLineWidth = 0;
            }
            layoutChild(child, maxLineWidth + marginLeft, totalHeight + marginTop, maxLineWidth + child.getMeasuredWidth() + marginLeft, totalHeight + child.getMeasuredHeight() + marginTop);
            maxLineWidth += child.getMeasuredWidth() + marginLeft;
        }
    }

    private void layoutChild(View child, int l, int t, int r, int b) {
        //所有子组件要统一向右和向下平移指定的padding
        child.layout(l + getPaddingLeft(), t + getPaddingTop(), r + getPaddingLeft(), b + getPaddingTop());
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        measureChildren(widthMeasureSpec, expandSpec);
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(expandSpec);
        setMeasuredDimension(width, height);

    }

    private int measureWidth(int widthMeasureSpec) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int width = 0;
        if (mode == MeasureSpec.EXACTLY) {
            width = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            //计算出多有子组件占的总宽度
            int n = getChildCount();
            int childrenWidth = 0;
            for (int i = 0; i < n; i++) {
                View child = getChildAt(i);
                int childWidth = child.getMeasuredWidth() + marginLeft;
                if (childWidth > size) {
                    throw new IllegalStateException("子控件的宽度不能大于父控件");
                }
                childrenWidth += childWidth;
            }
            //在wrap_content的情况下，如果子组件占的总宽度>容器的最大宽度，则应该取容器的最大宽度
            if (childrenWidth > size) {
                width = size;
            } else {
                width = childrenWidth;
            }
            width += this.getPaddingLeft() + this.getPaddingRight();
        }
        return width;
    }

    private int measureHeight(int heightMeasureSpec) {
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        int height = 0;
        if (mode == MeasureSpec.EXACTLY) {
            height = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            //计算出多有子组件占的总宽度
            int width = getMeasuredWidth();
            int n = getChildCount();
            int maxLineWidth = 0;//当前行的子组件的总宽度
            for (int i = 0; i < n; i++) {
                View child = getChildAt(i);
                maxLineWidth += child.getMeasuredWidth() + marginLeft;
                if (i < n - 1 && maxLineWidth + getChildAt(i + 1).getMeasuredWidth() + marginLeft > width - getPaddingLeft() - getPaddingRight()) {
                    height += child.getMeasuredHeight() + marginTop;
                    maxLineWidth = 0;
                } else if (i == n - 1) {
                    height += child.getMeasuredHeight() + marginTop;
                }
            }
            height += this.getPaddingTop() + this.getPaddingBottom();
        }
        return height;
    }

    public void setOptionList(List<String> optionList) {
        removeAllViews();
        if (optionList != null && optionList.size() > 0) {
            for (int i = 0; i < optionList.size(); i++) {
                final String item = optionList.get(i);
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_hjistorical_record, null);
                TextView childName = (TextView) view.findViewById(R.id.search_name);
                ImageView delete = (ImageView) view.findViewById(R.id.iv_delete_si);
                childName.setText(item);
                childName.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (viewlistener != null) {
                            viewlistener.onText(item);
                        }
                    }
                });
                delete.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (viewlistener != null) {
                            viewlistener.onImg(item);
                        }
                    }
                });
                addView(view);


            }
        }
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }


    private OnChildViewlistener viewlistener;

    public void setViewlistener(OnChildViewlistener viewlistener) {
        this.viewlistener = viewlistener;
    }

    public interface OnItemClickListener {
        void onItemClick(String param);
    }

    public interface OnChildViewlistener {
        void onText(String str);

        void onImg(String str);
    }
}
