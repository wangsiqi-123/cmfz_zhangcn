package com.baizhi.service;

import com.baizhi.entity.User;

import java.util.HashMap;

public interface UserService {

    HashMap<String, Object> queryByPage(Integer page, Integer rows);

    String add(User user);

    HashMap<String, Object> updateStatus(User user);

    void export();
}
