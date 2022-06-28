package com.datingsite.dao;

import java.sql.ResultSet;

public interface CodeDao {
    boolean saveCode(String email,String code);
    String queryCode(String email);
}
