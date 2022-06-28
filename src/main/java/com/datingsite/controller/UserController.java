package com.datingsite.controller;

import com.datingsite.dao.UserDao;
import com.datingsite.dao.impl.UserDaoImpl;
import com.datingsite.entity.User;
import com.datingsite.service.CodeService;
import com.datingsite.service.MessageService;
import com.datingsite.service.UserService;
import com.datingsite.service.impl.CodeServiceImpl;
import com.datingsite.service.impl.MessageServiceImpl;
import com.datingsite.service.impl.UserServiceImpl;
import com.datingsite.utils.*;

import com.google.gson.Gson;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/user")
public class UserController extends BaseServlet{

    public void showUser(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        UserDao userDao = new UserDaoImpl();
        List<User> list = userDao.showUser();
        JSONObject jsonObject = new JSONObject();
        for(int i=1;i<=list.size();i++){
            Gson gson = new Gson();
            String json = gson.toJson(list.get(i-1));
            jsonObject.put("user"+ i,json);
        }
        //System.out.println(object.toString());  //调用toString方法将json对象转换成json字符串
        //或者
        response.getWriter().write(jsonObject.toString());
    }

    public String userInfo(HttpServletRequest request, HttpServletResponse response){
        String uid = request.getParameter("uid");
        UserService userService = new UserServiceImpl();
        //获取user的实体类
        User user = userService.queryUser(uid,"visitor");
        Gson gson = new Gson();
        String str = gson.toJson(user);
//        request.setAttribute("user",user);
        //去到该用户的信息展示页
        return str;
    }

    public String myselfInfo(HttpServletRequest request, HttpServletResponse response){
        String uid = request.getParameter("uid");
        UserService userService = new UserServiceImpl();
        //获取user的实体类
        User user = userService.queryUser(uid,"self");
        Gson gson = new Gson();
        String str = gson.toJson(user);
        return str;
    }

    //检查用户名是否已经存在
    public String check(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        //1、获取用户名
        String username = request.getParameter("username");
        if (username == null) {
            return Constants.HAS_USER;//不能注册
        }
        //2、调用业务逻辑判断用户是否存在
        UserService userService = new UserServiceImpl();
        boolean flag = userService.checkedUser(username);
        //3、响应字符串 1存在 0 不存在
        if(flag){
            //用户存在
            return Constants.HAS_USER;
        }
        return Constants.NOT_HAS_USER;
    }

    //发送邮箱验证码
    public String sendCode(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        CodeService codeService = new CodeServiceImpl();
        String code  = codeService.saveCodeService(email);
        //如果成功写入数据库就发送信息
        if(code!=null){
            EmailUtils.sendEmail(username,email,code);
            return "1";
        }
        return "0";
    }

    //检验验证码
    public String checkCode(HttpServletRequest request, HttpServletResponse response){
        String email = request.getParameter("email");
        String code  = request.getParameter("code");
        CodeService codeService = new CodeServiceImpl();
        boolean flag=false;
        try {
            flag = codeService.checkCode(email,code);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(flag){
            return "1";
        }
        return "0";
    }

    //注册逻辑
    public String userRegister(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserService userService = new UserServiceImpl();
        boolean flag = userService.registerUser(username,email,password);
        if(flag){
            //初始化消息列表
            UserDao userDao = new UserDaoImpl();
            User user = userDao.queryUserByName(username);
            MessageService messageService = new MessageServiceImpl();
            if(user!=null){
                boolean b = messageService.initMsgList(Integer.toString(user.getUid()));
                if(b){
                    return "注册成功，请登录";
                }
            }
        }
        return "注册失败，请稍后再试";
    }

    //登录逻辑
    public String login(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //调用登录业务
        UserService userService = new UserServiceImpl();
        boolean flag = userService.checkPw(username,password);
        //如果密码验证成功
        if(flag){
            User user = userService.creatUser(username);
            //如果用户是被冻结的用户则提示被冻结
            if(user.getRole().equals("2")){
                return "2";
            }
            session.setAttribute("user",user);
            //存储cookie信息
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username",username);
            jsonObject.put("password",password);
            jsonObject.put("uid",user.getUid());
            jsonObject.put("role",user.getRole());
            String content1 = jsonObject.toString();
            //String content = username+Constants.FLAG+password;
            //content = Base64Utils.encode(content);
            content1 = Base64Utils.encode(content1);
            Cookie cookie = new Cookie(Constants.AUTO_NAME,content1);
            cookie.setPath("/");
            cookie.setMaxAge(14*24*60*60);
            response.addCookie(cookie);
//            Thread thread = new TestThread(username);
//            thread.start();
            if(user.getCode().equals("0"))return "3";
            return "1";
        }else{
            return "0";
        }
    }

    //获取根据标签找用户
    public String findUserByTag(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String tag = request.getParameter("tag");
        UserService userService = new UserServiceImpl();
        String json = userService.queryUserByTag(tag);
        return json;
    }

    //获取用户标签
    public String getTag(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String uid = request.getParameter("uid");
        UserDao userDao = new UserDaoImpl();
        JSONObject jsonObject = userDao.queryUserTag(uid);
        return jsonObject.toString();
    }

    //保存用户信息
    public String saveInfo(HttpServletRequest request, HttpServletResponse response){
        String uid = request.getParameter("uid");
        String uname = request.getParameter("uname");
        String sex = request.getParameter("sex");
        String birthday = request.getParameter("birthday");
        String school = request.getParameter("school");
        String introduce = request.getParameter("introduce");
        UserDao userDao = new UserDaoImpl();
        boolean flag = userDao.saveUserInfo(uid, uname, sex, school, birthday, introduce);
        if (flag){
            return "信息修改成功";
        }
        return "发生错误，请稍后再试！";
    }

    //冻结用户
    public String frozenUser(HttpServletRequest request, HttpServletResponse response){
        String uid = request.getParameter("uid");
        UserDao userDao = new UserDaoImpl();
        boolean flag = userDao.forzenUser(uid);
        if(flag)return "操作成功";
        return "出现问题！请稍后再试";
    }

    //获取用户信息
    public String getuserbyname(HttpServletRequest request, HttpServletResponse response){
        String unick = request.getParameter("unick");
        UserDao userDao = new UserDaoImpl();
        User user = userDao.queryUserByName(unick);
        Gson gson = new Gson();
        String str = gson.toJson(user);
        return str;
    }

    //获取用户实名认证信息
    public String getUserNameInfo(HttpServletRequest request, HttpServletResponse response){
        String page = request.getParameter("page");
        UserService userService = new UserServiceImpl();
        String s = userService.queryUserByCode(page);
        return s;
    }

    //实名认证
    public String checkuname(HttpServletRequest request, HttpServletResponse response){
        String uid = request.getParameter("uid");
        String result = request.getParameter("result");
        UserService userService = new UserServiceImpl();
        boolean flag = userService.setUserCheck(uid, result);
        if(flag){
            return "操作成功";
        }
        return "出现问题，请稍后再试";
    }

    //用户提交实名信息
    public String updateNameinfo(HttpServletRequest request, HttpServletResponse response){
        String uname = request.getParameter("uname");
        String uid = request.getParameter("uid");
        UserDao userDao = new UserDaoImpl();
        boolean b = userDao.updateNameInfo(uname, uid);
        if (b) return "提交成功";
        return "出现错误，请稍后再试";
    }

}
