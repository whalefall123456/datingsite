package com.datingsite.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FriendRequest {
    private String id;
    private String uid;
    private String tuid;
    private String retime;
    private String content;
    private String state;
    private String unick;
    private String image;

    public FriendRequest(){}

    public FriendRequest(ResultSet re) throws SQLException {
        this.id = re.getString("id");
        this.uid = re.getString("uid");
        this.tuid = re.getString("tuid");
        this.retime = re.getString("retime");
        this.content = re.getString("content");
        this.state = re.getString("state");
        this.unick = re.getString("unick");
        this.image = re.getString("image");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTuid() {
        return tuid;
    }

    public void setTuid(String tuid) {
        this.tuid = tuid;
    }

    public String getRetime() {
        return retime;
    }

    public void setRetime(String retime) {
        this.retime = retime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        return "FriendRequest{" +
                "id='" + id + '\'' +
                ", uid='" + uid + '\'' +
                ", tuid='" + tuid + '\'' +
                ", retime='" + retime + '\'' +
                ", content='" + content + '\'' +
                ", state='" + state + '\'' +
                ", unick='" + unick + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
