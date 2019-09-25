package com.baizhi;

import com.baizhi.entity.Student;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestPoi {

    @Test
    public void testExportPoi() {

        //创建一个Excel文档
        Workbook workbook = new HSSFWorkbook();

        //创建一个工作簿  sheet  参数:工作簿的名字(sheet1,sheet2,...)
        //如果不指定工作簿的名字默认按照sheet0,sheet1来命名
        Sheet sheet = workbook.createSheet("工作簿1");

        //创建一行    参数：行下标（下标从0开始）
        Row row = sheet.createRow(0);

        //创建一个单元格   参数:单元格下标（下标从0开始）
        Cell cell = row.createCell(0);

        //给单元格设置内容
        cell.setCellValue("这是第一行第一个单元格");

        try {
            //导出Excel
            workbook.write(new FileOutputStream(new File("D://TestPoi.xls")));

            //释放资源
            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExportPois() {

        Student stu1 = new Student("1", "小可爱", 18, new Date());
        Student stu2 = new Student("2", "小黑", 12, new Date());
        Student stu3 = new Student("3", "小贱人", 28, new Date());
        Student stu4 = new Student("4", "小狗蛋", 30, new Date());
        Student stu5 = new Student("5", "小网吧", 68, new Date());

        System.out.println("====执行一个查询所有方法返回一个集合==");
        List<Student> students = Arrays.asList(stu1, stu2, stu3, stu4, stu5);

        //创建一个Excel文档
        Workbook workbook = new HSSFWorkbook();

        //创建一个工作簿  sheet  参数:工作簿的名字(sheet1,sheet2,...)
        //如果不指定工作簿的名字默认按照sheet0,sheet1来命名
        Sheet sheet = workbook.createSheet("工作簿1");

        //合并单元格   起始行firstRow, 结束行lastRow, 起始单元格firstCol, 结束单元格lastCol
        CellRangeAddress addresses = new CellRangeAddress(0, 0, 0, 3);

        //设置合并
        sheet.addMergedRegion(addresses);

        //设置列宽  参数：列下标,列宽度
        sheet.setColumnWidth(3, 256 * 15);

        //创建日期格式对象
        DataFormat dataFormat = workbook.createDataFormat();
        //设置日期格式
        short format = dataFormat.getFormat("yyyy-MM-dd");
        //获取样式对象
        CellStyle cellStyle = workbook.createCellStyle();
        //将日期格式放入样式对象
        cellStyle.setDataFormat(format);

        //创建字体样式对象
        Font font = workbook.createFont();
        font.setBold(true); //字体加粗
        font.setColor(Font.COLOR_RED); //设置字体颜色
        font.setFontName("微软雅黑");  //设置字体
        font.setFontHeightInPoints((short) 20); //设置字号
        font.setItalic(true);    //斜体
        font.setUnderline(FontFormatting.U_SINGLE);  //下划线

        //创建样式对象
        CellStyle cellStyle1 = workbook.createCellStyle();
        //将字体样式放入样式对象
        cellStyle1.setFont(font);
        //设置居中
        cellStyle1.setAlignment(HorizontalAlignment.CENTER);  //文字居中

        //创建标题行
        Row rows = sheet.createRow(0);

        //创建标题行并设置内容
        Cell cell2 = rows.createCell(0);
        //设置样式
        cell2.setCellStyle(cellStyle1);
        cell2.setCellValue("学生信息表");

        //创建一行    参数：行下标（下标从0开始）
        Row row = sheet.createRow(1);

        //设置行高
        row.setHeight((short) (20 * 50));

        //创建一个目录行
        String[] titles = {"ID", "姓名", "年龄", "生日"};

        //遍历目录行
        for (int i = 0; i < titles.length; i++) {

            //创建一个单元格   参数:单元格下标（下标从0开始）
            Cell cell = row.createCell(i);
            //给单元格设置样式  字体样式
            cell.setCellStyle(cellStyle1);
            //给单元格设置内容
            cell.setCellValue(titles[i]);
        }

        //处理数据行
        for (int i = 0; i < students.size(); i++) {

            //创建数据行
            Row row1 = sheet.createRow(i + 2);

            //处理第一个单元格的数据
            Cell cell = row1.createCell(0);
            Student student = students.get(i);
            String id = student.getId();
            cell.setCellValue(id);
            //处理第二个单元格的数据
            row1.createCell(1).setCellValue(students.get(i).getName());
            row1.createCell(2).setCellValue(students.get(i).getAge());

            Cell cell1 = row1.createCell(3);
            //给单元格设置样式   日期格式
            cell1.setCellStyle(cellStyle);
            cell1.setCellValue(students.get(i).getBir());

        }

        try {
            //导出Excel
            workbook.write(new FileOutputStream(new File("D://TestPoi.xls")));

            //释放资源
            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testinportPoi() {

        try {
            //读取本地Excel文档
            Workbook workbook = new HSSFWorkbook(new FileInputStream(new File("D://TestPoi.xls")));

            //获取工作簿
            Sheet sheet = workbook.getSheet("工作簿1");

            System.out.println(sheet.getLastRowNum());

            for (int i = 2; i <= sheet.getLastRowNum(); i++) {


                Student student = new Student();
                //获取行
                Row row = sheet.getRow(i);

                //获取单元格
                Cell cell = row.getCell(0);
                //获取 String 类型的参数
                String id = cell.getStringCellValue();
                //将数据放入对象
                student.setId(id);
                //获取第一行第二个单元格中的内容并且放入对象中
                student.setName(row.getCell(1).getStringCellValue());

                //获取数字类型的数据
                double age = row.getCell(2).getNumericCellValue();
                student.setAge((int) age);
                //获取日期
                student.setBir(row.getCell(3).getDateCellValue());

                //将数据插入到数据库
                System.out.println("===student " + student);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testinportPois() {

        try {
            //读取本地Excel文档
            Workbook workbook = new HSSFWorkbook(new FileInputStream(new File("D://TestPoi.xls")));

            //获取工作簿
            Sheet sheet = workbook.getSheet("工作簿1");

            System.out.println(sheet.getLastRowNum());

            for (int i = 2; i <= sheet.getLastRowNum(); i++) {

                //获取行
                Row row = sheet.getRow(i);
                //获取单元格
                Cell cell = row.getCell(0);

                //获取 String 类型的参数
                String id = cell.getStringCellValue();
                String name = row.getCell(1).getStringCellValue();
                int age = (int) row.getCell(2).getNumericCellValue();
                Date bir = row.getCell(3).getDateCellValue();

                Student student = new Student(id, name, age, bir);
                //将数据插入到数据库
                System.out.println("===student " + student);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
