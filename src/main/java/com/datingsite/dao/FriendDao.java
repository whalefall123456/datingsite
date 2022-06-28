package com.datingsite.dao;

import java.sql.ResultSet;

public interface FriendDao {
    /**
     * 插入一条申请数据
     * @param uid
     * @param tuid
     * @param content
     * @return
     */
    boolean addRequest(String uid,String tuid,String content);

    /**
     * 查询某两个人之间的申请信息
     * @param uid
     * @param tuid
     * @return
     */
    boolean queryRequest(String uid,String tuid);

    /**
     * 更改好友申请的状态
     * @param requestid
     * @return
     */
    boolean updateState(String requestid,int state);

    /**
     * 向数据库中插入好友记录
     * @param uid
     * @param tuid
     * @return
     */
    boolean addFriendLog(String uid,String tuid,String fname);
}
