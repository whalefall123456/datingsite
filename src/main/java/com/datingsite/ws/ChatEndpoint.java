package com.datingsite.ws;

import com.datingsite.entity.ChatMsg;
import com.datingsite.entity.User;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/chat")
public class ChatEndpoint {

    //用来存储每一个客户端对应的ChatEndpoint对象
    private static Map<String,ChatEndpoint> onlineUser = new ConcurrentHashMap<>();

    //声明session对象，通过该对象可以发消息给指定用户
    private Session session;

    //声明一个httpsession对象，取用户名
    private HttpSession httpSession;

    @OnOpen
    //建立连接时执行
    public void onOpen(Session session, EndpointConfig config){
        //将局部session对象，赋值给成员session
        this.session = session;
        //获取httpsession对象
        HttpSession httpsession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        this.httpSession = httpsession;

        //从httpsession获取用户名
        User user = (User) httpsession.getAttribute("user");
        String uid = Integer.toString(user.getUid());
        //将当前对象存储到容器
        onlineUser.put(uid,this);
        //将当前在线用户推送给客户端
        //未赋值
        String message = new ChatMsg().toString();
    }

    //将消息推送给所有用户
    public void broadcastAllUsers(String message){
        Set<String> names = onlineUser.keySet();
        for(String name : names){
            ChatEndpoint chatEndpoint = onlineUser.get(name);
            try {
                chatEndpoint.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private Set<String> getNames(){
        return onlineUser.keySet();
    }

    @OnMessage
    //接收到客户端的数据时调用
    public void onMessage(String message,Session session){

    }

    @OnClose
    //连接关闭时使用
    public void onClose(Session session){

    }
}
