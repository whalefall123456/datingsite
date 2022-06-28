package com.datingsite.service.impl;

import com.datingsite.dao.GuestDao;
import com.datingsite.dao.impl.GuestDaoImpl;
import com.datingsite.service.GuestService;
import net.sf.json.JSONObject;

import java.sql.SQLException;

public class GuestServiceImpl implements GuestService {
    @Override
    public String saveGuestService(String uid,String tid,String text){
        //调用GuestDao来处理存储
        GuestDao guestDao = new GuestDaoImpl();
        boolean flag = false;
        try {
            flag = guestDao.saveGuest(uid,tid,text);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //如果存储成功
        if(flag){
            return "发送成功";
        }
        return "发送失败";
    }
}
