package com.abc.crm.settings.handler;

import com.abc.crm.settings.exception.loginException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

//登入全局异常处理类
@ControllerAdvice
public class LoginHandler {


    @ResponseBody
    @ExceptionHandler(value = {loginException.class})
    public Map<String, Object> loginExcption(Exception e) {

        Map<String, Object> map = new HashMap<>();

        map.put("success", false);
        map.put("msg", e.getMessage());

        return map;
    }

}
