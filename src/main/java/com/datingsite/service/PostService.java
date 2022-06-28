package com.datingsite.service;

public interface PostService {
    String queryPostByType(String type);
    String queryPostByUid(String uid);
    String queryPostByPid(String pid);
    String savePostService(String uid, String title, String content, String type);
    boolean sendRemarksService(String uid,String pid,String text);
    boolean postAddRemarks(String pid);
    String queryCommByPid(String pid);
    boolean depostService(String pid);
}
