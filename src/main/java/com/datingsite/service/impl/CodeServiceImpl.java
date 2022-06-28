package com.datingsite.service.impl;

import com.datingsite.dao.CodeDao;
import com.datingsite.dao.impl.CodeDaoImpl;
import com.datingsite.service.CodeService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CodeServiceImpl implements CodeService {

    @Override
    public String saveCodeService(String email) {
        //首先生成4位的数字验证码
        String code = String.valueOf((int)((Math.random()*9+1)*1000));
        //将验证码信息写入到数据库中
        CodeDao codeDao = new CodeDaoImpl();
        boolean flag = codeDao.saveCode(email,code);
        if(flag) {return code;}
        return null;
    }

    //检验验证码
    @Override
    public boolean checkCode(String email, String code) throws SQLException {
        CodeDao codeDao = new CodeDaoImpl();
        String code1 = codeDao.queryCode(email);
        //如果验证码相同
        if(code.equals(code1)){
            return true;
        }
        return false;
    }


}
