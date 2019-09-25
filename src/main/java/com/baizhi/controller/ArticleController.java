package com.baizhi.controller;

import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@RequestMapping("article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @RequestMapping("queryByPage")
    public HashMap<String, Object> queryByPage(Integer page, Integer rows) {

        HashMap<String, Object> map = articleService.queryByPage(page, rows);
        return map;
    }

    @RequestMapping("addArticle")
    public HashMap<String, Object> addArticle(Article article) {

        System.out.println("=调用业务方法添加数据=article=" + article);

        HashMap<String, Object> map = articleService.add(article);

        return map;
    }

    @RequestMapping("updateArticle")
    public HashMap<String, Object> updateArticle(Article article) {

        System.out.println("=调用业务方法修改数据=article=" + article);

        HashMap<String, Object> map = articleService.update(article);

        return map;
    }
}
