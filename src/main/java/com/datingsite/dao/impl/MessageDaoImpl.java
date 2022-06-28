package com.datingsite.dao.impl;

import com.datingsite.dao.MessageDao;
import com.datingsite.entity.FriendRequest;
import com.datingsite.entity.MsgList;
import com.datingsite.utils.C3P0Utils;
import net.sf.json.JSONObject;
import com.alibaba.fastjson.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDaoImpl implements MessageDao {

    /**
     * 查询某人的被申请好友信息
     * @param uid
     * @return
     */
    @Override
    public List<FriendRequest> queryRequest(String uid) {
        List<FriendRequest> list = new ArrayList<>();
        ResultSet resultSet = null;
        Connection conn = C3P0Utils.getConnection();
        String sql = "select friendrequest.*,user.unick,user.image from friendrequest,user where tuid = ? and friendrequest.uid = user.uid and friendrequest.state=0";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,uid);
            resultSet = pstm.executeQuery();
            while(resultSet.next()){
                FriendRequest friendRequest = new FriendRequest(resultSet);
                list.add(friendRequest);
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Utils.release(resultSet,null,conn);
        }
        return list;
    }

    /**
     * 查询留言信息------已经弃用
     * @param uid
     * @return
     */
    @Override
    public ResultSet queryGuest(String uid) {
        Connection conn = C3P0Utils.getConnection();
        //查询留言按时间顺序，或者id，
        String sql = "SELECT guest.*,user.unick,user.image from guest,user WHERE touid = ? and guest.uid = user.uid order by guest.gid";
        ResultSet resultSet = null;
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,uid);
            resultSet = pstm.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    /**
     * 初始化消息列表
     * @param uid
     * @return
     */
    @Override
    public boolean initMsgList(String uid) {
        Connection conn = C3P0Utils.getConnection();
        String sql = "insert into msglist(uid,renum,bbsnum,guenum,frinum) values(?,0,0,0,0)";
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,uid);
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
     * 查询所有的消息数量
     * @param uid
     * @return
     */
    @Override
    public MsgList queryNum(String uid) {
        ResultSet resultSet = null;
        String sql = "select * from msglist where uid = ?";
        Connection conn = C3P0Utils.getConnection();
        JSONObject jsonObject = null;
        MsgList msgList = null;
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,uid);
            resultSet = pstm.executeQuery();
            if(resultSet.next()){
                msgList = new MsgList(resultSet);
            }else{
                boolean flag = initMsgList(uid);
                resultSet = pstm.executeQuery();
                if(resultSet.next()){
                    msgList = new MsgList(resultSet);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Utils.release(resultSet,null,conn);
        }
        return msgList;
    }

    /**
     * 设置为已读
     * @param uid
     * @param type
     * @return
     */
    @Override
    public boolean setZero(String uid,String type){
        Connection conn = C3P0Utils.getConnection();
        String sql = "update msglist set "+type+" = 0 where uid = ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,uid);
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

    /**
     * 设置留言已读
     * @param id
     * @return
     */
    @Override
    public boolean setGuestRead(String id) {
        Connection conn = C3P0Utils.getConnection();
        String sql = "update guest set state = 1 where gid = ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,id);
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

    /**
     * 将某人的消息列表的某个数据加一
     * @param type
     * @param uid
     * @return
     */
    @Override
    public boolean addMsgNum(String type, String uid) {
        Connection conn = C3P0Utils.getConnection();
        String sql = "select * from msglist where uid = "+uid;
        ResultSet resultSet = null;
        Statement stm = null;
        try {
            stm = conn.createStatement();
            resultSet = stm.executeQuery(sql);
            if(resultSet.next()){
                String num = Integer.toString(Integer.valueOf(resultSet.getString(type))+1);
                String sql1 = "update msglist set "+type+" = ? where uid = ?";
                PreparedStatement pstm = conn.prepareStatement(sql1);
                pstm.setString(1,num);
                pstm.setString(2,uid);
                int i = pstm.executeUpdate();
                if (i!=0){
                    return true;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Utils.release(resultSet,stm,conn);
        }
        return false;
    }



}
