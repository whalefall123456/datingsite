package com.datingsite.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 留言实体类
 */
public class Guest {
    private String gid;
    private String uid;
    private String touid;
    private String text;
    private String time;
    private String state;
    private String unick;
    private String image;

    public Guest(){}

    public Guest(ResultSet re) throws SQLException {
        this.gid = re.getString("gid");
        this.uid = re.getString("uid");
        this.touid = re.getString("touid");
        this.text = re.getString("text");
        this.time = re.getString("time");
        this.state = re.getString("state");
        this.unick = re.getString("unick");
        this.image = re.getString("image");
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTouid() {
        return touid;
    }

    public void setTouid(String touid) {
        this.touid = touid;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
        return "Guest{" +
                "gid='" + gid + '\'' +
                ", uid='" + uid + '\'' +
                ", touid='" + touid + '\'' +
                ", text='" + text + '\'' +
                ", time='" + time + '\'' +
                ", state='" + state + '\'' +
                ", unick='" + unick + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
