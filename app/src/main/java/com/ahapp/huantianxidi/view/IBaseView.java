package com.ahapp.huantianxidi.view;

/**
 * Created by tiangongyipin on 16/2/15.
 */
public interface IBaseView {
    void showToast(String msg);

    void showLoading(String msg);

    void showEmpty(String msg);

    void showError(String msg);
}
