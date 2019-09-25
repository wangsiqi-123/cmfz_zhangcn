package com.baizhi.service;

import java.util.HashMap;

public interface AlbumService {

    HashMap<String, Object> queryByPage(Integer page, Integer rows);
}
