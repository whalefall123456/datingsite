package com.datingsite.dao.impl;

import com.datingsite.dao.PostDao;
import com.datingsite.entity.Comments;
import com.datingsite.entity.Post;
import com.datingsite.utils.C3P0Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostDaoImpl implements PostDao {
    private Connection conn = C3P0Utils.getConnection();
    private PreparedStatement pstm = null;
    private ResultSet resultSet = null;

    /**
     * 通过标签查询帖子
     * @param type
     * @return
     */
    @Override
    public List<Post> queryPostByType(String type) {
        List<Post> list = new ArrayList<>();
        String sql = "select post.*,user.unick,user.image from post,user where post.type = ? and post.uid = user.uid";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,type);
            resultSet = pstm.executeQuery();
            while (resultSet.next()){
                Post post = new Post(resultSet);
                list.add(post);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Utils.release(resultSet,pstm,conn);
        }
        return list;
    }

    /**
     * 根据uid查询帖子
     * @param uid
     * @return
     */
    @Override
    public List<Post> queryPostByUid(String uid) {
        List<Post> list = new ArrayList<>();
        String sql = "select post.*,user.unick,user.image from post,user where post.uid = ? and post.uid = user.uid";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,uid);
            resultSet = pstm.executeQuery();
            while (resultSet.next()){
                Post post = new Post(resultSet);
                list.add(post);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Utils.release(resultSet,pstm,conn);
        }
        return list;
    }

    /**
     * 通过id查询帖子
     * @param pid
     * @return
     */
    @Override
    public List<Post> queryPostByPid(String pid) {
        List<Post> list = new ArrayList<>();
        String sql = "select post.*,user.unick,user.image from post,user where post.pid = ? and post.uid = user.uid";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,pid);
            resultSet = pstm.executeQuery();
            while (resultSet.next()){
                Post post = new Post(resultSet);
                list.add(post);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Utils.release(resultSet,pstm,conn);
        }
        return list;
    }

    /**
     * 存储帖子信息
     * @param uid
     * @param title
     * @param content
     * @param type
     * @return
     */
    @Override
    public boolean savePost(String uid, String title, String content, String type) {
        String sql = "insert into post(type,uid,title,content,time) values(?,?,?,?,?)";
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = formatter.format(date);
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,type);
            pstm.setString(2,uid);
            pstm.setString(3,title);
            pstm.setString(4,content);
            pstm.setString(5,time);
            int i = pstm.executeUpdate();
            if(i!=0){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Utils.release(resultSet,pstm,conn);
        }
        return false;
    }

    /**
     * 存储一条评论信息
     */
    @Override
    public boolean saveRemarks(String uid, String pid, String text) {
        String sql = "insert into comments(uid,pid,text,time) values(?,?,?,?)";
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = formatter.format(date);
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,uid);
            pstm.setString(2,pid);
            pstm.setString(3,text);
            pstm.setString(4,time);
            int i = pstm.executeUpdate();
            if(i!=0){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Utils.release(resultSet,pstm,conn);
        }
        return false;
    }

    /**
     * 设置帖子的评论数
     * @param pid
     * @param remarks
     * @return
     */
    @Override
    public boolean setRemarks(String pid, String remarks) {
        String sql = "update post set remarks = ? where pid = ?";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,remarks);
            pstm.setString(2,pid);
            int i = pstm.executeUpdate();
            if (i!=0){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Utils.release(resultSet,pstm,conn);
        }
        return false;
    }

    /**
     * 根据帖子的id来查询它的评论
     * @param pid
     * @return
     */
    @Override
    public List<Comments> queryComByPid(String pid) {
        ResultSet resultSet = null;
        List<Comments> list = new ArrayList<Comments>();
        String sql = "select comments.*,user.unick,user.image from comments,user where pid = ? and comments.uid = user.uid";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,pid);
            resultSet = pstm.executeQuery();
            while(resultSet.next()){
                Comments comm = new Comments(resultSet);
                list.add(comm);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Utils.release(resultSet,pstm,conn);
        }
        return list;
    }

    /**
     * 根据帖子的id来删除所有的评论
     * @param pid
     * @return
     */
    @Override
    public boolean deCommentByPid(String pid) {
        conn = C3P0Utils.getConnection();
        String sql = "delete from comments where pid = ?";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,pid);
            int i = pstm.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Utils.release(null,pstm,conn);
        }
        return false;
    }

    /**
     * 删除帖子
     * @param pid
     * @return
     */
    @Override
    public boolean deletePost(String pid) {
        conn = C3P0Utils.getConnection();
        String sql = "delete from post where pid = ?";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,pid);
            int i = pstm.executeUpdate();
            if (i!=0) return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Utils.release(null,pstm,conn);
        }
        return false;
    }
}
