package com.datingsite.dao.impl;

import com.datingsite.dao.FriendDao;
import com.datingsite.utils.C3P0Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FriendDaoImpl implements FriendDao {
    Connection conn = null;
    PreparedStatement pstm = null;
    ResultSet resultSet = null;

    /**
     * 向数据库的friendrequest表中写入一条数据
     * @param uid
     * @param tuid
     * @param content
     * @return 成功返回true 失败返回false
     */
    @Override
    public boolean addRequest(String uid, String tuid, String content) {
        conn = C3P0Utils.getConnection();
        String sql = "insert into friendrequest(uid,tuid,retime,content) values(?,?,?,?)";
        try {
            pstm = conn.prepareStatement(sql);
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = formatter.format(date);
            pstm.setString(1,uid);
            pstm.setString(2,tuid);
            pstm.setString(3,time);
            pstm.setString(4,content);
            int i = pstm.executeUpdate();
            if(i!=0){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Utils.release(null,pstm,conn);
        }
        return false;
    }

    /**
     * 查询某两个人之间的申请信息
     * @param uid
     * @param tuid
     * @return 如果存在未处理记录则返回true否则返回false
     */
    @Override
    public boolean queryRequest(String uid, String tuid) {
        conn = C3P0Utils.getConnection();
        String sql = "select * from friendrequest where uid = ? and tuid = ? and state = 0";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,uid);
            pstm.setString(2,tuid);
            ResultSet resultSet = pstm.executeQuery();
            if(resultSet.next()){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Utils.release(null,pstm,conn);
        }
        return false;
    }


    /**
     * 根据id来修改好友的申请状态
     * @param requestid
     * @return
     */
    @Override
    public boolean updateState(String requestid,int state) {
        Connection conn = C3P0Utils.getConnection();
        String sql = "update friendrequest set state = ? where id = ?";
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1,state);
            pstm.setString(2,requestid);
            int i = pstm.executeUpdate();
            if(i == 1){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Utils.release(null,null,conn);
        }
        return false;
    }

    /**
     * 插入好友记录
     * @param uid
     * @param tuid
     * @return
     */
    @Override
    public boolean addFriendLog(String uid, String tuid,String fname) {
        Connection conn = C3P0Utils.getConnection();
        String sql = "insert into friend(uid,fuid,fname) values(?,?,?)";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,uid);
            pstm.setString(2,tuid);
            pstm.setString(3,fname);
            int i = pstm.executeUpdate();
            if(i!=0){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Utils.release(null,null,conn);
        }
        return false;
    }
}
