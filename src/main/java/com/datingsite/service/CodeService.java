package com.datingsite.service;

import java.sql.SQLException;

public interface CodeService {
    String saveCodeService(String email);
    boolean checkCode(String email,String code) throws SQLException;
}
