package com.baizhi.service;

import com.baizhi.entity.Article;

import java.util.HashMap;

public interface ArticleService {

    HashMap<String, Object> queryByPage(Integer page, Integer rows);

    HashMap<String, Object> add(Article article);

    HashMap<String, Object> update(Article article);
}
