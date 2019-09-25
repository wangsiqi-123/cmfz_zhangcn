package com.baizhi.serviceImpl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.dao.UserDao;
import com.baizhi.entity.Photo;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.baizhi.util.UUIDUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public HashMap<String, Object> queryByPage(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();

        //records 总条数
        Integer records = userDao.queryRecords();
        map.put("records", records);

        //total 总页数
        Integer total = records % rows == 0 ? records / rows : records / rows + 1;
        map.put("total", total);

        //page 当前页
        map.put("page", page);

        //rows  每页展示条数
        List<User> users = userDao.queryByPage((page - 1) * rows, rows);
        map.put("rows", users);

        return map;
    }

    @Override
    public String add(User user) {
        String uuid = UUIDUtil.getUUID();
        user.setId(uuid);
        user.setStatus("1");
        user.setGuru_id("1");
        user.setCrea_date(new Date());
        System.out.println("---service banner数据入库==：" + user);
        userDao.save(user);
        return uuid;
    }

    @Override
    public HashMap<String, Object> updateStatus(User user) {
        HashMap<String, Object> map = new HashMap<>();

        try {
            System.out.println("==user==" + user);
            userDao.update(user);
            map.put("success", "200");
            map.put("message", "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", "400");
            map.put("message", "修改失败");
        }

        return map;
    }

    @Override
    public void export() {

        ArrayList<Photo> photos = new ArrayList<>();
        photos.add(new Photo("1", "src/main/webapp/upload/photo/1568278390399-3.jpg", 18, new Date()));
        photos.add(new Photo("2", "D:/后期项目/code/cmfz_zhangcn/src/main/webapp/upload/photo/1568278390399-3.jpg", 12, new Date()));
        photos.add(new Photo("3", "D:/后期项目/code/cmfz_zhangcn/src/main/webapp/upload/photo/1568278390399-3.jpg", 28, new Date()));
        photos.add(new Photo("4", "D:/后期项目/code/cmfz_zhangcn/src/main/webapp/upload/photo/1568278390399-3.jpg", 30, new Date()));
        photos.add(new Photo("5", "D:/后期项目/code/cmfz_zhangcn/src/main/webapp/upload/photo/1568278390399-3.jpg", 68, new Date()));

        System.out.println("====执行一个查询所有方法返回一个集合==");

        try {

            //配置相关参数  参数：title:文档的标题,sheetName:工作簿的名字,要导出的实体类对象,要导出的集合
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("计算机一班学生", "学生信息表"), Photo.class, photos);

            //导出Excel
            workbook.write(new FileOutputStream(new File("D://EasyPoisPhoto.xls")));

            //释放资源
            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
