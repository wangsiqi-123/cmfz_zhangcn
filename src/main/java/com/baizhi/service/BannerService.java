package com.baizhi.service;

import com.baizhi.entity.Banner;

import java.util.HashMap;

public interface BannerService {

    HashMap<String, Object> queryByPage(Integer page, Integer rows);

    String add(Banner banner);

    HashMap<String, Object> updateStatus(Banner banner);
}
