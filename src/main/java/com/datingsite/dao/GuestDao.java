package com.datingsite.dao;

import com.datingsite.entity.Guest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface GuestDao {
    boolean saveGuest(String uid,String tid,String text) throws SQLException;
    List<Guest> queryGuestByUid(String uid) throws SQLException;
}
