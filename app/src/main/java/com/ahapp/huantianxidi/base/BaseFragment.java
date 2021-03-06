package com.ahapp.huantianxidi.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ahapp.huantianxidi.R;
import com.ahapp.huantianxidi.utils.http.HttpTool;
import com.ahapp.huantianxidi.view.IBaseView;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Field;

import butterknife.ButterKnife;


public abstract class BaseFragment extends Fragment implements IBaseView {
    private static final int NON_CODE = -1;
    protected Context mContext;
    protected View rootView;
    protected float mScreenDensity;
    protected int mScreenHeight;
    protected int mScreenWidth;
    protected boolean isVisible;
    protected HttpTool httpTool;
    private Toast mToast;
    public String TAG;
    protected Dialog loadingPop;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != getArguments()) {
            getExtraArguments(getArguments());
        }
        if (isBindEventBusHere()) {
            EventBus.getDefault().register(this);
        }
        TAG = this.getClass().getSimpleName();
        initHttp();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(getContentViewLayout(), container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        mScreenDensity = displayMetrics.density;
        mScreenHeight = displayMetrics.heightPixels;
        mScreenWidth = displayMetrics.widthPixels;
        initViewsAndEvents();
    }

    protected abstract int getContentViewLayout();

    protected void getExtraArguments(Bundle arguments) {

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onInvisible() {

    }

    protected void onVisible() {
        lazyLoad();
    }

    protected void lazyLoad() {

    }

    protected void initHttp() {
        httpTool=HttpTool.newInstance(getActivity());
    }

    protected boolean isBindEventBusHere() {
        return false;
    }

    protected abstract void initViewsAndEvents();

    protected void showToast(boolean bool) {
        showToast(String.format("%s", bool));
    }

    protected void showToast(int number) {
        showToast(String.format("%d", number));
    }

    public void showToast(String msg) {
        if (null != msg) {
            if (mToast == null) {
                mToast = Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT);
                mToast.setGravity(Gravity.CENTER, 0, 0);
            } else {
                mToast.setText(msg);
            }
            mToast.show();
        }
    }

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void showEmpty(String msg) {

    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }

    /**
     * startActivity
     *
     * @param clazz target Activity
     */
    protected void go(Class<? extends Activity> clazz) {
        _goActivity(clazz, null, NON_CODE, false);
    }

    /**
     * startActivity with bundle
     *
     * @param clazz  target Activity
     * @param bundle
     */
    protected void go(Class<? extends Activity> clazz, Bundle bundle) {
        _goActivity(clazz, bundle, NON_CODE, false);
    }

    protected void goForResult(Class<? extends Activity> clazz, int requestCode) {
        _goActivity(clazz, null, requestCode, false);
    }

    protected void goForResult(Class<? extends Activity> clazz, Bundle bundle, int requestCode) {
        _goActivity(clazz, bundle, requestCode, false);
    }

    /**
     * Activity 跳转
     *
     * @param clazz  目标activity
     * @param bundle 传递参数
     * @param finish 是否结束当前activity
     */
    private void _goActivity(Class<? extends Activity> clazz, Bundle bundle, int requestCode, boolean finish) {
        if (null == clazz) {
            throw new IllegalArgumentException("you must pass a target activity where to go.");
        }
        Intent intent = new Intent(getActivity(), clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        if (requestCode > NON_CODE) {
            startActivityForResult(intent, requestCode);
        } else {
            startActivity(intent);
        }
        if (finish) {
            getActivity().finish();
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        httpTool.clear();
        mToast = null;
        hideLoading();
        if (isBindEventBusHere()) {
            EventBus.getDefault().unregister(this);
        }
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        if (loadingPop!=null&&loadingPop.isShowing()){
            loadingPop.dismiss();
        }
        super.onDestroy();
    }

    public synchronized void hideLoading() {
        if (loadingPop!=null){
            loadingPop.dismiss();
        }
    }

    public synchronized void showLoading() {
        Log.e(TAG, ">>>>>>  showLoading");
        if (loadingPop==null){
            loadingPop=new Dialog(getActivity(), R.style.NoTitleDialogStyle);
            loadingPop.setContentView(R.layout.popup_loading);
            loadingPop.setCanceledOnTouchOutside(false);
        }
        loadingPop.show();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        try {
            Field mChildFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            mChildFragmentManager.setAccessible(true);
            mChildFragmentManager.set(this, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mToast != null) {
            mToast.cancel();
        }
    }
}
