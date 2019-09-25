package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("banner")
public class BannerController {

    @Resource
    private BannerService bannerService;

    @RequestMapping("queryByPage")
    public HashMap<String, Object> queryByPage(Integer page, Integer rows) {

        HashMap<String, Object> map = bannerService.queryByPage(page, rows);

        return map;
    }

    @RequestMapping("updateStatus")
    public HashMap<String, Object> updateStatus(Banner banner) {
        HashMap<String, Object> map = bannerService.updateStatus(banner);
        return map;
    }

    @RequestMapping("edit")
    public String edit(Banner banner, String oper) {

        String id = null;

        //执行添加操作
        if (oper.equals("add")) {

            System.out.println("===Controller接收的===: " + banner);

            id = bannerService.add(banner);
        }

        //执行修改操作
        if (oper.equals("edit")) {

        }

        //执行删除操作
        if (oper.equals("del")) {

        }

        return id;
    }

    @RequestMapping("bannerUpload")
    public void bannerUpload(MultipartFile img_path, String id, HttpServletRequest request) {

        //1.根据相对路径获取绝对路径
        String realPath = request.getSession().getServletContext().getRealPath("/upload/photo");

        //获取文件夹
        File file = new File(realPath);
        //判断文件夹是否存在
        if (!file.exists()) {
            file.mkdirs(); //创建文件夹
        }

        //获取文件名
        String filename = img_path.getOriginalFilename();

        //给文件加一个时间戳
        String name = new Date().getTime() + "-" + filename;

        //文件上传
        try {
            img_path.transferTo(new File(realPath, name));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //执行修改  修改文件的路径
        Banner banner = new Banner();
        banner.setId(id);
        banner.setImg_path(name);

        System.out.println("=====执行修改操作：" + banner);
        bannerService.updateStatus(banner);

    }
}
