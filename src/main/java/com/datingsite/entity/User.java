package com.datingsite.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class User {
    private int uid;
    private String uname;
    private String unick;
    private String phone;
    private String email;
    private String password;
    private String sex;
    private Date birthday;
    private String school;
    private String introduce;
    private String role;
    private String image;
    private String code;

    public User(){}

    public User(ResultSet re) throws SQLException {
        this.uid = Integer.parseInt(re.getString("uid"));
        this.uname = re.getString("uname");
        this.unick = re.getString("unick");
        this.phone = re.getString("phone");
        this.email = re.getString("email");
        this.password = re.getString("password");
        this.sex = re.getString("sex");
        this.birthday = re.getDate("birthday");
        this.school = re.getString("school");
        this.introduce = re.getString("introduce");
        this.role = re.getString("role");
        this.image = re.getString("image");
        this.code = re.getString("code");
    }

    //非本人获取到的信息
    public User(ResultSet re,String type) throws SQLException {
        if(type.equals("visitor")){
            this.uid = Integer.parseInt(re.getString("uid"));
            this.unick = re.getString("unick");
            this.introduce = re.getString("introduce");
            this.image = re.getString("image");
        }else if(type.equals("admin")){
            this.uid = Integer.parseInt(re.getString("uid"));
            this.unick = re.getString("unick");
            this.uname = re.getString("uname");
            this.image = re.getString("image");
        }

    }


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUnick() {
        return unick;
    }

    public void setUnick(String unick) {
        this.unick = unick;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", unick='" + unick + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", school='" + school + '\'' +
                ", introduce='" + introduce + '\'' +
                ", role='" + role + '\'' +
                ", image='" + image + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
