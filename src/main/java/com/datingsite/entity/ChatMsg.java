package com.datingsite.entity;

public class ChatMsg {
    private boolean isSystemMsg;
    private String fromuid;
    private String message;
    private String touid;

    public ChatMsg(){}
    public ChatMsg(String fromuid,String message,String touid){
        this.fromuid = fromuid;
        this.message = message;
        this.touid = touid;
    }

    public boolean isSystemMsg() {
        return isSystemMsg;
    }

    public void setSystemMsg(boolean systemMsg) {
        isSystemMsg = systemMsg;
    }

    public String getFromuid() {
        return fromuid;
    }

    public void setFromuid(String fromuid) {
        this.fromuid = fromuid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTouid() {
        return touid;
    }

    public void setTouid(String touid) {
        this.touid = touid;
    }

    @Override
    public String toString() {
        return "ChatMsg{" +
                "isSystemMsg=" + isSystemMsg +
                ", fromuid='" + fromuid + '\'' +
                ", message='" + message + '\'' +
                ", touid='" + touid + '\'' +
                '}';
    }
}
