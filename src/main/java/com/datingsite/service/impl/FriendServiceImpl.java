package com.datingsite.service.impl;

import com.datingsite.dao.FriendDao;
import com.datingsite.dao.UserDao;
import com.datingsite.dao.impl.FriendDaoImpl;
import com.datingsite.dao.impl.UserDaoImpl;
import com.datingsite.entity.User;
import com.datingsite.service.FriendService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FriendServiceImpl implements FriendService {
    @Override
    public int addFriendService(String uid, String tuid, String content) {
        //首先查询是否已经申请过
        FriendDao friendDao = new FriendDaoImpl();
        boolean flag = friendDao.queryRequest(uid, tuid);
        boolean flag1 = friendDao.queryRequest(tuid,uid);
        //如果存在记录，返回0
        if(flag || flag1){
            return 0;
        }else{
            boolean b = friendDao.addRequest(uid, tuid, content);
            //如果成功插入记录，返回1
            if(b){
                return 1;
            }
        }
        //代表发生错误，返回2
        return 2;
    }


    /**
     * 同意好友申请的一系列操作
     * @param uid 申请人
     * @param tuid 被申请人（当前用户）
     * @param requestid
     * @return
     */
    @Override
    public int agreeService(String uid, String tuid, String requestid) {
        //修改申请状态为同意1
        FriendDao friendDao = new FriendDaoImpl();
        boolean flag = friendDao.updateState(requestid,1);
        //如果修改成功继续完成后续操作
        if(flag){
            //查询用户名
            UserDao userDao = new UserDaoImpl();
            User user1 = userDao.queryUserById(uid,"visitor");
            User user2 = userDao.queryUserById(tuid,"visitor");
                String username1 = user1.getUnick();
                String username2 = user2.getUnick();
                boolean flag1 = friendDao.addFriendLog(uid,tuid,username2);
                boolean flag2 = friendDao.addFriendLog(tuid,uid,username1);
                if(flag1 && flag2){
                    return 1;
                }
        }
        return 0;
    }

    /**
     * 拒绝好友申请的业务
     * @param requestid 申请id
     * @return
     */
    @Override
    public boolean rejectService(String requestid){
        FriendDao friendDao = new FriendDaoImpl();
        boolean flag = friendDao.updateState(requestid, 2);
        return flag;
    }


}
