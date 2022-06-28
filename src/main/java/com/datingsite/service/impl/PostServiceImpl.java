package com.datingsite.service.impl;

import com.datingsite.dao.PostDao;
import com.datingsite.dao.impl.PostDaoImpl;
import com.datingsite.entity.Comments;
import com.datingsite.entity.Post;
import com.datingsite.service.PostService;
import com.datingsite.utils.C3P0Utils;
import com.google.gson.Gson;
import net.sf.json.JSONObject;

import java.util.List;

public class PostServiceImpl implements PostService {
    /**
     * 根据类型查询帖子
     * @param type
     * @return
     */
    @Override
    public String queryPostByType(String type) {
        PostDao postDao = new PostDaoImpl();
        List<Post> posts = postDao.queryPostByType(type);
        JSONObject jsonObject = new JSONObject();
        for(int i=1;i<=posts.size();i++){
            Gson gson = new Gson();
            String json = gson.toJson(posts.get(i-1));
            jsonObject.put("post"+Integer.toString(i),json);
        }
        return jsonObject.toString();
    }

    /**
     * 根据用户uid来查询帖子
     * @param uid
     * @return
     */
    @Override
    public String queryPostByUid(String uid) {
        PostDao postDao = new PostDaoImpl();
        List<Post> posts = postDao.queryPostByUid(uid);
        JSONObject jsonObject = new JSONObject();
        for(int i=1;i<=posts.size();i++){
            Gson gson = new Gson();
            String json = gson.toJson(posts.get(i-1));
            jsonObject.put("post"+Integer.toString(i),json);
        }
        return jsonObject.toString();
    }

    /**
     * 根据id来查询帖子信息
     * @param pid
     * @return
     */
    @Override
    public String queryPostByPid(String pid) {
        PostDao postDao = new PostDaoImpl();
        List<Post> posts = postDao.queryPostByPid(pid);
        JSONObject jsonObject = new JSONObject();
        for(int i=1;i<=posts.size();i++){
            Gson gson = new Gson();
            String json = gson.toJson(posts.get(i-1));
            jsonObject.put("post"+Integer.toString(i),json);
        }
        return jsonObject.toString();
    }

    /**
     * 帖子的存储业务
     * @param uid
     * @param title
     * @param content
     * @param type
     * @return
     */
    @Override
    public String savePostService(String uid, String title, String content, String type) {
        PostDao postDao = new PostDaoImpl();
        boolean flag = postDao.savePost(uid, title, content, type);
        if(flag){
            return "帖子发布成功";
        }
        return "发布失败，请稍后尝试！";
    }

    /**
     * 评论存储业务
     * @param uid
     * @param pid
     * @param text
     * @return
     */
    @Override
    public boolean sendRemarksService(String uid, String pid, String text) {
        PostDao postDao =new PostDaoImpl();
        boolean flag = postDao.saveRemarks(uid, pid, text);
        return flag;
    }

    /**
     * 评论加一业务
     * @return
     */
    @Override
    public boolean postAddRemarks(String pid) {
        PostDao postDao = new PostDaoImpl();
        List<Post> posts = postDao.queryPostByPid(pid);
        int num = Integer.valueOf(posts.get(0).getRemarks())+1;
        PostDao postDao1 = new PostDaoImpl();
        boolean flag = postDao1.setRemarks(pid, Integer.toString(num));
        return flag;
    }

    /**
     * 根据pid来查询评论
     * @param pid
     * @return
     */
    @Override
    public String queryCommByPid(String pid) {
        PostDao postDao = new PostDaoImpl();
        List<Comments> comments = postDao.queryComByPid(pid);
        JSONObject jsonObject = new JSONObject();
        for(int i=1;i<=comments.size();i++){
            Gson gson = new Gson();
            String json = gson.toJson(comments.get(i-1));
            jsonObject.put("comment"+Integer.toString(i),json);
        }
        return jsonObject.toString();
    }

    @Override
    public boolean depostService(String pid) {
        //首先将帖子的评论全部删除
        PostDao postDao = new PostDaoImpl();
        boolean flag = postDao.deCommentByPid(pid);
        if(flag){
            boolean b = postDao.deletePost(pid);
            if(b) return true;
        }
        return false;
    }
}
