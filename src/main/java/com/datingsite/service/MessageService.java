package com.datingsite.service;

import net.sf.json.JSONObject;

import java.sql.SQLException;

public interface MessageService {
    /**
     * 查询用户的所有好友申请信息
     * @param uid
     * @return 返回Json对象
     * @throws SQLException
     */
    JSONObject queryRequest(String uid) throws SQLException;

    /**
     * 查询某人的留言
     * @param uid
     * @return
     * @throws SQLException
     */
    JSONObject queryGuest(String uid) throws SQLException;

    //初始化消息列表
    boolean initMsgList(String uid);

    /**
     * 查询用户的所有消息的数量
     * @param uid
     * @return
     */
    JSONObject queryNum(String uid);

    /**
     * 将某一消息设置为已读
     * @param uid
     * @param type
     * @return
     */
    boolean setZeroService(String uid,String type);

    /**
     * 将某人的某种消息加一
     * @param type
     * @param uid
     * @return
     */
    boolean addMsgNum(String type,String uid);

}
