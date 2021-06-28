package com.abc.crm.settings.service.impl;

import com.abc.crm.settings.dao.UserDao;
import com.abc.crm.settings.domain.User;
import com.abc.crm.settings.exception.loginException;
import com.abc.crm.settings.service.UserService;
import com.abc.crm.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User login(String loginName, String loginPwd, String ip) throws loginException {

        Map<String, Object> map = new HashMap<>();

        map.put("loginName", loginName);

        map.put("loginPwd", loginPwd);

        User user = userDao.login(map);

        if (user == null) {
            throw new loginException("账户或密码输入错误!!");
        }

        //程序执行到这 账号密码正确 继续验证

        //验证失效时间
        int expireTime = Integer.valueOf(user.getExpireTime().replace("-", ""));

        //获取当前时间
        int currentTime = Integer.valueOf(DateTimeUtil.getSysTime());

        //如果指定的数大于参数返回 1
        if (currentTime > expireTime) {

            throw new loginException("账号已失效!!");
        }

        //判断账户状态
        if ("0".equals(user.getLockState())) {

            throw new loginException("账户已锁定！！");
        }

        //判断ip地址
        String uip = user.getAllowIps();

        if (!uip.contains(ip)) {

            throw new loginException("ip地址受限!!");
        }

        return user;

    }

    @Override
    public List<User> getUserAll() {
        return userDao.getUserAll();
    }
}
