package com.datingsite.dao;

import com.datingsite.entity.FriendRequest;
import com.datingsite.entity.MsgList;

import java.sql.ResultSet;
import java.util.List;

public interface MessageDao {
    List<FriendRequest> queryRequest(String uid);
    //已经弃用
    ResultSet queryGuest(String uid);
    MsgList queryNum(String uid);
    boolean addMsgNum(String type,String id);
    boolean setZero(String uid,String type);
    boolean setGuestRead(String id);
    boolean initMsgList(String uid);
}
