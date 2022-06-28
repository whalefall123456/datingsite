package com.datingsite.dao.impl;

import com.datingsite.dao.CodeDao;
import com.datingsite.utils.C3P0Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CodeDaoImpl implements CodeDao {
    //存储验证码
    @Override
    public boolean saveCode(String email,String code) {
        int flag =0;
        Connection conn = C3P0Utils.getConnection();
        String sql = "insert into code(email,codetime,code) values(?,?,?)";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = formatter.format(date);
            pstm.setString(1,email);
            pstm.setString(2,time);
            pstm.setString(3,code);
            flag = pstm.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Utils.release(null,null,conn);
        }
        if(flag!=0){
            return true;
        }
        return false;
    }

    //验证验证码
    @Override
    public String queryCode(String email) {
        Connection conn = C3P0Utils.getConnection();
        String sql = "select * from code where email = ? order by cid DESC limit 1";
        ResultSet resultSet = null;
        String code = null;
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,email);
            resultSet = pstm.executeQuery();
            resultSet.next();
            code = resultSet.getString("code");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return code;
    }



}
