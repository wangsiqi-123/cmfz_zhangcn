package com.baizhi.controller;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.baizhi.util.PhoneMsgUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("queryByPage")
    public HashMap<String, Object> queryByPage(Integer page, Integer rows) {

        HashMap<String, Object> map = userService.queryByPage(page, rows);

        return map;
    }

    @RequestMapping("updateStatus")
    public HashMap<String, Object> updateStatus(User user) {
        HashMap<String, Object> map = userService.updateStatus(user);
        return map;
    }

    @RequestMapping("edit")
    public String edit(User user, String oper) {

        String id = null;

        //执行添加操作
        if (oper.equals("add")) {

            System.out.println("===Controller接收的===: " + user);

            id = userService.add(user);
        }

        //执行修改操作
        if (oper.equals("edit")) {
        }

        //执行删除操作
        if (oper.equals("del")) {
        }

        return id;
    }

    @RequestMapping("export")
    public void export() {

        userService.export();
    }


    @RequestMapping("getPhone")
    public void getPhone(String phone) {

        String random = PhoneMsgUtil.getRandom(6);

        System.out.println("存入session " + random);

        String message = PhoneMsgUtil.getPhones(phone, random);
        System.out.println(message);

    }

}
