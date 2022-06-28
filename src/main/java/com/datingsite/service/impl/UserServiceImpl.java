package com.datingsite.service.impl;

import com.datingsite.dao.UserDao;
import com.datingsite.dao.impl.UserDaoImpl;
import com.datingsite.entity.User;
import com.datingsite.service.UserService;
import com.google.gson.Gson;
import net.sf.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    /**
     *
     * @param uid
     * @return
     */
    public  User queryUser(String uid,String role){
        ResultSet resultSet = null;
        User user = null;
        UserDao userDao = new UserDaoImpl();
        //调用UserDao的方法，获取用户信息的结果集
        user = userDao.queryUserById(uid,role);
        return user;
    }

    /**
     * 查询用户名是否存在
     * @param username
     * @return true存在，false不存在
     * @throws SQLException
     */
    @Override
    public boolean checkedUser(String username) throws SQLException {
        UserDao userDao = new UserDaoImpl();
        User user = userDao.queryUserByName(username);
        //如果查询的结果集不为空，就说明用户名已经存在，返回true
        if(user!=null){
            return true;
        }
        return false;
    }

    /**
     * 注册业务
     * @param username
     * @param email
     * @param password
     * @return 注册成功返回true 失败返回false
     */
    @Override
    public boolean registerUser(String username, String email, String password) {
        User user = new User();
        user.setUnick(username);
        user.setEmail(email);
        user.setPassword(password);
        UserDao userDao = new UserDaoImpl();
        boolean flag = userDao.registerUser(user);
        if(flag){
            return true;
        }
        return false;
    }

    /**
     * 检验密码是否正确
     * @param username
     * @param password
     * @return 如果正确返回true否则返回false
     */
    @Override
    public boolean checkPw(String username, String password) throws SQLException {
        UserDao userDao = new UserDaoImpl();
        User user = userDao.queryUserByName(username);
        if(password.equals(user.getPassword())){
            return true;
        }
        return false;
    }

    /**
     * 生成用户实体类对象
     * @param username
     * @return
     */
    public  User creatUser(String username){
        ResultSet resultSet = null;
        User user = null;
        UserDao userDao = new UserDaoImpl();
        //调用UserDao的方法，获取用户信息的结果集
        user = userDao.queryUserByName(username);
        return user;
    }

    /**
     * 根据标签找好友
     * @return
     */
    @Override
    public String queryUserByTag(String tag) throws SQLException {
        UserDao userDao = new UserDaoImpl();
        List<User> list = userDao.queryUserByTag(tag);
        JSONObject jsonObject = new JSONObject();
        for(int i=1;i<=list.size();i++){
            Gson gson = new Gson();
            String json = gson.toJson(list.get(i-1));
            String name = "user"+Integer.toString(i);
            jsonObject.put(name,json);
        }
        return jsonObject.toString();
    }

    @Override
    public boolean setUserCheck(String uid, String result) {
        UserDao userDao = new UserDaoImpl();
        String code = null;
        if(result.equals("agree")) code = "1";
        else code = "0";
        boolean flag = userDao.setUserCode(uid, code);
        return flag;
    }

    @Override
    public String queryUserByCode(String page) {
        UserDao userDao = new UserDaoImpl();
        int num = Integer.parseInt(page)-1;
        List<User> list = userDao.queryUserByCode(num);
        JSONObject jsonObject = new JSONObject();
        for(int i= 1;i<=list.size();i++){
            Gson gson = new Gson();
            jsonObject.put("user"+i,gson.toJson(list.get(i-1)));
        }
        return jsonObject.toString();
    }
}
