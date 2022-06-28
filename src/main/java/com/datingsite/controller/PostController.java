package com.datingsite.controller;

import com.datingsite.service.MessageService;
import com.datingsite.service.PostService;
import com.datingsite.service.impl.MessageServiceImpl;
import com.datingsite.service.impl.PostServiceImpl;
import net.sf.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 论坛控制
 */

@WebServlet("/post")
public class PostController extends BaseServlet{

    /**
     *根据帖子的类型来查询帖子数据
     */
    public String queryPostByType(HttpServletRequest request, HttpServletResponse response){
        String type = request.getParameter("type");
        PostService postService = new PostServiceImpl();
        String json = postService.queryPostByType(type);
        return json;
    }

    /**
     * 根据uid来查询该用户的帖子
     */
    public String queryPostByUid(HttpServletRequest request, HttpServletResponse response){
        String uid = request.getParameter("uid");
        PostService postService = new PostServiceImpl();
        String json = postService.queryPostByUid(uid);
        return json;
    }

    /**
     * 根据帖子的id来查帖子
     * @param request
     * @param response
     * @return
     */
    public String queryPostByPid(HttpServletRequest request, HttpServletResponse response){
        String pid = request.getParameter("pid");
        PostService postService = new PostServiceImpl();
        String json = postService.queryPostByPid(pid);
        return json;
    }

    /**
     * 用户发帖
     */
    public String savePost(HttpServletRequest request, HttpServletResponse response){
        //获取必要的参数
        String uid = request.getParameter("uid");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String type = request.getParameter("type");
        PostService postService = new PostServiceImpl();
        String text = postService.savePostService(uid, title, content, type);
        JSONObject json = new JSONObject();
        json.put("text",text);
        return json.toString();
    }

    /**
     * 对帖子进行评论
     */

    public String sendReamrks(HttpServletRequest request, HttpServletResponse response){
        /*
        1、向数据库中写入一条评论数据
        2、给该帖子的remarks数量加1
        3、向该用户的消息表bbsnum加1
         */
        //获取数据
        String uid = request.getParameter("uid");
        String pid = request.getParameter("pid");
        String text = request.getParameter("content");
        String puid = request.getParameter("puid");
        //1、向数据库中写入一条评论数据
        PostService postService = new PostServiceImpl();
        boolean flag = postService.sendRemarksService(uid, pid, text);
        if(flag){
            //2、给该帖子的remarks数量加1
            boolean flag1 = postService.postAddRemarks(pid);
            if(flag1){
                //3、向该用户的消息表bbsnum加1
                MessageService messageService = new MessageServiceImpl();
                //先查询用户的消息列表是否初始化,如果没有就先初始化
                JSONObject jsonObject = messageService.queryNum(puid);
                if(jsonObject == null){
                    //初始化
                    boolean flag2 = messageService.initMsgList(puid);
                    if(flag2){
                        messageService.addMsgNum("bbsnum",puid);
                        return "评论发布成功！";
                    }
                }
                messageService.addMsgNum("bbsnum",puid);
                return "评论发布成功！";
            }else{
                return "评论失败，请稍后再试";
            }
        }
        return "评论失败，请稍后再试";
    }

    /**
     * 获取帖子的评论信息
     */
    public String getComments(HttpServletRequest request, HttpServletResponse response){
        String pid = request.getParameter("pid");
        PostService postService =new PostServiceImpl();
        String json = postService.queryCommByPid(pid);
        return json;
    }

    public String deletepost(HttpServletRequest request, HttpServletResponse response){
        String pid = request.getParameter("pid");
        PostService postService = new PostServiceImpl();
        boolean flag = postService.depostService(pid);
        if(flag) return "操作成功";
        return "出现问题，请稍后再试";
    }

}
