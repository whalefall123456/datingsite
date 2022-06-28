package com.datingsite.controller;


import com.datingsite.dao.UserDao;
import com.datingsite.dao.impl.UserDaoImpl;
import com.datingsite.entity.User;
import com.datingsite.utils.JsonUtils;
import com.google.gson.Gson;
import net.sf.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/index")
public class IndexController extends BaseServlet {
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
}
