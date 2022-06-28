package com.datingsite.dao.impl;

import com.datingsite.dao.GuestDao;
import com.datingsite.entity.Guest;
import com.datingsite.utils.C3P0Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GuestDaoImpl implements GuestDao {
    private Connection conn = null;
    private ResultSet resultSet = null;
    PreparedStatement pstm = null;

    @Override
    public boolean saveGuest(String uid, String tid, String text) throws SQLException {
        String sql ="insert into guest(uid,touid,text,time) value(?,?,?,?)";
        conn = C3P0Utils.getConnection();
        pstm = conn.prepareStatement(sql);
        pstm.setString(1,uid);
        pstm.setString(2,tid);
        pstm.setString(3,text);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = formatter.format(date);
        pstm.setString(4,time);
        int i = pstm.executeUpdate();
        C3P0Utils.release(resultSet,pstm,conn);
        if(i!=0){
            return true;
        }
        return false;
    }

    @Override
    public List<Guest> queryGuestByUid(String uid) {
        List<Guest> list = new ArrayList<Guest>();
        String sql = "select guest.*,user.unick,user.image from guest,user where touid = ? and guest.uid = user.uid";
        conn = C3P0Utils.getConnection();
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,uid);
            resultSet = pstm.executeQuery();
            while(resultSet.next()){
                Guest guest = new Guest(resultSet);
                list.add(guest);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Utils.release(resultSet,pstm,conn);
        }
        return list;
    }

}
