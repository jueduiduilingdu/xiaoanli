package com.ahapp.huantianxidi.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ahapp.huantianxidi.R;


/**
 * Created by timor.fan on 2016/8/15.
 * *项目名：CZF
 * 类描述：
 */
public class AllTextView extends LinearLayout {
    TextView contentTextView;
    boolean isExpand;
    TextView tipTextView;
    CharSequence contentText;
    int maxLine;
    Context context;
    private OnFullTextListener fullTextListener;
    int lineNum;

    public AllTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        View view= LayoutInflater.from(context).inflate(R.layout.my_text_view,this);
        contentTextView= (TextView) view.findViewById(R.id.contentTextView);
        tipTextView= (TextView) view.findViewById(R.id.tipTextView);
        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.my_text_view);
        contentText=typedArray.getString(R.styleable.my_text_view_contentText);
        tipTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExpand){
                    isExpand=false;
                    contentTextView.setMaxLines(maxLine);
                    tipTextView.setText(AllTextView.this.context.getResources().getString(R.string.fullText));
                }else {
                    isExpand=true;
                    contentTextView.setMaxLines(100);
                    tipTextView.setText(AllTextView.this.context.getResources().getString(R.string.packUp));
                }

                if (fullTextListener!=null){
                    fullTextListener.onClick(isExpand);
                }
            }
        });

    }

    public void setOnFullClickListener(OnFullTextListener listener){
        fullTextListener=listener;
    }
    public void setText(String content, final boolean isExpand, int line) {
        this.maxLine=line;
        contentTextView.setEllipsize(null);
        this.isExpand=isExpand;
        contentTextView.setText(content);
        contentTextView.post(new Runnable() {
            @Override
            public void run() {
                lineNum = contentTextView.getLineCount();
                contentTextView.setEllipsize(TextUtils.TruncateAt.END);
                if (lineNum>maxLine){
                    tipTextView.setVisibility(VISIBLE);
                    if (isExpand){
                        contentTextView.setMaxLines(100);
                        tipTextView.setText(context.getResources().getString(R.string.packUp));
                    }else {
                        contentTextView.setMaxLines(maxLine);
                        tipTextView.setText(context.getResources().getString(R.string.fullText));
                    }
                }else {
                    if(tipTextView.getVisibility()!=GONE){
                        tipTextView.setVisibility(GONE);
                    }
                }
            }
        });
    }

    public interface OnFullTextListener{
        void onClick(boolean isExpand);
    }
}