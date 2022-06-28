package com.datingsite.dao.impl;

import com.datingsite.dao.UserDao;
import com.datingsite.entity.User;
import com.datingsite.service.CodeService;
import com.datingsite.utils.C3P0Utils;
import net.sf.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private Connection conn = null;
    private PreparedStatement pstm = null;
    private ResultSet resultSet = null;

    /**
     * 查询首页展示的用户的信息
     * @return
     * @throws SQLException
     */
    public List<User> showUser(){
        List<User> list = new ArrayList<User>();
        String sql = "select uid,unick,sex,introduce,image from user";
        conn = C3P0Utils.getConnection();
        try {
            pstm = conn.prepareStatement(sql);
            resultSet = pstm.executeQuery();
            while(resultSet.next()){
                User user = new User(resultSet,"visitor");
                list.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Utils.release(resultSet,pstm,conn);
        }
        return list;
    }

    /**
     * 根据uid查询用户的所有信息
     * @param uid
     * @return 查询结果集
     */
    public User queryUserById(String uid,String role) {
        User user = null;
        conn = C3P0Utils.getConnection();
        String sql = "select * from user where uid = ?";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,uid);
            resultSet = pstm.executeQuery();
            resultSet.next();
            if(role.equals("visitor")){
                user = new User(resultSet,"visitor");
            }else{
                user = new User(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Utils.release(resultSet,pstm,conn);
        }
        return user;
    }


    /**
     * 根据用户名来查询用户
     * @param username
     * @return 查询结果集
     */
    @Override
    public User queryUserByName(String username) {
        ResultSet resultSet = null;
        Connection conn = C3P0Utils.getConnection();
        String sql = "select * from user where unick = ?";
        User user = null;
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,username);
            resultSet = pstm.executeQuery();
            if(resultSet.next()){
                user = new User(resultSet);
                return user;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Utils.release(resultSet,pstm,conn);
        }
        return user;
    }

    //注册
    @Override
    public boolean registerUser(User user) {
        Connection conn = C3P0Utils.getConnection();
        String sql = "insert into user(unick,email,password,role,image) values(?,?,?,?,?)";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,user.getUnick());
            pstm.setString(2,user.getEmail());
            pstm.setString(3,user.getPassword());
            pstm.setString(4,"0");
            pstm.setString(5,"img/default.png");
            //如果成功写入，返回true
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
     * 通过标签来查询用户
     * @param tag
     * @return
     */
    @Override
    public List<User> queryUserByTag(String tag) {
        List<User> list = new ArrayList<>();
        conn = C3P0Utils.getConnection();
        String sql = "select * from user,userlabel where user.uid = userlabel.uid and userlabel.lid = ?";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,tag);
            resultSet = pstm.executeQuery();
            while(resultSet.next()){
                User user  = new User(resultSet,"visitor");
                list.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Utils.release(resultSet,pstm,conn);
        }
        return list;
    }

    /**
     * 查询用户的标签
     * @param uid
     * @return
     */
    @Override
    public JSONObject queryUserTag(String uid) {
        Connection conn = C3P0Utils.getConnection();
        String sql = "select label.name from label,userlabel where label.id = userlabel.lid and userlabel.uid = ?";
        ResultSet resultSet = null;
        PreparedStatement pstm = null;
        JSONObject jsonObject = new JSONObject();
        try {
             pstm =conn.prepareStatement(sql);
             pstm.setString(1,uid);
             resultSet = pstm.executeQuery();
             while(resultSet.next()){
                 jsonObject.put("tag",resultSet.getString("name"));
             }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Utils.release(resultSet,pstm,conn);
        }
        return jsonObject;
    }

    /**
     * 保存用户信息
     * @param uid
     * @param uname
     * @param sex
     * @param school
     * @param birthday
     * @param introduce
     * @return
     */
    @Override
    public boolean saveUserInfo(String uid, String uname, String sex, String school, String birthday, String introduce) {
        conn = C3P0Utils.getConnection();
        String sql = "update user set sex = ?,uname = ?,school = ?,birthday = ?,introduce = ? where uid = ?";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,sex);
            pstm.setString(2,uname);
            pstm.setString(3,school);
            pstm.setString(4,birthday);
            pstm.setString(5,introduce);
            pstm.setString(6,uid);

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
     * 冻结用户
     * @param uid
     * @return
     */
    @Override
    public boolean forzenUser(String uid) {
        conn = C3P0Utils.getConnection();
        String sql = "update user set role = '2' where uid = ?";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,uid);
            int i = pstm.executeUpdate();
            if (i!=0)return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Utils.release(null,pstm,conn);
        }
        return false;
    }

    /**
     * 设置用户的实名认证状态
     * @param uid
     * @param code
     * @return
     */
    @Override
    public boolean setUserCode(String uid, String code) {
        conn = C3P0Utils.getConnection();
        String sql = "update user set code = ? where uid = ?";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,code);
            pstm.setString(2,uid);
            int i = pstm.executeUpdate();
            if(i!=0) return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Utils.release(null,pstm,conn);
        }
        return false;
    }

    /**
     * 查询所有提交实名认证的用户信息
     * @return
     */
    @Override
    public List<User> queryUserByCode(int num) {
        conn = C3P0Utils.getConnection();
        String sql = "select * from user where code = '2' limit ?,5";
        List<User> list = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1,num*5);
            resultSet = pstm.executeQuery();
            while(resultSet.next()){
                User user = new User(resultSet,"admin");
                list.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Utils.release(resultSet,pstm,conn);
        }
        return list;
    }

    @Override
    public boolean updateNameInfo(String uname, String uid) {
        conn = C3P0Utils.getConnection();
        String sql = "update user set uname = ?,code = '2' where uid=?";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,uname);
            pstm.setString(2,uid);
            int i = pstm.executeUpdate();
            if (i!=0)return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Utils.release(null,pstm,conn);
        }
        return false;
    }
}
