package com.ahapp.huantianxidi.base;

import android.app.Dialog;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ahapp.huantianxidi.R;
import com.ahapp.huantianxidi.utils.http.HttpTool;
import com.ahapp.huantianxidi.utils.oss.OSSUploadFile;
import com.ahapp.huantianxidi.view.IBaseView;

import butterknife.ButterKnife;

public abstract class BaseActivity extends BaseAppCompatActivity implements IBaseView {
    protected String TAG = BaseActivity.class.getSimpleName();
    private Toast toast = null;
    protected Dialog loadingPop;
    protected HttpTool httpTool;

    protected OSSUploadFile ossUploadFile;

    @Override
    protected boolean isOverridePendingTransition() {
        return false;
    }

    @Override
    protected boolean hasTitleBar() {
        return findViewById(R.id.title_bar) != null;
    }

    @Override
    protected void initHttp() {
        httpTool=HttpTool.newInstance(this);
    }

    @Override
    protected void setCustomTitle(CharSequence title) {
        if (hasTitleBar()) {
            TextView titleView = ButterKnife.findById(this, R.id.title_tv_message);
            if (titleView != null) {
                titleView.setText(title);
                setTitle("");
            }
        }
    }


    public void showToast(String msg) {
        if (null != msg) {
            if (toast == null) {
                toast = Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
            } else {
                toast.setText(msg);
            }
            toast.show();
        }
    }

    public void showToast(int msg) {
        showToast(String.valueOf(msg));
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

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }


    protected OSSUploadFile getOssInstence(){
        if (ossUploadFile == null){
            String endpoint, accessKeyId, accessKeySecret, bucketName;
            endpoint = App.getDataIndex().get(Constants.DateIndex.ALIYUN_UPLOAD_ENDPOINT);
            accessKeyId =  App.getDataIndex().get(Constants.DateIndex.ALIYUN_UPLOAD_ID);
            accessKeySecret =  App.getDataIndex().get(Constants.DateIndex.ALIYUN_UPLOAD_KEY);
            bucketName =  App.getDataIndex().get(Constants.DateIndex.ALIYUN_UPLOAD_BUCKETNMAE);
            ossUploadFile = new OSSUploadFile(this,endpoint,accessKeyId, accessKeySecret,bucketName);
        }
        return  ossUploadFile;
    }

    @Override
    protected void onDestroy() {
        hideLoading();
        toast = null;
        httpTool.clear();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        if (toast != null) {
            toast.cancel();
        }
        super.onPause();
    }

    @Override
    protected void onNavigateClick() {
        if (hasTitleBar()) {
            ImageButton backView = ButterKnife.findById(this, R.id.actionbar_back);
            if (backView != null) {
                backView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            }
        }
    }
    public synchronized void hideLoading() {
        Log.e(TAG, ">>>>>>  hideLoading");
        if (loadingPop!=null){
            loadingPop.dismiss();
        }
    }

    public synchronized void showLoading() {
        Log.e(TAG, ">>>>>>  showLoading");
        if (loadingPop==null){
            loadingPop=new Dialog(this,R.style.NoTitleDialogStyle);
            loadingPop.setContentView(R.layout.popup_loading);
            loadingPop.setCanceledOnTouchOutside(false);
        }
        loadingPop.show();
    }

    @Override
    protected TransitionMode getTransitionMode() {
        return TransitionMode.RIGHT;
    }

}
