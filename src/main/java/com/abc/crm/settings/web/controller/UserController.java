package com.abc.crm.settings.web.controller;

import com.abc.crm.settings.domain.User;
import com.abc.crm.settings.exception.loginException;
import com.abc.crm.settings.service.UserService;
import com.abc.crm.utils.MD5Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller()
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;


    @RequestMapping("/login.do")
    @ResponseBody
    public Map<String, Object> loginMsg(String loginName, String loginPwd, HttpServletRequest req) throws loginException {

        System.out.println("进入到登入验证");

        Map<String, Object> map = new HashMap<>();

        //转换为MD5密文
        loginPwd = MD5Util.getMD5(loginPwd);
        System.out.println("密码==" + loginPwd);
        //接受浏览器端的ip地址
        String ip = req.getRemoteAddr();
        System.out.println("ip===" + ip);
//        try {
        User user = userService.login(loginName, loginPwd, ip);
        //获取session 将user存入session中 用于过滤器 判断是否登入过
        req.getSession().setAttribute("user", user);
        map.put("success", true);
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//            String msg = e.getMessage();
//
//            map.put("success", false);
//            map.put("msg", msg);
//        }
        return map;
    }

}
