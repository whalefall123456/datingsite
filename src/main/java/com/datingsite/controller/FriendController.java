package com.datingsite.controller;

import com.datingsite.service.FriendService;
import com.datingsite.service.MessageService;
import com.datingsite.service.impl.FriendServiceImpl;
import com.datingsite.service.impl.MessageServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/friend")
public class FriendController extends BaseServlet{

    /**
     * 好友申请
     * @param request
     * @param response
     * @return
     */
    public String addApply(HttpServletRequest request, HttpServletResponse response){
        //获取申请信息
        String uid = request.getParameter("uid");
        String tuid = request.getParameter("tuid");
        String content = request.getParameter("content");
        FriendService friendService = new FriendServiceImpl();
        int flag = friendService.addFriendService(uid,tuid,content);
        if(flag == 0){
            return "请勿重复申请！";
        }else if(flag == 1){
            //消息列表加一
            MessageService messageService = new MessageServiceImpl();
            boolean flag1 = messageService.addMsgNum("renum", tuid);
            return "好友申请已提交！";
        }
        return "出现错误，请稍后再试";
    }

    /**
     * 同意好友申请
     * @param request
     * @param response
     * @return
     */
    public String agreeApply(HttpServletRequest request, HttpServletResponse response){
        String tuid = request.getParameter("tuid");
        String uid = request.getParameter("uid");
        String requestid = request.getParameter("requestid");
        FriendService friendService = new FriendServiceImpl();
        int i = friendService.agreeService(uid, tuid, requestid);
        if(i==1){
            return "操作成功！";
        }
        return "出现问题，请稍后再试！";
    }

    /**
     * 拒绝好友申请
     * @param request
     * @param response
     * @return
     */
    public String rejectApply(HttpServletRequest request, HttpServletResponse response){
        String requestid = request.getParameter("requestid");
        FriendService friendService = new FriendServiceImpl();
        boolean flag = friendService.rejectService(requestid);
        if(flag){
            return "操作成功！";
        }
        return "出现问题，请稍后再试";
    }
}
