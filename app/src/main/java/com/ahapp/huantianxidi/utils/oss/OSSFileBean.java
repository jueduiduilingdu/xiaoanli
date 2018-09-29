package com.ahapp.huantianxidi.utils.oss;

import java.io.File;

/**
 * Created by lenovo on 2017/4/7.
 */
public class OSSFileBean {
    private File file;
    private int type;
    private String key;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
