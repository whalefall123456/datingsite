package com.datingsite.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//@WebFilter(urlPatterns = {"/user","/post","/friend","/guest","/message"})
@WebFilter(urlPatterns ="/*")
public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //1.设置编码格式（请求和响应）
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        servletRequest.setCharacterEncoding("utf-8");
        String contentType = request.getHeader("Accept");
        if (contentType.contains("application/signed-exchange;v=b3")){
            servletResponse.setContentType("text/html;charset=utf-8");
        }else if(contentType.contains("application/json")) {
            servletResponse.setContentType("application/json;charset=utf-8");
        }else if(contentType == null){
            servletResponse.setContentType("text/html;charset=utf-8");
        }else{
            servletResponse.setContentType(contentType+";charset=utf-8");
        }
        //2.放行
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}

