package com.ahapp.huantianxidi.ui.fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahapp.huantianxidi.R;
import com.ahapp.huantianxidi.base.App;
import com.ahapp.huantianxidi.base.BaseFragment;
import com.ahapp.huantianxidi.base.Constants;
import com.ahapp.huantianxidi.utils.comm.GlideUtils;
import com.ahapp.huantianxidi.utils.comm.StringUtils;
import com.ahapp.huantianxidi.utils.http.Apis;
import com.ahapp.huantianxidi.utils.http.HttpTool;
import com.ahapp.huantianxidi.view.Dialog;
import com.ahapp.huantianxidi.view.HorizontalScroll.HorizontalScrollBaseAdapter;
import com.ahapp.huantianxidi.view.HorizontalScroll.HorizontalScrollMenu;
import com.ahapp.huantianxidi.view.XRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.doumee.model.request.PaginationBaseObject;
import com.doumee.model.request.category.CategoryListRequestObject;
import com.doumee.model.request.category.CategoryListRequestParam;
import com.doumee.model.request.essay.EssayListRequestObject;
import com.doumee.model.request.essay.EssayListRequestParam;
import com.doumee.model.response.category.CateChildListResponseParam;
import com.doumee.model.response.category.CategoryListResponseObject;
import com.doumee.model.response.category.CategoryListResponseParam;
import com.doumee.model.response.essay.EssayListResponseObject;
import com.doumee.model.response.essay.EssayListResponseParam;
import com.doumee.model.response.userinfo.UserInfoResponseParam;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/3 0003.
 */

public class FindFragment extends BaseFragment {

    @Override
    protected int getContentViewLayout() {
        return R.layout.fragment_find;
    }

    @Override
    protected void initViewsAndEvents() {

    }
}
