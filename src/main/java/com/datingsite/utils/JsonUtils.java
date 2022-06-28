package com.datingsite.utils;

import net.sf.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JsonUtils {
    /**
     * 将用户信息转为Json
     * @param re
     * @return
     * @throws SQLException
     */
    public static JSONObject userJson(ResultSet re) throws SQLException {
        JSONObject jsonObject = new JSONObject();
        int i=1;
        while(re.next()){
            JSONObject json = new JSONObject();
            json.put("uid",re.getString("uid"));
            json.put("unick",re.getString("unick"));
            json.put("sex",re.getString("sex"));
            json.put("introduce",re.getString("introduce"));
            json.put("img",re.getString("image"));
            jsonObject.put("user"+i,json);
            i++;
        }
        return jsonObject;
    }

    /**
     * 将好友申请信息转为json
     * @return
     */
    public static JSONObject requestMsgJson(ResultSet re) throws SQLException {
        JSONObject jsonObject = new JSONObject();
        int i=1;
        while (re.next()){
            JSONObject json = new JSONObject();
            json.put("requestid",re.getString("id"));
            json.put("uid",re.getString("uid"));
            json.put("retime",re.getString("retime"));
            json.put("content",re.getString("content"));
            json.put("state",re.getString("state"));
            json.put("unick",re.getString("unick"));
            json.put("img",re.getString("image"));
            jsonObject.put("msg"+i,json);
            i++;
        }
        return jsonObject;
    }

    /**
     * 将留言信息转为json
     * @param re
     * @return
     */
    public static JSONObject guestMsgJson(ResultSet re)throws SQLException{
        JSONObject jsonObject = new JSONObject();
        int i=1;
        while (re.next()){
            JSONObject json = new JSONObject();
            json.put("gid",re.getString("gid"));
            json.put("uid",re.getString("uid"));
            json.put("time",re.getString("time"));
            json.put("content",re.getString("text"));
            json.put("state",re.getString("state"));
            json.put("unick",re.getString("unick"));
            json.put("img",re.getString("image"));
            jsonObject.put("msg"+i,json);
            i++;
        }
        return jsonObject;
    }
}
