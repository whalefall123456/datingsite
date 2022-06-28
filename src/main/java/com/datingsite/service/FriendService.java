package com.datingsite.service;

public interface FriendService {
    int addFriendService(String uid,String tuid,String content);
    int agreeService(String uid,String tuid,String requestid);
    boolean rejectService(String requestid);
}
