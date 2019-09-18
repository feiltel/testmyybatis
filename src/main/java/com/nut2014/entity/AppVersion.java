package com.nut2014.entity;

public class AppVersion {

    public AppVersion() {
    }

    public AppVersion(String updateInfo, int versionCode, String versionName, String downloadPath) {
        this.updateInfo = updateInfo;
        this.versionCode = versionCode;
        this.versionName = versionName;
        this.downloadPath = downloadPath;
    }

    public String getUpdateInfo() {
        return updateInfo;
    }

    public void setUpdateInfo(String updateInfo) {
        this.updateInfo = updateInfo;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    private String updateInfo;
    private int versionCode;
    private String versionName;
    private String downloadPath;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDownloadPath() {
        return downloadPath;
    }

    public void setDownloadPath(String downloadPath) {
        this.downloadPath = downloadPath;
    }
}
