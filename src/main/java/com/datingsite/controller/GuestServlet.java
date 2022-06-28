package com.datingsite.controller;

import com.datingsite.dao.GuestDao;
import com.datingsite.dao.impl.GuestDaoImpl;
import com.datingsite.service.GuestService;
import com.datingsite.service.impl.GuestServiceImpl;
import com.datingsite.service.impl.MessageServiceImpl;
import net.sf.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 留言控制
 */
@WebServlet("/guest")
public class GuestServlet extends BaseServlet {
    public void sendGuest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取前端传来的参数
        String uid = request.getParameter("uid");
        String touid = request.getParameter("tuid");
        String content = request.getParameter("text");
        //调用留言事务处理
        GuestService guestService = new GuestServiceImpl();
        String msg = guestService.saveGuestService(uid, touid, content);
        //将消息数加一
        MessageServiceImpl messageService = new MessageServiceImpl();
        messageService.addMsgNum("guenum",touid);
        //发送提示信息
        JSONObject object = new JSONObject();
        object.put("text", msg);
        response.getWriter().write(object.toString());
    }
}
