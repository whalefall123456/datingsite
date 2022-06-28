package com.datingsite.service.impl;

import com.datingsite.dao.GuestDao;
import com.datingsite.dao.MessageDao;
import com.datingsite.dao.UserDao;
import com.datingsite.dao.impl.GuestDaoImpl;
import com.datingsite.dao.impl.MessageDaoImpl;
import com.datingsite.dao.impl.UserDaoImpl;
import com.datingsite.entity.FriendRequest;
import com.datingsite.entity.Guest;
import com.datingsite.entity.MsgList;
import com.datingsite.service.FriendService;
import com.datingsite.service.MessageService;
import com.datingsite.utils.JsonUtils;
import com.google.gson.Gson;
import net.sf.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;

public class MessageServiceImpl implements MessageService {
    /**
     * 查询好友申请信息
     * @param uid
     * @return
     * @throws SQLException
     */
    @Override
    public JSONObject queryRequest(String uid) throws SQLException {
        MessageDao messageDao = new MessageDaoImpl();
        //查询所有的信息
        List<FriendRequest> list = messageDao.queryRequest(uid);
        JSONObject jsonObject = new JSONObject();
        for(int i=1;i<=list.size();i++){
            Gson gson = new Gson();
            String json = gson.toJson(list.get(i-1));
            jsonObject.put("msg"+i,json);
        }
        //调用工具类，将数据转为json格式
        return jsonObject;
    }

    /**
     * 查询留言信息
     * @param uid
     * @return
     */
    @Override
    public JSONObject queryGuest(String uid) throws SQLException {
        GuestDao guestDao = new GuestDaoImpl();
        JSONObject jsonObject = new JSONObject();
        List<Guest> list = guestDao.queryGuestByUid(uid);
        for(int i = 1;i<=list.size();i++){
            Gson gson = new Gson();
            String json = gson.toJson(list.get(i-1));
            jsonObject.put("guest"+i,json);
        }
        return jsonObject;
    }

    /**
     * 初始化消息列表
     * @param uid
     * @return
     */
    @Override
    public boolean initMsgList(String uid) {
        MessageDao messageDao = new MessageDaoImpl();
        boolean flag = messageDao.initMsgList(uid);
        return flag;
    }

    /***
     * 查询所有的消息数量
     * @param uid
     * @return
     */
    @Override
    public JSONObject queryNum(String uid) {
        MessageDao messageDao = new MessageDaoImpl();
        MsgList msgList = messageDao.queryNum(uid);
        JSONObject jsonObject = new JSONObject();
            if(msgList!=null){
                jsonObject.put("renum", msgList.getRenum());
                jsonObject.put("bbsnum", msgList.getBbsnum());
                jsonObject.put("guenum", msgList.getGuenum());
                jsonObject.put("frinum", msgList.getFrinum());
                return jsonObject;
            }
        return null;
    }

    /**
     * 将某类型消息设为已读
     * @param uid
     * @param type
     * @return
     */
    @Override
    public boolean setZeroService(String uid, String type) {
        MessageDao messageDao = new MessageDaoImpl();
        boolean flag = messageDao.setZero(uid, type);
        return flag;
    }

    /**
     * 将某一类型消息加一
     * @param type
     * @param uid
     * @return
     */
    @Override
    public boolean addMsgNum(String type, String uid) {
        //查询某人的消息列表是否存在
        MessageDao messageDao1 = new MessageDaoImpl();
        MsgList msgList = messageDao1.queryNum(uid);
        if(msgList==null){
            //如果为空说明消息列表没有初始化，则进行初始化
            initMsgList(uid);
        }
        MessageDao messageDao = new MessageDaoImpl();
        boolean flag = messageDao.addMsgNum(type, uid);
        return flag;
    }
}
