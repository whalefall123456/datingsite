package com.datingsite.entity;

import javafx.geometry.Pos;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Post {
    private String pid;
    private String type;
    private String uid;
    private String title;
    private String content;
    private String like;
    private String time;
    private String remarks;
    private String unick;
    private String image;

    public Post(){}

    public Post(ResultSet re) throws SQLException {
        this.pid = re.getString("pid");
        this.type = re.getString("type");
        this.uid = re.getString("uid");
        this.title = re.getString("title");
        this.content = re.getString("content");
        this.like = re.getString("like");
        this.time = re.getString("time");
        this.remarks = re.getString("remarks");
        this.unick = re.getString("unick");
        this.image = re.getString("image");
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
        return "Post{" +
                "pid='" + pid + '\'' +
                ", type='" + type + '\'' +
                ", uid='" + uid + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", like='" + like + '\'' +
                ", time='" + time + '\'' +
                ", remarks='" + remarks + '\'' +
                ", unick='" + unick + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
