package com.datingsite.controller;

import com.datingsite.dao.MessageDao;
import com.datingsite.dao.impl.MessageDaoImpl;
import com.datingsite.service.MessageService;
import com.datingsite.service.impl.MessageServiceImpl;
import net.sf.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/message")
public class MessageController extends BaseServlet{

    /**
     * 获取当前用户的所有申请信息
     * @param request
     * @param response
     * @return
     */
    public String queryRequestMsg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取到当前用户的uid
        String uid = request.getParameter("uid");
        MessageService messageService = new MessageServiceImpl();
        JSONObject jsonObject = null;
        try {
            jsonObject = messageService.queryRequest(uid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(jsonObject!=null){
            response.getWriter().write(jsonObject.toString());
        }
        return "";
    }

    /**
     * 查询用户的所有留言信息
     * @param request
     * @param response
     * @return
     */
    public String queryGuestMsg(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String uid = request.getParameter("uid");
        MessageService messageService = new MessageServiceImpl();
        JSONObject jsonObject = messageService.queryGuest(uid);
        return jsonObject.toString();
    }

    /**
     * 查询用户的所有信息的总数
     * @param request
     * @param response
     * @return
     */
    public String queryAllNum(HttpServletRequest request, HttpServletResponse response){
        String uid = request.getParameter("uid");
        MessageService messageService = new MessageServiceImpl();
        JSONObject jsonObject = messageService.queryNum(uid);
        if(jsonObject!=null)
        return jsonObject.toString();
        return "出现错误";
    }

    /**
     * 将某一类型的消息设置为已读
     * @param request
     * @param response
     * @return
     */
    public String setZero(HttpServletRequest request, HttpServletResponse response){
        //获取信息
        String uid = request.getParameter("uid");
        String type = request.getParameter("type");
        MessageService messageService = new MessageServiceImpl();
        boolean flag = messageService.setZeroService(uid, type);
        if(flag){
            return "1";
        }
        return "0";
    }

    /**
     *
     * @param request
     * @param response
     * @return
     */
    public String getMessage(HttpServletRequest request, HttpServletResponse response){
        return "";
    }


}
