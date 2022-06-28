package com.datingsite.service;

import com.datingsite.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface UserService {
    /*
    根据id查询用户，并返回User对象
     */
    User queryUser(String uid,String role);

    /**
     *
     * @param username
     * @return true存在 false不存在
     * @throws SQLException
     */
    boolean checkedUser(String username) throws SQLException;

    /**
     * 注册的业务逻辑
     * @param
     * @return  插入数据影响的行数
     */
    boolean registerUser(String username,String email,String password);

    /**
     * 检验密码是否正确
     * @param username
     * @param password
     * @return
     */
    boolean checkPw(String username,String password) throws SQLException;

    User creatUser(String username);
    String queryUserByTag(String tag) throws SQLException;
    boolean setUserCheck(String uid,String result);
    String queryUserByCode(String page);

}
