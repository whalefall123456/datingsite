package com.datingsite.dao;

import com.datingsite.entity.User;
import net.sf.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    List<User> showUser() throws SQLException;
    User queryUserById(String sql,String role);
    User queryUserByName(String username);
    boolean registerUser(User user);
    List<User> queryUserByTag(String tag);
    JSONObject queryUserTag(String uid);
    boolean saveUserInfo(String uid,String uname,String sex,String school,String birthday,String introduce);
    boolean forzenUser(String uid);
    boolean setUserCode(String uid,String code);
    List<User> queryUserByCode(int num);
    boolean updateNameInfo(String uname,String uid);
}
