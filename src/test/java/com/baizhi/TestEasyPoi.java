package com.baizhi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.baizhi.entity.Photo;
import com.baizhi.entity.Student;
import com.baizhi.entity.Teacher;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestEasyPoi {

    @Test
    public void exportEasyPoi() {

        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("1", "小可爱", 18, new Date()));
        students.add(new Student("2", "小黑", 12, new Date()));
        students.add(new Student("3", "小贱人", 28, new Date()));
        students.add(new Student("4", "小狗蛋", 30, new Date()));
        students.add(new Student("5", "小网吧", 68, new Date()));

        System.out.println("====执行一个查询所有方法返回一个集合==");

        try {

            //配置相关参数  参数：title:文档的标题,sheetName:工作簿的名字,要导出的实体类对象,要导出的集合
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("计算机一班学生", "学生信息表"), Student.class, students);

            //导出Excel
            workbook.write(new FileOutputStream(new File("D://EasyPois.xls")));

            //释放资源
            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void exportEasyPois() {

        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("1", "小可爱", 18, new Date()));
        students.add(new Student("2", "小黑", 12, new Date()));
        students.add(new Student("3", "小贱人", 28, new Date()));
        students.add(new Student("4", "小狗蛋", 30, new Date()));
        students.add(new Student("5", "小网吧", 68, new Date()));

        Teacher teacher1 = new Teacher("11", "孙帅", students);
        Teacher teacher2 = new Teacher("12", "胡鑫喆", students);

        ArrayList<Teacher> teachers = new ArrayList<>();

        teachers.add(teacher1);
        teachers.add(teacher2);

        System.out.println("====执行一个查询所有方法返回一个集合==");

        try {

            //配置相关参数  参数：title:文档的标题,sheetName:工作簿的名字,要导出的实体类对象,要导出的集合
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("计算机一班学生", "学生信息表"), Teacher.class, teachers);

            //导出Excel
            workbook.write(new FileOutputStream(new File("D://EasyPoi.xls")));

            //释放资源
            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void inportEasyPoi() {

        //配置导入的参数
        ImportParams params = new ImportParams();

        params.setTitleRows(1);  //标题所占行数
        params.setHeadRows(2);  //表头所占行数

        try {
            //数据导入
            List<Teacher> teachers = ExcelImportUtil.importExcel(new FileInputStream(new File("D://EasyPoi.xls")), Teacher.class, params);

            for (Teacher teacher : teachers) {
                System.out.println(teacher);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void inportEasyPois() {

        //配置导入的参数
        ImportParams params = new ImportParams();

        params.setTitleRows(1);  //标题所占行数
        params.setHeadRows(1);  //表头所占行数

        try {
            //数据导入
            List<Student> stus = ExcelImportUtil.importExcel(new FileInputStream(new File("D://EasyPois.xls")), Student.class, params);

            for (Student s : stus) {
                System.out.println(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void exportEasyPoiss() {

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
