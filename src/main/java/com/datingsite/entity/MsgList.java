package com.datingsite.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MsgList {
    private String uid;
    private String renum;
    private String guenum;
    private String bbsnum;
    private String frinum;

    public MsgList(){}

    public MsgList(ResultSet re) throws SQLException {
        this.uid = re.getString("uid");
        this.renum = re.getString("renum");
        this.guenum = re.getString("guenum");
        this.bbsnum = re.getString("bbsnum");
        this.frinum = re.getString("frinum");
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRenum() {
        return renum;
    }

    public void setRenum(String renum) {
        this.renum = renum;
    }

    public String getGuenum() {
        return guenum;
    }

    public void setGuenum(String guenum) {
        this.guenum = guenum;
    }

    public String getBbsnum() {
        return bbsnum;
    }

    public void setBbsnum(String bbsnum) {
        this.bbsnum = bbsnum;
    }

    public String getFrinum() {
        return frinum;
    }

    public void setFrinum(String frinum) {
        this.frinum = frinum;
    }

    @Override
    public String toString() {
        return "MsgList{" +
                "uid='" + uid + '\'' +
                ", renum='" + renum + '\'' +
                ", guenum='" + guenum + '\'' +
                ", bbsnum='" + bbsnum + '\'' +
                ", frinum='" + frinum + '\'' +
                '}';
    }
}
