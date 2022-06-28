package com.datingsite.dao;

import com.datingsite.entity.Comments;
import com.datingsite.entity.Post;

import java.util.List;

public interface PostDao {
    List<Post> queryPostByType(String type);
    List<Post> queryPostByUid(String uid);
    List<Post> queryPostByPid(String pid);
    boolean savePost(String uid,String title,String conten,String type);
    boolean saveRemarks(String uid, String pid, String text);
    boolean setRemarks(String pid,String remarks);
    List<Comments> queryComByPid(String pid);
    boolean deCommentByPid(String pid);
    boolean deletePost(String pid);
}
