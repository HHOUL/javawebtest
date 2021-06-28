package com.abc.crm.settings.web.filter;


import com.abc.crm.settings.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//登入过滤器
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("验证是否登入过");

        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;

        //获取请求的路径
        String path = servletRequest.getServletPath();

        System.out.println(path);

        //登入页面 登入资源放行
        if ("/login.jsp".equals(path) || "/user/login.do".equals(path) || "/index.html".equals(path)) {

            chain.doFilter(request, response);
        } else {

            //有session返回 没有返回null
            HttpSession session = servletRequest.getSession(false);

            User user = (User) session.getAttribute("user");

            if (user != null) {
                //已登入
                chain.doFilter(request, response);
            } else {

                servletResponse.sendRedirect(servletRequest.getContextPath() + "/login.jsp");
            }
        }
    }
}
