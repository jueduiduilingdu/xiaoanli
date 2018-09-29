package com.ahapp.huantianxidi.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ahapp.huantianxidi.R;
import com.doumee.model.response.city.CityListResponseParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MQ on 2017/5/8.
 */

public class ChooseCityAdapter extends RecyclerView.Adapter<ChooseCityAdapter.MyRecycleHolder> {

    protected List<CityListResponseParam> cityBeanList;
    private Context mContext;

    private OnItemLongClickListener onItemLongClickListener;
    private OnItemClickListener onItemClickListener;

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public OnItemLongClickListener getOnItemLongClickListener() {
        return onItemLongClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public ChooseCityAdapter(Context context) {
        this.mContext = context;
        cityBeanList = new ArrayList<>();
    }

    public void addAll(List<CityListResponseParam> beans) {
        if (cityBeanList.size() > 0) {
            cityBeanList.clear();
        }
        cityBeanList.addAll(beans);
        notifyDataSetChanged();
    }

    public void add(CityListResponseParam bean, int position) {
        cityBeanList.add(position, bean);
        notifyItemInserted(position);
    }

    public void add(CityListResponseParam bean) {
        cityBeanList.add(bean);
        notifyItemChanged(cityBeanList.size() - 1);
    }

    @Override
    public MyRecycleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_choose_city, parent, false);
        return new MyRecycleHolder(view);
    }

    @Override
    public void onBindViewHolder(MyRecycleHolder holder, final int position) {
        if (cityBeanList == null || cityBeanList.size() == 0 || cityBeanList.size() <= position)
            return;
        final CityListResponseParam bean = cityBeanList.get(position);
        if (bean != null) {
            holder.nameTv.setText(bean.getCityName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener!=null){
                        onItemClickListener.onItemClick(position);
                    }
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (onItemLongClickListener!=null){
                        onItemLongClickListener.onLongClick(position);
                    }
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return cityBeanList.size();
    }

    public static class MyRecycleHolder extends RecyclerView.ViewHolder {
        public final TextView nameTv;
        public final View itemView;

        public MyRecycleHolder(View itemView) {
            super(itemView);
            this.itemView=itemView;
            nameTv = itemView.findViewById(R.id.name_tv);
        }
    }

    public interface OnItemLongClickListener{
        void onLongClick(int position);
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
