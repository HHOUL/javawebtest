package com.abc.crm.settings.service;

import com.abc.crm.settings.domain.User;
import com.abc.crm.settings.exception.loginException;

import java.util.List;

public interface UserService {
    User login(String loginName, String loginPwd, String ip) throws loginException;

    List<User> getUserAll();
}
