package com.ahapp.huantianxidi.base;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/16.
 */

public class ListDataSaveString {
    private SharedPreferences preferencesString;
    private SharedPreferences.Editor editorString;

    public ListDataSaveString(Context mContext) {
        preferencesString = mContext.getSharedPreferences(Constants.HISTORiCAL_RECORD, Context.MODE_PRIVATE);
    }

    public <T> void setDataList(List<String> string) {
        editorString = preferencesString.edit();
        editorString.putInt("EnvironNums", string.size());
        for (int i = 0; i < string.size(); i++) {

            editorString.putString(Constants.RECORD + i, string.get(i));
        }

        editorString.commit();
    }

    /**
     * 获取lIst
     *
     * @return
     * @paramtag
     */
    public <T> List<String> getDataList() {
        List<String> environmentList = new ArrayList<String>();
        int environNums = preferencesString.getInt("EnvironNums", 0);
        for (int i = 0; i < environNums; i++) {
            String environItem = preferencesString.getString(Constants.RECORD + i, null);
            environmentList.add(environItem);
        }
//      String environItem = preferencesString.getString(CustomConfig.RECORD, null);
//      environmentList.add(environItem);

        return environmentList;

    }

    public void clear() {
        editorString = preferencesString.edit();
        editorString.clear();
        editorString.commit();

    }


}
