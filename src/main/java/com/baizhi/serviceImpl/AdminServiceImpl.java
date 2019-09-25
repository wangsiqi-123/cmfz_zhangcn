package com.baizhi.serviceImpl;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Override
    public HashMap<String, Object> login(String enCode, String username, String password, HttpServletRequest request) {

        //获取验证码
        String imageCode = (String) request.getSession().getAttribute("imageCode");

        HashMap<String, Object> map = new HashMap<>();

        //判断验证码
        if (imageCode.equals(enCode)) {

            if (username.equals("admin")) {

                if (password.equals("111111")) {

                    Admin admin = new Admin("1", "admin", "111111");
                    request.getSession().setAttribute("admin", admin);

                    map.put("success", "200");
                    map.put("message", "登陆成功");
                } else {
                    map.put("success", "400");
                    map.put("message", "密码错误");
                }
            } else {
                map.put("success", "400");
                map.put("message", "用户不存在");
            }
        } else {
            map.put("success", "400");
            map.put("message", "验证码错误");
        }
        return map;
    }
}
