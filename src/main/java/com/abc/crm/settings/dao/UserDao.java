package com.abc.crm.settings.dao;

import com.abc.crm.settings.domain.User;

import java.util.List;
import java.util.Map;

public interface UserDao {

    User login(Map<String, Object> map);

    List<User> getUserAll();
}
