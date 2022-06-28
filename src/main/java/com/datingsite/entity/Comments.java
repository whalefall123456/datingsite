package com.datingsite.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Comments {
    private String cid;
    private String uid;
    private String pid;
    private String text;
    private String time;
    private String unick;
    private String image;

    public Comments(){}

    public Comments(ResultSet re) throws SQLException {
        this.cid = re.getString("cid");
        this.uid = re.getString("uid");
        this.pid = re.getString("pid");
        this.text = re.getString("text");
        this.time = re.getString("time");
        this.unick = re.getString("unick");
        this.image = re.getString("image");
    }


    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUnick() {
        return unick;
    }

    public void setUnick(String unick) {
        this.unick = unick;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "cid='" + cid + '\'' +
                ", uid='" + uid + '\'' +
                ", pid='" + pid + '\'' +
                ", text='" + text + '\'' +
                ", time='" + time + '\'' +
                ", unick='" + unick + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
