package com.caveman.androidtimeaxis;

/**
 * Created by Administrator on 2018/10/26.
 * <p>
 * Description:
 */
public class TimeInfo {
    private String msg;
    private String smallText;
    private String bigText;
    private String imagePath;
    // 1 未完成   0 进行   -1 已完成
    private int state = 1;

    public TimeInfo() {
    }

    public TimeInfo(String msg, String smallText, String bigText, String imagePath, int state) {
        this.msg = msg;
        this.smallText = smallText;
        this.bigText = bigText;
        this.imagePath = imagePath;
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSmallText() {
        return smallText;
    }

    public void setSmallText(String smallText) {
        this.smallText = smallText;
    }

    public String getBigText() {
        return bigText;
    }

    public void setBigText(String bigText) {
        this.bigText = bigText;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
